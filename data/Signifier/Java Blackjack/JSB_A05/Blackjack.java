package blackjackSA5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private static final int DEALER_STANDS_AT = 17;
	private static final int MAX_SCORE = 21;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create deck and shuffle
		Deck deck = new Deck();
		deck.shuffle();

		// Create player and dealer hands
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

		// Deal initial cards
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());

		// Show initial hands
		System.out.println("Your hand: " + playerHand.toString());
		System.out.println("Dealer's hand: " + dealerHand.getVisibleCards());

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? (h/s): ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("h")) {
				playerHand.addCard(deck.drawCard());
				System.out.println("Your hand: " + playerHand.toString());
				if (playerHand.getScore() > MAX_SCORE) {
					System.out.println("You busted!");
					return;
				}
			} else if (input.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter h or s.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's turn: ");
		System.out.println("Dealer's hand: " + dealerHand.toString());
		while (dealerHand.getScore() < DEALER_STANDS_AT) {
			dealerHand.addCard(deck.drawCard());
			System.out.println("Dealer hits. Dealer's hand: " + dealerHand.toString());
			if (dealerHand.getScore() > MAX_SCORE) {
				System.out.println("Dealer busted! You win!");
				return;
			}
		}

		// Show final hands and determine winner
		System.out.println("Your hand: " + playerHand.toString());
		System.out.println("Dealer's hand: " + dealerHand.toString());
		int playerScore = playerHand.getScore();
		int dealerScore = dealerHand.getScore();
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private static class Deck {
		private List<Card> cards;

		public Deck() {
			cards = new ArrayList<>();
			for (Suit suit : Suit.values()) {
				for (Rank rank : Rank.values()) {
					cards.add(new Card(rank, suit));
				}
			}
		}

		public void shuffle() {
			for (int i = 0; i < cards.size(); i++) {
				int j = (int) (Math.random() * cards.size());
				Card temp = cards.get(i);
				cards.set(i, cards.get(j));
				cards.set(j, temp);
			}
		}

		public Card drawCard() {
			return cards.remove(0);
		}
	}

	private static class Card {
		private Rank rank;
		private Suit suit;

		public Card(Rank rank, Suit suit) {
			this.rank = rank;
			this.suit = suit;
		}

		public int getValue() {
			return rank.getValue();
		}

		@Override
		public String toString() {
			return rank + " of " + suit;
		}
	}

	private static class Hand {
		private List<Card> cards;

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
				if (card.getValue() == 1) {
					numAces++;
				}
				score += card.getValue();
			}
			while (numAces > 0 && score + 10 <= MAX_SCORE) {
				score += 10;
				numAces--;
			}
			return score;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Card card : cards) {
				sb.append(card.toString());
				sb.append(", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(" (").append(getScore()).append(")");
			return sb.toString();
		}

		public String getVisibleCards() {
			StringBuilder sb = new StringBuilder();
			sb.append(cards.get(0).toString());
			sb.append(", [hidden]");
			return sb.toString();
		}
	}

	private enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	private enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
		KING(10);

		private int value;

		private Rank(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
