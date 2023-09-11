package blackjackSA3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		List<String> deck = getDeck();
		Collections.shuffle(deck);

		int playerScore = 0;
		int dealerScore = 0;

		// Deal initial cards
		playerScore += getCardValue(deck.get(0));
		dealerScore += getCardValue(deck.get(1));
		playerScore += getCardValue(deck.get(2));
		dealerScore += getCardValue(deck.get(3));
		System.out.println("Your cards: " + deck.get(0) + " and " + deck.get(2));
		System.out.println("Dealer's card: " + deck.get(1));

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				playerScore += getCardValue(deck.get(4));
				System.out.println("Your card: " + deck.get(4));
				deck.remove(4);
				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (choice.equals("stand")) {
				break;
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			dealerScore += getCardValue(deck.get(4));
			System.out.println("Dealer's card: " + deck.get(4));
			deck.remove(4);
			if (dealerScore > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare scores
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore == dealerScore) {
			System.out.println("Push.");
		} else {
			System.out.println("You lose.");
		}
	}

	private static List<String> getDeck() {
		List<String> deck = new ArrayList<>();
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		for (String rank : ranks) {
			deck.add(rank + "♥");
			deck.add(rank + "♦");
			deck.add(rank + "♠");
			deck.add(rank + "♣");
		}
		return deck;
	}

	private static int getCardValue(String card) {
		String rank = card.substring(0, card.length() - 1);
		if (rank.equals("A")) {
			return 11;
		} else if (rank.equals("K") || rank.equals("Q") || rank.equals("J") || rank.equals("10")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}