package JDB_A02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerCards;
	private static ArrayList<Card> dealerCards;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");

		// Deal the initial cards
		playerCards.add(deck.drawCard());
		playerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());

		// Allow the player to hit or stand until they bust or stand
		while (true) {
			// Display the current hands
			System.out.println("Your hand: " + playerCards);
			System.out.println("Dealer's hand: " + dealerCards.get(0));

			// Check if the player has busted
			int playerTotal = getTotal(playerCards);
			if (playerTotal > 21) {
				System.out.println("You bust!");
				break;
			}

			// Prompt the player to hit or stand
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("hit")) {
				// Deal another card to the player
				playerCards.add(deck.drawCard());
			} else if (input.equalsIgnoreCase("stand")) {
				// The player stands, so the dealer takes their turn
				int dealerTotal = getTotal(dealerCards);
				while (dealerTotal < 17) {
					// The dealer hits
					dealerCards.add(deck.drawCard());
					dealerTotal = getTotal(dealerCards);
				}
				System.out.println("Dealer's hand: " + dealerCards);

				// Check if the dealer has busted
				if (dealerTotal > 21) {
					System.out.println("Dealer busts! You win!");
				} else {
					// Determine the winner based on the totals
					if (playerTotal > dealerTotal) {
						System.out.println("You win!");
					} else if (playerTotal < dealerTotal) {
						System.out.println("Dealer wins!");
					} else {
						System.out.println("It's a tie!");
					}
				}
				break;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}

		// Thank the player for playing
		System.out.println("Thank you for playing Blackjack!");
	}

	// Calculate the total value of a hand of cards
	private static int getTotal(ArrayList<Card> cards) {
		int total = 0;
		int numAces = 0;
		for (Card card : cards) {
			int value = card.getValue();
			if (value == 1) {
				numAces++;
			}
			total += value;
		}
		while (numAces > 0 && total <= 11) {
			total += 10;
			numAces--;
		}
		return total;
	}
}

enum Suit {
	DIAMONDS, CLUBS, HEARTS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("Deck is empty");
		}
		return cards.remove(0);
	}
}

class Card {
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public int getValue() {
		return rank.getValue();
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}