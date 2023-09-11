package JRB_C06;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int playerBalance = 100;
		boolean playAgain = true;

		while (playAgain) {
			System.out.println("Welcome to Blackjack! Your balance is $" + playerBalance);
			System.out.print("Enter your bet: ");
			int bet = scanner.nextInt();
			System.out.println();

			// Deal initial cards
			int playerTotal = 0;
			int dealerTotal = 0;
			int numPlayerCards = 0;
			int numDealerCards = 0;
			int[] playerCards = new int[10];
			int[] dealerCards = new int[10];
			playerCards[numPlayerCards++] = getRandomCard(random);
			playerCards[numPlayerCards++] = getRandomCard(random);
			playerTotal = getHandTotal(playerCards, numPlayerCards);
			dealerCards[numDealerCards++] = getRandomCard(random);
			dealerTotal = getHandTotal(dealerCards, numDealerCards);

			// Player's turn
			boolean playerDone = false;
			while (!playerDone) {
				System.out.println("Your hand: " + getHandString(playerCards, numPlayerCards));
				System.out.println("Dealer's hand: " + getHandString(dealerCards, 1));
				System.out.print("Do you want to hit or stand? ");
				String action = scanner.next();
				System.out.println();

				if (action.equalsIgnoreCase("hit")) {
					playerCards[numPlayerCards++] = getRandomCard(random);
					playerTotal = getHandTotal(playerCards, numPlayerCards);
					if (playerTotal > 21) {
						System.out.println("You busted! Dealer wins.");
						playerBalance -= bet;
						playerDone = true;
					}
				} else {
					playerDone = true;
				}
			}

			// Dealer's turn
			boolean dealerDone = false;
			while (!dealerDone && playerTotal <= 21) {
				if (dealerTotal < 17) {
					dealerCards[numDealerCards++] = getRandomCard(random);
					dealerTotal = getHandTotal(dealerCards, numDealerCards);
				} else {
					dealerDone = true;
				}
			}

			// Determine the winner
			if (playerTotal > dealerTotal || dealerTotal > 21) {
				System.out.println("You win!");
				playerBalance += bet;
			} else if (playerTotal < dealerTotal) {
				System.out.println("Dealer wins!");
				playerBalance -= bet;
			} else {
				System.out.println("It's a tie!");
			}

			System.out.println("Your hand: " + getHandString(playerCards, numPlayerCards));
			System.out.println("Dealer's hand: " + getHandString(dealerCards, numDealerCards));
			System.out.println("Your balance is now $" + playerBalance);
			System.out.print("Do you want to play again? ");
			String playAgainStr = scanner.next();
			System.out.println();

			if (!playAgainStr.equalsIgnoreCase("yes")) {
				playAgain = false;
			}
		}
	}

	private static int getRandomCard(Random random) {
		return random.nextInt(10) + 1;
	}

	private static int getHandTotal(int[] cards, int numCards) {
		int total = 0;
		int numAces = 0;

		for (int i = 0; i < numCards; i++) {
			if (cards[i] == 1) {
				numAces++;
			}

			total += Math.min(cards[i], 10);
		}

		while (total <= 11 && numAces > 0) {
			total += 10;
			numAces--;
		}

		return total;
	}

	private static String getHandString(int[] cards, int numCards) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numCards; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(cards[i]);
		}

		return sb.toString();
	}
}