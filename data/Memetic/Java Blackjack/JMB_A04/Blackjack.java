package A4;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		// Initialize the deck of cards
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		List<String> deck = new ArrayList<>();
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				deck.add(ranks[i] + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Deal the initial hands
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Play the game
		boolean playerBust = false;
		boolean dealerBust = false;
		boolean gameOver = false;
		while (!gameOver) {
			// Show the hands
			System.out.println("Your hand: " + playerHand);
			System.out.println("Dealer's hand: " + dealerHand.get(0) + ", [Hidden]");

			// Check for blackjack or bust
			int playerTotal = getTotal(playerHand);
			int dealerTotal = getTotal(dealerHand);
			if (playerTotal == 21) {
				System.out.println("Blackjack! You win!");
				gameOver = true;
			} else if (playerTotal > 21) {
				System.out.println("Bust! You lose.");
				playerBust = true;
				gameOver = true;
			} else if (dealerTotal == 21) {
				System.out.println("Dealer has blackjack! You lose.");
				gameOver = true;
			} else if (dealerTotal > 21) {
				System.out.println("Dealer busts! You win!");
				dealerBust = true;
				gameOver = true;
			}

			// Ask the player for their move
			if (!gameOver) {
				System.out.print("Hit or stand? ");
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("hit")) {
					playerHand.add(deck.remove(0));
				} else {
					gameOver = true;
				}
			}
		}

		// Dealer's turn
		if (!playerBust && !dealerBust) {
			while (getTotal(dealerHand) < 17) {
				dealerHand.add(deck.remove(0));
			}

			// Show the hands
			System.out.println("Your hand: " + playerHand);
			System.out.println("Dealer's hand: " + dealerHand);

			// Determine the winner
			int playerTotal = getTotal(playerHand);
			int dealerTotal = getTotal(dealerHand);
			if (dealerTotal > 21) {
				System.out.println("Dealer busts! You win!");
			} else if (dealerTotal > playerTotal) {
				System.out.println("Dealer wins.");
			} else if (dealerTotal < playerTotal) {
				System.out.println("You win!");
			} else if (dealerTotal == playerTotal) {
				System.out.println("Push.");
			} else {
				System.out.println("You lose.");
			}
		}
	}

	public static int getTotal(List<String> hand) {
		int total = 0;
		int numAces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			int value = getValue(rank);
			if (value == 11) {
				numAces++;
			}
			total += value;
		}
		while (numAces > 0 && total > 21) {
			total -= 10;
			numAces--;
		}
		return total;
	}

	public static int getValue(String rank) {
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