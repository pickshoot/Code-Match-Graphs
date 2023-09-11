package JSB_B03;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();

		// initial deal
		Hand playerHand = new Hand();
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());

		Hand dealerHand = new Hand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());

		System.out.println("Dealer's upcard: " + dealerHand.getUpcard());

		// player's turn
		while (true) {
			System.out.println("Your hand: " + playerHand.toString());
			System.out.println("Your total: " + playerHand.getTotal());

			System.out.print("Hit or stand? ");
			String input = sc.nextLine();

			if (input.equals("hit")) {
				playerHand.addCard(deck.dealCard());

				if (playerHand.getTotal() > 21) {
					System.out.println("Bust! Your hand: " + playerHand.toString());
					return;
				}
			} else if (input.equals("stand")) {
				break;
			}
		}

		// dealer's turn
		while (dealerHand.getTotal() < 17 || (dealerHand.hasAce() && dealerHand.getSoftTotal() == 17)) {
			dealerHand.addCard(deck.dealCard());
		}

		System.out.println("Dealer's hand: " + dealerHand.toString());
		System.out.println("Dealer's total: " + dealerHand.getTotal());

		if (dealerHand.getTotal() > 21) {
			System.out.println("Dealer busts! You win!");
		} else if (dealerHand.getTotal() > playerHand.getTotal()) {
			System.out.println("Dealer wins!");
		} else if (dealerHand.getTotal() < playerHand.getTotal()) {
			System.out.println("You win!");
		} else {
			System.out.println("Push!");
		}
	}
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 1; j <= 4; j++) {
				cards.add(new Card(i, j));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card dealCard() {
		return cards.remove(0);
	}
}

class Card {
	private int rank;
	private int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		if (rank == 1) {
			return 11;
		} else if (rank >= 10) {
			return 10;
		} else {
			return rank;
		}
	}

	public String toString() {
		String[] ranks = { "", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "", "Clubs", "Diamonds", "Hearts", "Spades" };
		return ranks[rank] + " of " + suits[suit];
	}
}

class Hand {
	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getTotal() {
		int total = 0;
		int numAces = 0;
		for (Card card : cards) {
			total += card.getValue();
			if (card.getRank() == 1) {
				numAces++;
			}
		}
		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}
		return total;
	}

	public int getSoftTotal() {
		int total = 0;
		for (Card card : cards) {
			total += card.getValue();
		}
		return total;
	}

	public boolean hasAce() {
		for (Card card : cards) {
			if (card.getRank() == 1) {
				return true;
			}
		}
		return false;
	}

	public Card getUpcard() {
		return cards.get(1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
}