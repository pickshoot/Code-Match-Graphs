package JRB_B06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	private static final int BLACKJACK = 21;
	private static final int DEALER_MIN_SCORE = 17;

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();

		// Initialize deck of cards and shuffle
		List<Card> deck = newDeck();
		shuffleDeck(deck);

		// Deal initial cards to player and dealer
		List<Card> playerCards = new ArrayList<>();
		List<Card> dealerCards = new ArrayList<>();
		dealCards(deck, playerCards, dealerCards);

		// Show player's cards and dealer's first card
		System.out.println(playerName + "'s cards: " + playerCards);
		System.out.println("Dealer's cards: [" + dealerCards.get(0) + ", **]");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				dealCard(deck, playerCards);
				System.out.println(playerName + "'s cards: " + playerCards);
				int playerScore = getScore(playerCards);
				if (playerScore > BLACKJACK) {
					System.out.println("Bust! " + playerName + " loses.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input.");
			}
		}

		// Dealer's turn
		while (getScore(dealerCards) < DEALER_MIN_SCORE) {
			dealCard(deck, dealerCards);
		}
		System.out.println("Dealer's cards: " + dealerCards);

		// Determine winner
		int playerScore = getScore(playerCards);
		int dealerScore = getScore(dealerCards);
		if (playerScore > BLACKJACK) {
			System.out.println("Bust! " + playerName + " loses.");
		} else if (dealerScore > BLACKJACK || playerScore > dealerScore) {
			System.out.println(playerName + " wins!");
		} else if (playerScore == dealerScore) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Dealer wins.");
		}
	}

	private static List<Card> newDeck() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		return deck;
	}

	private static void shuffleDeck(List<Card> deck) {
		for (int i = 0; i < deck.size(); i++) {
			int j = (int) (Math.random() * (deck.size() - i)) + i;
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	}

	private static void dealCards(List<Card> deck, List<Card> playerCards, List<Card> dealerCards) {
		dealCard(deck, playerCards);
		dealCard(deck, dealerCards);
		dealCard(deck, playerCards);
		dealCard(deck, dealerCards);
	}

	private static void dealCard(List<Card> deck, List<Card> hand) {
		Card card = deck.remove(0);
		hand.add(card);
	}

	private static int getScore(List<Card> hand) {
		int score = 0;
		int numAces = 0;
		for (Card card : hand) {
			score += card.getRank().getValue();
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}
		}
		while (score > BLACKJACK && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
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

		@Override
		public String toString() {
			return rank + " of " + suit;
		}
	}

	private enum Rank {
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
		ACE(11);

		private final int value;

		private Rank(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}
}