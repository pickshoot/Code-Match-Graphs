package JRB_B10;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
	private static final int MAX_SCORE = 21;
	private static final int DEALER_MIN_SCORE = 17;
	private static final int BLACKJACK = 21;
	private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
	private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
			"King" };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String playerName = scanner.nextLine();
		System.out.println("Welcome to Blackjack, " + playerName + "!\n");

		Deck deck = new Deck();
		deck.shuffle();

		Hand playerHand = new Hand();
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());

		Hand dealerHand = new Hand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());

		System.out.println("Dealer's Hand:");
		dealerHand.displayDealerHand();
		System.out.println();

		System.out.println(playerName + "'s Hand:");
		playerHand.displayHand();
		System.out.println("Total: " + playerHand.getScore() + "\n");

		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				playerHand.addCard(deck.dealCard());
				System.out.println(playerName + " draws a card.");
				playerHand.displayHand();
				System.out.println("Total: " + playerHand.getScore() + "\n");

				if (playerHand.getScore() > MAX_SCORE) {
					System.out.println(playerName + " busts! Dealer wins.");
					return;
				}
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter hit or stand.");
			}
		}

		System.out.println("Dealer's Hand:");
		dealerHand.displayHand();
		System.out.println("Total: " + dealerHand.getScore() + "\n");

		while (dealerHand.getScore() < DEALER_MIN_SCORE) {
			dealerHand.addCard(deck.dealCard());
			System.out.println("Dealer draws a card.");
			dealerHand.displayHand();
			System.out.println("Total: " + dealerHand.getScore() + "\n");
		}

		if (dealerHand.getScore() > MAX_SCORE) {
			System.out.println("Dealer busts! " + playerName + " wins.");
			return;
		}

		int playerScore = playerHand.getScore();
		int dealerScore = dealerHand.getScore();

		if (playerScore > dealerScore) {
			System.out.println(playerName + " wins!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	static class Deck {
		private ArrayList<Card> cards;

		public Deck() {
			cards = new ArrayList<>();
			for (String suit : SUITS) {
				for (String rank : RANKS) {
					Card card = new Card(suit, rank);
					cards.add(card);
				}
			}
		}

		public void shuffle() {
			for (int i = cards.size() - 1; i > 0; i--) {
				int j = (int) (Math.random() * (i + 1));
				Card temp = cards.get(i);
				cards.set(i, cards.get(j));
				cards.set(j, temp);
			}
		}

		public Card dealCard() {
			if (cards.isEmpty()) {
				throw new IllegalStateException("Deck is empty!");
			}
			return cards.remove(cards.size() - 1);
		}
	}

	static class Card {
		private final String suit;
		private final String rank;
		private final int value;

		public Card(String suit, String rank) {
			this.suit = suit;
			this.rank = rank;

			if (rank.equals("Ace")) {
				value = 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				value = 10;
			} else {
				value = Integer.parseInt(rank);
			}
		}

		public String getSuit() {
			return suit;
		}

		public String getRank() {
			return rank;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			return rank + " of " + suit;
		}
	}

	static class Hand {
		private ArrayList<Card> cards;

		public Hand() {
			cards = new ArrayList<>();
		}

		public void addCard(Card card) {
			cards.add(card);
		}

		public int getScore() {
			int score = 0;
			int numAces = 0;
			for (Card card : cards) {
				score += card.getValue();
				if (card.getRank().equals("Ace")) {
					numAces++;
				}
			}
			while (score > MAX_SCORE && numAces > 0) {
				score -= 10;
				numAces--;
			}
			return score;
		}

		public void displayHand() {
			for (Card card : cards) {
				System.out.println(card);
			}
		}

		public void displayDealerHand() {
			System.out.println("[" + cards.get(0) + "]");
			System.out.println("[Face Down]");
		}
	}
}