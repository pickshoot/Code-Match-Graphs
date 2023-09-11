package B5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private Player player;
	private Player dealer;
	private Scanner scanner;

	public Blackjack() {
		deck = new Deck();
		player = new Player();
		dealer = new Player();
		scanner = new Scanner(System.in);
	}

	public void startGame() {
		deck.shuffle();
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());

		System.out.println("Your cards: " + player.getHand());
		System.out.println("Dealer's cards: " + dealer.getHand().get(0));

		if (checkBlackjack(player)) {
			player.setHasWon(true);
			System.out.println("You have Blackjack!");
			return;
		}

		while (true) {
			System.out.print("Type 'hit' to draw another card or 'stand' to end your turn: ");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("hit")) {
				player.addCard(deck.dealCard());
				System.out.println("Your cards: " + player.getHand());

				if (checkBust(player)) {
					dealer.setHasWon(true);
					System.out.println("You bust! Dealer wins.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		while (dealer.getScore() < 17) {
			dealer.addCard(deck.dealCard());
		}

		System.out.println("Dealer's cards: " + dealer.getHand());
		if (checkBust(dealer)) {
			player.setHasWon(true);
			System.out.println("Dealer bust! You win.");
		} else if (dealer.getScore() >= player.getScore()) {
			dealer.setHasWon(true);
			System.out.println("Dealer wins.");
		} else {
			player.setHasWon(true);
			System.out.println("You win!");
		}
	}

	private boolean checkBlackjack(Player player) {
		return player.getScore() == 21 && player.getHand().size() == 2;
	}

	private boolean checkBust(Player player) {
		return player.getScore() > 21;
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		boolean playAgain = true;

		while (playAgain) {
			blackjack.startGame();

			System.out.print("Would you like to play again? (yes/no): ");
			String input = blackjack.scanner.nextLine();

			if (input.equalsIgnoreCase("no")) {
				playAgain = false;
			} else if (!input.equalsIgnoreCase("yes")) {
				System.out.println("Invalid input. Assuming you don't want to play again.");
				playAgain = false;
			}

			blackjack.player.getHand().clear();
			blackjack.dealer.getHand().clear();
			blackjack.player.setScore(0);
			blackjack.dealer.setScore(0);
			blackjack.player.setHasWon(false);
			blackjack.dealer.setHasWon(false);
		}
	}
}

class Card {
	private int rank;
	private String suit;

	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		for (String suit : suits) {
			for (int rank = 2; rank <= 10; rank++) {
				cards.add(new Card(rank, suit));
			}
			cards.add(new Card(10, suit)); // Jack
			cards.add(new Card(10, suit)); // Queen
			cards.add(new Card(10, suit)); // King
			cards.add(new Card(1, suit)); // Ace
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card dealCard() {
		if (cards.isEmpty()) {
			reset();
			shuffle();
		}
		return cards.remove(0);
	}

	public void reset() {
		cards.clear();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		for (String suit : suits) {
			for (int rank = 2; rank <= 10; rank++) {
				cards.add(new Card(rank, suit));
			}
			cards.add(new Card(10, suit)); // Jack
			cards.add(new Card(10, suit)); // Queen
			cards.add(new Card(10, suit)); // King
			cards.add(new Card(1, suit)); // Ace
		}
	}
}

class Player {
	private ArrayList<Card> hand;
	private int score;
	private boolean hasWon;

	public Player() {
		hand = new ArrayList<Card>();
		score = 0;
		hasWon = false;
	}

	public void addCard(Card card) {
		hand.add(card);
		score += card.getRank();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getScore() {
		return score;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	public boolean getHasWon() {
		return hasWon;
	}

	public void setScore(int score) {
		this.score = score;
	}
}