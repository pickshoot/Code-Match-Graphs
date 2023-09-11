package JCB_C06;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int playerScore = 0;
		int dealerScore = 0;
		int playerCards = 0;
		int dealerCards = 0;
		int[] deck = createDeck();

		// Deal two cards to the player and dealer
		playerScore += drawCard(deck);
		dealerScore += drawCard(deck);
		playerScore += drawCard(deck);
		dealerScore += drawCard(deck);
		playerCards += 2;
		dealerCards += 2;

		// Player's turn
		while (true) {
			System.out.print("Your current score is " + playerScore + ". Do you want to hit or stand? ");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("hit")) {
				playerScore += drawCard(deck);
				playerCards++;
				if (playerScore > 21) {
					System.out.println("Busted! Your score is " + playerScore + ".");
					return;
				}
			} else if (choice.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			dealerScore += drawCard(deck);
			dealerCards++;
		}

		// Determine winner
		System.out.println("Your score is " + playerScore + ".");
		System.out.println("The dealer's score is " + dealerScore + ".");
		if (dealerScore > 21) {
			System.out.println("Dealer busted. You win!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore == dealerScore) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Dealer wins.");
		}
	}

	// Creates a new deck of cards
	public static int[] createDeck() {
		int[] deck = new int[52];
		for (int i = 0; i < 52; i++) {
			deck[i] = i % 13 + 1;
		}
		shuffleDeck(deck);
		return deck;
	}

	// Shuffles the deck
	public static void shuffleDeck(int[] deck) {
		Random rand = new Random();
		for (int i = 0; i < 52; i++) {
			int j = rand.nextInt(52);
			int temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
	}

	// Draws a card from the deck and returns its value
	public static int drawCard(int[] deck) {
		int card = deck[0];
		for (int i = 0; i < 51; i++) {
			deck[i] = deck[i + 1];
		}
		deck[51] = card;
		int value = Math.min(card, 10);
		return value;
	}
}