package blackjackSA8;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!\n");

		// Initialize deck and shuffle
		Deck deck = new Deck();
		deck.shuffle();

		// Initialize player and dealer
		Player player = new Player();
		Dealer dealer = new Dealer();

		// Deal initial cards
		for (int i = 0; i < 2; i++) {
			player.addCard(deck.drawCard());
			dealer.addCard(deck.drawCard());
		}

		// Show initial hands
		System.out.println("Your hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getVisibleHand());

		// Player's turn
		while (player.getHandValue() < 21) {
			System.out.print("\nWould you like to hit or stand? (h/s) ");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("h")) {
				player.addCard(deck.drawCard());
				System.out.println("Your hand: " + player.getHand());
			} else if (choice.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'h' or 's'.");
			}
		}

		// Check if player busts
		if (player.getHandValue() > 21) {
			System.out.println("You busted! Dealer wins.");
			return;
		}

		// Dealer's turn
		System.out.println("\nDealer's turn:");
		System.out.println("Dealer's hand: " + dealer.getHand());

		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.drawCard());
			System.out.println("Dealer hits: " + dealer.getHand());
		}

		if (dealer.getHandValue() > 21) {
			System.out.println("Dealer busts! You win!");
			return;
		}

		// Compare hands
		int playerScore = player.getHandValue();
		int dealerScore = dealer.getHandValue();

		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(0);
	}
}

class Card {
	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Player {
	private List<Card> hand;

	public Player() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		int aces = 0;

		for (Card card : hand) {
			int cardValue = card.getValue();
			value += cardValue;
			if (cardValue == 11) {
				aces++;
			}

			// Handle aces as 1 if bust
			while (value > 21 && aces > 0) {
				value -= 10;
				aces--;
			}
		}

		return value;
	}
}

class Dealer {
	private List<Card> hand;

	public Dealer() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getVisibleHand() {
		List<Card> visibleHand = new ArrayList<Card>();
		visibleHand.add(hand.get(0));
		visibleHand.add(new Card(Rank.HIDDEN, Suit.HIDDEN));
		return visibleHand;
	}

	public int getHandValue() {
		int value = 0;
		int aces = 0;

		for (Card card : hand) {
			int cardValue = card.getValue();
			value += cardValue;
			if (cardValue == 11) {
				aces++;
			}

			// Handle aces as 1 if bust
			while (value > 21 && aces > 0) {
				value -= 10;
				aces--;
			}
		}

		return value;
	}
}

enum Rank {
	ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10), HIDDEN(0);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	SPADES, HEARTS, DIAMONDS, CLUBS, HIDDEN
}