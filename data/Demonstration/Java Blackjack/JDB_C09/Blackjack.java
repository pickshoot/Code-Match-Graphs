package JDB_C09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private static final int MAX_SCORE = 21;
	private static final int DEALER_MIN_SCORE = 17;
	private static final int NUM_DECKS = 1;
	private static final int NUM_SUITS = 4;
	private static final int NUM_RANKS = 13;
	private List<Card> deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;

	public Blackjack() {
		deck = new ArrayList<>();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		initializeDeck();
		shuffleDeck();
	}

	private void initializeDeck() {
		for (int i = 0; i < NUM_DECKS; i++) {
			for (int j = 0; j < NUM_SUITS; j++) {
				for (int k = 0; k < NUM_RANKS; k++) {
					deck.add(new Card(j, k));
				}
			}
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private void dealInitialCards() {
		for (int i = 0; i < 2; i++) {
			playerHand.add(deck.remove(0));
			dealerHand.add(deck.remove(0));
		}
	}

	private int calculateHandValue(List<Card> hand) {
		int total = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.rank == 0) { // Ace
				numAces++;
				total += 11;
			} else if (card.rank >= 9) { // 10, Jack, Queen, King
				total += 10;
			} else { // 2-9
				total += (card.rank + 2);
			}
		}
		while (numAces > 0 && total > MAX_SCORE) {
			total -= 10;
			numAces--;
		}
		return total;
	}

	private void displayHands(boolean showDealer) {
		System.out.println("Dealer's hand:");
		if (showDealer) {
			for (Card card : dealerHand) {
				System.out.println(card);
			}
		} else {
			System.out.println("[hidden]");
			System.out.println(dealerHand.get(1));
		}
		System.out.println("Player's hand:");
		for (Card card : playerHand) {
			System.out.println(card);
		}
		System.out.println("Player's total: " + calculateHandValue(playerHand));
	}

	private boolean isBust(List<Card> hand) {
		return calculateHandValue(hand) > MAX_SCORE;
	}

	private void playDealerTurn() {
		while (calculateHandValue(dealerHand) < DEALER_MIN_SCORE) {
			dealerHand.add(deck.remove(0));
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			System.out.println("New round");
			playerHand.clear();
			dealerHand.clear();
			dealInitialCards();
			displayHands(false); // hide one of the dealer's cards
			// check for player blackjack
			if (calculateHandValue(playerHand) == MAX_SCORE) {
				System.out.println("Blackjack! You win!");
				gameOver = true;
				continue;
			}
			// player's turn
			while (true) {
				System.out.print("Do you want to hit (h) or stand (s)? ");
				String input = scanner.nextLine().trim();
				if (input.equalsIgnoreCase("h")) {
					playerHand.add(deck.remove(0));
					displayHands(false); // hide one of the dealer's cards
					if (isBust(playerHand)) {
						System.out.println("Bust! You lose.");
						gameOver = true;
						break;
					}
				} else if (input.equalsIgnoreCase("s")) {
					break;
				} else {
					System.out.println("Invalid input, please try again.");
				}
			}
			if (gameOver) {
				continue;
			}
			// dealer's turn
			playDealerTurn();
			displayHands(true);
			if (isBust(dealerHand)) {
				System.out.println("Dealer busts! You win!");
				gameOver = true;
				continue;
			}
			int playerScore = calculateHandValue(playerHand);
			int dealerScore = calculateHandValue(dealerHand);
			if (playerScore > dealerScore) {
				System.out.println("You win!");
			} else if (playerScore < dealerScore) {
				System.out.println("You lose.");
			} else {
				System.out.println("It's a tie.");
			}
			gameOver = true;
		}
		scanner.close();
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Card {
	private static final String[] SUITS = { "Spades", "Hearts", "Diamonds", "Clubs" };
	private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
			"King" };
	int suit;
	int rank;

	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String toString() {
		return RANKS[rank] + " of " + SUITS[suit];
	}
}
