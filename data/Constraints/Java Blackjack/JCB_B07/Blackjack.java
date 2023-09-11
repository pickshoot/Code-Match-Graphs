package JCB_B07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	private static final int BLACKJACK_VALUE = 21;
	private static final int DEALER_HIT_THRESHOLD = 17;
	private static final int INITIAL_NUM_CARDS = 2;

	private final Deck deck;
	private final List<Card> playerCards;
	private final List<Card> dealerCards;
	private int playerScore;
	private int dealerScore;
	private boolean gameOver;

	public Blackjack() {
		deck = new Deck();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		gameOver = false;
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		System.out.println("======================");

		while (!gameOver) {
			System.out.println("Dealing cards...");
			dealCards();
			printGameState();

			while (true) {
				if (playerScore == BLACKJACK_VALUE) {
					System.out.println("Blackjack! You win!");
					gameOver = true;
					break;
				} else if (playerScore > BLACKJACK_VALUE) {
					System.out.println("Bust! You lose.");
					gameOver = true;
					break;
				}

				Scanner scanner = new Scanner(System.in);
				System.out.print("Hit or stand? ");
				String input = scanner.nextLine();

				if (input.equalsIgnoreCase("hit")) {
					hit(playerCards);
					printGameState();
				} else if (input.equalsIgnoreCase("stand")) {
					break;
				} else {
					System.out.println("Invalid input.");
				}
			}

			if (!gameOver) {
				System.out.println("Dealer's turn...");
				dealerTurn();
				printGameState();
				checkWinner();
			}

			gameOver = !playAgain();
		}

		System.out.println("Thanks for playing!");
	}

	private void dealCards() {
		playerCards.clear();
		dealerCards.clear();
		playerScore = 0;
		dealerScore = 0;

		for (int i = 0; i < INITIAL_NUM_CARDS; i++) {
			hit(playerCards);
			hit(dealerCards);
		}
	}

	private void hit(List<Card> cards) {
		Card card = deck.draw();
		cards.add(card);
		updateScore(card, cards == playerCards);
	}

	private void updateScore(Card card, boolean isPlayer) {
		int value = card.getValue();

		if (value == 1 && (isPlayer ? playerScore + 11 <= BLACKJACK_VALUE : dealerScore + 11 <= BLACKJACK_VALUE)) {
			value = 11;
		}

		if (isPlayer) {
			playerScore += value;
		} else {
			dealerScore += value;
		}
	}

	private void dealerTurn() {
		while (dealerScore < DEALER_HIT_THRESHOLD) {
			hit(dealerCards);
		}
	}

	private void checkWinner() {
		if (dealerScore > BLACKJACK_VALUE) {
			System.out.println("Dealer busts! You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else if (dealerScore < playerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("Push!");
		}
	}

	private boolean playAgain() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Play again? (y/n) ");
		String input = scanner.nextLine();
		return input.equalsIgnoreCase("y");
	}

	private void printGameState() {
		System.out.println("Your cards: " + playerCards);
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer's cards: " + dealerCards.get(0) + ", [hidden]");
	}

	private static class Deck {
		private final List<Card> cards;
		private int currentCardIndex;

		public Deck() {
			cards = new ArrayList<>();

			for (Suit suit : Suit.values()) {
				for (int i = 1; i <= 13; i++) {
					cards.add(new Card(suit, i));
				}
			}

			Collections.shuffle(cards);
			currentCardIndex = 0;
		}

		public Card draw() {
			return cards.get(currentCardIndex++);
		}
	}

	private static class Card {
		private final Suit suit;
		private final int value;

		public Card(Suit suit, int value) {
			this.suit = suit;
			this.value = value;
		}

		public int getValue() {
			return Math.min(value, 10);
		}

		@Override
		public String toString() {
			String valueString;

			switch (value) {
			case 1:
				valueString = "A";
				break;
			case 11:
				valueString = "J";
				break;
			case 12:
				valueString = "Q";
				break;
			case 13:
				valueString = "K";
				break;
			default:
				valueString = String.valueOf(value);
				break;
			}

			return valueString + suit;
		}
	}

	private enum Suit {
		CLUBS("\u2663"), DIAMONDS("\u2666"), HEARTS("\u2665"), SPADES("\u2660");

		private final String symbol;

		Suit(String symbol) {
			this.symbol = symbol;
		}

		@Override
		public String toString() {
			return symbol;
		}
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
	}
}