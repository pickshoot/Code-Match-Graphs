package blackjackSA7;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		int[] deck = new int[52];
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}

		for (int i = 0; i < deck.length; i++) {
			int index = random.nextInt(52);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}

		int dealerTotal = 0;
		int playerTotal = 0;

		for (int i = 0; i < 2; i++) {
			int card = deck[i];
			String suit = suits[card / 13];
			String rank = ranks[card % 13];
			int value = values[card % 13];

			System.out.println("Dealer dealt himself " + (i == 0 ? "a hidden card" : rank + " of " + suit));

			if (i == 1) {
				dealerTotal += value;
			}
		}

		for (int i = 2; i < 4; i++) {
			int card = deck[i];
			String suit = suits[card / 13];
			String rank = ranks[card % 13];
			int value = values[card % 13];

			System.out.println("You were dealt the " + rank + " of " + suit);
			playerTotal += value;
		}

		boolean playerBust = false;

		while (true) {
			System.out.println("Your total is " + playerTotal);
			System.out.print("Hit or stand? ");

			String choice = input.nextLine();

			if (choice.equalsIgnoreCase("hit")) {
				int card = deck[4];
				String suit = suits[card / 13];
				String rank = ranks[card % 13];
				int value = values[card % 13];

				System.out.println("You were dealt the " + rank + " of " + suit);
				playerTotal += value;

				if (playerTotal > 21) {
					System.out.println("You busted!");
					playerBust = true;
					break;
				}
			} else {
				break;
			}
		}

		if (!playerBust) {
			System.out.println("Dealer reveals his hidden card: ");

			for (int i = 0; i < 2; i++) {
				int card = deck[i];
				String suit = suits[card / 13];
				String rank = ranks[card % 13];
				int value = values[card % 13];

				if (i == 0) {
					System.out.println("Dealer's hidden card was " + rank + " of " + suit);
				} else {
					dealerTotal += value;
					System.out.println("Dealer's other card was " + rank + " of " + suit);
				}
			}

			while (dealerTotal < 17) {
				int card = deck[4];
				String suit = suits[card / 13];
				String rank = ranks[card % 13];
				int value = values[card % 13];

				System.out.println("Dealer hits and gets the " + rank + " of " + suit);
				dealerTotal += value;

				if (dealerTotal > 21) {
					System.out.println("Dealer busts!");
					break;
				}
			}

			System.out.println("Your total: " + playerTotal);
			System.out.println("Dealer's total: " + dealerTotal);

			if (playerBust) {
				System.out.println("You lose!");
			} else if (dealerTotal > playerTotal) {
				System.out.println("Dealer wins!");
			} else if (playerTotal > dealerTotal) {
				System.out.println("You win!");
			} else {
				System.out.println("It's a tie!");
			}
		}
	}
}