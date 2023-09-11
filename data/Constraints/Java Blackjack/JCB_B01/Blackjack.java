package JCB_B01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private static final int BLACKJACK_VALUE = 21;
	private static final int DEALER_HIT_THRESHOLD = 17;

	private final List<Card> deck = new ArrayList<>();
	private final List<Card> playerHand = new ArrayList<>();
	private final List<Card> dealerHand = new ArrayList<>();

	private int playerScore;
	private int dealerScore;

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}

	private void play() {
		System.out.println("Welcome to Blackjack!");

		initializeDeck();
		shuffleDeck();

		dealCards();
		showInitialHands();

		while (true) {
			if (playerScore == BLACKJACK_VALUE) {
				System.out.println("Congratulations! You win!");
				break;
			} else if (playerScore > BLACKJACK_VALUE) {
				System.out.println("Sorry, you bust! Dealer wins.");
				break;
			}

			System.out.println("Would you like to hit or stand? (h/s)");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("h")) {
				hit(playerHand);
				showHands();
			} else if (choice.equalsIgnoreCase("s")) {
				System.out.println("Player stands.");
				break;
			}
		}

		while (dealerScore < DEALER_HIT_THRESHOLD) {
			System.out.println("Dealer hits.");
			hit(dealerHand);
			showHands();
		}

		if (dealerScore == BLACKJACK_VALUE) {
			System.out.println("Dealer has Blackjack! Dealer wins.");
		} else if (dealerScore > BLACKJACK_VALUE) {
			System.out.println("Dealer busts! You win!");
		} else if (dealerScore >= playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("You win!");
		}
	}

	private void initializeDeck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private void dealCards() {
		hit(playerHand);
		hit(dealerHand);
		hit(playerHand);
		hit(dealerHand);
	}

	private void hit(List<Card> hand) {
		Card card = deck.remove(0);
		hand.add(card);

		int score = calculateScore(hand);
		if (hand == playerHand) {
			playerScore = score;
		} else {
			dealerScore = score;
		}
	}

	private void showInitialHands() {
		System.out.println("Dealer's hand: ");
		System.out.println(dealerHand.get(0));
		System.out.println("Hidden Card");

		System.out.println("Player's hand: ");
		for (Card card : playerHand) {
			System.out.println(card);
		}
		System.out.println("Score: " + playerScore);
	}

	private void showHands() {
		System.out.println("Dealer's hand: ");
		for (Card card : dealerHand) {
			System.out.println(card);
		}
		System.out.println("Score: " + dealerScore);

		System.out.println("Player's hand: ");
		for (Card card : playerHand) {
			System.out.println(card);
		}
		System.out.println("Score: " + playerScore);
	}

	private int calculateScore(List<Card> hand) {
		int score = 0;
		int numAces = 0;

		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			} else if (card.getRank().getValue() >= 10) {
				score += 10;
			} else {
				score += card.getRank().getValue();
			}
		}

		// Calculate the value of aces as either 1 or 11, depending on which value would
		// result in a better score.
		for (int i = 0; i < numAces; i++) {
			if (score + 11 <= BLACKJACK_VALUE) {
				score += 11;
			} else {
				score += 1;
			}
		}

		return score;
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

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private final int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}