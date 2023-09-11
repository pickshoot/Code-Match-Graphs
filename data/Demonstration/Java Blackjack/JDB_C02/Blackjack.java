package JDB_C02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private static final int TARGET_SCORE = 21;
	private static final int DEALER_MIN_SCORE = 17;
	private List<Card> deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;
	private Scanner scanner;

	public Blackjack() {
		deck = new ArrayList<>();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		scanner = new Scanner(System.in);
		initializeDeck();
	}

	private void initializeDeck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(deck);
	}

	private void dealCards() {
		playerCards.clear();
		dealerCards.clear();
		for (int i = 0; i < 2; i++) {
			playerCards.add(deck.remove(0));
			dealerCards.add(deck.remove(0));
		}
	}

	private void printCards(boolean showDealerCards) {
		System.out.println("Your cards:");
		for (Card card : playerCards) {
			System.out.println(card);
		}
		if (showDealerCards) {
			System.out.println("Dealer's cards:");
			for (Card card : dealerCards) {
				System.out.println(card);
			}
		} else {
			System.out.println("Dealer's cards:");
			System.out.println(dealerCards.get(0));
			System.out.println("Hidden card");
		}
		System.out.println();
	}

	private int calculateScore(List<Card> cards) {
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
		return calculateScore(cards) > TARGET_SCORE;
	}

	private boolean isBlackjack(List<Card> cards) {
		return calculateScore(cards) == TARGET_SCORE && cards.size() == 2;
	}

	private void playerTurn() {
		while (true) {
			printCards(true);
			System.out.println("Your current score: " + calculateScore(playerCards));
			System.out.print("Do you want to hit or stand? (h/s): ");
			String choice = scanner.nextLine().trim().toLowerCase();
			if (choice.equals("h")) {
				playerCards.add(deck.remove(0));
				if (isBust(playerCards)) {
					System.out.println("Bust! Your score is over " + TARGET_SCORE);
					break;
				}
			} else if (choice.equals("s")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter h or s.");
			}
		}
	}

	private void dealerTurn() {
		while (calculateScore(dealerCards) < DEALER_MIN_SCORE) {
			dealerCards.add(deck.remove(0));
		}
		printCards(true);
		if (isBust(dealerCards)) {
			System.out.println("Dealer busts! You win!");
		} else if (calculateScore(playerCards) > calculateScore(dealerCards)) {
			System.out.println("You win!");
		} else if (calculateScore(playerCards) < calculateScore(dealerCards)) {
			System.out.println("You lose!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		while (true) {
			System.out.println("Let's play a round!");
			dealCards();
			printCards(false);
			if (isBlackjack(playerCards)) {
				System.out.println("Blackjack! You win!");
				continue;
			}
			playerTurn();
			if (!isBust(playerCards)) {
				dealerTurn();
			}
			System.out.print("Do you want to play another round? (y/n): ");
			String choice = scanner.nextLine().trim().toLowerCase();
			if (choice.equals("n")) {
				break;
			}
		}
		scanner.close();
		System.out.println("Thanks for playing!");
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

//Enum for the suits of the cards
enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

//Enum for the ranks of the cards
enum Rank {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
	ACE(11);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

//Class representing a playing card
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

	public String toString() {
		return rank + " of " + suit;
	}
}