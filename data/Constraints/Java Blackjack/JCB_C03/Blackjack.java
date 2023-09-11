package JCB_C03;

import java.util.*;

public class Blackjack {

	private static final int TARGET_SCORE = 21;
	private static final int DEALER_MIN_SCORE = 17;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Random random = new Random();

		int playerScore = 0;
		int dealerScore = 0;

		// Deal initial cards
		playerScore += dealCard(random);
		playerScore += dealCard(random);
		dealerScore += dealCard(random);
		dealerScore += dealCard(random);

		// Player's turn
		while (playerScore < TARGET_SCORE) {
			System.out.println("Your score is " + playerScore);
			System.out.print("Do you want to hit or stand? ");
			String choice = input.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				playerScore += dealCard(random);
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid choice, try again.");
			}
		}

		// Dealer's turn
		while (dealerScore < DEALER_MIN_SCORE) {
			dealerScore += dealCard(random);
		}

		// Determine winner
		if (playerScore > TARGET_SCORE) {
			System.out.println("You busted! Dealer wins.");
		} else if (dealerScore > TARGET_SCORE) {
			System.out.println("Dealer busted! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}

		input.close();
	}

	private static int dealCard(Random random) {
		int card = random.nextInt(10) + 1;
		System.out.println("Dealt a card: " + card);
		return card;
	}

}