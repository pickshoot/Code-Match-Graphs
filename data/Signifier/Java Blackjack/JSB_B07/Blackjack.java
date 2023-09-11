package JSB_B07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Initialize the deck of cards and shuffle it
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		Collections.shuffle(deck);

		// Deal two cards to the player and the dealer
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Print the initial hands
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", *]");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String answer = input.nextLine();
			if (answer.equals("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				int handValue = calculateHandValue(playerHand);
				if (handValue > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (answer.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + dealerHand);
		while (calculateHandValue(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer hits: " + dealerHand);
		}
		int dealerHandValue = calculateHandValue(dealerHand);
		if (dealerHandValue > 21) {
			System.out.println("Dealer busts! You win.");
		} else {
			int playerHandValue = calculateHandValue(playerHand);
			if (playerHandValue > dealerHandValue) {
				System.out.println("You win!");
			} else if (playerHandValue < dealerHandValue) {
				System.out.println("You lose.");
			} else {
				System.out.println("It's a tie.");
			}
		}
	}

	public static int calculateHandValue(ArrayList<String> hand) {
		int value = 0;
		int aces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				aces++;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				value += 10;
			} else {
				value += Integer.parseInt(rank);
			}
		}
		for (int i = 0; i < aces; i++) {
			if (value + 11 <= 21) {
				value += 11;
			} else {
				value += 1;
			}
		}
		return value;
	}
}