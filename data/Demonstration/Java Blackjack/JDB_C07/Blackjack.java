package JDB_C07;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private Hand playerHand;
	private Hand dealerHand;
	private boolean playerStands;
	private boolean gameOver;

	public Blackjack() {
		deck = new Deck();
		playerHand = new Hand();
		dealerHand = new Hand();
		playerStands = false;
		gameOver = false;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		// shuffle the deck
		deck.shuffle();

		// deal initial hands
		playerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());

		// print hands
		System.out.println("Dealer's Hand: ");
		dealerHand.printHand(false);
		System.out.println("Player's Hand: ");
		playerHand.printHand(true);

		// player's turn
		while (!playerStands) {
			System.out.println("Hit or stand? (H/S)");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h")) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player's Hand: ");
				playerHand.printHand(true);
				if (playerHand.isBust()) {
					System.out.println("Bust! Dealer wins.");
					gameOver = true;
					break;
				}
			} else {
				playerStands = true;
			}
		}

		// dealer's turn
		if (!gameOver) {
			System.out.println("Dealer's turn: ");
			dealerHand.printHand(true);
			while (dealerHand.getScore() < 17) {
				dealerHand.addCard(deck.dealCard());
				System.out.println("Dealer's Hand: ");
				dealerHand.printHand(true);
				if (dealerHand.isBust()) {
					System.out.println("Dealer busts! Player wins.");
					gameOver = true;
					break;
				}
			}
		}

		// determine winner
		if (!gameOver) {
			int playerScore = playerHand.getScore();
			int dealerScore = dealerHand.getScore();
			if (playerScore > dealerScore) {
				System.out.println("Player wins!");
			} else if (dealerScore > playerScore) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Deck {
	private Card[] cards;
	private int topCardIndex;

	public Deck() {
		cards = new Card[52];
		topCardIndex = 0;
		int index = 0;
		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				cards[index] = new Card(rank, suit);
				index++;
			}
		}
	}

	public void shuffle() {
		topCardIndex = 0;
		Random random = new Random();
		for (int i = 0; i < cards.length; i++) {
			int j = random.nextInt(cards.length);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}

	public Card dealCard() {
		Card card = cards[topCardIndex];
		topCardIndex++;
		return card;
	}
}

class Card {
	private int rank;
	private int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		if (rank == 1) {
			return 11;
		} else if (rank >= 10) {
			return 10;
		} else {
			return rank;
		}
	}

	public String toString() {
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		return ranks[rank - 1] + " of " + suits[suit];
	}
}

class Hand {
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		int aces = 0;
		for (Card card : cards) {
			score += card.getValue();
			if (card.getRank() == 1) {
				aces++;
			}
		}
		while (score > 21 && aces > 0) {
			score -= 10;
			aces--;
		}
		return score;
	}

	public boolean isBust() {
		return getScore() > 21;
	}

	public void printHand(boolean showFirstCard) {
		if (showFirstCard) {
			for (Card card : cards) {
				System.out.println(card);
			}
		} else {
			System.out.println("HIDDEN CARD");
			for (int i = 1; i < cards.size(); i++) {
				System.out.println(cards.get(i));
			}
		}
	}
}
