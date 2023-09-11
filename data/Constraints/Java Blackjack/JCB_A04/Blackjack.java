/*package JCB_A04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		Player player = new Player();
		Dealer dealer = new Dealer();

		// Deal the initial cards
		for (int i = 0; i < 2; i++) {
			player.addCard(deck.drawCard());
			dealer.addCard(deck.drawCard());
		}

		// Player's turn
		while (true) {
			System.out.println("Your cards: " + player.getHand());
			System.out.println("Dealer's card: " + dealer.getVisibleCard());
			System.out.println("What do you want to do? (hit/stand)");
			String choice = scanner.nextLine();
			if (choice.equals("hit")) {
				player.addCard(deck.drawCard());
				if (player.getHandValue() > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else {
				break;
			}
		}

		// Dealer's turn
		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.drawCard());
		}

		// Determine the winner
		int playerValue = player.getHandValue();
		int dealerValue = dealer.getHandValue();
		System.out.println("Your cards: " + player.getHand() + " (" + playerValue + ")");
		System.out.println("Dealer's cards: " + dealer.getHand() + " (" + dealerValue + ")");
		if (dealerValue > 21 || playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (playerValue == dealerValue) {
			System.out.println("Push!");
		} else {
			System.out.println("You lose.");
		}
	}
}

class Card {
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
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

class Hand {
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getHandValue() {
		int value = 0;
		boolean hasAce = false;
		for (Card card : cards) {
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

	public ArrayList<Card> getCards() {
		return cards;
	}

	public String getVisibleCard() {
		return cards.get(0).toString();
	}

	public String toString() {
		return cards.toString();
	}
}

class Player extends Hand {
}

class Dealer extends Hand {
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
}*/