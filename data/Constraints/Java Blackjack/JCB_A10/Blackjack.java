package JCB_A10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create and shuffle deck
		Deck deck = new Deck();
		deck.shuffle();

		// Create player and dealer
		Player player = new Player();
		Player dealer = new Player();

		// Deal initial cards
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());

		// Player's turn
		boolean playerDone = false;
		while (!playerDone) {
			System.out.println("Your hand: " + player.getHand());
			System.out.println("Dealer's hand: " + dealer.getHand().get(0));
			System.out.print("Would you like to hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				player.addCard(deck.dealCard());
				if (player.getHandValue() > 21) {
					System.out.println("Bust! Your hand is worth " + player.getHandValue() + ".");
					playerDone = true;
				}
			} else if (choice.equals("stand")) {
				playerDone = true;
			}
		}

		// Dealer's turn
		boolean dealerDone = false;
		while (!dealerDone) {
			if (dealer.getHandValue() < 17) {
				dealer.addCard(deck.dealCard());
			} else {
				dealerDone = true;
			}
		}

		// Determine winner
		int playerValue = player.getHandValue();
		int dealerValue = dealer.getHandValue();
		System.out.println("Your hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getHand());
		if (playerValue > 21) {
			System.out.println("You bust! Your hand is worth " + playerValue + ".");
		} else if (dealerValue > 21) {
			System.out.println("Dealer busts! Dealer's hand is worth " + dealerValue + ".");
		} else if (playerValue == dealerValue) {
			System.out.println("Push! Both hands are worth " + playerValue + ".");
		} else if (playerValue > dealerValue) {
			System.out.println("You win! Your hand is worth " + playerValue + ", and the dealer's hand is worth "
					+ dealerValue + ".");
		} else {
			System.out.println("Dealer wins! Your hand is worth " + playerValue + ", and the dealer's hand is worth "
					+ dealerValue + ".");
		}
	}
}

class Card {
	private final String rank;
	private final String suit;

	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private final List<Card> cards;

	public Deck() {
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		cards = new ArrayList<>();
		for (String suit : suits) {
			for (String rank : ranks) {
				cards.add(new Card(rank, suit));
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
	private final List<Card> hand;

	public Player() {
		hand = new ArrayList<>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}
}