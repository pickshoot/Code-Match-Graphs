package JDB_B06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {

		// Create a deck of cards
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Initialize variables for the game
		int playerScore = 0;
		int dealerScore = 0;
		Scanner scanner = new Scanner(System.in);

		// Deal the initial cards
		System.out.println("Dealing cards...");
		playerScore += getCardValue(deck.get(0));
		System.out.println("You are dealt the " + deck.get(0));
		deck.remove(0);
		dealerScore += getCardValue(deck.get(0));
		System.out.println("The dealer is dealt the " + deck.get(0));
		deck.remove(0);
		playerScore += getCardValue(deck.get(0));
		System.out.println("You are dealt the " + deck.get(0));
		deck.remove(0);
		dealerScore += getCardValue(deck.get(0));
		System.out.println("The dealer's second card is hidden."+dealerScore);
		deck.remove(0);

		// Allow the player to take their turn
		while (playerScore < 21) {
			System.out.println("Your score is " + playerScore + ". Hit or stand? (H/S)");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("H")) {
				playerScore += getCardValue(deck.get(0));
				System.out.println("You are dealt the " + deck.get(0));
				deck.remove(0);
			} else {
				break;
			}
		}

		// If the player has not gone bust, let the dealer take their turn
		if (playerScore <= 21) {
			System.out.println("The dealer's second card is " + deck.get(0));
			dealerScore += getCardValue(deck.get(0));
			deck.remove(0);
			while (dealerScore < 17) {
				dealerScore += getCardValue(deck.get(0));
				System.out.println("The dealer is dealt the " + deck.get(0));
				deck.remove(0);
			}
		}

		// Determine the winner of the game
		System.out.println("Your score is " + playerScore);
		System.out.println("The dealer's score is " + dealerScore);
		if (playerScore > 21) {
			System.out.println("You have gone bust. The dealer wins!");
		} else if (dealerScore > 21) {
			System.out.println("The dealer has gone bust. You win!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("The dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

		scanner.close();

	}

	public static int getCardValue(String card) {
		String rank = card.split(" ")[0];
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("2")) {
			return 2;
		} else if (rank.equals("3")) {
			return 3;
		} else if (rank.equals("4")) {
			return 4;
		} else if (rank.equals("5")) {
			return 5;
		} else if (rank.equals("6")) {
			return 6;
		} else if (rank.equals("7")) {
			return 7;
		} else if (rank.equals("8")) {
			return 8;
		} else if (rank.equals("9")) {
			return 9;
		} else {
			return 10;
		}
	}
}
