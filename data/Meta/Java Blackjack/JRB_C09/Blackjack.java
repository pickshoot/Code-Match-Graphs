package JRB_C09;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int balance = 1000;

		while (balance > 0) {
			System.out.println("Balance: $" + balance);
			System.out.print("Enter bet amount: $");
			int bet = input.nextInt();
			while (bet > balance) {
				System.out.println("Invalid bet amount. Please enter a bet amount less than or equal to your balance.");
				System.out.print("Enter bet amount: $");
				bet = input.nextInt();
			}

			int playerTotal = 0;
			int dealerTotal = 0;

			// Deal two cards to player
			int playerCard1 = random.nextInt(10) + 1;
			int playerCard2 = random.nextInt(10) + 1;
			playerTotal = playerCard1 + playerCard2;
			System.out.println("Your cards: " + playerCard1 + ", " + playerCard2);

			// Deal one card to dealer
			int dealerCard1 = random.nextInt(10) + 1;
			dealerTotal = dealerCard1;
			System.out.println("Dealer's card: " + dealerCard1);

			// Player's turn
			boolean playerBust = false;
			boolean playerStand = false;
			while (!playerBust && !playerStand) {
				System.out.print("Hit or stand? (h/s) ");
				String choice = input.next();
				if (choice.equalsIgnoreCase("h")) {
					int newCard = random.nextInt(10) + 1;
					playerTotal += newCard;
					System.out.println("New card: " + newCard);
					System.out.println("Total: " + playerTotal);
					if (playerTotal > 21) {
						System.out.println("Bust!");
						playerBust = true;
					}
				} else if (choice.equalsIgnoreCase("s")) {
					playerStand = true;
				} else {
					System.out.println("Invalid choice. Please enter 'h' or 's'.");
				}
			}

			// Dealer's turn
			while (dealerTotal < 17 && !playerBust && playerStand) {
				int newCard = random.nextInt(10) + 1;
				dealerTotal += newCard;
				System.out.println("Dealer's new card: " + newCard);
				System.out.println("Dealer's total: " + dealerTotal);
			}

			// Determine winner
			if (playerBust) {
				System.out.println("You lose!");
				balance -= bet;
			} else if (dealerTotal > 21) {
				System.out.println("You win!");
				balance += bet;
			} else if (dealerTotal > playerTotal) {
				System.out.println("You lose!");
				balance -= bet;
			} else if (dealerTotal < playerTotal) {
				System.out.println("You win!");
				balance += bet;
			} else {
				System.out.println("Push!");
			}

			// Play again or quit
			System.out.print("Play again? (y/n) ");
			String playAgain = input.next();
			if (!playAgain.equalsIgnoreCase("y")) {
				break;
			}
		}

		System.out.println("Thanks for playing!");
	}
}
