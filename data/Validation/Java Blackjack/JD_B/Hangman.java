import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] words = { "prompt", "programming", "artificial", "intelligence", "evaluation" };
		String word = words[(int) (Math.random() * words.length)];
		char[] guesses = new char[word.length()];
		for (int i = 0; i < guesses.length; i++) {
			guesses[i] = '_';
		}
		int incorrectGuesses = 0;
		System.out.println(new String(guesses));
		while (incorrectGuesses < 6 && new String(guesses).contains("_")) {
			System.out.println("Guess a letter:");
			String input = scanner.nextLine().toLowerCase();
			try {
				char letter = input.charAt(0);
				if (!Character.isLetter(letter)) {
					throw new IllegalArgumentException("Invalid input. Please enter a letter.");
				}
				boolean found = false;
				for (int i = 0; i < word.length(); i++) {
					if (word.charAt(i) == letter) {
						guesses[i] = letter;
						found = true;
					}
				}
				if (!found) {
					incorrectGuesses++;
					System.out.println("Incorrect! You have " + (6 - incorrectGuesses) + " guesses left.");
				}
				System.out.println(new String(guesses));
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		if (new String(guesses).contains("_")) {
			System.out.println("Sorry, you ran out of guesses. The word was " + word);
		} else {
			System.out.println("Congratulations! You guessed the word " + word + " correctly.");
		}

		scanner.close();
	}
}
