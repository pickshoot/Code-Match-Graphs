package JDB_C04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private List<Card> deck; // list of cards in the deck
	private List<Card> playerHand; // list of cards in the player's hand
	private List<Card> dealerHand; // list of cards in the dealer's hand
	private int playerScore; // current score of the player's hand
	private int dealerScore; // current score of the dealer's hand

	public Blackjack() {
		deck = new ArrayList<>();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		initializeDeck();
	}

	private void initializeDeck() {
		// initialize the deck with 52 cards
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Value value : Card.Value.values()) {
				deck.add(new Card(value, suit));
			}
		}
		// shuffle the deck
		Collections.shuffle(deck);
	}

	private void dealInitialCards() {
		// deal two cards to the player and two cards to the dealer
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		// calculate the initial scores
		calculateScore(playerHand, playerScore);
		calculateScore(dealerHand, dealerScore);
	}

	private void printHands(boolean showDealerCard) {
		// print out the player's hand
		System.out.println("Player's hand:");
		for (Card card : playerHand) {
			System.out.println(card);
		}
		System.out.println("Player's score: " + playerScore);
		System.out.println();
		// print out the dealer's hand
		System.out.println("Dealer's hand:");
		for (int i = 0; i < dealerHand.size(); i++) {
			if (i == 0 && !showDealerCard) {
				System.out.println("HIDDEN");
			} else {
				System.out.println(dealerHand.get(i));
			}
		}
		if (!showDealerCard) {
			System.out.println("Dealer's score: HIDDEN");
		} else {
			System.out.println("Dealer's score: " + dealerScore);
		}
		System.out.println();
	}

	private void playerTurn() {
		Scanner scanner = new Scanner(System.in);
		// keep asking the player if they want to hit or stand
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				// player wants to hit, deal a new card
				playerHand.add(deck.remove(0));
				calculateScore(playerHand, playerScore);
				// check if player busts
				if (playerScore > 21) {
					System.out.println("You bust! Dealer wins.");
					return;
				}
				// print out the hands
				printHands(false);
			} else if (input.equals("stand")) {
				// player wants to stand, turn ends
				System.out.println("Player stands.");
				return;
			} else {
				// invalid input, ask again
				System.out.println("Invalid input.");
			}
		}
	}

	private void dealerTurn() {
		// reveal the dealer's hand
		printHands(true);
		// dealer hits until score is 17 or higher
		while (dealerScore < 17) {
			dealerHand.add(deck.remove(0));
			calculateScore(dealerHand, dealerScore);
			// print out the hands after each hit
			printHands(true);
			// check if dealer busts
			if (dealerScore > 21) {
				System.out.println("Dealer busts! Player wins.");
				return;
			}
		}
		System.out.println("Dealer stands.");
	}

	private void calculateScore(List<Card> hand, int score) {
		// calculate the score of a hand
		score = 0;
		int aceCount = 0;
		for (Card card : hand) {
			if (card.getValue() == Card.Value.ACE) {
				aceCount++;
			}
			score += card.getRank();
		}
		// adjust the score for aces
		while (score > 21 && aceCount > 0) {
			score -= 10;
			aceCount--;
		}
	}

	public void play() {
		// deal initial cards
		dealInitialCards();
		// print out the hands
		printHands(false);
		// player turn
		playerTurn();
		// dealer turn
		dealerTurn();
		// determine the winner
		determineWinner();
	}

	private void determineWinner() {
		// compare the scores and determine the winner
		if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Card {
	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	public enum Value {
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
		ACE(11);

		private int rank;

		Value(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	private final Value value;
	private final Suit suit;

	public Card(Value value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	public Value getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return value.getRank();
	}

	@Override
	public String toString() {
		return value + " of " + suit;
	}
}
