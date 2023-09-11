import re

import matplotlib.pyplot as plt
import networkx as nx
import numpy as np
from thefuzz import fuzz


def read_file(filename):
    with open(filename, 'r', encoding='utf-8') as f:
        text = f.read().lower()
        words = re.findall('\w+', text)
    return words

def remove_language_keywords(words, language):
    # Remove language-specific keywords
    if language == "py":
        keywords = set(['and', 'as', 'assert', 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except', 'False', 'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is', 'lambda', 'None', 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'True', 'try', 'while', 'with', 'yield'])
    elif language == "java":
        keywords = set(['abstract', 'assert', 'boolean', 'break', 'byte', 'case', 'catch', 'char', 'class', 'const', 'continue', 'default', 'do', 'double', 'else', 'enum', 'extends', 'false', 'final', 'finally', 'float', 'for', 'goto', 'if', 'implements', 'import', 'instanceof', 'int', 'interface', 'long', 'native', 'new', 'null', 'package', 'private', 'protected', 'public', 'return', 'short', 'static', 'strictfp', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws', 'transient', 'true', 'try', 'void', 'volatile', 'while', 'string', 'main', 'args', ''])
    else:
        return words
    filtered_words = [word for word in words if word not in keywords]
    return filtered_words

def calculate_similarity(reference_file, candidate_file):
    reference_text = read_file(reference_file)
    candidate_text = read_file(candidate_file)

    reference_tokens = remove_language_keywords(reference_text, reference_file.split('.')[-1])
    candidate_tokens = remove_language_keywords(candidate_text, candidate_file.split('.')[-1])

    fuz = fuzz.token_sort_ratio(reference_tokens, candidate_tokens) / 100
    return fuz


def get_category(filename):
    category = filename.split('/')[0]
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

if __name__ == '__main__':
    task = "Java TicTacToe.txt"
    #task = "Java Blackjack.txt"
    #task = "Python TicTacToe.txt"
    #task = "Python Blackjack.txt"
    filepath = "data/"
    list_file = filepath+task
    with open(list_file, 'r', encoding='utf-8') as f:
        file_block = f.read()
    file_names = file_block.splitlines()
    count = np.zeros(len(file_names))
    # Create list of nodes
    nodes = []
    tokens = []
    for i, ref_file in enumerate(file_names):
        category = get_category(ref_file)
        nodes.append((ref_file, {'category': category}))
        text = read_file(filepath+ref_file)
        tokens.append(remove_language_keywords(text, ref_file.split('.')[-1]))
    # Create dictionary of category colors
    category_colors = {"Signifiers": "#648FFF", "Demonstration": "#785EF0", "Memetic proxy": "#DC267F", "Constraints": "#FE6100", "Meta prompt": "#FFB000"}
    #, "Validation": "#beafc2"
    # Create graph
    G = nx.Graph()
    G.add_nodes_from(nodes)
    count_extreme = 0
    count_strong = 0
    count_related = 0
    count_weak = 0
    # Add edges
    threshold = 0.75
    for i, ref_file in enumerate(file_names):
        for j, cand_file in enumerate(file_names):
            if i != j and i < j:
                score = fuzz.token_sort_ratio(tokens[i], tokens[j]) / 100
                #score = calculate_similarity(filepath+ref_file, filepath+cand_file)
                if score >= threshold:
                    G.add_edge(ref_file, cand_file, weight=score, color='black')
                    count_strong = count_strong+1
                elif score >= (threshold*0.9):
                    G.add_edge(ref_file, cand_file, weight=score*0.25, color='lightgray')
                    count_related = count_related+1
                #elif score >= (threshold*0.8):
                    #G.add_edge(ref_file, cand_file, weight=score*0.10, color='gainsboro')
                else:
                    G.add_edge(ref_file, cand_file, weight=score*0.05, color='white')
                    count_weak = count_weak+1
    """total = count_extreme + count_strong + count_related + count_weak
    print("total:", total)
    print("strong:", count_strong, "percentage: ", (count_strong/total) * 100)
    print("close:", count_related, "percentage: ", (count_related/total) * 100)
    print("weak:", count_weak, "percentage: ", (count_weak/total) * 100)"""

    # Draw graph
    if task == "Python TicTacToe.txt":
        pos = nx.spring_layout(G, k=0.2, iterations=50, seed=5)
    elif task == "Python Blackjack.txt":
        pos = nx.spring_layout(G, k=0.2, seed=7)
    elif task == "Java Blackjack.txt":
        pos = nx.spring_layout(G, k=0.2, seed=2)
    else:
        pos = nx.spring_layout(G, k=0.2, seed=1)

    node_colors = [category_colors[data['category']] for _, data in G.nodes(data=True)]
    edge_colors = [color for _, _, color in G.edges(data='color')]
    weights = [weight for _, _, weight in G.edges(data='weight')]
    fig, ax = plt.subplots(figsize=(10, 10))
    nx.draw_networkx_nodes(G, pos, node_color=node_colors, node_size=600)
    nx.draw_networkx_edges(G, pos, edge_color=edge_colors, width=weights)
    labels = {}
    for node, degree in nodes:
        node_parts = node.split("/")
        node_name = node_parts[2].split(".")
        node_num = node_name[0].split("_")
        node_short = (node_num[1])
        labels[node] = node_short
        #labels[node] = node_short[0]
    nx.draw_networkx_labels(G, pos, labels, font_size=12, font_color='white', font_weight='normal')

    ax = plt.gca()
    ax.legend(handles=[plt.Circle([], [], color=color, label=category) for category, color in category_colors.items()], loc='upper left', fontsize=12)
    plt.axis('off')
    tsk = task.split(".")
    if tsk[0] == "Java TicTacToe":
        tsk = "Java Tic-Tac-Toe"
    elif tsk[0] == "Python TicTacToe":
        tsk = "Python Tic-Tac-Toe"
    else:
        tsk = tsk[0]
    plt.title(tsk+" proximity graph", fontsize=24)
    plt.subplots_adjust(bottom=0, right=1, left=0, hspace=0, wspace=0)
    plt.savefig(tsk+'.png')
    plt.show()

