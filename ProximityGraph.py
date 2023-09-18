import re
import os

import matplotlib.pyplot as plt
import networkx as nx
import numpy as np
from thefuzz import fuzz

from tokenCalculator import Tokens


class Proximity:
    # Create dictionary of category colors
    category_colors = {"Signifiers": "#648FFF", "Demonstration": "#785EF0", "Memetic proxy": "#DC267F", "Constraints": "#FE6100", "Meta prompt": "#FFB000"}
    #, "Validation": "#beafc2"

    # Handles loading data from saved files
    savefile = ""
    reset = True
    savepath = "temps/pg/"
    # reset should be set to True in order to generate new files otherwise it will use existing saves if they exist.
    def __init__(self, savefile, reset):
        self.savefile = savefile
        if type(reset) == bool:
            self.reset = reset
        # Create temp folders if not exist
        if not os.path.exists("temps"):
            os.makedirs("temps")
            print("Created temps dir")
        if not os.path.exists(self.savepath):
            os.makedirs(self.savepath)
            print("Created "+self.savepath+"dir")

    def get_category(self, filename):
        category = filename.split('/')[0]
        # Changes the category names to match the spelling in the thesis paper
        if category == "Signifier":
            category = "Signifiers"
            return category
        elif category == "Memetic":
            category = "Memetic proxy"
            return category
        elif category == "Meta":
            category = "Meta prompt"
            return category
        else:
            return category

    def generate_proximity_graph_data(self, file_names, filepath):
        tokenCalc = Tokens()
        # Converts the code files into tokens, also stores the nodes category type which determiens the color
        nodes = []
        tokens = []
        for i, ref_file in enumerate(file_names):
            category = self.get_category(ref_file)
            nodes.append((ref_file, {'category': category}))
            text = tokenCalc.read_file(filepath+ref_file)
            tokens.append(tokenCalc.remove_language_keywords(text, ref_file.split('.')[-1]))
        
        # Create graph
        G = nx.Graph()
        G.add_nodes_from(nodes)
        scores = np.zeros((len(file_names), len(file_names)))
        # Calculates the code match between each solution listed in file_names
        threshold = 0.75
        for i, ref_file in enumerate(file_names):
            for j, cand_file in enumerate(file_names):
                if i != j and i < j:
                    score = fuzz.token_sort_ratio(tokens[i], tokens[j]) / 100
                    scores[i,j] = score
                    scores[j,i] = score
                    if score >= threshold:
                        G.add_edge(ref_file, cand_file, weight=score, color='black')
                    elif score >= (threshold*0.9):
                        G.add_edge(ref_file, cand_file, weight=score*0.25, color='lightgray')
                    else:
                        G.add_edge(ref_file, cand_file, weight=score*0.05, color='white')
        self.save_data_pg(self.savefile, scores)
        return G, nodes
    
    # Used when the data is loaded from the saved file instead of the generate function.
    def build_proximity_graph(self, file_names, scores):
        nodes = []
        
        for i, ref_file in enumerate(file_names):
            category = self.get_category(ref_file)
            nodes.append((ref_file, {'category': category}))
        # Create graph
        G = nx.Graph()
        G.add_nodes_from(nodes)
        # Calculates the code match between each solution listed in file_names
        threshold = 0.75
        for i, ref_file in enumerate(file_names):
            for j, cand_file in enumerate(file_names):
                if i != j and i < j:
                    score = scores[i,j]
                    if score >= threshold:
                        G.add_edge(ref_file, cand_file, weight=score, color='black')
                    elif score >= (threshold*0.9):
                        G.add_edge(ref_file, cand_file, weight=score*0.25, color='lightgray')
                    else:
                        G.add_edge(ref_file, cand_file, weight=score*0.05, color='white')
        return G, nodes
    
    def save_data_pg(self, filename, scores):
        try:
            if filename == "":
                raise Exception("Savefile destination required!")

            if not re.findall(".npy$", filename):
                filename = filename + ".npy"

            with open(self.savepath+filename, 'wb') as f:
                np.save(f, scores)
            
            return
        except Exception as e:
            print(e)

    
    def load_data_pg(self, filename):
        try:
            if not re.findall(".npy$", filename):
                filename = filename + ".npy"

            with open(self.savepath+filename, 'rb') as f:
                scores = np.load(f)
            return scores
        except Exception as e:
            print(e)

    def reset_data(self, dirpath):
        try:
            files = os.listdir(dirpath)
            for file in files:
                file_path = os.path.join(dirpath, file)
                if os.path.isfile(file_path):
                    os.remove(file_path)
        except Exception as e: 
            print(e)
        return
    
    def start(self, filename, filepath):
        # Reads the txt which contains the names of all the selected code files to compare
        list_file = filepath+filename
        with open(list_file, 'r', encoding='utf-8') as f:
            file_block = f.read()

        file_names = file_block.splitlines()
        # Call function to create the graph
        if os.path.isfile(self.savepath+self.savefile+".npy"):
            scores = self.load_data_pg(self.savefile)
            G, nodes = self.build_proximity_graph(file_names, scores)
            print("loaded data")
        else:
            G, nodes = self.generate_proximity_graph_data(file_names, filepath)
            print("generated data")

        # Draw graph, seeds are used to generate a predetermined figure
        if filename == "Python TicTacToe.txt":
            pos = nx.spring_layout(G, k=0.2, iterations=50, seed=5)
        elif filename == "Python Blackjack.txt":
            pos = nx.spring_layout(G, k=0.2, seed=7)
        elif filename == "Java Blackjack.txt":
            pos = nx.spring_layout(G, k=0.2, seed=2)
        else:
            pos = nx.spring_layout(G, k=0.2, seed=1)

        #Colors the nodes and edges based on the task specification method used and the score threshold achieved
        node_colors = [self.category_colors[data['category']] for _, data in G.nodes(data=True)]
        edge_colors = [color for _, _, color in G.edges(data='color')]

        weights = [weight for _, _, weight in G.edges(data='weight')]
        fig, ax = plt.subplots(figsize=(10, 10))
        nx.draw_networkx_nodes(G, pos, node_color=node_colors, node_size=600)
        nx.draw_networkx_edges(G, pos, edge_color=edge_colors, width=weights)
        
        # Adds labels to the nodes. The code takes the node name and removes filepath so that only the serial number is shown like A01
        labels = {}
        for node, degree in nodes:
            node_parts = node.split("/")
            node_name = node_parts[2].split(".")
            node_num = node_name[0].split("_")
            node_short = (node_num[1])
            labels[node] = node_short
        nx.draw_networkx_labels(G, pos, labels, font_size=12, font_color='white', font_weight='normal')

        ax = plt.gca()
        ax.legend(handles=[plt.Circle([], [], color=color, label=category) for category, color in self.category_colors.items()], loc='upper left', fontsize=12)
        plt.axis('off')
        #Corrects the filename so that it matches the thesis paper
        tsk = filename.split(".")
        if tsk[0] == "Java TicTacToe":
            tsk = "Java Tic-Tac-Toe"
        elif tsk[0] == "Python TicTacToe":
            tsk = "Python Tic-Tac-Toe"
        else:
            tsk = tsk[0]
        plt.title(tsk+" proximity graph", fontsize=24)
        plt.subplots_adjust(bottom=0, right=1, left=0, hspace=0, wspace=0)
        #plt.savefig(tsk+'.png')
        plt.show()

if __name__ == '__main__':
    proximity = Proximity("score_pg", False)
    # txt file name lists which files should be read
    #filename = "Java TicTacToe.txt"
    #filename = "Java Blackjack.txt"
    #filename = "Python TicTacToe.txt"
    filename = "Python Blackjack.txt"
    filepath = "data/"
    proximity.start(filename, filepath)