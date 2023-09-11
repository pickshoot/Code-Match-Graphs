import difflib
import math
import re
from collections import Counter

import matplotlib.pyplot as plt
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
    return filename.split('/')[0]

if __name__ == '__main__':
    #task = "Java TicTacToe.txt"
    #task = "Java Blackjack.txt"
    #task = "Python TicTacToe.txt"
    task = "Python Blackjack.txt"
    filepath = "data/"
    list_file = filepath+task
    with open(list_file, 'r', encoding='utf-8') as f:
        file_block = f.read()
    file_names = file_block.splitlines()
    tokens = []
    for i, ref_file in enumerate(file_names):
        text = read_file(filepath+ref_file)
        tokens.append(remove_language_keywords(text, ref_file.split('.')[-1]))
    low_score = 0
    tot_score = 0
    scores = np.zeros((len(file_names), len(file_names)))
    count = np.zeros(len(file_names))
    for i, ref_file in enumerate(file_names):
        for j, cand_file in enumerate(file_names):
            if i != j and i < j:
                score = fuzz.token_sort_ratio(tokens[i], tokens[j]) / 100
                #score = calculate_similarity(filepath+ref_file, filepath+cand_file)
                scores[i,j] = score
                scores[j,i] = score
                count[i] += score
                count[j] += score
                if score < 0.4:
                    low_score = low_score + 1
                else:
                    tot_score = tot_score + 1
                """if score < low_score:
                    low_score = score
                    print("ref:", ref_file, "cand:",cand_file,"Score:",low_score)"""

    """print("low:",low_score,"percent:", (low_score/(tot_score+low_score))*100,"%")
    avg = np.zeros(len(file_names))
    #print(count)
    for i, ref_file in enumerate(file_names):
        avg[i] = count[i]/len(file_names)

    print("mean:",avg.mean())"""
    """
    maximus = avg.max()
    for i, ref_file in enumerate(file_names):
        if avg[i] == maximus:
            ref_parts = ref_file.split("/")
            ref_name = ref_parts[2].split(".")
            ref_short = (ref_name[0])

            print("max:",maximus,"ref:",ref_short)
            break
    minimus = 1
    minref = ""
    for i, ref_file in enumerate(file_names):
        if avg[i] < minimus and avg[i] != 0:
            minimus = avg[i]
            ref_parts = ref_file.split("/")
            ref_name = ref_parts[2].split(".")
            ref_short = (ref_name[0])
            minref = ref_short

    print("min:",minimus,"ref:",minref)
    #print(avg)"""
    """lent = len(file_names)-1
    for i, ref_file in enumerate(file_names):
        print(scores[i][lent])"""
    fig, ax = plt.subplots(figsize=(12,12))
    heatmap = ax.imshow(scores, cmap='YlGnBu', vmin=0.5, vmax=1.0)
    ax.set_xticks(np.arange(0, len(file_names), 10))
    ax.set_yticks(np.arange(0, len(file_names), 10))
    short_names = []
    for x in file_names:
        #category = get_category(x)
        parts = x.split("/")
        name = parts[2].split(".")
        short_names.append(name[0])
        #iden = name[0].split("_")
        #comp = category+" "+iden[1]
        #short_names.append(comp)

    x_tick_labels = short_names[::10]
    y_tick_labels = short_names[::10]
    ax.set_xticklabels(x_tick_labels, fontsize=16, rotation=45, ha="right", rotation_mode="anchor")
    ax.set_yticklabels(y_tick_labels, fontsize=16)


    fig.tight_layout()
    cbar = ax.figure.colorbar(heatmap, ax=ax)
    cbar.ax.tick_params(labelsize=16)
    cbar.set_label('Similarity', fontsize=16)
    tsk = task.split(".")
    if tsk[0] == "Java TicTacToe":
        tsk = "Java Tic-Tac-Toe"
    elif tsk[0] == "Python TicTacToe":
        tsk = "Python Tic-Tac-Toe"
    else:
        tsk = tsk[0]
    plt.title(tsk+" code similarity heat map", fontsize=24)
    #plt.subplots_adjust(bottom=0.05)
    plt.savefig(tsk+'.png')
    plt.show()
