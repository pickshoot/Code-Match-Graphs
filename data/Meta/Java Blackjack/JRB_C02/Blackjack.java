package JRB_C02;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random random = new Random();

		int playerBalance = 100;
		int betAmount = 0;
		int playerHandValue = 0;
		int dealerHandValue = 0;

		boolean gameOver = false;
		boolean playerWins = false;

		System.out.println("Welcome to Blackjack!");

		while (playerBalance > 0) {

			// Reset game variables
			playerHandValue = 0;
			dealerHandValue = 0;
			playerWins = false;
			gameOver = false;

			System.out.println("Your current balance is: $" + playerBalance);

			// Get bet amount from player
			System.out.print("Enter bet amount: $");
			betAmount = sc.nextInt();

			// Deal initial cards
			int playerCard1 = random.nextInt(10) + 1;
			int playerCard2 = random.nextInt(10) + 1;
			int dealerCard1 = random.nextInt(10) + 1;

			playerHandValue = playerCard1 + playerCard2;
			dealerHandValue = dealerCard1;

			System.out.println("Dealer's card: " + dealerCard1);
			System.out.println("Your cards: " + playerCard1 + ", " + playerCard2);

			// Player turn
			while (true) {
				System.out.print("Do you want to hit (h) or stand (s)? ");
				String decision = sc.next();

				if (decision.equalsIgnoreCase("h")) {
					int newCard = random.nextInt(10) + 1;
					playerHandValue += newCard;
					System.out.println("You drew a " + newCard + ". Your hand value is now: " + playerHandValue);

					if (playerHandValue > 21) {
						System.out.println("Busted! You lose.");
						gameOver = true;
						break;
					}

				} else if (decision.equalsIgnoreCase("s")) {
					System.out.println("You stand with a hand value of " + playerHandValue);
					break;

				} else {
					System.out.println("Invalid input. Please enter 'h' or 's'.");
				}
			}

			// Dealer turn
			if (!gameOver) {
				System.out.println("Dealer's turn:");
				System.out.println("Dealer's cards: " + dealerCard1 + ", ?");

				while (dealerHandValue < 17) {
					int newCard = random.nextInt(10) + 1;
					dealerHandValue += newCard;
					System.out
							.println("Dealer draws a " + newCard + ". Dealer's hand value is now: " + dealerHandValue);

					if (dealerHandValue > 21) {
						System.out.println("Dealer busts! You win.");
						playerWins = true;
						gameOver = true;
						break;
					}
				}
			}

			// Compare hands
			if (!gameOver) {
				System.out.println("Dealer's cards: " + dealerCard1 + ", " + (dealerHandValue - dealerCard1));
				System.out.println("Your cards: " + playerCard1 + ", " + playerCard2 + ", "
						+ (playerHandValue - playerCard1 - playerCard2));

				if (playerHandValue > dealerHandValue) {
					System.out.println("You win!");
					playerWins = true;
				} else if (playerHandValue < dealerHandValue) {
					System.out.println("Dealer wins!");
				} else {
					System.out.println("It's a tie!");
				}
			}

			// Update balance and ask player if they want to play again
			if (playerWins) {
				playerBalance += betAmount;
			} else {
				playerBalance -= betAmount;
			}

			System.out.println("Your new balance is: $" + playerBalance);

			if (playerBalance == 0) {
				System.out.println("You have no more money to play. Goodbye!");
				break;
			}

			System.out.print("Do you want to play again? (y/n) ");
			String playAgain = sc.next();

			if (!playAgain.equalsIgnoreCase("y")) {
				System.out.println("Thanks for playing. Goodbye!");
				break;
			}

		}

		sc.close();
	}
}
