package JDB_C05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private ArrayList<String> deck; // deck of cards
	private ArrayList<String> playerHand; // player's hand
	private ArrayList<String> dealerHand; // dealer's hand
	private int playerScore; // player's score
	private int dealerScore; // dealer's score

	public Blackjack() {
		deck = new ArrayList<String>();
		playerHand = new ArrayList<String>();
		dealerHand = new ArrayList<String>();
		playerScore = 0;
		dealerScore = 0;
		initializeDeck();
		shuffleDeck();
	}

	private void initializeDeck() {
		// create a standard deck of 52 cards
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}
	}

	private void shuffleDeck() {
		// shuffle the deck
		Collections.shuffle(deck);
	}

	private int getCardValue(String card) {
		// get the value of a card
		String[] parts = card.split(" ");
		String value = parts[0];
		if (value.equals("Ace")) {
			return 11;
		} else if (value.equals("King") || value.equals("Queen") || value.equals("Jack") || value.equals("10")) {
			return 10;
		} else {
			return Integer.parseInt(value);
		}
	}

	private void dealCards() {
		// deal two cards to the player and two cards to the dealer
		playerHand.add(deck.get(0));
		playerHand.add(deck.get(1));
		dealerHand.add(deck.get(2));
		dealerHand.add(deck.get(3));
		playerScore = getHandScore(playerHand);
		dealerScore = getHandScore(dealerHand);
		System.out.println("Player's hand: " + playerHand.get(0) + ", " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", <hidden card>");
	}

	private int getHandScore(ArrayList<String> hand) {
		// get the score of a hand
		int score = 0;
		int numAces = 0;
		for (String card : hand) {
			int value = getCardValue(card);
			score += value;
			if (value == 11) {
				numAces++;
			}
			while (score > 21 && numAces > 0) {
				score -= 10;
				numAces--;
			}
		}
		return score;
	}

	private void playerTurn() {
		// player's turn
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Player's turn. Your current score is " + playerScore);
			System.out.println("Do you want to hit or stand? (h/s): ");
			String input = scanner.nextLine();
			if (input.equals("h")) {
				playerHand.add(deck.get(0));
				deck.remove(0);
				playerScore = getHandScore(playerHand);
				System.out.println("Player's hand: " + playerHand);
				if (playerScore > 21) {
					System.out.println("Player busts. Dealer wins!");
					System.exit(0);
				}
			} else if (input.equals("s")) {
				System.out.println("Player stands.");
				break;
			} else {
				System.out.println("Invalid input. Please enter 'h' to hit or 's' to stand.");
			}
		}
	}

	private void dealerTurn() {
		// dealer's turn
		System.out.println("Dealer's turn. Dealer's hand: " + dealerHand);
		while (dealerScore < 17) {
			dealerHand.add(deck.get(0));
			deck.remove(0);
			dealerScore = getHandScore(dealerHand);
			System.out.println("Dealer hits. Dealer's hand: " + dealerHand);
			if (dealerScore > 21) {
				System.out.println("Dealer busts. Player wins!");
				System.exit(0);
			}
		}
		System.out.println("Dealer stands.");
	}

	private void compareHands() {
		// compare the player's hand and the dealer's hand
		System.out.println("Player's hand: " + playerHand + ". Player's score: " + playerScore);
		System.out.println("Dealer's hand: " + dealerHand + ". Dealer's score: " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		dealCards();
		playerTurn();
		dealerTurn();
		compareHands();
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}
