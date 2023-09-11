package JRB_C03;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		int playerBalance = 1000; // Starting balance of the player
		int betAmount = 0; // Bet amount for each game

		while (true) {
			System.out.println("Current balance: " + playerBalance);
			System.out.print("Enter bet amount (or 0 to quit): ");
			betAmount = input.nextInt();
			if (betAmount <= 0 || betAmount > playerBalance) {
				break;
			}

			int playerHandValue = 0; // Hand value of the player
			int dealerHandValue = 0; // Hand value of the dealer

			// Dealing cards to the player and the dealer
			int playerCard1 = rand.nextInt(10) + 1;
			int playerCard2 = rand.nextInt(10) + 1;
			int dealerCard1 = rand.nextInt(10) + 1;

			playerHandValue = playerCard1 + playerCard2;
			System.out.println("Your cards: " + playerCard1 + " " + playerCard2);
			System.out.println("Dealer's card: " + dealerCard1);

			// Player's turn
			while (true) {
				System.out.print("Do you want to hit (h) or stand (s)? ");
				char choice = input.next().charAt(0);
				if (choice == 'h') {
					int newCard = rand.nextInt(10) + 1;
					playerHandValue += newCard;
					System.out.println("New card: " + newCard);
					System.out.println("Total hand value: " + playerHandValue);
					if (playerHandValue > 21) {
						System.out.println("Bust! You lose.");
						playerBalance -= betAmount;
						break;
					}
				} else if (choice == 's') {
					break;
				}
			}

			// Dealer's turn
			if (playerHandValue <= 21) {
				while (dealerHandValue < 17) {
					int newCard = rand.nextInt(10) + 1;
					dealerHandValue += newCard;
					System.out.println("Dealer's new card: " + newCard);
					System.out.println("Dealer's total hand value: " + dealerHandValue);
				}

				// Determine winner
				if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
					System.out.println("You win!");
					playerBalance += betAmount;
				} else if (playerHandValue == dealerHandValue) {
					System.out.println("Push!");
				} else {
					System.out.println("You lose.");
					playerBalance -= betAmount;
				}
			}

			// Check if player has run out of money
			if (playerBalance <= 0) {
				System.out.println("You're out of money! Game over.");
				break;
			}
		}

		System.out.println("Final balance: " + playerBalance);
		System.out.println("Thanks for playing!");
	}
}
