package C10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create and shuffle the deck
		ArrayList<String> deck = createDeck();
		Collections.shuffle(deck);

		// Initialize player and dealer hands
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();

		// Deal two cards to the player and dealer
		playerHand.add(deck.get(0));
		dealerHand.add(deck.get(1));
		playerHand.add(deck.get(2));
		dealerHand.add(deck.get(3));

		// Print the initial hands
		System.out.println("Your hand: " + playerHand.get(0) + " and " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + " and one face down card.");

		// Player's turn
		boolean playerDone = false;
		while (!playerDone) {
			System.out.println("Do you want to hit or stand? (enter 'h' or 's')");
			String choice = scanner.nextLine();
			if (choice.equals("h")) {
				playerHand.add(deck.get(4));
				System.out.println("Your new card is: " + playerHand.get(playerHand.size() - 1));
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! Your hand is worth: " + getHandValue(playerHand));
					playerDone = true;
				}
			} else {
				playerDone = true;
			}
		}

		// Dealer's turn
		boolean dealerDone = false;
		while (!dealerDone) {
			if (getHandValue(dealerHand) < 17) {
				dealerHand.add(deck.get(5));
				System.out.println("Dealer hits and gets a " + dealerHand.get(dealerHand.size() - 1));
				if (getHandValue(dealerHand) > 21) {
					System.out.println("Dealer busts! Your hand wins!");
					dealerDone = true;
				}
			} else {
				dealerDone = true;
			}
		}

		// Determine the winner
		int playerValue = getHandValue(playerHand);
		int dealerValue = getHandValue(dealerHand);
		if (playerValue > 21) {
			System.out.println("You bust! Dealer wins!");
		} else if (dealerValue > 21) {
			System.out.println("Dealer busts! Your hand wins!");
		} else if (playerValue > dealerValue) {
			System.out.println("Your hand wins!");
		} else if (playerValue < dealerValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

		scanner.close();
	}

	public static ArrayList<String> createDeck() {
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		ArrayList<String> deck = new ArrayList<String>();
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		return deck;
	}

	public static int getHandValue(ArrayList<String> hand) {
		int value = 0;
		boolean hasAce = false;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("ace")) {
				hasAce = true;
			}
			if (rank.equals("2")) {
				value += 2;
			} else if (rank.equals("3")) {
				value += 3;
			} else if (rank.equals("4")) {
				value += 4;
			} else if (rank.equals("5")) {
				value += 5;
			} else if (rank.equals("6")) {
				value += 6;
			} else if (rank.equals("7")) {
				value += 7;
			} else if (rank.equals("8")) {
				value += 8;
			} else if (rank.equals("9")) {
				value += 9;
			} else {
				value += 10;
			}
		}
		if (hasAce && value <= 11) {
			value += 10;
		}
		return value;
	}
}