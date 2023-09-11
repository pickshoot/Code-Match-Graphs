package JSB_C08;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");
		System.out.println("What's your name?");
		String playerName = input.nextLine();
		System.out.println("Hello, " + playerName + "!");
		System.out.println("Let's start playing...");
		System.out.println();

		Deck deck = new Deck();
		deck.shuffle();

		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

		playerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());

		System.out.println("Your hand: " + playerHand.toString());
		System.out.println("Dealer's hand: " + dealerHand.getCard(0));
		System.out.println();

		boolean playerTurn = true;
		boolean gameOver = false;

		while (!gameOver) {
			if (playerTurn) {
				System.out.println("What would you like to do?");
				System.out.println("1. Hit");
				System.out.println("2. Stand");
				int choice = input.nextInt();
				System.out.println();
				if (choice == 1) {
					playerHand.addCard(deck.dealCard());
					System.out.println("Your hand: " + playerHand.toString());
					if (playerHand.getBlackjackValue() > 21) {
						System.out.println("Bust! You lose.");
						gameOver = true;
					}
				} else {
					playerTurn = false;
				}
			} else {
				while (dealerHand.getBlackjackValue() <= 16) {
					dealerHand.addCard(deck.dealCard());
				}
				System.out.println("Dealer's hand: " + dealerHand.toString());
				if (dealerHand.getBlackjackValue() > 21) {
					System.out.println("Dealer busts! You win.");
					gameOver = true;
				} else if (dealerHand.getBlackjackValue() >= playerHand.getBlackjackValue()) {
					System.out.println("Dealer wins.");
					gameOver = true;
				} else {
					System.out.println("You win!");
					gameOver = true;
				}
			}
		}

		input.close();
	}

}

class Card {

	private String suit;
	private String rank;
	private int value;

	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return rank + " of " + suit;
	}

}

class Deck {

	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				cards.add(new Card(suits[i], ranks[j], values[j]));
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

	public int getBlackjackValue() {
		int value = 0;
		int aceCount = 0;
		for (Card card : cards) {
			value += card.getValue();
			if (card.getValue() == 11) {
				aceCount++;
			}
		}
		while (value > 21 && aceCount > 0) {
			value -= 10;
			aceCount--;
		}
		return value;
	}

	public Card getCard(int index) {
		return cards.get(index);
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