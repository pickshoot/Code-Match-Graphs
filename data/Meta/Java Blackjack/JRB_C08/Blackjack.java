package JRB_C08;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		int balance = 100;

		while (true) {

			System.out.println("Your balance is: $" + balance);
			System.out.print("Enter your bet: $");
			int bet = scanner.nextInt();

			if (bet > balance) {
				System.out.println("You don't have enough balance to place that bet.");
				continue;
			}

			balance -= bet;

			int playerTotal = 0;
			int dealerTotal = 0;

			List<Integer> playerCards = new ArrayList<>();
			List<Integer> dealerCards = new ArrayList<>();

			// Deal initial cards
			playerCards.add(random.nextInt(10) + 1);
			playerCards.add(random.nextInt(10) + 1);

			dealerCards.add(random.nextInt(10) + 1);

			System.out.println("Dealer's card: " + dealerCards.get(0));
			System.out.println("Your cards: " + playerCards);

			// Player's turn
			while (true) {
				System.out.print("Do you want to hit or stand? ");
				String input = scanner.next();

				if (input.equalsIgnoreCase("hit")) {
					playerCards.add(random.nextInt(10) + 1);
					System.out.println("Your cards: " + playerCards);

					playerTotal = calculateTotal(playerCards);
					if (playerTotal > 21) {
						System.out.println("Bust! You lose.");
						break;
					}
				} else if (input.equalsIgnoreCase("stand")) {
					playerTotal = calculateTotal(playerCards);
					break;
				} else {
					System.out.println("Invalid input.");
				}
			}

			// Dealer's turn
			if (playerTotal <= 21) {
				System.out.println("Dealer's cards: " + dealerCards);
				while (true) {
					dealerTotal = calculateTotal(dealerCards);
					if (dealerTotal >= 17) {
						break;
					}
					dealerCards.add(random.nextInt(10) + 1);
					System.out.println("Dealer draws a card. Dealer's cards: " + dealerCards);
				}

				System.out.println("Dealer's total: " + dealerTotal);

				// Determine winner
				if (dealerTotal > 21 || playerTotal > dealerTotal) {
					System.out.println("You win!");
					balance += bet * 2;
				} else if (dealerTotal > playerTotal) {
					System.out.println("Dealer wins!");
				} else {
					System.out.println("It's a tie!");
					balance += bet;
				}
			}

			// Check if player wants to play again
			System.out.print("Do you want to play again? ");
			String input = scanner.next();
			if (!input.equalsIgnoreCase("yes")) {
				break;
			}
		}
	}

	// Calculate total value of a hand
	public static int calculateTotal(List<Integer> cards) {
		int total = 0;
		for (int card : cards) {
			total += card;
		}
		for (int card : cards) {
			if (card == 1 && total <= 11) {
				total += 10;
			}
		}
		return total;
	}
}
