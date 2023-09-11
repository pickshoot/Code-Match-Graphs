package JCB_C08;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// initialize the deck of cards
		Deck deck = new Deck();
		deck.shuffle();

		// initialize the player's hand and deal two cards
		Hand playerHand = new Hand();
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());

		// initialize the dealer's hand and deal two cards
		Hand dealerHand = new Hand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());

		// display the initial hands
		System.out.println("Player's Hand: " + playerHand);
		System.out.println("Dealer's Hand: " + dealerHand.getFirstCard() + " [hidden]");

		// player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String answer = input.nextLine().toLowerCase();
			if (answer.equals("hit")) {
				Card card = deck.dealCard();
				playerHand.addCard(card);
				System.out.println("You drew a " + card);
				System.out.println("Your hand is now: " + playerHand);
				if (playerHand.isBusted()) {
					System.out.println("Busted! You lose.");
					return;
				}
			} else if (answer.equals("stand")) {
				System.out.println("You chose to stand.");
				break;
			} else {
				System.out.println("Invalid input, please enter 'hit' or 'stand'.");
			}
		}

		// dealer's turn
		System.out.println();
		System.out.println("Dealer's Hand: " + dealerHand);
		while (dealerHand.getScore() < 17) {
			Card card = deck.dealCard();
			dealerHand.addCard(card);
			System.out.println("Dealer drew a " + card);
			System.out.println("Dealer's hand is now: " + dealerHand);
			if (dealerHand.isBusted()) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// compare hands and determine the winner
		System.out.println();
		System.out.println("Player's Hand: " + playerHand);
		System.out.println("Dealer's Hand: " + dealerHand);
		if (playerHand.getScore() > dealerHand.getScore()) {
			System.out.println("You win!");
		} else if (playerHand.getScore() < dealerHand.getScore()) {
			System.out.println("You lose!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < 4; j++) {
				Suit suit = Suit.values()[j];
				Card card = new Card(i, suit);
				cards.add(card);
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

class Hand {
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		boolean hasAce = false;
		for (Card card : cards) {
			int value = card.getValue();
			if (value == 1) {
				hasAce = true;
			}
			score += Math.min(value, 10);
		}
		if (hasAce && score <= 11) {
			score += 10;
		}
		return score;
	}

	public boolean isBusted() {
		return getScore() > 21;
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card);
			sb.append(" ");
		}
		return sb.toString();
	}
}

class Card {
	private int value;
	private Suit suit;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString() {
		String faceValue;
		switch (value) {
		case 1:
			faceValue = "Ace";
			break;
		case 11:
			faceValue = "Jack";
			break;
		case 12:
			faceValue = "Queen";
			break;
		case 13:
			faceValue = "King";
			break;
		default:
			faceValue = Integer.toString(value);
			break;
		}
		return faceValue + " of " + suit;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}