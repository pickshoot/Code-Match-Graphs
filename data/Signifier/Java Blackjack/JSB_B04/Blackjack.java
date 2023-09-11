package JSB_B04;

import java.util.*;

public class Blackjack {

	// Declare and initialize constants
	private static final int BLACKJACK = 21;
	private static final int DEALER_MIN_SCORE = 17;

	// Declare instance variables
	private Deck deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerScore;
	private int dealerScore;

	public Blackjack() {
		deck = new Deck();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
	}

	public void play() {
		// Shuffle the deck
		deck.shuffle();

		// Deal two cards to the player
		deal(playerHand);
		deal(playerHand);
		calculateScore(playerHand, playerScore);

		// Deal one card to the dealer
		deal(dealerHand);
		calculateScore(dealerHand, dealerScore);

		// Show the initial hands
		showHands(true);

		// Let the player hit or stand
		while (true) {
			System.out.print("Do you want to hit or stand? (h/s) ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine().toLowerCase();

			if (input.equals("h")) {
				deal(playerHand);
				calculateScore(playerHand, playerScore);
				showHands(true);

				if (playerScore > BLACKJACK) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			} else if (input.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// Let the dealer hit until their score is at least DEALER_MIN_SCORE
		while (dealerScore < DEALER_MIN_SCORE) {
			deal(dealerHand);
			calculateScore(dealerHand, dealerScore);
			showHands(false);

			if (dealerScore > BLACKJACK) {
				System.out.println("Dealer busted! You win.");
				return;
			}
		}

		// Determine the winner
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	// Deal a card from the deck to the given hand
	private void deal(List<Card> hand) {
		hand.add(deck.dealCard());
	}

	// Calculate the score of a hand
	private void calculateScore(List<Card> hand, int score) {
		score = 0;
		int aceCount = 0;
		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				aceCount++;
			}
			score += card.getValue();
		}
		while (score > BLACKJACK && aceCount > 0) {
			score -= 10;
			aceCount--;
		}
	}

	// Show the current hands
	private void showHands(boolean showDealer) {
		System.out.println("Player's hand: " + playerHand + " (score: " + playerScore + ")");
		if (showDealer) {
			System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", ?]");
		} else {
			System.out.println("Dealer's hand: " + dealerHand + " (score: " + dealerScore + ")");
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Deck {
	private List<Card> cards;
	private int nextCardIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
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
		Card card = cards.get(nextCardIndex);
		nextCardIndex++;
		return card;
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

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}