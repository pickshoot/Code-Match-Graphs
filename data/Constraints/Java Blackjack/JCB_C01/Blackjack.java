package JCB_C01;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Initialize deck of cards
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		List<String> deck = new ArrayList<>();
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Initialize game variables
		int playerScore = 0;
		int dealerScore = 0;
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();

		// Deal two cards to the player and two cards to the dealer
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Show the player's cards and the dealer's first card
		System.out.println("Your cards: " + playerHand);
		System.out.println("Dealer's cards: " + dealerHand.get(0));

		// Player's turn
		while (true) {
			System.out.print("Hit or stand (String input): ");
			String input = sc.nextLine().toLowerCase();
			if (input.equals("hit")) {
				// Deal another card to the player
				playerHand.add(deck.remove(0));
				System.out.println("Your cards: " + playerHand);
				// Calculate player's score
				playerScore = calculateScore(playerHand);
				if (playerScore > 21) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			} else if (input.equals("stand")) {
				// Player stands, dealer's turn
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}
		// Dealer's turn
		System.out.println("Dealer's cards: " + dealerHand);
		dealerScore = calculateScore(dealerHand);
		while (dealerScore < 17) {
			// Dealer hits if score is less than 17
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer hits.");
			System.out.println("Dealer's cards: " + dealerHand);
			dealerScore = calculateScore(dealerHand);
			if (dealerScore > 21) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// Determine the winner
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	public static int calculateScore(List<String> hand) {
		int score = 0;
		int aces = 0;
		for (String card : hand) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				aces++;
			}
			int value = getValue(rank);
			score += value;
		}
		while (score > 21 && aces > 0) {
			score -= 10;
			aces--;
		}
		return score;
	}

	public static int getValue(String rank) {
		switch (rank) {
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			return Integer.parseInt(rank);
		case "Jack":
		case "Queen":
		case "King":
			return 10;
		case "Ace":
			return 11;
		default:
			return 0;
		}
	}
}