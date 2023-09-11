package JRB_B05;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		// Prompt player for their name
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();

		// Initialize deck of cards
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}

		// Shuffle deck of cards
		for (int i = 0; i < deck.size(); i++) {
			int j = random.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}

		// Deal two cards to player and dealer
		ArrayList<String> playerCards = new ArrayList<String>();
		ArrayList<String> dealerCards = new ArrayList<String>();
		playerCards.add(deck.get(0));
		dealerCards.add(deck.get(1));
		playerCards.add(deck.get(2));
		dealerCards.add(deck.get(3));
		deck.remove(0);
		deck.remove(1);
		deck.remove(1);
		deck.remove(2);

		// Display player's cards and dealer's first card
		System.out.println(playerName + "'s cards: " + playerCards.get(0) + ", " + playerCards.get(1));
		System.out.println("Dealer's cards: " + dealerCards.get(0) + ", [Face Down]");

		// Player's turn
		boolean playerTurn = true;
		while (playerTurn) {
			System.out.print("Enter 'hit' or 'stand': ");
			String input = scanner.nextLine();
			if (input.equals("hit")) {
				playerCards.add(deck.get(0));
				deck.remove(0);
				System.out.println(playerName + "'s cards: " + playerCards);
				int playerScore = calculateScore(playerCards);
				if (playerScore > 21) {
					System.out.println(playerName + " busts!");
					playerTurn = false;
				}
			} else if (input.equals("stand")) {
				playerTurn = false;
			}
		}

		// Dealer's turn
		boolean dealerTurn = true;
		while (dealerTurn) {
			int dealerScore = calculateScore(dealerCards);
			if (dealerScore < 17) {
				dealerCards.add(deck.get(0));
				deck.remove(0);
			} else {
				dealerTurn = false;
			}
		}
		System.out.println("Dealer's cards: " + dealerCards);
		int dealerScore = calculateScore(dealerCards);
		if (dealerScore > 21) {
			System.out.println("Dealer busts!");
		}

		// Determine winner
		int playerScore = calculateScore(playerCards);
		if (playerScore <= 21 && (dealerScore > 21 || playerScore > dealerScore)) {
			System.out.println(playerName + " wins!");
		} else if (dealerScore <= 21 && (playerScore > 21 || dealerScore > playerScore)) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	// Calculates the score of a hand of cards
	public static int calculateScore(ArrayList<String> cards) {
		int score = 0;
		int numAces = 0;
		for (String card : cards) {
			String value = card.substring(0, card.indexOf(" "));
			if (value.equals("Ace")) {
				score += 11;
				numAces++;
			} else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
				score += 10;
			} else {
				score += Integer.parseInt(value);
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}
}