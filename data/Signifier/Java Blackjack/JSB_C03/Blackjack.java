package JSB_C03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		Player player = new Player();
		Dealer dealer = new Dealer();

		// Deal the initial cards
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());

		System.out.println("Welcome to Blackjack!");
		System.out.println("Your hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getHand());

		// Player's turn
		while (true) {
			System.out.println("Would you like to hit or stand? (Enter H or S)");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("H")) {
				player.addCard(deck.deal());
				System.out.println("Your hand: " + player.getHand());
				if (player.isBusted()) {
					System.out.println("Busted! Dealer wins.");
					return;
				}
			} else if (choice.equalsIgnoreCase("S")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter H or S.");
			}
		}

		// Dealer's turn
		while (dealer.getScore() < 17) {
			dealer.addCard(deck.deal());
			System.out.println("Dealer's hand: " + dealer.getHand());
			if (dealer.isBusted()) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// Determine the winner
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

class Deck {
	private ArrayList<Card> cards;
	private int nextCardIndex;

	public Deck() {
		cards = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < 4; j++) {
				cards.add(new Card(i));
			}
		}
		nextCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		nextCardIndex = 0;
	}

	public Card deal() {
		Card card = cards.get(nextCardIndex);
		nextCardIndex++;
		return card;
	}
}

class Card {
	private int value;

	public Card(int value) {
		this.value = value;
	}

	public int getValue() {
		if (value == 1) {
			return 11;
		} else if (value >= 10) {
			return 10;
		} else {
			return value;
		}
	}

	public String toString() {
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] names = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
				"Queen", "King" };
		return names[value - 1] + " of " + suits[(value - 1) / 4];

	}
}

class Hand {
	protected ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		int numAces = 0;
		for (Card card : cards) {
			int value = card.getValue();
			if (value == 11) {
				numAces++;
			}
			score += value;
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	public boolean isBusted() {
		return getScore() > 21;
	}
}

class Player extends Hand {

}

class Dealer extends Hand {
	public String getHand() {
		StringBuilder sb = new StringBuilder();
		sb.append(cards.get(0).toString());
		sb.append(", ");
		sb.append("[hidden]");
		return sb.toString();
	}
}