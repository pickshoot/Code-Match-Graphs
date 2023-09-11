package JRB_B03;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");

		// Prompt the player for their name
		System.out.print("Enter your name: ");
		String playerName = sc.nextLine();

		// Initialize the deck
		List<String> deck = new ArrayList<>();
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Deal the cards
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();

		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Display the player's hand
		System.out.println("Your cards:");
		for (String card : playerHand) {
			System.out.println("    " + card);
		}

		// Display the dealer's hand
		System.out.println("Dealer's hand:");
		System.out.println("    " + dealerHand.get(0));
		System.out.println("    [hidden]");

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = sc.nextLine();

			if (input.equals("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your cards:");
				for (String card : playerHand) {
					System.out.println("    " + card);
				}

				// Check if player busts
				int playerScore = getScore(playerHand);
				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's cards:");
		for (String card : dealerHand) {
			System.out.println("    " + card);
		}
		while (getScore(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer hits.");
			System.out.println("Dealer's cards:");
			for (String card : dealerHand) {
				System.out.println("    " + card);
			}
		}

		// Determine the winner
		int playerScore = getScore(playerHand);
		int dealerScore = getScore(dealerHand);
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer's score: " + dealerScore);

		if (dealerScore > 21 || (playerScore <= 21 && playerScore > dealerScore)) {
			System.out.println("You win!");
		} else if (playerScore > 21 || dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static int getScore(List<String> hand) {
		int score = 0;
		int numAces = 0;

		for (int i = 0; i < hand.size(); i++) {
			String card = hand.get(i);
			String rank = card.split(" ")[0];
			int value;

			if (rank.equals("A")) {
				numAces++;
				value = 11;
			} else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
				value = 10;
			} else {
				value = Integer.parseInt(rank);
			}

			score += value;
		}

		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}

		return score;
	}
}