package JDB_B08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Set up the deck of cards
		List<String> deck = new ArrayList<>();
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		String[] suits = { "H", "D", "C", "S" };
		for (String rank : ranks) {
			for (String suit : suits) {
				deck.add(rank + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Deal the initial cards
		List<String> playerCards = new ArrayList<>();
		List<String> dealerCards = new ArrayList<>();
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));

		// Print the initial hands
		System.out.println("Your hand: " + playerCards);
		System.out.println("Dealer's hand: [?, " + dealerCards.get(1) + "]");

		// Player's turn
		while (true) {
			System.out.println("Would you like to hit or stand?");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				playerCards.add(deck.remove(0));
				System.out.println("Your hand: " + playerCards);
				if (getHandValue(playerCards) > 21) {
					System.out.println("Bust! You lose.");
					scanner.close();
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + dealerCards);
		while (getHandValue(dealerCards) < 17) {
			dealerCards.add(deck.remove(0));
			System.out.println("Dealer hits: " + dealerCards.get(dealerCards.size() - 1));
			if (getHandValue(dealerCards) > 21) {
				System.out.println("Dealer busts! You win.");
				scanner.close();
				return;
			}
		}

		// Determine the winner
		int playerValue = getHandValue(playerCards);
		int dealerValue = getHandValue(dealerCards);
		System.out.println("Your hand: " + playerCards);
		System.out.println("Dealer's hand: " + dealerCards);
		if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (playerValue < dealerValue) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie.");
		}

		scanner.close();
	}

	private static int getHandValue(List<String> cards) {
		int value = 0;
		int numAces = 0;
		for (String card : cards) {
			String rank = card.substring(0, card.length() - 1);
			if (rank.equals("A")) {
				numAces++;
				value += 11;
			} else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
				value += 10;
			} else {
				value += Integer.parseInt(rank);
			}
		}
		while (numAces > 0 && value > 21) {
			value -= 10;
			numAces--;
		}
		return value;
	}
}
