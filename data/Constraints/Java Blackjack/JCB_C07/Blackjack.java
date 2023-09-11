package JCB_C07;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		ArrayList<String> deck = new ArrayList<>();
		String[] suits = { "hearts", "diamonds", "spades", "clubs" };
		String[] values = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };

		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}

		int playerScore = 0;
		int dealerScore = 0;
		boolean playerBust = false;
		boolean dealerBust = false;
		boolean gameOver = false;

		System.out.println("Welcome to Blackjack!");

		while (!gameOver) {
			// Shuffle the deck
			for (int i = 0; i < deck.size(); i++) {
				int j = random.nextInt(deck.size());
				String temp = deck.get(i);
				deck.set(i, deck.get(j));
				deck.set(j, temp);
			}

			// Deal initial hands
			ArrayList<String> playerHand = new ArrayList<>();
			ArrayList<String> dealerHand = new ArrayList<>();
			playerHand.add(deck.remove(0));
			dealerHand.add(deck.remove(0));
			playerHand.add(deck.remove(0));
			dealerHand.add(deck.remove(0));

			// Calculate initial scores
			playerScore = calculateScore(playerHand);
			dealerScore = calculateScore(dealerHand);

			System.out.println("Your hand: " + playerHand);
			System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", HIDDEN]");

			// Player's turn
			while (playerScore < 21) {
				System.out.print("Hit or stand? (h/s) ");
				String choice = input.nextLine().toLowerCase();
				if (choice.equals("h")) {
					playerHand.add(deck.remove(0));
					playerScore = calculateScore(playerHand);
					System.out.println("Your hand: " + playerHand);
				} else if (choice.equals("s")) {
					break;
				} else {
					System.out.println("Invalid input. Please enter 'h' or 's'.");
				}
			}

			if (playerScore > 21) {
				playerBust = true;
				System.out.println("Bust! Your score is " + playerScore);
			}

			// Dealer's turn
			if (!playerBust) {
				while (dealerScore < 17) {
					dealerHand.add(deck.remove(0));
					dealerScore = calculateScore(dealerHand);
				}

				System.out.println("Dealer's hand: " + dealerHand);

				if (dealerScore > 21) {
					dealerBust = true;
					System.out.println(
							"Dealer busts! Your score is " + playerScore + ", dealer's score is " + dealerScore);
				} else {
					System.out.println("Your score is " + playerScore + ", dealer's score is " + dealerScore);
					if (playerScore > dealerScore) {
						System.out.println("You win!");
					} else if (playerScore == dealerScore) {
						System.out.println("It's a tie!");
					} else {
						System.out.println("You lose!");
					}
				}
			}
			// Ask if player wants to play again
			System.out.print("Play again? (y/n) ");
			String choice = input.nextLine().toLowerCase();
			if (choice.equals("n")) {
				gameOver = true;
			}
		}

		System.out.println("Thanks for playing!");
	}

	public static int calculateScore(ArrayList<String> hand) {
		int score = 0;
		int numAces = 0;

		for (String card : hand) {
			String value = card.split(" ")[0];
			if (value.equals("ace")) {
				numAces++;
				score += 11;
			} else if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
				score += 10;
			} else {
				score += Integer.parseInt(value);
			}
		}

		while (numAces > 0 && score > 21) {
			score -= 10;
			numAces--;
		}

		return score;
	}
}