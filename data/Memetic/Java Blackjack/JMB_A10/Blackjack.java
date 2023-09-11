package A10;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// create a deck of cards
		int[] deck = new int[52];
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}

		// shuffle the deck
		for (int i = 0; i < deck.length; i++) {
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}

		// initialize game variables
		int dealerTotal = 0;
		int playerTotal = 0;
		int dealerCards = 0;
		int playerCards = 0;

		// deal the cards
		dealerTotal += getCardValue(deck[0]);
		dealerCards++;
		System.out.println("Dealer shows: " + getCardName(deck[0]));

		playerTotal += getCardValue(deck[1]);
		playerCards++;
		System.out.println("You were dealt: " + getCardName(deck[1]));

		playerTotal += getCardValue(deck[2]);
		playerCards++;
		System.out.println("You were dealt: " + getCardName(deck[2]));

		// player's turn
		while (playerTotal < 21) {
			System.out.println("Your total is: " + playerTotal);
			System.out.print("Hit or stand? ");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerTotal += getCardValue(deck[playerCards + 2]);
				playerCards++;
				System.out.println("You were dealt: " + getCardName(deck[playerCards + 2]));
			} else {
				break;
			}
		}

		// dealer's turn
		while (dealerTotal < 17) {
			dealerTotal += getCardValue(deck[dealerCards + 1]);
			dealerCards++;
			System.out.println("Dealer draws: " + getCardName(deck[dealerCards + 1]));
		}

		// determine the winner
		if (playerTotal > 21) {
			System.out.println("You bust!");
		} else if (dealerTotal > 21) {
			System.out.println("Dealer busts! You win!");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (playerTotal < dealerTotal) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

		// print the final hands
		System.out.println("Dealer's hand: " + getCardName(deck[0]) + ", " + getCardName(deck[dealerCards + 1]));
		System.out.print("Your hand: ");
		for (int i = 1; i <= playerCards; i++) {
			System.out.print(getCardName(deck[i + 1]) + ", ");
		}
		System.out.println(getCardName(deck[playerCards + 2]));
	}

	public static int getCardValue(int card) {
		int value = card % 13 + 1;
		if (value > 10) {
			value = 10;
		}
		return value;
	}

	public static String getCardName(int card) {
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		if (card == 0 || card == 13 || card == 26 || card == 39) {
			return ranks[0] + " of " + suits[card / 13];
		} else if (card == 10 || card == 23 || card == 36 || card == 49) {
			return ranks[9] + " of " + suits[card / 13];
		} else if (card == 11 || card == 24 || card == 37 || card == 50) {
			return ranks[10] + " of " + suits[card / 13];
		} else if (card == 12 || card == 25 || card == 38 || card == 51) {
			return ranks[11] + " of " + suits[card / 13];
		} else {
			return ranks[card % 13] + " of " + suits[card / 13];
		}
	}
}