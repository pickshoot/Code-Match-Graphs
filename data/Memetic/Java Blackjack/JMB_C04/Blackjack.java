package C4;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		// Create a deck of cards
		List<String> deck = new ArrayList<>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Initialize variables for the game
		int playerScore = 0;
		int dealerScore = 0;
		int playerCards = 0;
		int dealerCards = 0;
		Scanner scanner = new Scanner(System.in);

		// Deal two cards to the player and two cards to the dealer
		for (int i = 0; i < 2; i++) {
			String card = deck.get(0);
			System.out.println("Player was dealt " + card);
			playerScore += getCardValue(card);
			playerCards++;
			deck.remove(0);

			card = deck.get(0);
			if (i == 0) {
				System.out.println("Dealer was dealt " + card);
			} else {
				System.out.println("Dealer's second card is hidden");
			}
			if (i == 1) {
				dealerScore += getCardValue(card);
			}
			dealerCards++;
			deck.remove(0);
		}

		// Player's turn
		while (true) {
			System.out.println("Your score is " + playerScore);
			System.out.println("Do you want to hit or stand?");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				String card = deck.get(0);
				System.out.println("You were dealt " + card);
				playerScore += getCardValue(card);
				playerCards++;
				deck.remove(0);
				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			String card = deck.get(0);
			System.out.println("Dealer was dealt " + card);
			dealerScore += getCardValue(card);
			dealerCards++;
			deck.remove(0);
			if (dealerScore > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare scores
		System.out.println("Player's score is " + playerScore);
		System.out.println("Dealer's score is " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int getCardValue(String card) {
		int value = 0;
		String rank = card.split(" ")[0];
		if (rank.equals("Ace")) {
			value = 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
			value = 10;
		} else {
			value = Integer.parseInt(rank);
		}
		return value;
	}
}