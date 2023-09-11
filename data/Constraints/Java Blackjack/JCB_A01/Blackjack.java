package JCB_A01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		Player player = new Player();
		Player dealer = new Player();

		// Deal initial cards
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());

		// Show player's hand
		System.out.println("Your hand: " + player.getHand());

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String answer = input.nextLine().toLowerCase();
			if (answer.equals("hit")) {
				Card card = deck.dealCard();
				player.addCard(card);
				System.out.println("You drew a " + card + ".");
				System.out.println("Your hand: " + player.getHand());
				if (player.isBust()) {
					System.out.println("You bust! Dealer wins.");
					return;
				}
			} else if (answer.equals("stand")) {
				System.out.println("Your hand: " + player.getHand());
				break;
			} else {
				System.out.println("Invalid answer. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + dealer.getHand());
		while (dealer.getHandValue() < 17) {
			Card card = deck.dealCard();
			dealer.addCard(card);
			System.out.println("Dealer drew a " + card + ".");
			System.out.println("Dealer's hand: " + dealer.getHand());
			if (dealer.isBust()) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare hands
		int playerHandValue = player.getHandValue();
		int dealerHandValue = dealer.getHandValue();
		if (playerHandValue > dealerHandValue) {
			System.out.println("You win!");
		} else if (dealerHandValue > playerHandValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

class Card {
	private final String suit;
	private final String rank;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		switch (rank) {
		case "Ace":
			return 11;
		case "King":
		case "Queen":
		case "Jack":
			return 10;
		default:
			return Integer.parseInt(rank);
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private final ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				cards.add(new Card(suit, rank));
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

class Player {
	private final ArrayList<Card> hand;

	public Player() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		int aceCount = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getRank().equals("Ace")) {
				aceCount++;
			}
		}
		while (value > 21 && aceCount > 0) {
			value -= 10;
			aceCount--;
		}
		return value;
	}

	public boolean isBust() {
		return getHandValue() > 21;
	}
}