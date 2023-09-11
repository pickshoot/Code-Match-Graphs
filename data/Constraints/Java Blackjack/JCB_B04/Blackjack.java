package JCB_B04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	// Create constants for the game
	private static final int BLACKJACK_VALUE = 21;
	private static final int DEALER_MINIMUM_VALUE = 17;
	private static final int ACE_VALUE = 11;
	private static final int FACE_CARD_VALUE = 10;
	private static final int NUM_DECKS = 6;
	private static final int NUM_CARDS_IN_DECK = 52;

	// Create a deck of cards
	private static List<Card> deck = new ArrayList<>();

	// Create a list to hold the players
	private static List<Player> players = new ArrayList<>();

	// Create a scanner object for user input
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// Initialize the deck of cards
		initializeDeck();

		// Shuffle the deck
		shuffleDeck();

		// Get the number of players from the user
		System.out.print("Enter the number of players: ");
		int numPlayers = scanner.nextInt();

		// Create the players
		createPlayers(numPlayers);

		// Deal the initial cards
		dealInitialCards();

		// Play the game
		playGame();

		// Print the final scores
		printScores();
	}

	// Method to initialize the deck of cards
	private static void initializeDeck() {
		for (int i = 0; i < NUM_DECKS; i++) {
			for (int j = 1; j <= NUM_CARDS_IN_DECK; j++) {
				deck.add(new Card(j));
			}
		}
	}

	// Method to shuffle the deck of cards
	private static void shuffleDeck() {
		Collections.shuffle(deck);
	}

	// Method to create the players
	private static void createPlayers(int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Enter the name of player " + (i + 1) + ": ");
			String name = scanner.next();
			players.add(new Player(name));
		}
	}

	// Method to deal the initial cards
	private static void dealInitialCards() {
		for (Player player : players) {
			// Deal the first two cards to the player
			player.addCardToHand(deck.remove(0));
			player.addCardToHand(deck.remove(0));
		}
	}

	// Method to play the game
	private static void playGame() {
		for (Player player : players) {
			// Print the player's hand
			System.out.println(player.getName() + "'s hand: " + player.getHand());

			// Keep asking the player if they want to hit until they stand or bust
			while (true) {
				System.out.print(player.getName() + ", do you want to hit (h) or stand (s)? ");
				String input = scanner.next();

				if (input.equalsIgnoreCase("h")) {
					// Player wants to hit, so deal them another card
					player.addCardToHand(deck.remove(0));

					// Print the player's hand
					System.out.println(player.getName() + "'s hand: " + player.getHand());

					// Check if the player busts
					if (player.getHandValue() > BLACKJACK_VALUE) {
						System.out.println(player.getName() + " busts!");
						break;
					}
				} else {
					// Player wants to stand, so
					break;
				}
			}
		}

		// Dealer's turn
		Player dealer = new Player("Dealer");

		// Deal the first two cards to the dealer
		dealer.addCardToHand(deck.remove(0));
		dealer.addCardToHand(deck.remove(0));

		// Print the dealer's hand
		System.out.println("Dealer's hand: " + dealer.getHand());

		// Keep hitting until the dealer reaches the minimum value
		while (dealer.getHandValue() < DEALER_MINIMUM_VALUE) {
			// Dealer hits
			dealer.addCardToHand(deck.remove(0));

			// Print the dealer's hand
			System.out.println("Dealer's hand: " + dealer.getHand());

			// Check if the dealer busts
			if (dealer.getHandValue() > BLACKJACK_VALUE) {
				System.out.println("Dealer busts!");
				break;
			}
		}

		// Check who wins
		for (Player player : players) {
			if (player.getHandValue() <= BLACKJACK_VALUE
					&& (dealer.getHandValue() > BLACKJACK_VALUE || player.getHandValue() > dealer.getHandValue())) {
				player.win();
			} else if (player.getHandValue() == dealer.getHandValue()) {
				player.push();
			} else {
				player.lose();
			}
		}
	}

	// Method to print the final scores
	private static void printScores() {
		for (Player player : players) {
			System.out.println(player.getName() + ": " + player.getScore());
		}
	}

	// Class representing a card
	private static class Card {
		private int value;

		public Card(int value) {
			this.value = value;
		}

		public int getValue() {
			// Determine the value of the card based on its rank
			int rank = (value - 1) % 13 + 1;
			if (rank == 1) {
				return ACE_VALUE;
			} else if (rank >= 11 && rank <= 13) {
				return FACE_CARD_VALUE;
			} else {
				return rank;
			}
		}

		@Override
		public String toString() {
			// Determine the string representation of the card based on its value
			int rank = (value - 1) % 13 + 1;
			String suit = "";
			if (value <= 13) {
				suit = "Hearts";
			} else if (value <= 26) {
				suit = "Diamonds";
			} else if (value <= 39) {
				suit = "Clubs";
			} else {
				suit = "Spades";
			}
			if (rank == 1) {
				return "Ace of " + suit;
			} else if (rank == 11) {
				return "Jack of " + suit;
			} else if (rank == 12) {
				return "Queen of " + suit;
			} else if (rank == 13) {
				return "King of " + suit;
			} else {
				return rank + " of " + suit;
			}
		}
	}

	// Class representing a player
	private static class Player {
		private String name;
		private List<Card> hand;
		private int score;

		public Player(String name) {
			this.name = name;
			this.hand = new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public void addCardToHand(Card card) {
			hand.add(card);
		}

		public List<Card> getHand() {
			return hand;
		}

		public int getHandValue() {
			int value = 0;
			int numAces = 0;
			for (Card card : hand) {
				value += card.getValue();
				if (card.getValue() == ACE_VALUE) {
					numAces++;
				}
			}
			// If the hand contains an ace and the value is over 21, convert the ace to a
			// value of 1
			while (numAces > 0 && value > BLACKJACK_VALUE) {
				value -= 10;
				numAces--;
			}
			return value;
		}

		public int getScore() {
			return score;
		}

		public void win() {
			score++;
			System.out.println(name + " wins!");
		}

		public void lose() {
			score--;
			System.out.println(name + " loses!");
		}

		public void push() {
			System.out.println(name + " pushes!");
		}
	}
}