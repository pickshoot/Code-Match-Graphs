import random

words = ["prompt", "programming", "artificial", "intelligence", "evaluation"]
word = random.choice(words)
guesses = ["_"] * len(word)
incorrect_guesses = 0

print(" ".join(guesses))

while incorrect_guesses < 6 and "_" in guesses:
    try:
        letter = input("Guess a letter: ").lower()
        if len(letter) != 1 or not letter.isalpha():
            print("Invalid input. Please enter a single letter.")
            continue

        found = False
        for i, c in enumerate(word):
            if c == letter:
                guesses[i] = letter
                found = True

        if not found:
            incorrect_guesses += 1
            print(f"Incorrect! You have {6 - incorrect_guesses} guesses left.")

        print(" ".join(guesses))
    except:
        print("Something went wrong try again!")

if "_" in guesses:
    print(f"Sorry, you ran out of guesses. The word was {word}.")
else:
    print(f"Congratulations! You guessed the word {word} correctly.")
