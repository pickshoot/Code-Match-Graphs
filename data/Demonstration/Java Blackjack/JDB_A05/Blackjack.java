package JDB_A05;

//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerCards;
	private static ArrayList<Card> dealerCards;
	private static int playerScore;
	private static int dealerScore;
	private static Scanner scanner;

	public static void main(String[] args) {
		// Initialize the deck and player and dealer hands
		deck = new Deck();
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		playerScore = 0;
		dealerScore = 0;
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack game!");

		// Play a game round
		play();

		// Print a message indicating that the game is over
		System.out.println("Game over. Thank you for playing and welcome back!");
	}

	// Define a method to play a round of the game
	private static void play() {
		// Shuffle the deck and draw the initial two cards for the player and dealer
		deck.shuffle();
		playerCards.add(deck.drawCard());
		playerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());

		// Print the initial hands for the player and dealer
		System.out.println("\nPlayer's hand: " + playerCards.get(0) + ", " + playerCards.get(1));
		System.out.println("Dealer's hand: " + dealerCards.get(0) + ", ?");

		// Calculate the score for the player and dealer
		playerScore = calculateScore(playerCards);
		dealerScore = calculateScore(dealerCards);

		// Check if the player or dealer has blackjack
		if (playerScore == 21) {
			System.out.println("Congratulations! You have Blackjack.");
			return;
		}
		if (dealerScore == 21) {
			System.out.println("Dealer has Blackjack. You lose.");
			return;
		}

		// Prompt the player to hit or stand until they stand or bust
		while (true) {
			System.out.print("\nHit or stand? (h/s): ");
			String input = scanner.nextLine();

			if (input.equals("h")) {
				playerCards.add(deck.drawCard());
				System.out.println("Player's hand: " + playerCards);
				playerScore = calculateScore(playerCards);

				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				} else if (playerScore == 21) {
					System.out.println("Congratulations! You have Blackjack.");
					return;
				}
			} else if (input.equals("s")) {
				break;
			}
		}

		// Dealer's turn to hit or stand
		while (dealerScore < 17) {
			dealerCards.add(deck.drawCard());
			System.out.println("Dealer's hand: " + dealerCards);
			dealerScore = calculateScore(dealerCards);
		}

		// Determine the winner of the game
		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins. You lose.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	// Define a method to calculate the score of a hand of cards
	private static int calculateScore(ArrayList<Card> cards) {
		int score = 0;
		int aceCount = 0;

		for (Card card : cards) {
			int value = card.getValue();
			if (value == 1) { // Ace
				score += 11;
				aceCount++;
			} else if (value >= 10) { // Face cards
				score += 10;
			} else { // Number cards
				score += value;
			}
		}

		// Adjust the score for aces
		while (score > 21 && aceCount > 0) {
			score -= 10;
			aceCount--;
		}

		return score;
	}
}

//Define the Card and Deck classes
class Card {
	private String suit;
	private String rank;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getValue() {
		switch (rank) {
		case "Ace":
			return 1;
		case "Two":
			return 2;
		case "Three":
			return 3;
		case "Four":
			return 4;
		case "Five":
			return 5;
		case "Six":
			return 6;
		case "Seven":
			return 7;
		case "Eight":
			return 8;
		case "Nine":
			return 9;
		default:
			return 10;
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();

		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
				"Queen", "King" };

		for (String suit : suits) {
			for (String rank : ranks) {
				cards.add(new Card(suit, rank));
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