package JRB_C05;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		// Initialize scanner object for user input
		Scanner scanner = new Scanner(System.in);

		// Initialize player balance
		int balance = 100;

		// Begin game loop
		while (true) {

			// Get player bet amount
			System.out.println("Balance: $" + balance);
			System.out.print("Enter bet amount: $");
			int bet = scanner.nextInt();

			// Deal initial cards
			int playerTotal = 0;
			int dealerTotal = 0;
			int playerCard1 = drawCard();
			int playerCard2 = drawCard();
			int dealerCard1 = drawCard();

			// Calculate initial hand values
			playerTotal += getCardValue(playerCard1);
			playerTotal += getCardValue(playerCard2);
			dealerTotal += getCardValue(dealerCard1);

			// Show initial hands
			System.out.println("Your cards: " + getCardName(playerCard1) + " " + getCardName(playerCard2));
			System.out.println("Dealer's card: " + getCardName(dealerCard1));

			// Player turn loop
			while (true) {
				System.out.print("Hit or stand? ");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("hit")) {
					int newCard = drawCard();
					playerTotal += getCardValue(newCard);
					System.out.println("New card: " + getCardName(newCard));
					if (playerTotal > 21) {
						System.out.println("Bust! You lose.");
						balance -= bet;
						break;
					}
				} else if (choice.equalsIgnoreCase("stand")) {
					break;
				}
			}

			// Dealer turn loop
			while (dealerTotal < 17) {
				int newCard = drawCard();
				dealerTotal += getCardValue(newCard);
				System.out.println("Dealer draws " + getCardName(newCard));
				if (dealerTotal > 21) {
					System.out.println("Dealer busts! You win.");
					balance += bet;
					break;
				}
			}

			// Compare hands and determine winner
			if (playerTotal <= 21 && (dealerTotal > 21 || playerTotal > dealerTotal)) {
				System.out.println("You win!");
				balance += bet;
			} else if (dealerTotal <= 21 && (playerTotal > 21 || dealerTotal > playerTotal)) {
				System.out.println("Dealer wins!");
				balance -= bet;
			} else {
				System.out.println("It's a tie!");
			}

			// End game loop if player balance is zero
			if (balance == 0) {
				System.out.println("Game over! You're out of money.");
				break;
			}

			// Ask if player wants to play again
			System.out.print("Play again? (y/n) ");
			String again = scanner.next();
			if (!again.equalsIgnoreCase("y")) {
				break;
			}
		}

		// Close scanner
		scanner.close();
	}

	// Method to draw a random card
	public static int drawCard() {
		return (int) (Math.random() * 13) + 1;
	}

	// Method to get the value of a card
	public static int getCardValue(int card) {
		if (card > 10) {
			return 10;
		} else {
			return card;
		}
	}

	// Method to get the name of a card
	public static String getCardName(int card) {
		switch (card) {
		case 1:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return "";
		}
	}

}
