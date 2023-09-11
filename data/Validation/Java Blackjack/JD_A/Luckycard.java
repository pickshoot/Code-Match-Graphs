
//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Luckycard {
	private static Deck deck;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();

		System.out.println("Welcome to LuckyCard game!");
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
		// Draw three cards from the deck to play a game round
		Card card1 = deck.drawCard();
		Card card2 = deck.drawCard();
		Card card3 = deck.drawCard();

		// Print the value and bonus of each card drawn
		System.out.println("Card 1: " + card1 + " → Value = " + (card1.getValue() + card1.getBonus()));
		System.out.println("Card 2: " + card2 + " → Value = " + (card2.getValue() + card2.getBonus()));
		System.out.println("Card 3: " + card3 + " → Value = " + (card3.getValue() + card3.getBonus()));

		// Determine if the player has won or lost the game round based on the values of
		// the cards.
		int value1 = card1.getValue() + card1.getBonus();
		int value2 = card2.getValue() + card2.getBonus();
		int value3 = card3.getValue() + card3.getBonus();
		if (value3 > value1 && value3 < value2 || value3 < value1 && value3 > value2) {
			System.out.println("\nYou win!");
		} else {
			System.out.println("\nYou lose.");
		}
	}
}

enum Suit {
	DIAMONDS, CLUBS, HEARTS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12),
	KING(13);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Deck {
	private ArrayList<Card> cards;

	// Create a new deck of cards and add each card to it
	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	// Shuffle the deck to randomize the order of the cards
	public void shuffle() {
		Collections.shuffle(cards);
	}

	// Check if the deck is empty to determine if the game should continue or end
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card drawCard() {
		if (isEmpty()) {
			return null;
		}
		return cards.remove(0);
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

	public int getBonus() {
		if (suit == Suit.DIAMONDS) {
			return 4;
		} else if (suit == Suit.CLUBS) {
			return 6;
		} else if (suit == Suit.HEARTS) {
			return 8;
		} else {
			return 10;
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}

}
