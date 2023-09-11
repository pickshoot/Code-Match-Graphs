package JSB_B10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create deck of cards
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Initialize hands and deal cards
		ArrayList<String> playerHand = new ArrayList<String>();
		ArrayList<String> dealerHand = new ArrayList<String>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Display initial hands
		System.out.println("Your hand: " + playerHand.get(0) + ", " + playerHand.get(1));
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", <hidden>");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = sc.nextLine();
			if (input.equals("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + String.join(", ", playerHand));
				int playerScore = getHandScore(playerHand);
				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			}
		}

		// Dealer's turn
		System.out.println("Dealer's hand: " + String.join(", ", dealerHand));
		int dealerScore = getHandScore(dealerHand);
		while (dealerScore < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer hits and gets " + dealerHand.get(dealerHand.size() - 1));
			dealerScore = getHandScore(dealerHand);
		}
		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
			return;
		}

		// Compare scores
		int playerScore = getHandScore(playerHand);
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer's score: " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int getHandScore(ArrayList<String> hand) {
		int score = 0;
		int numAces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				numAces++;
				score += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				score += 10;
			} else {
				score += Integer.parseInt(rank);
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}
}