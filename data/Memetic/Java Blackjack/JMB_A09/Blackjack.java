package A9;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int playerScore = 0;
		int dealerScore = 0;

		System.out.println("Welcome to Blackjack!\n");

		// Deal initial cards
		int card1 = dealCard();
		int card2 = dealCard();
		playerScore += card1 + card2;
		System.out.println("You were dealt a " + card1 + " and a " + card2 + ".");

		int dealerCard1 = dealCard();
		dealerScore += dealerCard1;
		System.out.println("The dealer's face-up card is a " + dealerCard1 + ".");

		// Player's turn
		while (playerScore <= 21) {
			System.out.print("Would you like to hit or stand? ");
			String answer = input.next();
			if (answer.equalsIgnoreCase("hit")) {
				int newCard = dealCard();
				playerScore += newCard;
				System.out.println("You were dealt a " + newCard + ".");
			} else if (answer.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
			System.out.println("Your score is " + playerScore + ".");
		}

		// Dealer's turn
		while (dealerScore < 17 && playerScore <= 21) {
			int newCard = dealCard();
			dealerScore += newCard;
			System.out.println("The dealer was dealt a " + newCard + ".");
		}

		// Determine the winner
		if (playerScore > 21) {
			System.out.println("You busted! The dealer wins.");
		} else if (dealerScore > 21) {
			System.out.println("The dealer busted! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("The dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int dealCard() {
		Random rand = new Random();
		int card = rand.nextInt(10) + 1;
		return card > 9 ? 10 : card;
	}
}