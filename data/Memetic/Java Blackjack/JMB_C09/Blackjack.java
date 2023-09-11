package C9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Card> deck = createDeck();
		Collections.shuffle(deck);

		ArrayList<Card> dealerHand = new ArrayList<Card>();
		ArrayList<Card> playerHand = new ArrayList<Card>();

		dealerHand.add(deck.get(0));
		playerHand.add(deck.get(1));
		dealerHand.add(deck.get(2));
		playerHand.add(deck.get(3));

		boolean playerBust = false;
		boolean dealerBust = false;
		boolean gameOver = false;

		while (!gameOver) {
			System.out.println("Dealer hand: [" + dealerHand.get(0) + ", ???]");
			System.out.println("Your hand: " + playerHand);

			// Player's turn
			System.out.print("Hit or stand? ");
			String response = input.nextLine().toLowerCase();
			if (response.equals("hit")) {
				playerHand.add(deck.get(4));
				deck.remove(4);
				System.out.println("You draw a " + playerHand.get(playerHand.size() - 1) + ".");
				if (getHandValue(playerHand) > 21) {
					System.out.println("You bust!");
					playerBust = true;
					gameOver = true;
				}
			} else {
				System.out.println("You stand with a hand value of " + getHandValue(playerHand) + ".");
				gameOver = true;
			}

			// Dealer's turn
			if (!gameOver) {
				while (getHandValue(dealerHand) < 17) {
					dealerHand.add(deck.get(4));
					deck.remove(4);
					System.out.println("Dealer draws a " + dealerHand.get(dealerHand.size() - 1) + ".");
					if (getHandValue(dealerHand) > 21) {
						System.out.println("Dealer busts!");
						dealerBust = true;
						gameOver = true;
					}
				}
				if (!dealerBust) {
					System.out.println("Dealer stands with a hand value of " + getHandValue(dealerHand) + ".");
					gameOver = true;
				}
			}
		}

		// Determine winner
		int playerScore = getHandValue(playerHand);
		int dealerScore = getHandValue(dealerHand);
		if (playerBust || (dealerScore <= 21 && dealerScore > playerScore)) {
			System.out.println("Dealer wins!");
		} else if (dealerBust || playerScore > dealerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private static ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(new Card(rank, suit));
			}
		}

		return deck;
	}

	private static int getHandValue(ArrayList<Card> hand) {
		int value = 0;
		int aces = 0;

		for (Card card : hand) {
			if (card.getRank().equals("Ace")) {
				aces++;
			} else if (card.getRank().equals("King") || card.getRank().equals("Queen")
					|| card.getRank().equals("Jack")) {
				value += 10;
			} else {
				value += Integer.parseInt(card.getRank());
			}
		}

		for (int i = 0; i < aces; i++) {
			if (value + 11 > 21) {
				value += 1;
			} else {
				value += 11;
			}
		}

		return value;
	}
}

class Card {
	private String rank;
	private String suit;

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

	public String toString() {
		return rank + " of " + suit;
	}
}