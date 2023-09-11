package blackjackSA6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> deck = new ArrayList<>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

		// Initialize deck
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle deck
		Collections.shuffle(deck);

		int playerTotal = 0;
		int dealerTotal = 0;
		int playerScore = 0;
		int dealerScore = 0;

		// Deal initial cards
		System.out.println("Dealing cards...");
		playerTotal += getCardValue(deck.get(0));
		playerTotal += getCardValue(deck.get(1));
		playerScore = playerTotal;
		System.out.println("You have " + deck.get(0) + " and " + deck.get(1));
		dealerTotal += getCardValue(deck.get(2));
		dealerTotal += getCardValue(deck.get(3));
		dealerScore = dealerTotal;
		System.out.println("Dealer has " + deck.get(2) + " and one hidden card");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equals("hit")) {
				playerTotal += getCardValue(deck.get(4));
				playerScore = playerTotal;
				System.out.println("You drew " + deck.get(4));
				deck.remove(4);
				if (playerTotal > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			}
		}

		// Dealer's turn
		System.out.println("Dealer's turn...");
		System.out.println("Dealer reveals hidden card: " + deck.get(3));
		while (dealerTotal < 17) {
			dealerTotal += getCardValue(deck.get(4));
			dealerScore = dealerTotal;
			System.out.println("Dealer draws " + deck.get(4));
			deck.remove(4);
			if (dealerTotal > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare scores
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer score: " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int getCardValue(String card) {
		String rank = card.split(" ")[0];
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack") || rank.equals("10")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}