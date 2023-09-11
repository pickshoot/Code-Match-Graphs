package JDB_A04;

//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerHand;
	private static ArrayList<Card> dealerHand;
	private static Scanner scanner;

	public static void main(String[] args) {
		deck = new Deck();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");

		// Loop until the deck is empty or the user quits
		while (!deck.isEmpty()) {
			// Prompt the user to play again or quit
			System.out.print("\n=========> Press ENTER to play another hand or “q” to quit: ");

			// Read the user input
			String input = scanner.nextLine();

			// If the user enters "q", break out of the loop
			if (input.equals("q")) {
				break;
			}
			// Otherwise, play another hand
			playHand();
		}

		// Print a message indicating that the deck is empty
		System.out.println("Deck is empty. Thank you for playing and welcome back!");
	}

	// Define a method to play a hand of Blackjack
	private static void playHand() {
		System.out.println("\n------- Playing a hand of Blackjack");

		// Draw two cards for the player and two cards for the dealer
		playerHand.clear();
		dealerHand.clear();
		playerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Print the initial hands
		System.out.println("Dealer's Hand: " + dealerHand.get(0) + " and one face down card");
		System.out.println("Player's Hand: " + playerHand.get(0) + " and " + playerHand.get(1));

		// Check for Blackjack
		if (getHandValue(playerHand) == 21) {
			System.out.println("Blackjack! You win!");
			return;
		} else if (getHandValue(dealerHand) == 21) {
			System.out.println("Dealer has Blackjack. You lose.");
			return;
		}

		// Player's turn
		while (true) {
			System.out.print("Press \"h\" to hit or \"s\" to stand: ");
			String input = scanner.nextLine();

			if (input.equals("h")) {
				playerHand.add(deck.drawCard());
				System.out.println("Player's Hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input.");
			}
		}

		// Dealer's turn
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.drawCard());
			System.out.println("Dealer's Hand: " + dealerHand);
			if (getHandValue(dealerHand) > 21) {
				System.out.println("Dealer busts! You win!");
				return;
			}
		}

		// Determine the winner
		int playerValue = getHandValue(playerHand);
		int dealerValue = getHandValue(dealerHand);
		if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	// Define a method to get the value of a hand
	private static int getHandValue(ArrayList<Card> hand) {
		int value = 0;
		int aceCount = 0;
		for (Card card : hand) {
			int rankValue = card.getRank().getValue();
			if (rankValue == 1) {
				aceCount++;
			} else if (rankValue >= 10) {
				value += 10;
			} else {
				value += rankValue;
			}
		}

		// Handle aces
		for (int i = 0; i < aceCount; i++) {
			if (value + 11 <= 21) {
				value += 11;
			} else {
				value += 1;
			}
		}

		return value;
	}
}

//Define a class for a card
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

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

//Define a class for a deck of cards
class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		Collections.shuffle(cards);
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card drawCard() {
		if (isEmpty()) {
			throw new IllegalStateException("Cannot draw from empty deck");
		}
		return cards.remove(cards.size() - 1);
	}
}

//Define an enum for the suit of a card
enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

//Define an enum for the suit of a card
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