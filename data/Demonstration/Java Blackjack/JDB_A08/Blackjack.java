package JDB_A08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static Player player;
	private static Dealer dealer;

	public static void main(String[] args) {
		// Initialize the game
		deck = new Deck();
		player = new Player();
		dealer = new Dealer();

		// Shuffle the deck
		deck.shuffle();

		// Deal the initial two cards to the player and dealer
		player.addCardToHand(deck.drawCard());
		dealer.addCardToHand(deck.drawCard());
		player.addCardToHand(deck.drawCard());
		dealer.addCardToHand(deck.drawCard());

		// Show the player's hand and the dealer's face-up card
		System.out.println("Player's hand: " + player.getHand() + " (" + player.getHandValue() + ")");
		System.out.println("Dealer's face-up card: " + dealer.getFaceUpCard());

		// Prompt the player to hit or stand until they stand, bust, or get a blackjack
		while (true) {
			System.out.print("\nDo you want to hit (h) or stand (s)? ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();

			if (input.equals("h")) {
				player.addCardToHand(deck.drawCard());
				System.out.println("Player's hand: " + player.getHand() + " (" + player.getHandValue() + ")");
				if (player.getHandValue() > 21) {
					System.out.println("Player busts! Dealer wins.");
					return;
				} else if (player.getHandValue() == 21) {
					System.out.println("Blackjack! Player wins!");
					return;
				}
			} else if (input.equals("s")) {
				break;
			}
		}

		// Dealer's turn
		System.out.println("\nDealer's turn:");
		System.out.println("Dealer's hand: " + dealer.getHand() + " (" + dealer.getHandValue() + ")");
		while (dealer.getHandValue() < 17) {
			dealer.addCardToHand(deck.drawCard());
			System.out.println("Dealer hits: " + dealer.getHand() + " (" + dealer.getHandValue() + ")");
			if (dealer.getHandValue() > 21) {
				System.out.println("Dealer busts! Player wins.");
				return;
			}
		}

		// Determine the winner
		if (player.getHandValue() > dealer.getHandValue()) {
			System.out.println("Player wins!");
		} else if (player.getHandValue() < dealer.getHandValue()) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

class Player {
	private ArrayList<Card> hand;

	public Player() {
		hand = new ArrayList<>();
	}

	public void addCardToHand(Card card) {
		hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		boolean hasAce = false;

		for (Card card : hand) {
			int cardValue = card.getValue();
			if (cardValue == 1) {
				hasAce = true;
			}
			value += cardValue;
		}

		if (hasAce && value + 10 <= 21) {
			value += 10;
		}

		return value;
	}
}

class Dealer {
	private ArrayList<Card> hand;

	public Dealer() {
		hand = new ArrayList<>();
	}

	public void addCardToHand(Card card) {
		hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		boolean hasAce = false;

		for (Card card : hand) {
			int cardValue = card.getValue();
			if (cardValue == 1) {
				hasAce = true;
			}
			value += cardValue;
		}

		if (hasAce && value + 10 <= 21) {
			value += 10;
		}

		return value;
	}

	public Card getFaceUpCard() {
		return hand.get(0);
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
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

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		switch (rank) {
		case ACE:
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		default:
			return 10;
		}
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
	ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}