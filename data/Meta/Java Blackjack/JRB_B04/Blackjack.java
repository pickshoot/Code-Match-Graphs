package JRB_B04;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Prompt for player's name
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();

		// Initialize deck and scores
		Deck deck = new Deck();
		int playerScore = 0;
		int dealerScore = 0;

		// Deal two cards to player and dealer
		Card playerCard1 = deck.drawCard();
		Card playerCard2 = deck.drawCard();
		Card dealerCard1 = deck.drawCard();
		Card dealerCard2 = deck.drawCard();

		// Show player's cards and dealer's face-down card
		System.out.println("\n" + playerName + "'s hand:");
		System.out.println(playerCard1);
		System.out.println(playerCard2);

		System.out.println("\nDealer's hand:");
		System.out.println(dealerCard1);
		System.out.println("[hidden]");

		// Update scores
		playerScore += playerCard1.getValue() + playerCard2.getValue();
		dealerScore += dealerCard1.getValue() + dealerCard2.getValue();

		// Player's turn
		while (true) {
			System.out.print("\nHit or stand? ");
			String input = scanner.nextLine().toLowerCase();

			if (input.equals("hit")) {
				Card card = deck.drawCard();
				System.out.println("You drew: " + card);
				playerScore += card.getValue();

				if (playerScore > 21) {
					System.out.println("\nBust! You lose.");
					return;
				}
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			Card card = deck.drawCard();
			System.out.println("\nDealer drew: " + card);
			dealerScore += card.getValue();

			if (dealerScore > 21) {
				System.out.println("\nDealer busts! You win.");
				return;
			}
		}

		// Compare scores
		System.out.println("\n" + playerName + "'s score: " + playerScore);
		System.out.println("Dealer's score: " + dealerScore);

		if (playerScore > dealerScore && playerScore <= 21) {
			System.out.println("\n" + playerName + " wins!");
		} else if (dealerScore > playerScore && dealerScore <= 21) {
			System.out.println("\nDealer wins!");
		} else {
			System.out.println("\nIt's a tie!");
		}
	}
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();

		// Populate deck with cards
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}

		// Shuffle deck
		Collections.shuffle(cards);
	}

	public Card drawCard() {
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

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}