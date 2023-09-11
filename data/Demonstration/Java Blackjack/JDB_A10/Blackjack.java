package JDB_A10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerHand;
	private static ArrayList<Card> dealerHand;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();

		System.out.println("Welcome to Blackjack game!");
		Scanner scanner = new Scanner(System.in);

		// Initialize the player and dealer hands
		playerHand = new ArrayList<Card>();
		dealerHand = new ArrayList<Card>();

		// Deal two cards to the player and one card to the dealer
		playerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// Loop until the player busts, stands, or quits
		boolean playerBusted = false;
		boolean playerStands = false;
		while (!playerBusted && !playerStands) {
			// Print the current status of the game
			System.out.println("\n----- Current Game Status -----");
			System.out.println("Player Hand: " + playerHand);
			System.out.println("Dealer Hand: " + dealerHand.get(0) + ", <hidden>");

			// Prompt the user to hit, stand, or quit
			System.out.print("\nPress “h” to hit, “s” to stand, or “q” to quit: ");

			// Read the user input
			String input = scanner.nextLine();

			// Handle the user input
			if (input.equals("q")) {
				// If the user quits, exit the loop
				break;
			} else if (input.equals("s")) {
				// If the user stands, exit the loop
				playerStands = true;
			} else if (input.equals("h")) {
				// If the user hits, draw another card and add it to the player's hand
				Card newCard = deck.drawCard();
				playerHand.add(newCard);
				System.out.println("You drew a " + newCard);

				// Check if the player has busted
				if (getHandValue(playerHand) > 21) {
					System.out.println("\n----- Current Game Status -----");
					System.out.println("Player Hand: " + playerHand);
					System.out.println("Dealer Hand: " + dealerHand);
					System.out.println("You busted! Game over.");
					playerBusted = true;
				}
			} else {
				System.out.println("Invalid input. Try again.");
			}
		}

		// If the player has not busted or quit, play the dealer's hand
		if (!playerBusted) {
			while (getHandValue(dealerHand) < 17) {
				// If the dealer's hand is less than17, draw another card and add it to the
				// dealer's hand
				Card newCard = deck.drawCard();
				dealerHand.add(newCard);
				System.out.println("Dealer drew a " + newCard);
			}
			// Print the final status of the game
			System.out.println("\n----- Final Game Status -----");
			System.out.println("Player Hand: " + playerHand);
			System.out.println("Dealer Hand: " + dealerHand);

			// Determine the winner
			int playerValue = getHandValue(playerHand);
			int dealerValue = getHandValue(dealerHand);
			if (playerValue > 21) {
				System.out.println("You busted! Dealer wins!");
			} else if (dealerValue > 21) {
				System.out.println("Dealer busted! You win!");
			} else if (playerValue > dealerValue) {
				System.out.println("You win!");
			} else if (playerValue < dealerValue) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}
		scanner.close();
	}

	private static int getHandValue(ArrayList<Card> hand) {
		int value = 0;
		boolean hasAce = false;
		for (Card card : hand) {
			if (card.getRank() == 1) {
				hasAce = true;
			}
			value += card.getValue();
		}
		if (hasAce && value + 10 <= 21) {
			value += 10;
		}
		return value;
	}
}

class Card {
	private final int rank;
	private final int suit;

	// Constructor for Card class
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	// Getter methods for rank and suit
	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	// Getter method for value of the card
	public int getValue() {
		// Face cards have a value of 10
		if (rank > 10) {
			return 10;
		}
		// Ace has a value of 11, unless it causes the hand to bust
		else if (rank == 1) {
			return 11;
		} else {
			return rank;
		}
	}

	// Method to return a String representation of the card
	public String toString() {
		String rankString;
		String suitString;

		// Convert rank to String
		switch (rank) {
		case 1:
			rankString = "Ace";
			break;
		case 11:
			rankString = "Jack";
			break;
		case 12:
			rankString = "Queen";
			break;
		case 13:
			rankString = "King";
			break;
		default:
			rankString = Integer.toString(rank);
			break;
		}

		// Convert the rank and suit integers to their corresponding Strings
		switch (suit) {
		case 1:
			suitString = "Clubs";
			break;
		case 2:
			suitString = "Diamonds";
			break;
		case 3:
			suitString = "Hearts";
			break;
		case 4:
			suitString = "Spades";
			break;
		default:
			suitString = "Invalid Suit";
			break;
		}

		// Return the String representation of the card
		return rankString + " of " + suitString;
	}
}

class Deck {
	private final ArrayList<Card> cards;

	// Constructor for Deck class
	public Deck() {
		cards = new ArrayList<Card>();
		for (int suit = 1; suit <= 4; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				Card card = new Card(rank, suit);
				cards.add(card);
			}
		}
	}

	// Method to shuffle the deck
	public void shuffle() {
		Collections.shuffle(cards);
	}

	// Method to draw a card from the deck
	public Card drawCard() {
		return cards.remove(0);
	}
}
