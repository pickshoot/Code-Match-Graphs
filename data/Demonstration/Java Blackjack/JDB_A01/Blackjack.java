package JDB_A01;

//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static Player player;
	private static Player dealer;

	public static void main(String[] args) {
		deck = new Deck();
		player = new Player();
		dealer = new Player();

		System.out.println("Welcome to Blackjack!");
		Scanner scanner = new Scanner(System.in);

		// Loop until the deck is empty or the user quits
		while (!deck.isEmpty()) {
			// Deal the initial cards
			player.addCard(deck.drawCard());
			dealer.addCard(deck.drawCard());
			player.addCard(deck.drawCard());
			dealer.addCard(deck.drawCard());

			// Print the cards
			System.out.println("\nPlayer's cards: " + player);
			System.out.println("Dealer's cards: " + dealer.getVisibleCard());

			// Check for natural blackjack
			if (player.getScore() == 21) {
				System.out.println("\nBlackjack! You win!");
			} else if (dealer.getScore() == 21) {
				System.out.println("\nDealer has blackjack. You lose.");
			} else {
				// Ask the player if they want to hit or stand
				while (true) {
					System.out.print("\nDo you want to hit or stand? (h/s): ");
					String input = scanner.nextLine();
					if (input.equalsIgnoreCase("h")) {
						player.addCard(deck.drawCard());
						System.out.println("\nPlayer's cards: " + player);
						if (player.getScore() > 21) {
							System.out.println("\nBust! You lose.");
							break;
						}
					} else if (input.equalsIgnoreCase("s")) {
						// Dealer's turn
						System.out.println("\nDealer's turn:");
						System.out.println("Dealer's cards: " + dealer);
						while (dealer.getScore() < 17) {
							dealer.addCard(deck.drawCard());
							System.out.println("Dealer hits: " + dealer.getLastCard());
							System.out.println("Dealer's cards: " + dealer);
						}
						if (dealer.getScore() > 21) {
							System.out.println("\nDealer busts! You win!");
						} else if (dealer.getScore() > player.getScore()) {
							System.out.println("\nDealer wins with " + dealer.getScore() + " points.");
						} else if (dealer.getScore() < player.getScore()) {
							System.out.println("\nYou win with " + player.getScore() + " points.");
						} else {
							System.out.println("\nIt's a tie!");
						}
						break;
					}
				}
			}
			// Ask the user to play again or quit
			System.out.print("\n=========> Press ENTER to play again or “q” to quit: ");
			String input = scanner.nextLine();
			if (input.equals("q")) {
				break;
			} else {
				// Start a new round
				deck.shuffle();
				player.reset();
				dealer.reset();
			}
		}
		// Print a message indicating that the deck is empty
		System.out.println("Deck is empty. Thank you for playing and welcome back!");
	}
}

class Player {
	private ArrayList<Card> cards;

	public Player() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void reset() {
		cards = new ArrayList<Card>();
	}

	public int getScore() {
		int score = 0;
		boolean hasAce = false;
		for (Card card : cards) {
			if (card.getValue() == 1) {
				hasAce = true;
			}
			score += card.getValue();
		}
		if (hasAce && score + 10 <= 21) {
			score += 10;
		}
		return score;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card).append(" ");
		}
		sb.append("(").append(getScore()).append(")");
		return sb.toString();
	}

	public Card getVisibleCard() {
		return cards.get(0);
	}

	public Card getLastCard() {
		return cards.get(cards.size() - 1);
	}
}

class Card {
	private final Suit suit;
	private final int value;

	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

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
			valueString = Integer.toString(value);
			break;
		}
		return valueString + suit.getSymbol();
	}
}

enum Suit {
	CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), SPADES("♠");

	private final String symbol;

	Suit(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}

class Deck {
	private ArrayList<Card> cards;
	private int currentCard;

	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (int value = 1; value <= 13; value++) {
				cards.add(new Card(suit, value));
			}
		}
		shuffle();
		currentCard = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		currentCard = 0;
	}

	public boolean isEmpty() {
		return currentCard >= cards.size();
	}

	public Card drawCard() {
		Card card = cards.get(currentCard);
		currentCard++;
		return card;
	}
}