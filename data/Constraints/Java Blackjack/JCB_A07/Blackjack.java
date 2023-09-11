package JCB_A07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();

		Player player = new Player();
		Dealer dealer = new Dealer();

		// Deal initial cards
		player.addCard(deck.drawCard());
		dealer.addCard(deck.drawCard());
		player.addCard(deck.drawCard());
		dealer.addCard(deck.drawCard());

		// Print initial hands
		System.out.println("Player's hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getVisibleHand());

		// Player's turn
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				player.addCard(deck.drawCard());
				System.out.println("Player's hand: " + player.getHand());
				if (player.getHandValue() > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input.");
			}
		}

		// Dealer's turn
		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.drawCard());
		}
		System.out.println("Dealer's hand: " + dealer.getHand());

		// Determine winner
		int playerHandValue = player.getHandValue();
		int dealerHandValue = dealer.getHandValue();
		if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
			System.out.println("You win!");
		} else if (dealerHandValue > playerHandValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

class Deck {

	private List<Card> cards;

	public Deck() {
		this.cards = new ArrayList<>();
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
		if (rank == Rank.ACE) {
			return 11;
		} else if (rank == Rank.KING || rank == Rank.QUEEN || rank == Rank.JACK) {
			return 10;
		} else {
			return rank.getValue();
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Player {

	private List<Card> hand;

	public Player() {
		this.hand = new ArrayList<>();
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
			if (card.getValue() == 11) {
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

class Dealer {
	private List<Card> hand;

	public Dealer() {
		this.hand = new ArrayList<>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public List<Card> getVisibleHand() {
		List<Card> visibleHand = new ArrayList<>();
		visibleHand.add(hand.get(0));
		visibleHand.add(new Card(Rank.UNKNOWN, Suit.UNKNOWN));
		return visibleHand;
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getValue() == 11) {
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

enum Rank {
	ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10), UNKNOWN(0);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES, UNKNOWN;
}