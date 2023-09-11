package blackjackSA9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> deck = createDeck();
		Collections.shuffle(deck);
		int playerScore = 0;
		int dealerScore = 0;
		int cardIndex = 0;
		boolean gameOver = false;

		// Deal two cards to the player and two to the dealer
		for (int i = 0; i < 2; i++) {
			playerScore += getCardValue(deck.get(cardIndex++));
			dealerScore += getCardValue(deck.get(cardIndex++));
		}

		while (!gameOver) {
			// Display the current scores
			System.out.println("Player score: " + playerScore);
			System.out.println("Dealer score: " + dealerScore);

			// Ask the player if they want to hit or stand
			System.out.print("Hit or stand? (h/s): ");
			String choice = scanner.nextLine();
			if (choice.equals("h")) {
				// Deal another card to the player
				playerScore += getCardValue(deck.get(cardIndex++));
				if (playerScore > 21) {
					System.out.println("Player busts! Dealer wins.");
					gameOver = true;
				}
			} else {
				// Dealer's turn
				while (dealerScore < 17) {
					dealerScore += getCardValue(deck.get(cardIndex++));
				}

				// Determine the winner
				if (dealerScore > 21) {
					System.out.println("Dealer busts! Player wins.");
				} else if (dealerScore > playerScore) {
					System.out.println("Dealer wins.");
				} else if (dealerScore < playerScore) {
					System.out.println("Player wins.");
				} else {
					System.out.println("It's a tie!");
				}
				gameOver = true;
			}
		}
	}

	// Creates a deck of 52 cards
	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}
		return deck;
	}

	// Returns the value of a card (10 for face cards, 1 for aces)
	public static int getCardValue(String card) {
		if (card.startsWith("J") || card.startsWith("Q") || card.startsWith("K")) {
			return 10;
		} else if (card.startsWith("A")) {
			return 1;
		} else if (card.startsWith("10")) {
			return Integer.parseInt(card.substring(0, 2));
		} else {
			return Integer.parseInt(card.substring(0, 1));
		}
	}
}