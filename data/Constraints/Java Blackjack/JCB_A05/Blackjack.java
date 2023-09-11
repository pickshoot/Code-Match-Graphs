package JCB_A05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create the deck of cards
		Deck deck = new Deck();

		// Shuffle the deck
		deck.shuffle();

		// Create the player and dealer hands
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

		// Deal two cards to the player and two cards to the dealer
		playerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());

		// Show the initial hands
		System.out.println("Your hand: " + playerHand.toString());
		System.out.println("Dealer's hand: " + dealerHand.getCard(0) + " [Hidden]");

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				playerHand.addCard(deck.drawCard());
				System.out.println("Your hand: " + playerHand.toString());
				if (playerHand.getScore() > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input.");
			}
		}

		// Dealer's turn
		while (dealerHand.getScore() < 17) {
			dealerHand.addCard(deck.drawCard());
		}
		System.out.println("Dealer's hand: " + dealerHand.toString());

		// Determine the winner
		int playerScore = playerHand.getScore();
		int dealerScore = dealerHand.getScore();
		if (playerScore > dealerScore || dealerScore > 21) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("Tie.");
		}
	}

}

class Card {
	private final String rank;
	private final String suit;
	private final int value;

	public Card(String rank, String suit, int value) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public int getValue() {
		return value;
	}
}

class Deck {
	private final List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				int value = i + 1;
				if (i >= 10) {
					value = 10;
				}
				if (i == 0) {
					value = 11;
				}
				Card card = new Card(ranks[i], suit, value);
				cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(cards.size() - 1);
	}
}

class Hand {
	private final List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : cards) {
			builder.append(card.toString());
			builder.append(", ");
		}
		return builder.toString();
	}

	public int getScore() {
		int score = 0;
		int aces = 0;
		for (Card card : cards) {
			int value = card.getValue();
			if (value == 11) {
				aces++;
			}
			score += value;
		}
		while (score > 21 && aces > 0) {
			score -= 10;
			aces--;
		}
		return score;
	}

	public Card getCard(int index) {
		return cards.get(index);
	}
}