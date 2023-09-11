package JDB_B01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Set up the deck of cards
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		Collections.shuffle(deck);

		// Deal two cards to the player and two to the dealer
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Show the player's hand and one of the dealer's cards
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", hidden]");

		// Player's turn
		while (true) {
			System.out.println("Do you want to hit or stand? (Enter H or S)");
			String input = scanner.nextLine().toUpperCase();
			if (input.equals("H")) {
				// Player draws another card
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					scanner.close();
					return;
				}
			} else {
				// Player stands
				break;
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + dealerHand);
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer hits: " + dealerHand.get(dealerHand.size() - 1));
		}
		if (getHandValue(dealerHand) > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (getHandValue(playerHand) > getHandValue(dealerHand)) {
			System.out.println("You win!");
		} else if (getHandValue(playerHand) == getHandValue(dealerHand)) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Dealer wins!");
		}

		scanner.close();
	}

	public static int getHandValue(ArrayList<String> hand) {
		int value = 0;
		int aces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("ace")) {
				value += 11;
				aces++;
			} else if (rank.equals("king") || rank.equals("queen") || rank.equals("jack")) {
				value += 10;
			} else {
				value += Integer.parseInt(rank);
			}
		}
		while (value > 21 && aces > 0) {
			value -= 10;
			aces--;
		}
		return value;
	}
}
