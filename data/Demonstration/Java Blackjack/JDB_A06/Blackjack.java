package JDB_A06;

//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerHand;
	private static ArrayList<Card> dealerHand;
	private static int playerScore;
	private static int dealerScore;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();

		playerHand = new ArrayList<Card>();
		dealerHand = new ArrayList<Card>();
		playerScore = 0;
		dealerScore = 0;

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
			playRound();
		}

		// Print a message indicating that the deck is empty
		System.out.println("Deck is empty. Thank you for playing and welcome back!");
	}

	// Define a method to play a round of the game
	private static void playRound() {
		System.out.println("\n------- Playing a game round");

		// Draw two cards for the player and two cards for the dealer
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Calculate the initial scores for the player and the dealer
		playerScore = calculateScore(playerHand);
		dealerScore = calculateScore(dealerHand);

		// Print the player's and dealer's initial hands
		System.out.println(
				"Your hand: " + playerHand.get(0) + " and " + playerHand.get(1) + " (score: " + playerScore + ")");
		System.out.println("Dealer's hand: " + dealerHand.get(0) + " and [hidden]");

		// Ask the player to hit or stand until they choose to stand or bust
		while (true) {
			System.out.print("Would you like to hit or stand? ");
			String input = new Scanner(System.in).nextLine();

			if (input.equalsIgnoreCase("hit")) {
				playerHand.add(deck.drawCard());
				playerScore = calculateScore(playerHand);
				System.out.println("Your hand: " + playerHand + " (score: " + playerScore + ")");

				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Reveal the dealer's hidden card and continue drawing until they have 17 or
		// more
		System.out.println("\nDealer's hand: " + dealerHand + " (score: " + dealerScore + ")");
		while (dealerScore < 17) {
			System.out.println("Dealer hits.");
			dealerHand.add(deck.drawCard());
			dealerScore = calculateScore(dealerHand);
			System.out.println("Dealer's hand: " + dealerHand + " (score: " + dealerScore + ")");
		}
		// Determine the winner
		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins! You lose.");
		} else {
			System.out.println("It's a tie!");
		}
		// Clear the player's and dealer's hands for the next round
		playerHand.clear();
		dealerHand.clear();
	}

	// Define a method to calculate the score for a hand of cards
	private static int calculateScore(ArrayList<Card> hand) {
		int score = 0;
		int numAces = 0;

		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}
			score += card.getValue();
		}

		// Convert aces from 11 to 1 if necessary
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}

		return score;
	}
}

//Define the Card class
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
		switch (rank) {
		case ACE:
			return 11;
		case KING:
		case QUEEN:
		case JACK:
		case TEN:
			return 10;
		default:
			return rank.ordinal() + 2;
		}
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

//Define the deck class 
class Deck {
	// Define the instance variables for the deck class
	private ArrayList<Card> cards;

	// Define the constructor for the deck class
	public Deck() {
		this.cards = new ArrayList<Card>();
		// Create a deck of 52 cards
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				this.cards.add(card);
			}
		}
	}

	// Define a method to shuffle the deck
	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	// Define a method to draw a card from the deck
	public Card drawCard() {
		if (this.cards.size() == 0) {
			return null;
		}
		Card card = this.cards.get(0);
		this.cards.remove(0);
		return card;
	}

	// Define a method to check if the deck is empty
	public boolean isEmpty() {
		return this.cards.size() == 0;
	}

}

//Define the Suit enum
enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

//Define the Rank enum
enum Rank {
	TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}
