package JDB_C08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> dealerHand;
	private int playerScore;
	private int dealerScore;
	private boolean playerBust;
	private boolean dealerBust;
	private boolean gameOver;

	public Blackjack() {
		deck = new Deck();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		playerBust = false;
		dealerBust = false;
		gameOver = false;
	}

	public void play() {
		// Shuffle the deck
		deck.shuffle();

		// Deal the initial cards
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Print out the initial hands
		System.out.println("Player's hand: " + playerHand.get(0) + " and " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + " and an unknown card");

		// Calculate the initial scores
		playerScore = calculateScore(playerHand);
		dealerScore = calculateScore(dealerHand);

		// Player's turn
		while (playerScore < 21) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Do you want to hit (h) or stand (s)? ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("h")) {
				playerHand.add(deck.drawCard());
				playerScore = calculateScore(playerHand);
				System.out.println("Player's hand: " + playerHand);
				System.out.println("Player's score: " + playerScore);
			} else if (choice.equals("s")) {
				break;
			} else {
				System.out.println("Invalid choice.");
			}
		}

		// Check if player busts
		if (playerScore > 21) {
			playerBust = true;
			System.out.println("Player busts!");
			gameOver = true;
		}

		// Dealer's turn
		while (!gameOver && dealerScore < 17) {
			dealerHand.add(deck.drawCard());
			dealerScore = calculateScore(dealerHand);
			System.out.println("Dealer's hand: " + dealerHand);
			System.out.println("Dealer's score: " + dealerScore);
			if (dealerScore > 21) {
				dealerBust = true;
				System.out.println("Dealer busts!");
				gameOver = true;
			}
		}

		// Determine the winner
		if (!gameOver) {
			System.out.println("Player's hand: " + playerHand);
			System.out.println("Dealer's hand: " + dealerHand);
			if (playerScore > dealerScore) {
				System.out.println("Player wins!");
			} else if (dealerScore > playerScore) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}
	}

	private int calculateScore(ArrayList<Card> hand) {
		int score = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
				score += 11;
			} else if (card.getRank() == Rank.JACK || card.getRank() == Rank.QUEEN || card.getRank() == Rank.KING) {
				score += 10;
			} else {
				score += card.getRank().getValue();
			}
		}
		// If there are aces in the hand and the score is greater than 21, change the
		// value of the aces to 1
		while (numAces > 0 && score > 21) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(0);
	}

	public int getSize() {
		return cards.size();
	}
}

enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
	ACE(11);

	private final int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}