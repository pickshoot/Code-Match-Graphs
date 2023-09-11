package JDB_B09;

import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int playerTotal = 0;
		int dealerTotal = 0;

		// Deal the first two cards
		playerTotal += dealCard();
		playerTotal += dealCard();

		dealerTotal += dealCard();
		dealerTotal += dealCard();

		// Player's turn
		while (playerTotal < 21) {
			System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");

			String input = scanner.nextLine().toLowerCase();

			if (input.equals("hit")) {
				playerTotal += dealCard();
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		while (dealerTotal < 17) {
			dealerTotal += dealCard();
		}

		// Determine the winner
		if (playerTotal > 21) {
			System.out.println("You bust! The dealer wins.");
		} else if (dealerTotal > 21) {
			System.out.println("The dealer busts! You win.");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (playerTotal < dealerTotal) {
			System.out.println("The dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}

		scanner.close();
	}

	public static int dealCard() {
		return (int) (Math.random() * 10) + 2;
	}
}
