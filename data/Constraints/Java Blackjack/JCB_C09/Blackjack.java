package JCB_C09;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();

		System.out.println("Welcome to Blackjack!");

		// Deal initial cards
		Hand dealerHand = new Hand();
		Hand playerHand = new Hand();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());

		// Show initial hands
		System.out.println("Dealer's hand: " + dealerHand.getCard(0) + " [hidden]");
		System.out.println("Your hand: " + playerHand);

		// Player's turn
		while (true) {
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Your hand: " + playerHand);
				if (playerHand.getScore() > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		while (dealerHand.getScore() < 17) {
			dealerHand.addCard(deck.dealCard());
		}
		System.out.println("Dealer's hand: " + dealerHand);

		// Determine winner
		int dealerScore = dealerHand.getScore();
		int playerScore = playerHand.getScore();
		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (playerScore > 21) {
			System.out.println("Bust! You lose.");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie!");
		}
	}

}

class Deck {

	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card dealCard() {
		return cards.remove(0);
	}

}

class Hand {

	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		boolean hasAce = false;
		for (Card card : cards) {
			Rank rank = card.getRank();
			if (rank == Rank.ACE) {
				hasAce = true;
			}
			score += Math.min(rank.getValue(), 10);
		}
		if (hasAce && score <= 11) {
			score += 10;
		}
		return score;
	}

	public Card getCard(int index) {
		return cards.get(index);
	}

	public String toString() {
		return cards.toString() + " (score: " + getScore() + ")";
	}

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

	public String toString() {
		return rank + " of " + suit;
	}
}