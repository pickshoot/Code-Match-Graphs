package JSB_C10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Set up deck
		ArrayList<Card> deck = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
		Collections.shuffle(deck);

		// Set up player and dealer
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		playerHand.addCard(deck.remove(0));
		dealerHand.addCard(deck.remove(0));
		playerHand.addCard(deck.remove(0));
		dealerHand.addCard(deck.remove(0));

		// Play game
		boolean playerBusted = false;
		boolean dealerBusted = false;
		while (true) {
			// Show hands
			System.out.println("Your hand: " + playerHand.toString());
			System.out.println("Dealer's hand: " + dealerHand.toString());

			// Player's turn
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerHand.addCard(deck.remove(0));
				if (playerHand.getScore() > 21) {
					System.out.println("You busted!");
					playerBusted = true;
					break;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input, please try again.");
			}

			// Dealer's turn
			if (dealerHand.getScore() < 17) {
				dealerHand.addCard(deck.remove(0));
				if (dealerHand.getScore() > 21) {
					System.out.println("Dealer busted!");
					dealerBusted = true;
					break;
				}
			}
		}

		// Show final hands
		System.out.println("Your hand: " + playerHand.toString());
		System.out.println("Dealer's hand: " + dealerHand.toString());

		// Determine winner
		if (playerBusted) {
			System.out.println("You lose!");
		} else if (dealerBusted) {
			System.out.println("You win!");
		} else if (playerHand.getScore() > dealerHand.getScore()) {
			System.out.println("You win!");
		} else if (playerHand.getScore() < dealerHand.getScore()) {
			System.out.println("You lose!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private final int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Card {
	private final Suit suit;
	private final Rank rank;

	Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Hand {
	private final ArrayList<Card> cards;

	Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		int aces = 0;
		for (Card card : cards) {
			score += card.getValue();
			if (card.getRank() == Rank.ACE) {
				aces++;
			}
		}
		while (aces > 0 && score + 10 <= 21) {
			score += 10;
			aces--;
		}
		return score;
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