package JVB_SC1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Welcome to Blackjack!");

		// Create a deck of cards
		List<String> deck = new ArrayList<>();
		String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
		String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}

		// Shuffle the deck
		for (int i = 0; i < deck.size(); i++) {
			int randomIndex = random.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(randomIndex));
			deck.set(randomIndex, temp);
		}

		int playerScore = 0;
		int dealerScore = 0;

		// Deal two cards to the player and the dealer
		System.out.println("Dealing cards...");
		playerScore += getCardValue(deck.remove(0));
		dealerScore += getCardValue(deck.remove(0));
		playerScore += getCardValue(deck.remove(0));
		dealerScore += getCardValue(deck.remove(0));

		// Show the player's cards and ask if they want to hit or stand
		while (true) {
			System.out.println("Your cards: " + playerScore);
			System.out.println("Dealer's cards: " + dealerScore);
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine();

			if (choice.equals("hit")) {
				// Give the player another card
				playerScore += getCardValue(deck.remove(0));
				if (playerScore > 21) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			} else if (choice.equals("stand")) {
				// Dealer's turn
				while (dealerScore < 17) {
					dealerScore += getCardValue(deck.remove(0));
				}

				if (dealerScore > 21) {
					System.out.println("Dealer busted! You win.");
				} else if (dealerScore > playerScore) {
					System.out.println("Dealer wins.");
				} else if (playerScore > dealerScore) {
					System.out.println("You win!");
				} else {
					System.out.println("It's a tie!");
				}

				return;
			} else {
				System.out.println("Invalid choice. Please enter \"hit\" or \"stand\".");
			}
		}
	}

	public static int getCardValue(String card) {
		String value = card.split(" ")[0];
		if (value.equals("Ace")) {
			return 11;
		} else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(value);
		}
	}
}
