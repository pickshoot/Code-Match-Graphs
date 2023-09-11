package blackjackSA2;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int playerTotal = 0;
		int dealerTotal = 0;

		// Deal the initial hand to the player
		int card1 = rand.nextInt(10) + 1;
		int card2 = rand.nextInt(10) + 1;
		playerTotal = card1 + card2;

		System.out.println("Your cards are: " + card1 + " and " + card2);
		System.out.println("Your total is: " + playerTotal);

		// Deal the initial hand to the dealer
		int dealerCard1 = rand.nextInt(10) + 1;
		int dealerCard2 = rand.nextInt(10) + 1;
		dealerTotal = dealerCard1 + dealerCard2;

		System.out.println("The dealer's face up card is: " + dealerCard1);

		// Player's turn
		while (playerTotal < 21) {
			System.out.println("Would you like to hit or stand? (h/s)");
			String choice = input.nextLine();

			if (choice.equalsIgnoreCase("h")) {
				int card = rand.nextInt(10) + 1;
				playerTotal += card;
				System.out.println("You drew a " + card);
				System.out.println("Your total is now: " + playerTotal);
			} else if (choice.equalsIgnoreCase("s")) {
				break;
			} else {
				System.out.println("Invalid input, please enter 'h' to hit or 's' to stand.");
			}
		}

		// Dealer's turn
		while (dealerTotal < 17) {
			int card = rand.nextInt(10) + 1;
			dealerTotal += card;
			System.out.println("Dealer drew a " + card);
			System.out.println("Dealer's total is now: " + dealerTotal);
		}

		// Determine the winner
		if (playerTotal > 21) {
			System.out.println("You busted! Dealer wins.");
		} else if (dealerTotal > 21) {
			System.out.println("Dealer busted! You win.");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}
}