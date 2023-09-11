package blackjackSA10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		// Initialize game variables
		Scanner sc = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();
		int playerScore = 0;
		int dealerScore = 0;

		// Deal the cards
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Show the initial hands
		System.out.println("Your hand: " + playerHand.get(0) + ", " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", hidden");

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? ");
			String input = sc.nextLine();
			if (input.equals("hit")) {
				Card card = deck.drawCard();
				playerHand.add(card);
				playerScore += card.getValue();
				System.out.println("Your hand: " + playerHand);
				if (playerScore > 21) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input, please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + dealerHand);
		while (dealerScore < 17) {
			Card card = deck.drawCard();
			dealerHand.add(card);
			dealerScore += card.getValue();
			System.out.println("Dealer hits: " + card);
			System.out.println("Dealer's hand: " + dealerHand);
			if (dealerScore > 21) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// Determine the winner
		playerScore = getHandValue(playerHand);
		dealerScore = getHandValue(dealerHand);
		System.out.println("Your hand: " + playerHand + " (" + playerScore + ")");
		System.out.println("Dealer's hand: " + dealerHand + " (" + dealerScore + ")");
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

	}

	public static int getHandValue(ArrayList<Card> hand) {
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

class Card {

	private final String rank;
	private final String suit;
	private final int value;

	public Card(String rank, String suit, int value) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
	}

	public String getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {

	private final ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				cards.add(new Card(ranks[i], suits[j], values[i]));
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