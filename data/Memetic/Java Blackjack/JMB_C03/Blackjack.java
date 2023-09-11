package C3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		// Initialize the deck
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Initialize the player's hand and dealer's hand
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();

		// Deal two cards to the player and two cards to the dealer
		playerHand.add(deck.get(0));
		playerHand.add(deck.get(1));
		dealerHand.add(deck.get(2));
		dealerHand.add(deck.get(3));

		// Print the player's hand and the dealer's upcard
		System.out.println("Your hand: " + playerHand.get(0) + ", " + playerHand.get(1));
		System.out.println("Dealer's upcard: " + dealerHand.get(0));

		// Ask the player if they want to hit or stand
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerHand.add(deck.get(4));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("You bust! Dealer wins!");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			}
		}

		// Dealer's turn
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.get(4));
			System.out.println("Dealer's hand: " + dealerHand);
			if (getHandValue(dealerHand) > 21) {
				System.out.println("Dealer busts! You win!");
				return;
			}
		}

		// Determine the winner
		int playerHandValue = getHandValue(playerHand);
		int dealerHandValue = getHandValue(dealerHand);
		if (playerHandValue > dealerHandValue) {
			System.out.println("You win!");
		} else if (playerHandValue < dealerHandValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

	}

	public static int getHandValue(ArrayList<String> hand) {

		int value = 0;
		int numAces = 0;

		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				numAces++;
				value += 11;
			} else if (rank.equals("2")) {
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

		// Handle aces
		while (numAces > 0 && value > 21) {
			value -= 10;
			numAces--;
		}

		return value;

	}

}