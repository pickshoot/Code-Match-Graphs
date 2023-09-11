package JCB_B03;

import java.util.*;

public class Blackjack {

	// Define constants for card values
	private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
			"King" };
	private static final int[] VALUES = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

	// Initialize deck of cards
	private List<String> deck = new ArrayList<>();

	// Initialize player and dealer hands
	private List<String> playerHand = new ArrayList<>();
	private List<String> dealerHand = new ArrayList<>();

	// Initialize player and dealer scores
	private int playerScore = 0;
	private int dealerScore = 0;

	// Initialize scanner for user input
	private Scanner scanner = new Scanner(System.in);

	// Main method to start game
	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}

	// Method to start game
	public void play() {
		System.out.println("Welcome to Blackjack!");

		// Shuffle deck
		shuffleDeck();

		// Deal initial hands
		dealCards();

		// Show initial hands
		showHands(true);

		// Check for blackjack
		if (checkBlackjack()) {
			return;
		}

		// Player's turn
		while (playerScore < 21) {
			System.out.println("Would you like to hit or stand?");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				hit(playerHand);
				showHands(true);
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// Check for bust
		if (playerScore > 21) {
			System.out.println("Bust! You lose.");
			return;
		}

		// Dealer's turn
		while (dealerScore < 17) {
			hit(dealerHand);
			showHands(false);
		}

		// Check for bust
		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
			return;
		}

		// Compare scores
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("Push!");
		}
	}

	// Method to shuffle deck
	private void shuffleDeck() {
		deck.clear();
		for (int i = 0; i < RANKS.length; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(RANKS[i]);
			}
		}
		Collections.shuffle(deck);
	}

	// Method to deal cards
	private void dealCards() {
		hit(playerHand);
		hit(dealerHand);
		hit(playerHand);
		hit(dealerHand);
	}

	// Method to hit
	private void hit(List<String> hand) {
		String card = deck.remove(0);
		hand.add(card);
		int value = VALUES[Arrays.asList(RANKS).indexOf(card)];
		if (value == 11 && getScore(hand) > 21) {
			value = 1;
		}
		addScore(hand, value);
	}

//Method to show hands
	private void showHands(boolean showDealer) {
		System.out.println("Your hand: " + playerHand);
		System.out.println("Your score: " + playerScore);
		if (showDealer) {
			System.out.println("Dealer's hand: " + dealerHand);
			System.out.println("Dealer's score: " + dealerScore);
		} else {
			System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", **]");
		}
	}

//Method to check for blackjack
	private boolean checkBlackjack() {
		if (playerScore == 21) {
			System.out.println("Blackjack! You win.");
			return true;
		} else if (dealerScore == 21) {
			System.out.println("Dealer has blackjack. You lose.");
			return true;
		}
		return false;
	}

//Method to get score of hand
	private int getScore(List<String> hand) {
		int score = 0;
		for (String card : hand) {
			int value = VALUES[Arrays.asList(RANKS).indexOf(card)];
			score += value;
		}
		return score;
	}

//Method to add score to hand
	private void addScore(List<String> hand, int value) {
		if (hand == playerHand) {
			playerScore += value;
		} else {
			dealerScore += value;
		}
	}
}