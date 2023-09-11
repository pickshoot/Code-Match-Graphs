package JRB_B02;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		// Prompt the player for their name
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();
		System.out.println("Hello, " + playerName + "! Let's play Blackjack.");

		// Initialize the deck of cards
		ArrayList<String> deck = new ArrayList<>();
		for (int i = 2; i <= 10; i++) {
			deck.add(i + " of Hearts");
			deck.add(i + " of Diamonds");
			deck.add(i + " of Clubs");
			deck.add(i + " of Spades");
		}
		deck.add("Jack of Hearts");
		deck.add("Jack of Diamonds");
		deck.add("Jack of Clubs");
		deck.add("Jack of Spades");
		deck.add("Queen of Hearts");
		deck.add("Queen of Diamonds");
		deck.add("Queen of Clubs");
		deck.add("Queen of Spades");
		deck.add("King of Hearts");
		deck.add("King of Diamonds");
		deck.add("King of Clubs");
		deck.add("King of Spades");
		deck.add("Ace of Hearts");
		deck.add("Ace of Diamonds");
		deck.add("Ace of Clubs");
		deck.add("Ace of Spades");

		// Shuffle the deck
		for (int i = 0; i < deck.size(); i++) {
			int j = random.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}

		// Deal the initial cards
		ArrayList<String> playerCards = new ArrayList<>();
		ArrayList<String> dealerCards = new ArrayList<>();
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));

		// Display the player's cards and the dealer's first card
		System.out.println("Your cards:");
		for (String card : playerCards) {
			System.out.println("  " + card);
		}
		System.out.println("Dealer's cards:");
		System.out.println("  " + dealerCards.get(0));
		System.out.println("  [face down]");

		// Player's turn
		int playerScore = getScore(playerCards);
		while (playerScore < 21) {
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine();
			if (choice.equals("hit")) {
				playerCards.add(deck.remove(0));
				System.out.println("Your cards:");
				for (String card : playerCards) {
					System.out.println("  " + card);
				}
				playerScore = getScore(playerCards);
			} else if (choice.equals("stand")) {
				break;
			}
		}

		// Dealer's turn
		int dealerScore = getScore(dealerCards);
		while (dealerScore < 17) {
			dealerCards.add(deck.remove(0));
			dealerScore = getScore(dealerCards);
		}
		System.out.println("Dealer's cards:");
		for (String card : dealerCards) {
			System.out.println("  " + card);
		}

		// Determine the winner
		if (playerScore > 21) {
			System.out.println("You busted! Dealer wins.");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busted! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	// Helper method to calculate the score of a hand of cards
	private static int getScore(ArrayList<String> cards) {
		int score = 0;
		int numAces = 0;
		for (String card : cards) {
			String rank = card.split(" ")[0];
			if (rank.equals("Ace")) {
				numAces++;
				score += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				score += 10;
			} else {
				score += Integer.parseInt(rank);
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}
}