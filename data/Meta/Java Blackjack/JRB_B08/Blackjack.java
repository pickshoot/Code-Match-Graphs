package JRB_B08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static final int BLACKJACK = 21;
	private static final int DEALER_STAND = 17;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Prompt for player name
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();

		// Initialize deck and shuffle
		ArrayList<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(deck);

		// Deal initial hands
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		playerHand.addCard(deck.remove(0));
		dealerHand.addCard(deck.remove(0));
		playerHand.addCard(deck.remove(0));
		dealerHand.addCard(deck.remove(0));

		// Display hands
		System.out.println("Dealer's Hand:");
		System.out.println("HIDDEN CARD");
		System.out.println(dealerHand.getCards().get(1));
		System.out.println();
		System.out.println(playerName + "'s Hand:");
		playerHand.printHand();

		// Player's turn
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				playerHand.addCard(deck.remove(0));
				System.out.println(playerName + "'s Hand:");
				playerHand.printHand();
				if (playerHand.getScore() > BLACKJACK) {
					System.out.println("Busted! You lose.");
					return;
				}
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's Hand:");
		dealerHand.printHand();
		while (dealerHand.getScore() < DEALER_STAND) {
			dealerHand.addCard(deck.remove(0));
			System.out.println("Dealer hits.");
			System.out.println("Dealer's Hand:");
			dealerHand.printHand();
		}
		if (dealerHand.getScore() > BLACKJACK) {
			System.out.println("Dealer busts! You win!");
			return;
		}

		// Compare scores
		int playerScore = playerHand.getScore();
		int dealerScore = dealerHand.getScore();
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private final int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES;
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

	public int getValue() {
		return rank.getValue();
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

class Hand {
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public int getScore() {
		int score = 0;
		boolean hasAce = false;
		for (Card card : cards) {
			int value = card.getValue();
			score += value;
			if (value == 1) {
				hasAce = true;
			}
		}
		if (hasAce && score <= 11) {
			score += 10;
		}
		return score;
	}

	public void printHand() {
		for (Card card : cards) {
			System.out.println(card);
		}
		System.out.println("Total score: " + getScore());
		System.out.println();
	}
}