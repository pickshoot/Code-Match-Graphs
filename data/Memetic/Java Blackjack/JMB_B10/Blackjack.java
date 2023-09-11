package B10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private List<Deck.Card> playerHand;
	private List<Deck.Card> dealerHand;
	private int playerScore;
	private int dealerScore;
	private Scanner scanner;

	public Blackjack() {
		deck = new Deck();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		scanner = new Scanner(System.in);
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");

		// deal initial cards
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		// show initial hands
		System.out.println("Player's hand: " + playerHand);
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", ?]");

		// player's turn
		while (playerScore < 21) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				playerHand.add(deck.drawCard());
				playerScore = calculateScore(playerHand);
				System.out.println("Player's hand: " + playerHand);
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// dealer's turn
		dealerScore = calculateScore(dealerHand);
		while (dealerScore < 17) {
			dealerHand.add(deck.drawCard());
			dealerScore = calculateScore(dealerHand);
		}

		// show final hands
		System.out.println("Player's hand: " + playerHand);
		System.out.println("Dealer's hand: " + dealerHand);

		// determine winner
		if (playerScore > 21) {
			System.out.println("Player busts. Dealer wins.");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busts. Player wins.");
		} else if (playerScore > dealerScore) {
			System.out.println("Player wins.");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	private int calculateScore(List<Deck.Card> hand) {
		int score = 0;
		int numAces = 0;
		for (Deck.Card card : hand) {
			if (card.getValue() == 1) {
				numAces++;
			} else if (card.getValue() >= 10) {
				score += 10;
			} else {
				score += card.getValue();
			}
		}
		for (int i = 0; i < numAces; i++) {
			if (score + 11 <= 21) {
				score += 11;
			} else {
				score += 1;
			}
		}
		return score;
	}

	private static class Deck {
		private List<Card> cards;

		public Deck() {
			cards = new ArrayList<>();
			for (int i = 1; i <= 13; i++) {
				for (String suit : new String[] { "Hearts", "Diamonds", "Clubs", "Spades" }) {
					Card card = new Card(i, suit);
					cards.add(card);
				}
			}
			Collections.shuffle(cards);
		}

		public Card drawCard() {
			if (cards.isEmpty()) {
				throw new IllegalStateException("Deck is empty");
			}
			return cards.remove(0);
		}

		public class Card {
			private int value;
			private String suit;

			public Card(int value, String suit) {
				this.value = value;
				this.suit = suit;
			}

			public int getValue() {
				return value;
			}

			public String getSuit() {
				return suit;
			}

			@Override
			public String toString() {
				String valueString;
				switch (value) {
				case 1:
					valueString = "Ace";
					break;
				case 11:
					valueString = "Jack";
					break;
				case 12:
					valueString = "Queen";
					break;
				case 13:
					valueString = "King";
					break;
				default:
					valueString = String.valueOf(value);
				}
				return valueString + " of " + suit;
			}
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}

class Board {
	private final int SIZE = 3;
	private final String[][] cells;

	public Board() {
		this.cells = new String[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				cells[i][j] = " ";
			}
		}
	}

	public boolean isFull() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (cells[i][j].equals(" ")) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean hasWinner() {
		for (int i = 0; i < SIZE; i++) {
			if (cells[i][0].equals(cells[i][1]) && cells[i][0].equals(cells[i][2]) && !cells[i][0].equals(" ")) {
				return true;
			}
			if (cells[0][i].equals(cells[1][i]) && cells[0][i].equals(cells[2][i]) && !cells[0][i].equals(" ")) {
				return true;
			}
		}
		if (cells[0][0].equals(cells[1][1]) && cells[0][0].equals(cells[2][2]) && !cells[0][0].equals(" ")) {
			return true;
		}
		if (cells[0][2].equals(cells[1][1]) && cells[0][2].equals(cells[2][0]) && !cells[0][2].equals(" ")) {
			return true;
		}
		return false;
	}

	public void set(int row, int col, String value) {
		cells[row][col] = value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  0 1 2\n");
		for (int i = 0; i < SIZE; i++) {
			sb.append(i).append(" ");
			for (int j = 0; j < SIZE; j++) {
				sb.append(cells[i][j]).append("|");
			}
			sb.setCharAt(sb.length() - 1, '\n');
			if (i < SIZE - 1) {
				sb.append("  -----\n");
			}
		}
		return sb.toString();
	}
}

abstract class Player {
	protected final String symbol;
	protected final String name;

	public Player(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public abstract int[] getMove(Board board);
}

class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String symbol) {
        super(symbol, "Human");
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] getMove(Board board) {
        System.out.print("Enter row and column (e.g., 1 2): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
}