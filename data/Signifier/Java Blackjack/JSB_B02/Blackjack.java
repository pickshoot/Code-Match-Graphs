package JSB_B02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Set up the deck of cards
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

		// Deal the initial cards
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Print the initial hands
		System.out.println("Player's hand: " + playerHand.get(0) + ", " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", Hidden");

		// Player's turn
		while (true) {
			// Ask the player if they want to hit or stand
			System.out.print("Do you want to hit or stand? ");
			String choice = input.nextLine();

			// If the player chooses to hit, deal another card and add it to their hand
			if (choice.equalsIgnoreCase("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Player's hand: " + String.join(", ", playerHand));

				// Check if the player has gone over 21 (bust)
				int total = getTotal(playerHand);
				if (total > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			}
			// If the player chooses to stand, move on to the dealer's turn
			else if (choice.equalsIgnoreCase("stand")) {
				break;
			}
			// If the player inputs an invalid choice, ask again
			else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + String.join(", ", dealerHand));
		while (getTotal(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer's hand: " + String.join(", ", dealerHand));
		}

		// Determine the winner
		int playerTotal = getTotal(playerHand);
		int dealerTotal = getTotal(dealerHand);
		if (dealerTotal > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (playerTotal < dealerTotal) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	// Helper method to calculate the total value of a hand
	public static int getTotal(ArrayList<String> hand) {
		int total = 0;
		int aces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				aces++;
				total += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				total += 10;
			} else {
				total += Integer.parseInt(rank);
			}
		}

		// Handle aces
		while (total > 21 && aces > 0) {
			total -= 10;
			aces--;
		}

		return total;
	}
}