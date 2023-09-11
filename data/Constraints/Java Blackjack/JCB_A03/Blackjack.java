package JCB_A03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;

	public Blackjack() {
		deck = new Deck();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
	}

	public void start() {
		deck.shuffle();
		dealCards();
		showInitialCards();
		while (true) {
			if (isBust(playerCards)) {
				System.out.println("You are bust. Dealer wins!");
				return;
			}

			if (isBlackjack(playerCards)) {
				System.out.println("You have Blackjack. You win!");
				return;
			}

			System.out.print("Do you want to hit (H) or stand (S)? ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("H")) {
				hit(playerCards);
				showPlayerCards();
			} else if (input.equalsIgnoreCase("S")) {
				break;
			}
		}

		while (getScore(dealerCards) < 17) {
			hit(dealerCards);
		}

		showAllCards();

		if (isBust(dealerCards)) {
			System.out.println("Dealer is bust. You win!");
		} else if (getScore(playerCards) > getScore(dealerCards)) {
			System.out.println("You win!");
		} else if (getScore(playerCards) == getScore(dealerCards)) {
			System.out.println("Push!");
		} else {
			System.out.println("Dealer wins!");
		}
	}

	private void dealCards() {
		hit(playerCards);
		hit(dealerCards);
		hit(playerCards);
		hit(dealerCards);
	}

	private void hit(List<Card> cards) {
		cards.add(deck.draw());
	}

	private void showInitialCards() {
		System.out.println("Dealer's card: " + dealerCards.get(0));
		System.out.println("Your cards: ");
		for (Card card : playerCards) {
			System.out.println(card);
		}
	}

	private void showPlayerCards() {
		System.out.println("Your cards: ");
		for (Card card : playerCards) {
			System.out.println(card);
		}
	}

	private void showAllCards() {
		System.out.println("Dealer's cards: ");
		for (Card card : dealerCards) {
			System.out.println(card);
		}
		System.out.println("Your cards: ");
		for (Card card : playerCards) {
			System.out.println(card);
		}
	}

	private boolean isBust(List<Card> cards) {
		return getScore(cards) > 21;
	}

	private boolean isBlackjack(List<Card> cards) {
		if (cards.size() != 2) {
			return false;
		}
		Card card1 = cards.get(0);
		Card card2 = cards.get(1);
		return (card1.getRank() == Rank.ACE && card2.getRank().getValue() == 10)
				|| (card1.getRank().getValue() == 10 && card2.getRank() == Rank.ACE);
	}

	private int getScore(List<Card> cards) {
		int score = 0;
		int aceCount = 0;
		for (Card card : cards) {
			if (card.getRank() == Rank.ACE) {
				aceCount++;
				score += 11;
			} else {
				score += card.getRank().getValue();
			}
		}
		while (score > 21 && aceCount > 0) {
			score -= 10;
			aceCount--;
		}
		return score;
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.start();
	}
}

class Deck {
	private List<Card> cards;
	private int nextCardIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(rank, suit);
				cards.add(card);
			}
		}
		nextCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		nextCardIndex = 0;
	}

	public Card draw() {
		Card card = cards.get(nextCardIndex);
		nextCardIndex++;
		return card;
	}
}

class Card {
	private final Rank rank;
	private final Suit suit;

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
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
	ACE(1);

	private final int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}