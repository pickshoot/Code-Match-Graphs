package JCB_C02;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Random random = new Random();

		// Set up the deck of cards
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		String[] deck = new String[52];
		int[] deckValues = new int[52];

		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				int index = 13 * i + j;
				deck[index] = ranks[j] + " of " + suits[i];
				deckValues[index] = values[j];
			}
		}

		// Shuffle the deck
		for (int i = 0; i < deck.length; i++) {
			int j = random.nextInt(deck.length);
			String temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
			int tempValue = deckValues[i];
			deckValues[i] = deckValues[j];
			deckValues[j] = tempValue;
		}

		// Set up the game
		int playerScore = 0;
		int dealerScore = 0;
		int playerCards = 0;
		int dealerCards = 0;
		boolean playerTurn = true;

		// Deal the cards
		for (int i = 0; i < 4; i++) {
			String card = deck[i];
			int value = deckValues[i];
			if (i % 2 == 0) {
				playerScore += value;
				playerCards++;
			} else {
				dealerScore += value;
				dealerCards++;
			}
			System.out.println(card);
		}

		// Play the game
		while (playerTurn) {
			System.out.println("Your score: " + playerScore);
			System.out.println("Hit or stand? (h/s)");
			String choice = input.nextLine();
			if (choice.equals("h")) {
				String card = deck[playerCards + dealerCards];
				int value = deckValues[playerCards + dealerCards];
				playerScore += value;
				playerCards++;
				System.out.println(card);
				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					playerTurn = false;
				}
			} else if (choice.equals("s")) {
				playerTurn = false;
			} else {
				System.out.println("Invalid input.");
			}
		}

		// Dealer's turn
		while (dealerScore < 17 && playerScore <= 21) {
			String card = deck[playerCards + dealerCards];
			int value = deckValues[playerCards + dealerCards];
			dealerScore += value;
			dealerCards++;
			System.out.println(card);
		}

		// Determine the winner
		if (dealerScore > 21) {
			System.out.println("Dealer bust! You win.");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie.");
		}

		// Ask the user if they want to play again
		System.out.println("Play again? (y/n)");
		String playAgain = input.nextLine();
		if (playAgain.equals("y")) {
			main(args); // Recursive call to start a new game
		} else {
			System.out.println("Thanks for playing!");
		}

		input.close();
	}
}