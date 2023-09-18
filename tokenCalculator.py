import re

from thefuzz import fuzz


class Tokens:
    keywordsPython = " "
    keywordsJava = " "

    def __init__(self):
        with open("keywords/python.txt", 'r', encoding='utf-8') as f:
            keywords_block = f.read()
        self.keywordsPython = keywords_block.splitlines()

        with open("keywords/java.txt", 'r', encoding='utf-8') as f:
            keywords_block = f.read()
        self.keywordsJava = keywords_block.splitlines()

    def read_file(self, filename):
        # Reads the file and creates tokens based on what is considered a word
        with open(filename, 'r', encoding='utf-8') as f:
            text = f.read()
            words = re.findall('\w+', text)
        return words

    def remove_language_keywords(self, words, language):
        # Remove language-specific keywords
        if language == "py":
            keywords = self.keywordsPython
        elif language == "java":
            keywords = self.keywordsJava
        else:
            return words
        filtered_words = [word for word in words if word not in keywords]
        return filtered_words

    def calculate_similarity(self, reference_file, candidate_file):
        reference_text = self.read_file(reference_file)
        candidate_text = self.read_file(candidate_file)

        reference_tokens = self.remove_language_keywords(reference_text, reference_file.split('.')[-1])
        candidate_tokens = self.remove_language_keywords(candidate_text, candidate_file.split('.')[-1])
        fuz = fuzz.token_sort_ratio(reference_tokens, candidate_tokens) / 100
        return fuz