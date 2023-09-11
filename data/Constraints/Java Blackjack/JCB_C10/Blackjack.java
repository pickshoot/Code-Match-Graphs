package JCB_C10;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blackjack {

	// Declare variables
	private static List<Integer> playerCards = new ArrayList<Integer>();
	private static List<Integer> dealerCards = new ArrayList<Integer>();
	private static int playerTotal = 0;
	private static int dealerTotal = 0;
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();

	public static void main(String[] args) {

		// Start the game
		System.out.println("Welcome to Blackjack!");
		dealCards();

		// Player's turn
		while (playerTotal < 21) {
			System.out.println("Your cards: " + playerCards.toString());
			System.out.println("Your total: " + playerTotal);
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				hit(playerCards);
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Check if the player busts
		if (playerTotal > 21) {
			System.out.println("Bust! You lose.");
			return;
		}

		// Dealer's turn
		while (dealerTotal < 17) {
			hit(dealerCards);
		}
		System.out.println("Dealer's cards: " + dealerCards.toString());
		System.out.println("Dealer's total: " + dealerTotal);

		// Determine the winner
		if (dealerTotal > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Dealer wins.");
		} else if (dealerTotal < playerTotal) {
			System.out.println("You win!");
		} else {
			System.out.println("Push.");
		}
	}

	// Deal the initial two cards to the player and dealer
	private static void dealCards() {
		hit(playerCards);
		hit(dealerCards);
		hit(playerCards);
		hit(dealerCards);
		System.out.println("Dealer's face-up card: " + dealerCards.get(0));
		System.out.println("Your cards: " + playerCards.toString());
		playerTotal = getHandTotal(playerCards);
		dealerTotal = dealerCards.get(0);
	}

	// Draw a card from the deck and add it to the specified hand
	private static void hit(List<Integer> hand) {
		int card = random.nextInt(10) + 1;
		hand.add(card);
		if (hand == playerCards) {
			playerTotal = getHandTotal(playerCards);
		} else {
			dealerTotal = getHandTotal(dealerCards);
		}
	}

	// Calculate the total value of a hand
	private static int getHandTotal(List<Integer> hand) {
		int total = 0;
		int numAces = 0;
		for (int card : hand) {
			if (card == 1) {
				numAces++;
				total += 11;
			} else if (card >= 10) {
				total += 10;
			} else {
				total += card;
			}
		}
		// Handle aces
		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}
		return total;
	}
}