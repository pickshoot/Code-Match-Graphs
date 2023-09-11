package JCB_B09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Create the deck
		Deck deck = new Deck();
		deck.shuffle();

		// Create the player and dealer
		Player player = new Player();
		Dealer dealer = new Dealer();

		// Deal the initial cards
		player.addCard(deck.drawCard());
		dealer.addCard(deck.drawCard());
		player.addCard(deck.drawCard());
		dealer.addCard(deck.drawCard());

		// Show the initial hands
		System.out.println("Player's hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getHand());

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("hit")) {
				Card card = deck.drawCard();
				player.addCard(card);
				System.out.println("Player's hand: " + player.getHand());

				if (player.getHandValue() > 21) {
					System.out.println("Player busts! Dealer wins.");
					return;
				}
			} else if (choice.equals("stand")) {
				break;
			}
		}

		// Dealer's turn
		while (dealer.getHandValue() < 17) {
			Card card = deck.drawCard();
			dealer.addCard(card);
			System.out.println("Dealer's hand: " + dealer.getHand());

			if (dealer.getHandValue() > 21) {
				System.out.println("Dealer busts! Player wins.");
				return;
			}
		}

		// Determine the winner
		int playerHandValue = player.getHandValue();
		int dealerHandValue = dealer.getHandValue();

		if (playerHandValue > dealerHandValue) {
			System.out.println("Player wins!");
		} else if (dealerHandValue > playerHandValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

class Deck {
	private List<Card> cards;
	private int currentIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		currentIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		currentIndex = 0;
	}

	public Card drawCard() {
		Card card = cards.get(currentIndex);
		currentIndex++;
		return card;
	}
}

class Player {
	private List<Card> hand;

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
		boolean hasAce = false;
		for (Card card : hand) {
			value += card.getRank().getValue();
			if (card.getRank() == Rank.ACE) {
				hasAce = true;
			}
		}
		if (hasAce && value + 10 <= 21) {
			value += 10;
		}
		return value;
	}
}

class Dealer {
	private List<Card> hand;

	public Dealer() {
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
		boolean hasAce = false;
		for (Card card : hand) {
			value += card.getRank().getValue();
			if (card.getRank() == Rank.ACE) {
				hasAce = true;
			}
		}
		if (hasAce && value + 10 <= 21) {
			value += 10;
		}
		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
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

	public String toString() {
		return rank + " of " + suit;
	}
}