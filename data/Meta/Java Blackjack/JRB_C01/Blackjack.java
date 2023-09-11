package JRB_C01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Scanner;

public class Blackjack {
	private static final int INITIAL_BALANCE = 100;
	private static final int MIN_BET_AMOUNT = 10;

	private int playerBalance;
	private int currentBetAmount;
	private Deck deck;
	private Hand playerHand;
	private Hand dealerHand;

	public Blackjack() {
		playerBalance = INITIAL_BALANCE;
		deck = new Deck();
	}

	public void startGame() {
		System.out.println("Welcome to Blackjack!");
		Scanner scanner = new Scanner(System.in);

		while (playerBalance >= MIN_BET_AMOUNT) {
			System.out.printf("Your current balance is %d. Enter your bet amount (minimum %d): ", playerBalance,
					MIN_BET_AMOUNT);
			currentBetAmount = scanner.nextInt();
			if (currentBetAmount < MIN_BET_AMOUNT || currentBetAmount > playerBalance) {
				System.out.println("Invalid bet amount!");
				continue;
			}

			playerHand = new Hand();
			dealerHand = new Hand();

			deck.shuffle();

			playerHand.addCard(deck.dealCard());
			playerHand.addCard(deck.dealCard());

			dealerHand.addCard(deck.dealCard());

			System.out.printf("Your hand: %s (value: %d)\n", playerHand, playerHand.getValue());
			System.out.printf("Dealer's hand: %s\n", dealerHand.getFirstCard());

			while (true) {
				System.out.print("Do you want to hit or stand? (h/s): ");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("h")) {
					playerHand.addCard(deck.dealCard());
					System.out.printf("Your hand: %s (value: %d)\n", playerHand, playerHand.getValue());
					if (playerHand.isBust()) {
						System.out.println("Bust! You lose.");
						playerBalance -= currentBetAmount;
						break;
					}
				} else if (choice.equalsIgnoreCase("s")) {
					while (dealerHand.getValue() < 17) {
						dealerHand.addCard(deck.dealCard());
						System.out.printf("Dealer's hand: %s\n", dealerHand);
					}
					System.out.printf("Dealer's hand value: %d\n", dealerHand.getValue());

					if (dealerHand.isBust() || playerHand.getValue() > dealerHand.getValue()) {
						System.out.println("You win!");
						playerBalance += currentBetAmount;
					} else if (dealerHand.getValue() > playerHand.getValue()) {
						System.out.println("You lose.");
						playerBalance -= currentBetAmount;
					} else {
						System.out.println("It's a tie.");
					}

					break;
				} else {
					System.out.println("Invalid choice!");
				}
			}

			System.out.printf("Your current balance is %d.\n", playerBalance);

			if (playerBalance < MIN_BET_AMOUNT) {
				System.out.println("You don't have enough balance to play.");
				break;
			}

			System.out.print("Do you want to play again? (y/n): ");
			String choice = scanner.next();
			if (!choice.equalsIgnoreCase("y")) {
				break;
			}
		}

		System.out.println("Thanks for playing!");
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.startGame();
	}
}

enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

class Card {
	private final int rank;
	private final Suit suit;

	public Card(int rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString() {
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		return ranks[rank - 1] + " of " + suit.toString().toLowerCase();
	}
}

class Deck {
	private final List<Card> cards;
	private int nextCardIndex;

	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (int rank = 1; rank <= 13; rank++) {
				cards.add(new Card(rank, suit));
			}
		}
		nextCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		nextCardIndex = 0;
	}

	public Card dealCard() {
		if (nextCardIndex >= cards.size()) {
			throw new IllegalStateException("No more cards in the deck!");
		}
		return cards.get(nextCardIndex++);
	}
}

class Hand {
	private final List<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : cards) {
			int rank = card.getRank();
			if (rank == 1) {
				numAces++;
				value += 11;
			} else if (rank >= 10) {
				value += 10;
			} else {
				value += rank;
			}
		}
		while (numAces > 0 && value > 21) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	public boolean isBust() {
		return getValue() > 21;
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
}
