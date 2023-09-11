package JSB_C09;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {

	private static final int BLACKJACK = 21;
	private static final int DEALER_HIT_THRESHOLD = 17;
	private static final int INITIAL_NUM_CARDS = 2;

	private List<Card> deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private Scanner scanner;

	public Blackjack() {
		deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		scanner = new Scanner(System.in);
	}

	public void play() {
		shuffleDeck();
		dealInitialCards();
		printHands(false);

		if (hasBlackjack(playerHand)) {
			System.out.println("Blackjack! You win!");
			return;
		}

		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine();
			if (choice.equalsIgnoreCase("hit")) {
				hit(playerHand);
				printHands(false);
				if (hasBusted(playerHand)) {
					System.out.println("You busted! Dealer wins!");
					return;
				}
			} else if (choice.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter \"hit\" or \"stand\".");
			}
		}

		while (sumHand(dealerHand) < DEALER_HIT_THRESHOLD) {
			hit(dealerHand);
			printHands(true);
			if (hasBusted(dealerHand)) {
				System.out.println("Dealer busted! You win!");
				return;
			}
		}

		int playerSum = sumHand(playerHand);
		int dealerSum = sumHand(dealerHand);
		if (dealerSum > playerSum) {
			System.out.println("Dealer wins!");
		} else if (dealerSum < playerSum) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private void shuffleDeck() {
		Random random = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int j = random.nextInt(deck.size() - i) + i;
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	}

	private void dealInitialCards() {
		for (int i = 0; i < INITIAL_NUM_CARDS; i++) {
			hit(playerHand);
			hit(dealerHand);
		}
	}

	private void hit(List<Card> hand) {
		Card card = deck.remove(0);
		hand.add(card);
	}

	private boolean hasBlackjack(List<Card> hand) {
		return sumHand(hand) == BLACKJACK;
	}

	private boolean hasBusted(List<Card> hand) {
		return sumHand(hand) > BLACKJACK;
	}

	private int sumHand(List<Card> hand) {
		int sum = 0;
		int numAces = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			} else {
				sum += card.getValue();
			}
		}
		for (int i = 0; i < numAces; i++) {
			if (sum + 11 <= BLACKJACK - numAces + 1) {
				sum += 11;
			} else {
				sum += 1;
			}
		}
		return sum;
	}

	private void printHands(boolean showDealerCard) {
		System.out.println("Your hand: " + playerHand);
		if (showDealerCard) {
			System.out.println("Dealer's hand: " + dealerHand);
		} else {
			System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", ??]");
		}
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

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