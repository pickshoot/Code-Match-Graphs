package JCB_C05;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		ArrayList<Integer> playerCards = new ArrayList<Integer>();
		ArrayList<Integer> dealerCards = new ArrayList<Integer>();

		int playerScore = 0;
		int dealerScore = 0;
		boolean playerBust = false;
		boolean dealerBust = false;

		// Deal initial cards to player and dealer
		playerCards.add(rand.nextInt(10) + 1);
		playerCards.add(rand.nextInt(10) + 1);
		dealerCards.add(rand.nextInt(10) + 1);
		dealerCards.add(rand.nextInt(10) + 1);

		// Calculate initial scores
		playerScore = calculateScore(playerCards);
		dealerScore = calculateScore(dealerCards);

		// Print initial hands
		System.out.println("Your hand: " + playerCards.get(0) + ", " + playerCards.get(1));
		System.out.println("Dealer's hand: " + dealerCards.get(0) + ", ?");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String choice = input.nextLine();

			if (choice.equals("hit")) {
				playerCards.add(rand.nextInt(10) + 1);
				playerScore = calculateScore(playerCards);
				System.out.println("Your hand: " + joinCards(playerCards));

				if (playerScore > 21) {
					System.out.println("Bust! Your score is " + playerScore);
					playerBust = true;
					break;
				}
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		if (!playerBust) {
			System.out.println("Dealer's hand: " + joinCards(dealerCards));

			while (dealerScore < 17) {
				dealerCards.add(rand.nextInt(10) + 1);
				dealerScore = calculateScore(dealerCards);
				System.out.println("Dealer hits. Dealer's hand: " + joinCards(dealerCards));

				if (dealerScore > 21) {
					System.out.println(
							"Dealer busts! Your score is " + playerScore + ", dealer's score is " + dealerScore);
					dealerBust = true;
					break;
				}
			}

			if (!dealerBust) {
				System.out.println("Dealer stands. Dealer's score is " + dealerScore);
			}
		}

		// Determine winner
		if (!playerBust && !dealerBust) {
			if (playerScore > dealerScore) {
				System.out.println("You win! Your score is " + playerScore + ", dealer's score is " + dealerScore);
			} else if (playerScore < dealerScore) {
				System.out.println("Dealer wins! Your score is " + playerScore + ", dealer's score is " + dealerScore);
			} else {
				System.out.println("It's a tie! Your score is " + playerScore + ", dealer's score is " + dealerScore);
			}
		}
	}

	// Returns the score of a hand of cards
	public static int calculateScore(ArrayList<Integer> cards) {
		int score = 0;
		int numAces = 0;

		for (int card : cards) {
			if (card == 1) {
				numAces++;
			} else if (card >= 10) {
				score += 10;
			} else {
				score += card;
			}
		}

		for (int i = 0; i < numAces; i++) {
			if (score + 11 <= 21) {
				score += 11;
			} else {
				score += 1;
			}
		}

		return score;
	}

	// Joins the elements of an ArrayList into a comma-separated string
	public static String joinCards(ArrayList<Integer> cards) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < cards.size(); i++) {
			sb.append(cards.get(i));

			if (i != cards.size() - 1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}
}