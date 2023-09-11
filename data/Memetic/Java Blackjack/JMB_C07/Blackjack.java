package C7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");
		System.out.println();

		Deck deck = new Deck();
		deck.shuffle();

		List<Card> playerCards = new ArrayList<>();
		playerCards.add(deck.drawCard());
		playerCards.add(deck.drawCard());

		List<Card> dealerCards = new ArrayList<>();
		dealerCards.add(deck.drawCard());
		dealerCards.add(deck.drawCard());

		while (true) {
			System.out.println("Your cards: " + playerCards);
			System.out.println("Your total: " + getHandTotal(playerCards));
			System.out.println("Dealer's card: " + dealerCards.get(0));
			System.out.println();

			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			System.out.println();

			if ("hit".equalsIgnoreCase(input)) {
				playerCards.add(deck.drawCard());
				int playerTotal = getHandTotal(playerCards);

				if (playerTotal > 21) {
					System.out.println("Your cards: " + playerCards);
					System.out.println("Your total: " + playerTotal);
					System.out.println("Bust! You lose.");
					return;
				}
			} else if ("stand".equalsIgnoreCase(input)) {
				int dealerTotal = getHandTotal(dealerCards);
				int playerTotal = getHandTotal(playerCards);

				while (dealerTotal < 17) {
					dealerCards.add(deck.drawCard());
					dealerTotal = getHandTotal(dealerCards);
				}

				System.out.println("Your cards: " + playerCards);
				System.out.println("Your total: " + getHandTotal(playerCards));
				System.out.println("Dealer's cards: " + dealerCards);
				System.out.println("Dealer's total: " + dealerTotal);
				System.out.println();

				if (dealerTotal > 21 || playerTotal > dealerTotal) {
					System.out.println("You win!");
				} else if (playerTotal == dealerTotal) {
					System.out.println("Push.");
				} else {
					System.out.println("You lose.");
				}

				return;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	private static int getHandTotal(List<Card> cards) {
		int total = 0;
		int numAces = 0;

		for (Card card : cards) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			}

			total += card.getValue();
		}

		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}

		return total;
	}

}

enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
	ACE(11);

	private final int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Card {
	private final Suit suit;
	private final Rank rank;

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

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private final List<Card> cards;
	private int currentIndex;

	public Deck() {
		cards = new ArrayList<>();

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}

		currentIndex = 0;
	}

	public void shuffle() {
		for (int i = 0; i < cards.size(); i++) {
			int j = (int) (Math.random() * cards.size());
			Card temp = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j, temp);
		}

		currentIndex = 0;
	}

	public Card drawCard() {
		if (currentIndex >= cards.size()) {
			throw new IllegalStateException("Deck is empty");
		}

		Card card = cards.get(currentIndex);
		currentIndex++;

		return card;
	}
}