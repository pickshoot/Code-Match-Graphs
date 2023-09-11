package JCB_B02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	private static final int DECK_SIZE = 52;
	private static final int MAX_SCORE = 21;
	private static final int DEALER_STAND_SCORE = 17;

	private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };

	private static final Scanner scanner = new Scanner(System.in);
	private static final Random random = new Random();

	private List<Card> deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;

	private int playerScore;
	private int dealerScore;

	public Blackjack() {
		this.deck = createDeck();
		this.playerCards = new ArrayList<>();
		this.dealerCards = new ArrayList<>();
		this.playerScore = 0;
		this.dealerScore = 0;
	}

	public void startGame() {
		System.out.println("Welcome to Blackjack!");
		System.out.println("Dealing cards...");
		dealInitialCards();

		while (true) {
			System.out.println("Your cards: ");
			for (Card card : playerCards) {
				System.out.println(card.toString());
			}

			System.out.println("Your score: " + playerScore);

			if (playerScore > MAX_SCORE) {
				System.out.println("Bust! You lose.");
				break;
			} else if (playerScore == MAX_SCORE) {
				System.out.println("Blackjack! You win!");
				break;
			}

			System.out.println("Dealer's card: ");
			System.out.println(dealerCards.get(0).toString());

			System.out.println("Do you want to hit or stand? (h/s)");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h")) {
				hit(playerCards);
			} else if (input.equalsIgnoreCase("s")) {
				break;
			}
		}

		if (playerScore <= MAX_SCORE) {
			System.out.println("Dealer's cards: ");
			for (Card card : dealerCards) {
				System.out.println(card.toString());
			}

			while (dealerScore < DEALER_STAND_SCORE) {
				System.out.println("Dealer hits.");
				hit(dealerCards);
				System.out.println("Dealer's cards: ");
				for (Card card : dealerCards) {
					System.out.println(card.toString());
				}
			}

			if (dealerScore > MAX_SCORE) {
				System.out.println("Dealer busts! You win!");
			} else if (dealerScore == playerScore) {
				System.out.println("Push! It's a tie.");
			} else if (dealerScore > playerScore) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("You win!");
			}
		}

		System.out.println("Thanks for playing!");
	}

	private List<Card> createDeck() {
		List<Card> deck = new ArrayList<>();

		for (String suit : SUITS) {
			for (String rank : RANKS) {
				int value;
				if (rank.equals("A")) {
					value = 11;
				} else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
					value = 10;
				} else {
					value = Integer.parseInt(rank);
				}
				Card card = new Card(rank, suit, value);
				deck.add(card);
			}
		}

		return deck;
	}

	private void shuffleDeck() {
		for (int i = 0; i < DECK_SIZE; i++) {
			int index1 = random.nextInt(DECK_SIZE);
			int index2 = random.nextInt(DECK_SIZE);
			Card temp = deck.get(index1);
			deck.set(index1, deck.get(index2));
			deck.set(index2, temp);
		}
	}

	private void dealInitialCards() {
		shuffleDeck();

		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));
		playerCards.add(deck.remove(0));
		dealerCards.add(deck.remove(0));

		playerScore = calculateScore(playerCards);
		dealerScore = calculateScore(dealerCards);
	}

	private void hit(List<Card> hand) {
		hand.add(deck.remove(0));
		int newScore = calculateScore(hand);
		if (newScore > MAX_SCORE && hasAce(hand)) {
			newScore -= 10;
		}
		if (hand == playerCards) {
			playerScore = newScore;
		} else {
			dealerScore = newScore;
		}
	}

	private int calculateScore(List<Card> hand) {
		int score = 0;
		int aceCount = 0;
		for (Card card : hand) {
			score += card.getValue();
			if (card.getRank().equals("A")) {
				aceCount++;
			}
		}
		while (score > MAX_SCORE && aceCount > 0) {
			score -= 10;
			aceCount--;
		}
		return score;
	}

	private boolean hasAce(List<Card> hand) {
		for (Card card : hand) {
			if (card.getRank().equals("A")) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.startGame();
	}
}

class Card {
	private final String rank;
	private final String suit;
	private final int value;

	public Card(String rank, String suit, int value) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
	}

	public String getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}