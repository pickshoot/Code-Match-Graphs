package JRB_B01;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String playerName = sc.nextLine();

		// Create deck of cards
		List<String> deck = new ArrayList<>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		Collections.shuffle(deck);

		// Deal cards to player and dealer
		List<String> playerCards = new ArrayList<>();
		List<String> dealerCards = new ArrayList<>();
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));

		// Display player's cards and dealer's first card
		System.out.println("\n" + playerName + "'s cards: " + playerCards);
		System.out.println("Dealer's cards: [Face Down, " + dealerCards.get(1) + "]");

		// Player's turn
		boolean playerDone = false;
		while (!playerDone) {
			System.out.print("\n" + playerName + ", would you like to hit or stand? ");
			String response = sc.nextLine().toLowerCase();
			if (response.equals("hit")) {
				playerCards.add(deck.remove(0));
				System.out.println("\n" + playerName + "'s cards: " + playerCards);
				if (getTotal(playerCards) > 21) {
					System.out.println("Bust! " + playerName + " loses.");
					return;
				}
			} else if (response.equals("stand")) {
				playerDone = true;
			} else {
				System.out.println("Invalid response. Please enter \"hit\" or \"stand\".");
			}
		}

		// Dealer's turn
		System.out.println("\nDealer's cards: " + dealerCards);
		while (getTotal(dealerCards) < 17) {
			dealerCards.add(deck.remove(0));
			System.out.println("Dealer hits. Dealer's cards: " + dealerCards);
		}

		// Determine winner
		int playerTotal = getTotal(playerCards);
		int dealerTotal = getTotal(dealerCards);
		if (playerTotal > 21) {
			System.out.println("Bust! " + playerName + " loses.");
		} else if (dealerTotal > 21) {
			System.out.println("Dealer busts! " + playerName + " wins.");
		} else if (playerTotal > dealerTotal) {
			System.out.println(playerName + " wins!");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	// Helper method to calculate the total score of a hand of cards
	public static int getTotal(List<String> cards) {
		int total = 0;
		int numAces = 0;
		for (String card : cards) {
			String rank = card.substring(0, card.indexOf(" "));
			if (rank.equals("Ace")) {
				numAces++;
				total += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				total += 10;
			} else {
				total += Integer.parseInt(rank);
			}
		}
		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}
		return total;
	}
}