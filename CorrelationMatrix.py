import re
import os
from collections import Counter

import matplotlib.pyplot as plt
import numpy as np
from tempfile import TemporaryFile
from thefuzz import fuzz

from tokenCalculator import Tokens


class Correlation:
    savefile = ""
    reset = True
    savepath = "temps/cm/"
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

    def get_category(filename):
        return filename.split('/')[0]

    def generate_correlation_matrix_data(self, file_names, filepath):
        # Converts the code files into tokens
        tokenCalc = Tokens()
        tokens = []
        for i, ref_file in enumerate(file_names):
            text = tokenCalc.read_file(filepath+ref_file)
            tokens.append(tokenCalc.remove_language_keywords(text, ref_file.split('.')[-1]))

        # Calculates the code match between each solution listed in file_names
        scores = np.zeros((len(file_names), len(file_names)))
        for i, ref_file in enumerate(file_names):
            for j, cand_file in enumerate(file_names):
                if i != j and i < j:
                    score = fuzz.token_sort_ratio(tokens[i], tokens[j]) / 100
                    scores[i,j] = score
                    scores[j,i] = score
        self.save_data_cm(self.savefile, scores)
        return scores
    
    def save_data_cm(self, filename, scores):
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

    
    def load_data_cm(self, filename):
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
        if self.reset:
            self.reset_data(self.savepath)

        # Reads the txt which contains the names of all the selected code files to compare
        list_file = filepath+filename
        with open(list_file, 'r', encoding='utf-8') as f:
            file_block = f.read()

        file_names = file_block.splitlines()
        # Call function to create the graph
        if os.path.isfile(self.savepath+self.savefile+".npy"):
            scores = self.load_data_cm(self.savefile)
            print("loaded data")
        else:
            scores = self.generate_correlation_matrix_data(file_names, filepath)
            print("generated data")


        fig, ax = plt.subplots(figsize=(12,12))
        heatmap = ax.imshow(scores, cmap='YlGnBu', vmin=0.5, vmax=1.0)
        #Create ticks on every 10th entry in file_names, reducing standard 150 to 15 ticks
        ax.set_xticks(np.arange(0, len(file_names), 10))
        ax.set_yticks(np.arange(0, len(file_names), 10))
        short_names = []
        for x in file_names:
            parts = x.split("/")
            name = parts[2].split(".")
            short_names.append(name[0])

        x_tick_labels = short_names[::10]
        y_tick_labels = short_names[::10]
        ax.set_xticklabels(x_tick_labels, fontsize=16, rotation=45, ha="right", rotation_mode="anchor")
        ax.set_yticklabels(y_tick_labels, fontsize=16)


        fig.tight_layout()
        cbar = ax.figure.colorbar(heatmap, ax=ax)
        cbar.ax.tick_params(labelsize=16)
        cbar.set_label('Similarity', fontsize=16)
        #Corrects the filename so that it matches the thesis paper
        tsk = filename.split(".")
        if tsk[0] == "Java TicTacToe":
            tsk = "Java Tic-Tac-Toe"
        elif tsk[0] == "Python TicTacToe":
            tsk = "Python Tic-Tac-Toe"
        else:
            tsk = tsk[0]
        plt.title(tsk+" code similarity heat map", fontsize=24)
        #plt.subplots_adjust(bottom=0.05)
        #plt.savefig(tsk+'.png')
        plt.show()

if __name__ == '__main__':
    correlation = Correlation("score_cm", True)
    # txt file name lists which files should be read
    #filename = "Java TicTacToe.txt"
    filename = "Java Blackjack.txt"
    #filename = "Python TicTacToe.txt"
    #filename = "Python Blackjack.txt"
    filepath = "data/"
    correlation.start(filename, filepath)