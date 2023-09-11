package JCB_B05;

import java.util.*;

public class Blackjack {
	private static final int BLACKJACK = 21;
	private static final int DEALER_HIT_THRESHOLD = 17;
	private static final int ACE_VALUE = 11;
	private static final int FACE_CARD_VALUE = 10;

	private List<Card> deck;
	private final List<Card> dealerHand;
	private final List<Card> playerHand;
	private int playerScore;
	private int dealerScore;
	private boolean isGameOver;

	public Blackjack() {
		deck = createDeck();
		dealerHand = new ArrayList<>();
		playerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		isGameOver = false;
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");
		while (!isGameOver) {
			// Deal cards
			if (playerHand.isEmpty() && dealerHand.isEmpty()) {
				dealCards();
				printGameState();
			}

			// Player's turn
			if (!isGameOver) {
				playerTurn();
				printGameState();
			}

			// Dealer's turn
			if (!isGameOver) {
				dealerTurn();
				printGameState();
			}

			// Check for game over
			if (!isGameOver) {
				determineWinner();
				printGameState();
			}

			// Ask to play again
			if (isGameOver) {
				askToPlayAgain();
			}
		}
	}

	private List<Card> createDeck() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
		Collections.shuffle(deck);
		return deck;
	}

	private void dealCards() {
		for (int i = 0; i < 2; i++) {
			playerHand.add(drawCard());
			dealerHand.add(drawCard());
		}
	}

	private Card drawCard() {
		if (deck.isEmpty()) {
			deck = createDeck();
		}
		return deck.remove(0);
	}

	private void playerTurn() {
		Scanner scanner = new Scanner(System.in);
		while (playerScore < BLACKJACK) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("hit")) {
				Card card = drawCard();
				playerHand.add(card);
				playerScore += getCardValue(card);
			} else if (input.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input, please enter 'hit' or 'stand'");
			}
		}
		if (playerScore > BLACKJACK) {
			System.out.println("You bust!");
			isGameOver = true;
		}
	}

	private void dealerTurn() {
		while (dealerScore < DEALER_HIT_THRESHOLD) {
			Card card = drawCard();
			dealerHand.add(card);
			dealerScore += getCardValue(card);
		}
		if (dealerScore > BLACKJACK) {
			System.out.println("Dealer busts!");
		}
		isGameOver = true;
	}

	private void determineWinner() {
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
		isGameOver = true;
	}

	private void askToPlayAgain() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Do you want to play again? ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("yes")) {
				resetGame();
				break;
			} else if (input.equals("no")) {
				System.out.println("Thanks for playing!");
				break;
			} else {
				System.out.println("Invalid input, please enter 'yes' or 'no'");
			}
		}
	}

	private void resetGame() {
		deck.clear();
		dealerHand.clear();
		playerHand.clear();
		playerScore = 0;
		dealerScore = 0;
		isGameOver = false;
	}

	private int getCardValue(Card card) {
		switch (card.getRank()) {
		case ACE:
			return ACE_VALUE;
		case KING:
		case QUEEN:
		case JACK:
			return FACE_CARD_VALUE;
		default:
			return card.getRank().getValue();
		}
	}

	private void printGameState() {
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", [hidden]");
		System.out.println("Your hand: " + playerHand);
		System.out.println("Your score: " + playerScore);
	}
}

interface CardBehavior {
	public String toString();
}

class Card implements CardBehavior {
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
	HEARTS, SPADES, CLUBS, DIAMONDS
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private final int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}