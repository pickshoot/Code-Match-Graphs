package JDB_C01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private List<Card> deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerScore;
	private int dealerScore;

	public Blackjack() {
		deck = new ArrayList<>();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		initializeDeck();
	}

	private void initializeDeck() {
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				deck.add(new Card(suits[i], ranks[j], values[j]));
			}
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private void dealInitialCards() {
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerScore = calculateHandScore(playerHand);
		dealerScore = calculateHandScore(dealerHand);
	}

	private void playerTurn(Scanner scanner) {
		while (playerScore < 21) {
			System.out.println("Your hand: " + playerHand);
			System.out.println("Your score: " + playerScore);
			System.out.println("Hit or stand? (h/s): ");
			String input = scanner.nextLine();
			if (input.equals("h")) {
				playerHand.add(deck.remove(0));
				playerScore = calculateHandScore(playerHand);
			} else if (input.equals("s")) {
				break;
			}
		}
	}

	private void dealerTurn() {
		while (dealerScore < 17) {
			dealerHand.add(deck.remove(0));
			dealerScore = calculateHandScore(dealerHand);
		}
	}

	private int calculateHandScore(List<Card> hand) {
		int score = 0;
		int numAces = 0;
		for (Card card : hand) {
			score += card.getValue();
			if (card.getRank().equals("ace")) {
				numAces++;
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	private void printFinalHands() {
		System.out.println("Your final hand: " + playerHand);
		System.out.println("Your final score: " + playerScore);
		System.out.println("Dealer's final hand: " + dealerHand);
		System.out.println("Dealer's final score: " + dealerScore);
	}

	private void printResult() {
		if (playerScore > 21) {
			System.out.println("You bust. Dealer wins!");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busts. You win!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore == dealerScore) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Dealer wins!");
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		shuffleDeck();
		dealInitialCards();
		playerTurn(scanner);
		if (playerScore <= 21) {
			dealerTurn();
		}
		printFinalHands();
		printResult();
		scanner.close();
	}

	public static void main(String args[]) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Card {
	private String suit;
	private String rank;
	private int value;

	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}
