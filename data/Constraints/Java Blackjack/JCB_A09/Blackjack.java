package JCB_A09;

import java.util.*;

public class Blackjack {

	private static final int MAX_SCORE = 21;
	private static final int DEALER_THRESHOLD = 17;

	private Deck deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerScore;
	private int dealerScore;
	private boolean playerBust;
	private boolean dealerBust;

	public Blackjack() {
		deck = new Deck();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		playerBust = false;
		dealerBust = false;
	}

	public void play() {
		// Shuffle deck
		deck.shuffle();

		// Deal initial cards
		dealCardToPlayer();
		dealCardToDealer();
		dealCardToPlayer();
		dealCardToDealer();

		// Check for natural blackjack
		if (playerScore == MAX_SCORE) {
			System.out.println("You have a natural blackjack!");
			return;
		}

		// Player's turn
		while (playerScore < MAX_SCORE) {
			System.out.println("Your hand: " + playerHand);
			System.out.println("Your score: " + playerScore);
			System.out.print("Hit or stand? (h/s) ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h")) {
				dealCardToPlayer();
				if (playerBust) {
					System.out.println("You bust!");
					return;
				}
			} else if (input.equalsIgnoreCase("s")) {
				break;
			}
		}

		// Dealer's turn
		while (dealerScore < DEALER_THRESHOLD) {
			dealCardToDealer();
			if (dealerBust) {
				System.out.println("Dealer busts!");
				return;
			}
		}

		// Determine winner
		if (playerBust) {
			System.out.println("Dealer wins!");
		} else if (dealerBust) {
			System.out.println("You win!");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private void dealCardToPlayer() {
		Card card = deck.deal();
		playerHand.add(card);
		playerScore += card.getValue();
		if (playerScore > MAX_SCORE) {
			playerBust = true;
		}
	}

	private void dealCardToDealer() {
		Card card = deck.deal();
		dealerHand.add(card);
		dealerScore += card.getValue();
		if (dealerScore > MAX_SCORE) {
			dealerBust = true;
		}
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
	}

}

class Deck {

	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card deal() {
		return cards.remove(0);
	}

}

enum Suit {
	HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

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

	public int getValue() {
		return rank.getValue();
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}