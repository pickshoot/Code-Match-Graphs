package JDB_B02;

import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// create a deck of cards
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		String[] deck = new String[52];
		int deckIndex = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				deck[deckIndex] = ranks[j] + " of " + suits[i];
				deckIndex++;
			}
		}

		// shuffle the deck
		for (int i = 0; i < deck.length; i++) {
			int randomIndex = (int) (Math.random() * deck.length);
			String temp = deck[i];
			deck[i] = deck[randomIndex];
			deck[randomIndex] = temp;
		}

		// deal the cards
		int playerTotal = 0;
		int dealerTotal = 0;
		int playerCards = 0;
		int dealerCards = 0;
		boolean playerHasAce = false;
		boolean dealerHasAce = false;
		while (playerCards < 2) {
			String card = deck[0];
			deck = removeCard(deck, 0);
			int value = getValue(card);
			if (value == 11) {
				playerHasAce = true;
			}
			playerTotal += value;
			playerCards++;
			System.out.println("You were dealt the " + card);
		}
		while (dealerCards < 2) {
			String card = deck[0];
			deck = removeCard(deck, 0);
			int value = getValue(card);
			if (value == 11) {
				dealerHasAce = true;
			}
			dealerTotal += value;
			dealerCards++;
			if (dealerCards == 1) {
				System.out.println("The dealer was dealt the " + card);
			} else {
				System.out.println("The dealer was dealt a face-down card.");
			}
		}

		// player's turn
		boolean playerBust = false;
		while (!playerBust) {
			System.out.println("Your total is " + playerTotal + ".");
			System.out.println("Do you want to hit or stand? (h/s)");
			String choice = scanner.nextLine();
			if (choice.equals("h")) {
				String card = deck[0];
				deck = removeCard(deck, 0);
				int value = getValue(card);
				if (value == 11) {
					playerHasAce = true;
				}
				playerTotal += value;
				playerCards++;
				System.out.println("You were dealt the " + card);
				if (playerTotal > 21 && playerHasAce) {
					playerTotal -= 10;
					playerHasAce = false;
				}
				if (playerTotal > 21) {
					System.out.println("You busted!");
					playerBust = true;
				}
			} else {
				break;
			}
		}

		// dealer's turn
		boolean dealerBust = false;
		while (dealerTotal < 17) {
			String card = deck[0];
			deck = removeCard(deck, 0);
			int value = getValue(card);
			if (value == 11) {
				dealerHasAce = true;
			}
			dealerTotal += value;
			dealerCards++;
			System.out.println("The dealer was dealt the " + card);
			if (dealerTotal > 21 && dealerHasAce) {
				dealerTotal -= 10;
				dealerHasAce = false;
			}
			if (dealerTotal > 21) {
				System.out.println("The dealer busted!");
				dealerBust = true;
			}
		}
		// determine the winner
		if (!playerBust && (playerTotal > dealerTotal || dealerBust)) {
			System.out.println("Congratulations! You win!");
		} else if (!dealerBust && (dealerTotal > playerTotal || playerBust)) {
			System.out.println("Sorry, you lose.");
		} else {
			System.out.println("It's a tie.");
		}

		scanner.close();
	}

	public static String[] removeCard(String[] deck, int index) {
		String[] newDeck = new String[deck.length - 1];
		for (int i = 0, j = 0; i < deck.length; i++) {
			if (i != index) {
				newDeck[j++] = deck[i];
			}
		}
		return newDeck;
	}

	public static int getValue(String card) {
		String[] parts = card.split(" ");
		String rank = parts[0];
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("2")) {
			return 2;
		} else if (rank.equals("3")) {
			return 3;
		} else if (rank.equals("4")) {
			return 4;
		} else if (rank.equals("5")) {
			return 5;
		} else if (rank.equals("6")) {
			return 6;
		} else if (rank.equals("7")) {
			return 7;
		} else if (rank.equals("8")) {
			return 8;
		} else if (rank.equals("9")) {
			return 9;
		} else {
			return 10;
		}
	}
}