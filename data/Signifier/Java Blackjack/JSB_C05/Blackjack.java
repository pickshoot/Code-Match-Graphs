package JSB_C05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

	private static final int TARGET_SCORE = 21;
	private static final int DEALER_STAND_THRESHOLD = 17;

	private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

	private ArrayList<String> deck = new ArrayList<String>();
	private ArrayList<String> playerCards = new ArrayList<String>();
	private ArrayList<String> dealerCards = new ArrayList<String>();

	public Blackjack() {
		// Initialize deck
		for (String rank : RANKS) {
			for (int i = 0; i < 4; i++) {
				deck.add(rank);
			}
		}
		Collections.shuffle(deck);
	}

	private int getScore(ArrayList<String> cards) {
		int score = 0;
		int numAces = 0;
		for (String card : cards) {
			int index = java.util.Arrays.asList(RANKS).indexOf(card);
			int value = VALUES[index];
			score += value;
			if (card.equals("A")) {
				numAces++;
			}
		}
		while (numAces > 0 && score > TARGET_SCORE) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public void dealCards() {
		// Deal two cards to player and two cards to dealer
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
	}

	public void playerTurn() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Your cards: " + playerCards);
			System.out.println("Dealer's up card: " + dealerCards.get(0));
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerCards.add(deck.remove(0));
				int score = getScore(playerCards);
				if (score > TARGET_SCORE) {
					System.out.println("Bust! Your score is " + score);
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				return;
			}
		}
	}

	public void dealerTurn() {
		while (getScore(dealerCards) < DEALER_STAND_THRESHOLD) {
			dealerCards.add(deck.remove(0));
		}
	}

	public void showHands() {
		int playerScore = getScore(playerCards);
		int dealerScore = getScore(dealerCards);
		System.out.println("Your cards: " + playerCards + " (" + playerScore + ")");
		System.out.println("Dealer's cards: " + dealerCards + " (" + dealerScore + ")");
		if (playerScore > TARGET_SCORE) {
			System.out.println("You bust!");
		} else if (dealerScore > TARGET_SCORE) {
			System.out.println("Dealer busts! You win!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.dealCards();
		game.playerTurn();
		game.dealerTurn();
		game.showHands();
	}
}