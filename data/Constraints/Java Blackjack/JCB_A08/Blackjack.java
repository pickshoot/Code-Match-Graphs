package JCB_A08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	private List<Card> deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerScore;
	private int dealerScore;

	public Blackjack() {
		this.deck = new ArrayList<>();
		this.playerHand = new ArrayList<>();
		this.dealerHand = new ArrayList<>();
		this.playerScore = 0;
		this.dealerScore = 0;
	}

	public void start() {
		System.out.println("Welcome to Blackjack!");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();
		System.out.println("Hello, " + playerName + "!\n");

		// Create and shuffle a deck of cards
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(deck);

		// Deal two cards to the player and dealer
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Show the initial hands
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", **]");
		System.out.println("Your hand: " + playerHand);

		// Player's turn
		while (playerScore < 21) {
			System.out.print("Hit or stand? (h/s): ");
			String choice = scanner.nextLine();
			if (choice.equals("h")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				playerScore = calculateScore(playerHand);
			} else {
				break;
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			dealerHand.add(deck.remove(0));
			dealerScore = calculateScore(dealerHand);
		}

		// Show the final hands
		System.out.println("Dealer's hand: " + dealerHand);
		System.out.println("Your hand: " + playerHand);

		// Determine the winner
		if (playerScore > 21) {
			System.out.println("Bust! You lose.");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	private int calculateScore(List<Card> hand) {
		int score = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}
			score += card.getRank().getValue();
		}
		while (numAces > 0 && score > 21) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.start();
	}

}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
	ACE(11);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
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
		return "[" + rank + " of " + suit + "]";
	}
}