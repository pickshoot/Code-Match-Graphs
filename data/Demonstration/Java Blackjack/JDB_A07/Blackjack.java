package JDB_A07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerCards;
	private static ArrayList<Card> dealerCards;
	private static int playerScore;
	private static int dealerScore;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();

		System.out.println("Welcome to Blackjack game!");
		Scanner scanner = new Scanner(System.in);

		// Loop until the deck is empty or the user quits
		while (!deck.isEmpty()) {
			// Prompt the user to play again or quit
			System.out.print("\n=========> Press ENTER to play again or “q” to quit: ");

			// Read the user input
			String input = scanner.nextLine();

			// If the user enters "q", break out of the loop
			if (input.equals("q")) {
				break;
			}
			// Otherwise, play another round
			play();
		}

		// Print a message indicating that the deck is empty
		System.out.println("Deck is empty. Thank you for playing and welcome back!");
	}

	// Define a method to play a round of the game
	private static void play() {
		System.out.println("\n------- Playing a game round");
		// Deal two cards to the player and two to the dealer
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		playerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());
		playerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());
		Scanner scanner = new Scanner(System.in);
		// Print the player's cards and the value of the first dealer's card
		System.out.println("Your cards: ");
		for (Card card : playerCards) {
			System.out.println(card);
			playerScore += card.getValue();
		}
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer's cards: ");
		System.out.println(dealerCards.get(0));
		dealerScore = dealerCards.get(0).getValue();

		// Ask the player if they want to hit or stand
		boolean hit = true;
		while (hit && playerScore <= 21) {
			System.out.print("Do you want to hit or stand? (h/s): ");
			String input = scanner.nextLine();
			if (input.equals("h")) {
				playerCards.add(deck.drawCard());
				Card lastCard = playerCards.get(playerCards.size() - 1);
				System.out.println("Your new card: " + lastCard);
				playerScore += lastCard.getValue();
				System.out.println("Your score: " + playerScore);
			} else {
				hit = false;
			}
		}

		// Dealer's turn
		while (dealerScore < 17 && playerScore <= 21) {
			dealerCards.add(deck.drawCard());
			Card lastCard = dealerCards.get(dealerCards.size() - 1);
			dealerScore += lastCard.getValue();
			System.out.println("Dealer's new card: " + lastCard);
		}

		// Determine the winner
		if (playerScore > 21) {
			System.out.println("You busted. Dealer wins.");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busted. You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

enum Suit {
	DIAMONDS, CLUBS, HEARTS, SPADES
}

class Card {
	private Suit suit;
	private int value;

	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		String strValue;
		if (value == 1) {
			strValue = "Ace";
		} else if (value == 11) {
			strValue = "Jack";
		} else if (value == 12) {
			strValue = "Queen";
		} else if (value == 13) {
			strValue = "King";
		} else {
			strValue = Integer.toString(value);
		}
		return strValue + " of " + suit;
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (int i = 1; i <= 13; i++) {
				cards.add(new Card(suit, i));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(0);
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}
}