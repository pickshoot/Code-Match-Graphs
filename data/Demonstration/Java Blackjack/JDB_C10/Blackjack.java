package JDB_C10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static final int BLACKJACK_VALUE = 21;
	private static final int DEALER_MIN_VALUE = 17;
	private ArrayList<Card> deck;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> dealerCards;
	private Scanner scanner;

	public Blackjack() {
		deck = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		scanner = new Scanner(System.in);
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		initializeDeck();
		shuffleDeck();
		dealCards();
		printCards(false);

		// player's turn
		while (getCardValue(playerCards) < BLACKJACK_VALUE) {
			System.out.print("Do you want to hit or stand? (H/S): ");
			String input = scanner.nextLine().toUpperCase();
			if (input.equals("H")) {
				hit(playerCards);
				printCards(false);
			} else if (input.equals("S")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// dealer's turn
		printCards(true);
		while (getCardValue(dealerCards) < DEALER_MIN_VALUE) {
			hit(dealerCards);
			printCards(true);
		}

		// determine the winner
		int playerValue = getCardValue(playerCards);
		int dealerValue = getCardValue(dealerCards);
		if (playerValue > BLACKJACK_VALUE) {
			System.out.println("You bust! Dealer wins.");
		} else if (dealerValue > BLACKJACK_VALUE) {
			System.out.println("Dealer busts! You win.");
		} else if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (playerValue < dealerValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}

		scanner.close();
	}

	private void initializeDeck() {
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private void dealCards() {
		for (int i = 0; i < 2; i++) {
			hit(playerCards);
			hit(dealerCards);
		}
	}

	private void hit(ArrayList<Card> hand) {
		hand.add(deck.remove(0));
	}

	private int getCardValue(ArrayList<Card> hand) {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank().equals("Ace")) {
				numAces++;
				value += 11;
			} else if (card.getRank().equals("King") || card.getRank().equals("Queen")
					|| card.getRank().equals("Jack")) {
				value += 10;
			} else {
				value += Integer.parseInt(card.getRank());
			}
		}
		while (numAces > 0 && value > BLACKJACK_VALUE) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	private void printCards(boolean showDealerCard) {
		System.out.println("\nDealer's cards:");
		if (showDealerCard) {
			for (Card card : dealerCards) {
				System.out.println(card);
			}
			System.out.println("Total value: " + getCardValue(dealerCards));
		} else {
			System.out.println("HIDDEN CARD");
			System.out.println(dealerCards.get(1));
		}

		System.out.println("\nPlayer's cards:");
		for (Card card : playerCards) {
			System.out.println(card);
		}
		System.out.println("Total value: " + getCardValue(playerCards));
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

//Card class
class Card {
	private String suit;
	private String rank;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public String toString() {
		return rank + " of " + suit;
	}
}