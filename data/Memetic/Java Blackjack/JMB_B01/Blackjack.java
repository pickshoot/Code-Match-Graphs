package B1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	private static final int TARGET_SCORE = 21;
	private static final int DEALER_STAND_SCORE = 17;

	private List<Card> deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;
	private Scanner scanner;

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}

	public Blackjack() {
		deck = createDeck();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		scanner = new Scanner(System.in);
	}

	private void play() {
		System.out.println("Welcome to Blackjack!");

		while (true) {
			shuffleDeck();
			dealInitialCards();
			showCards(true);

			boolean playerWins = playerTurn();

			if (playerWins || dealerTurn()) {
				System.out.println("You win!");
			} else {
				System.out.println("Dealer wins!");
			}

			System.out.print("Do you want to play again? (y/n) ");
			String answer = scanner.nextLine().toLowerCase();

			if (answer.startsWith("n")) {
				break;
			}

			playerCards.clear();
			dealerCards.clear();
		}

		System.out.println("Thanks for playing!");
	}

	private boolean playerTurn() {
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String answer = scanner.nextLine().toLowerCase();

			if (answer.startsWith("s")) {
				return false;
			}

			playerCards.add(drawCard());
			showCards(true);

			if (isBust(playerCards)) {
				System.out.println("You busted!");
				return false;
			}

			if (getScore(playerCards) == TARGET_SCORE) {
				System.out.println("You got 21!");
				return true;
			}
		}
	}

	private boolean dealerTurn() {
		while (getScore(dealerCards) < DEALER_STAND_SCORE) {
			dealerCards.add(drawCard());
			showCards(false);
		}

		if (isBust(dealerCards)) {
			System.out.println("Dealer busted!");
			return true;
		}

		int playerScore = getScore(playerCards);
		int dealerScore = getScore(dealerCards);

		if (dealerScore > playerScore) {
			System.out.printf("Dealer wins with %d points!\n", dealerScore);
			return false;
		} else if (dealerScore == playerScore) {
			System.out.printf("It's a tie with %d points each!\n", dealerScore);
			return false;
		} else {
			System.out.printf("Player wins with %d points!\n", playerScore);
			return true;
		}
	}

	private void dealInitialCards() {
		playerCards.add(drawCard());
		dealerCards.add(drawCard());
		playerCards.add(drawCard());
		dealerCards.add(drawCard());
	}

	private void showCards(boolean showDealer) {
		System.out.println("Dealer's cards:");
		if (showDealer) {
			for (Card card : dealerCards) {
				System.out.println(card);
			}
		} else {
			System.out.println(dealerCards.get(0));
			System.out.println("???");
		}

		System.out.println("Your cards:");
		for (Card card : playerCards) {
			System.out.println(card);
		}
	}

	private List<Card> createDeck() {
		List<Card> deck = new ArrayList<>();

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}

		return deck;
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private Card drawCard() {
		return deck.remove(0);
	}

	private int getScore(List<Card> cards) {
		int score = 0;
		int numAces = 0;

		for (Card card : cards) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			} else {
				score += card.getValue();
			}
		}

		for (int i = 0; i < numAces; i++) {
			if (score + 11 <= TARGET_SCORE) {
				score += 11;
			} else {
				score += 1;
			}
		}

		return score;
	}

	private boolean isBust(List<Card> cards) {
		return getScore(cards) > TARGET_SCORE;
	}

	private enum Suit {
		HEARTS, DIAMONDS, CLUBS, SPADES
	}

	private enum Rank {
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

	private class Card {
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
}