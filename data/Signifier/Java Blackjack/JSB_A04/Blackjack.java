package blackjackSA4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		// Initialize the deck of cards
		List<String> deck = new ArrayList<>();
		for (String suit : new String[] { "Hearts", "Diamonds", "Clubs", "Spades" }) {
			for (int i = 2; i <= 10; i++) {
				deck.add(i + " of " + suit);
			}
			deck.add("Jack of " + suit);
			deck.add("Queen of " + suit);
			deck.add("King of " + suit);
			deck.add("Ace of " + suit);
		}
		Collections.shuffle(deck);

		// Initialize the player and dealer hands
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();

		// Deal the initial hands
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Display the initial hands
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", hidden]");

		// Player's turn
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String action = scanner.nextLine();
			if (action.equalsIgnoreCase("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (action.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid action. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
		}
		System.out.println("Dealer's hand: " + dealerHand);
		if (getHandValue(dealerHand) > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (getHandValue(dealerHand) > getHandValue(playerHand)) {
			System.out.println("Dealer wins!");
		} else if (getHandValue(playerHand) > getHandValue(dealerHand)) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie.");
		}
	}

	private static int getHandValue(List<String> hand) {
		int value = 0;
		int numAces = 0;
		for (String card : hand) {
			int cardValue = getCardValue(card);
			if (cardValue == 11) {
				numAces++;
			}
			value += cardValue;
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	private static int getCardValue(String card) {
		String rank = card.substring(0, card.indexOf(" "));
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack") || rank.equals("10")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}