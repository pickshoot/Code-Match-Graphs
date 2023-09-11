package JDB_B05;

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
		String card;

		// deal two cards to player and two to dealer
		for (int i = 0; i < 2; i++) {
			card = deck.remove(0);
			playerScore += getCardValue(card);
			System.out.println("You were dealt a " + card + ". Your score is now " + playerScore + ".");
			card = deck.remove(0);
			dealerScore += getCardValue(card);
			if (i == 0) {
				System.out.println("The dealer is showing a " + card + ".");
			}
		}

		// player's turn
		while (playerScore < 21) {
			System.out.println("Do you want to hit or stand? (h/s)");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("h")) {
				card = deck.remove(0);
				playerScore += getCardValue(card);
				System.out.println("You were dealt a " + card + ". Your score is now " + playerScore + ".");
			} else if (input.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'h' or 's'.");
			}
		}

		// dealer's turn
		if (playerScore <= 21) {
			System.out.println("The dealer's hole card is " + deck.get(0) + ".");
			while (dealerScore < 17) {
				card = deck.remove(0);
				dealerScore += getCardValue(card);
				System.out.println(
						"The dealer hits and gets a " + card + ". The dealer's score is now " + dealerScore + ".");
			}
			if (dealerScore > 21) {
				System.out.println("The dealer busts. You win!");
			} else if (dealerScore > playerScore) {
				System.out.println("The dealer's score is higher than yours. You lose.");
			} else if (dealerScore == playerScore) {
				System.out.println("It's a tie!");
			} else {
				System.out.println("Your score is higher than the dealer's. You win!");
			}
		} else {
			System.out.println("You bust. You lose.");
		}

		scanner.close();
	}

	private static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		return deck;
	}

	private static int getCardValue(String card) {
		String[] parts = card.split(" ");
		String rank = parts[0];
		if (rank.equals("ace")) {
			return 11;
		} else if (rank.equals("10") || rank.equals("jack") || rank.equals("queen") || rank.equals("king")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}
