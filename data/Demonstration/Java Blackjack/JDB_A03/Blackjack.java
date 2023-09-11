package JDB_A03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerHand;
	private static ArrayList<Card> dealerHand;
	private static int playerScore;
	private static int dealerScore;
	private static Scanner scanner;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		scanner = new Scanner(System.in);

		// Deal two cards to the player and the dealer
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Print the initial hands of the player and the dealer
		System.out.println("Player hand: " + playerHand.get(0) + " and " + playerHand.get(1));
		System.out.println("Dealer hand: " + dealerHand.get(0) + " and a hidden card");

		// Loop until the player chooses to stand or bust
		while (true) {
			// Print the player's current score and prompt for their move
			System.out.println("Your current score is: " + playerScore);
			System.out.print("Do you want to hit (h) or stand (s)? ");
			String input = scanner.nextLine();

			// If the player chooses to hit, draw a card and add it to their hand
			if (input.equals("h")) {
				Card card = deck.drawCard();
				playerHand.add(card);
				playerScore += card.getValue();
				System.out.println("You drew a " + card);
				if (playerScore > 21) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			}
			// If the player chooses to stand, break out of the loop
			else if (input.equals("s")) {
				break;
			}
			// If the player enters an invalid input, prompt them to try again
			else {
				System.out.println("Invalid input. Please enter 'h' or 's'.");
			}
		}

		// If the player has not busted, reveal the dealer's hidden card and draw cards
		// until their score is at least 17
		System.out.println("\nDealer reveals their hidden card: " + dealerHand.get(1));
		dealerScore = dealerHand.get(0).getValue() + dealerHand.get(1).getValue();
		while (dealerScore < 17) {
			Card card = deck.drawCard();
			dealerHand.add(card);
			dealerScore += card.getValue();
			System.out.println("Dealer draws a " + card);
			if (dealerScore > 21) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// Determine the winner of the game based on the scores
		System.out.println("\nPlayer score: " + playerScore);
		System.out.println("Dealer score: " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}
}

enum Suit {
	DIAMONDS, CLUBS, HEARTS, SPADES
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
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
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

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private ArrayList<Card> cards;
	private int currentCardIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		currentCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		currentCardIndex = 0;
	}

	public Card drawCard() {
		if (currentCardIndex >= cards.size()) {
			throw new RuntimeException("Deck is empty");
		}
		return cards.get(currentCardIndex++);
	}
}