package B7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Welcome to Blackjack!");

		// Create deck of cards
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}

		// Shuffle deck
		for (int i = deck.size() - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}

		// Deal initial hands
		List<Card> playerHand = new ArrayList<>();
		List<Card> dealerHand = new ArrayList<>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Show initial hands
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: " + dealerHand.get(0) + " and [hidden]");

		// Player's turn
		boolean playerDone = false;
		while (!playerDone) {
			System.out.println("Do you want to hit or stand? (h/s)");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("h")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					playerDone = true;
				}
			} else if (input.equals("s")) {
				playerDone = true;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}

		// Dealer's turn
		boolean dealerDone = false;
		while (!dealerDone) {
			if (getHandValue(dealerHand) < 17) {
				dealerHand.add(deck.remove(0));
				System.out.println("Dealer hits: " + dealerHand.get(dealerHand.size() - 1));
				if (getHandValue(dealerHand) > 21) {
					System.out.println("Dealer busts! You win.");
					dealerDone = true;
				}
			} else {
				dealerDone = true;
			}
		}

		// Compare hands
		int playerValue = getHandValue(playerHand);
		int dealerValue = getHandValue(dealerHand);
		if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}

		// Clean up
		scanner.close();
	}

	private static int getHandValue(List<Card> hand) {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}
			value += card.getValue();
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	private static class Card {
		private final Rank rank;
		private final Suit suit;

		public Card(Rank rank, Suit suit) {
			this.rank = rank;
			this.suit = suit;
		}

		public Rank getRank() {
			return rank;
		}

		public Suit getSuit() {
			return suit;
		}

		public int getValue() {
			return rank.getValue();
		}

		@Override
		public String toString() {
			return rank + " of " + suit;
		}
	}

	private enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
		KING(10);

		private final int value;

		private Rank(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES;
	}
}