package JRB_C10;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		int balance = 100; // starting balance
		boolean playAgain = true;

		while (playAgain) {
			System.out.println("Your balance is: $" + balance);

			// get bet amount from player
			System.out.print("Enter your bet amount: $");
			int bet = input.nextInt();

			// check if bet is valid
			if (bet > balance) {
				System.out.println("Bet amount exceeds balance.");
				continue; // start the loop again
			}

			// deal cards
			int playerTotal = 0;
			int dealerTotal = 0;

			// player's initial cards
			int card1 = random.nextInt(10) + 1; // 1-10
			int card2 = random.nextInt(10) + 1;
			playerTotal = card1 + card2;
			System.out.println("Your cards: " + card1 + ", " + card2);

			// dealer's initial card
			int dealerCard = random.nextInt(10) + 1;
			dealerTotal = dealerCard;
			System.out.println("Dealer's card: " + dealerCard);

			// player's turn
			while (true) {
				System.out.print("Hit or stand? (h/s): ");
				String choice = input.next();

				if (choice.equalsIgnoreCase("h")) {
					int card = random.nextInt(10) + 1;
					playerTotal += card;
					System.out.println("Your card: " + card);
					System.out.println("Your total: " + playerTotal);

					if (playerTotal > 21) {
						System.out.println("Bust! You lose.");
						balance -= bet;
						break;
					}
				} else {
					break;
				}
			}

			// dealer's turn
			while (dealerTotal < 17) {
				int card = random.nextInt(10) + 1;
				dealerTotal += card;
				System.out.println("Dealer's card: " + card);
				System.out.println("Dealer's total: " + dealerTotal);

				if (dealerTotal > 21) {
					System.out.println("Dealer busts! You win.");
					balance += bet;
					break;
				}
			}

			// compare hands and determine winner
			if (playerTotal <= 21 && dealerTotal <= 21) {
				if (playerTotal > dealerTotal) {
					System.out.println("You win!");
					balance += bet;
				} else if (playerTotal < dealerTotal) {
					System.out.println("Dealer wins.");
					balance -= bet;
				} else {
					System.out.println("It's a tie.");
				}
			}

			// check if player wants to play again
			System.out.print("Play again? (y/n): ");
			String choice = input.next();

			if (choice.equalsIgnoreCase("n")) {
				playAgain = false;
			}
		}

		System.out.println("Final balance: $" + balance);
		System.out.println("Thanks for playing!");
	}
}
