package JDB_C03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static final int MAX_SCORE = 21;
	private static final int DEALER_MIN_SCORE = 17;
	private ArrayList<Card> deck;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> dealerHand;
	private Scanner scanner;

	public Blackjack() {
		deck = new ArrayList<Card>();
		playerHand = new ArrayList<Card>();
		dealerHand = new ArrayList<Card>();
		scanner = new Scanner(System.in);
		initializeDeck();
		Collections.shuffle(deck);
	}

	private void initializeDeck() {
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}

	private void dealCards() {
		playerHand.add(drawCard());
		dealerHand.add(drawCard());
		playerHand.add(drawCard());
		dealerHand.add(drawCard());
	}

	private Card drawCard() {
		return deck.remove(deck.size() - 1);
	}

	private int getHandScore(ArrayList<Card> hand) {
		int score = 0;
		boolean hasAce = false;
		for (Card card : hand) {
			score += card.getRank().getValue();
			if (card.getRank() == Card.Rank.ACE) {
				hasAce = true;
			}
		}
		if (hasAce && score <= MAX_SCORE - 10) {
			score += 10;
		}
		return score;
	}

	private boolean isBust(int score) {
		return score > MAX_SCORE;
	}

	private void printHand(ArrayList<Card> hand, boolean showAllCards) {
		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (i == 0 && !showAllCards) {
				System.out.println("[HIDDEN]");
			} else {
				System.out.println(card);
			}
		}
	}

	private void printGameStatus(boolean showAllDealerCards) {
		System.out.println("Dealer's hand:");
		printHand(dealerHand, showAllDealerCards);
		System.out.println("Player's hand:");
		printHand(playerHand, true);
	}

	private void playDealerTurn() {
		while (getHandScore(dealerHand) < DEALER_MIN_SCORE) {
			dealerHand.add(drawCard());
			printGameStatus(false);
		}
	}

	private void playPlayerTurn() {
		boolean isStand = false;
		while (!isBust(getHandScore(playerHand)) && !isStand) {
			printGameStatus(false);
			System.out.print("Do you want to hit (h) or stand (s)? ");
			String input = scanner.nextLine().trim().toLowerCase();
			switch (input) {
			case "h":
				playerHand.add(drawCard());
				break;
			case "s":
				isStand = true;
				break;
			default:
				System.out.println("Invalid input. Please try again.");
			}
		}
		printGameStatus(false);
	}

	private void determineWinner() {
		int playerScore = getHandScore(playerHand);
		int dealerScore = getHandScore(dealerHand);
		if (isBust(playerScore)) {
			System.out.println("Player busts. Dealer wins!");
		} else if (isBust(dealerScore)) {
			System.out.println("Dealer busts. Player wins!");
		} else if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		dealCards();
		printGameStatus(false);
		playPlayerTurn();
		if (!isBust(getHandScore(playerHand))) {
			playDealerTurn();
		}
		determineWinner();
		scanner.close();
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
	}
}

class Card {
	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	public enum Rank {
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