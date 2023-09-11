package A6;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int playerScore = 0;
		int dealerScore = 0;
		int cardValue;

		System.out.println("Welcome to Blackjack!");
		System.out.println("You will be playing against the dealer.");

		// Deal first two cards to player
		cardValue = dealCard();
		playerScore += cardValue;
		System.out.println("Your first card is: " + cardValue);

		cardValue = dealCard();
		playerScore += cardValue;
		System.out.println("Your second card is: " + cardValue);

		// Deal first two cards to dealer
		cardValue = dealCard();
		dealerScore += cardValue;

		System.out.println("The dealer's first card is: " + cardValue);
		System.out.println("The dealer's second card is face down.");

		// Player's turn
		while (playerScore < 21) {
			System.out.print("Do you want to hit or stay? (h/s): ");
			String input = sc.nextLine();
			if (input.equals("h")) {
				cardValue = dealCard();
				playerScore += cardValue;
				System.out.println("You drew a " + cardValue);
			} else if (input.equals("s")) {
				break;
			}
		}

		if (playerScore > 21) {
			System.out.println("Bust! You lose.");
			return;
		}

		// Dealer's turn
		System.out.println("The dealer's second card is: " + dealCard());
		while (dealerScore < 17) {
			cardValue = dealCard();
			dealerScore += cardValue;
			System.out.println("The dealer drew a " + cardValue);
		}

		if (dealerScore > 21) {
			System.out.println("The dealer busts! You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("The dealer wins!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int dealCard() {
		Random rand = new Random();
		int cardValue = rand.nextInt(10) + 1;
		return cardValue;
	}
}