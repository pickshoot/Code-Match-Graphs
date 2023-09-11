package JDB_B03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Set up the deck
		List<Card> deck = newDeck();

		// Shuffle the deck
		Collections.shuffle(deck);

		// Set up the player and dealer hands
		List<Card> playerHand = new ArrayList<>();
		List<Card> dealerHand = new ArrayList<>();

		// Deal the initial cards
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Show the player's hand and the dealer's up card
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's up card: " + dealerHand.get(0));

		// Player's turn
		while (true) {
			System.out.println("Hit or stand? (h/s)");
			String choice = scanner.nextLine();
			if (choice.equals("h")) {
				// Player hits
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else {
				// Player stands
				break;
			}
		}

		// Dealer's turn
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer's hand: " + dealerHand);
			if (getHandValue(dealerHand) > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Determine the winner
		int playerValue = getHandValue(playerHand);
		int dealerValue = getHandValue(dealerHand);
		if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}

		scanner.close();
	}

	private static List<Card> newDeck() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		return deck;
	}

	private static int getHandValue(List<Card> hand) {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}
			value += card.getRank().getValue();
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}

}

class Card {

	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

enum Rank {
	ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	SPADES, HEARTS, CLUBS, DIAMONDS;
}
