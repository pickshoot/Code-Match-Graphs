package JCB_A02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Deck deck = new Deck();
		Player player = new Player();
		Dealer dealer = new Dealer();
		Scanner scanner = new Scanner(System.in);
		boolean playAgain = true;

		while (playAgain) {
			System.out.println("Welcome to Blackjack!");

			// Shuffle the deck and deal the initial cards
			deck.shuffle();
			player.addCard(deck.deal());
			dealer.addCard(deck.deal());
			player.addCard(deck.deal());
			dealer.addCard(deck.deal());

			// Show the player's cards and the dealer's first card
			System.out.println("Your cards:");
			player.showCards();
			System.out.println("Dealer's first card:");
			dealer.showFirstCard();

			// Ask the player if they want to hit or stand
			boolean playerTurn = true;
			while (playerTurn) {
				System.out.println("Do you want to hit or stand? (h/s)");
				String choice = scanner.nextLine();
				if (choice.equalsIgnoreCase("h")) {
					Card card = deck.deal();
					System.out.println("You drew:");
					card.show();
					player.addCard(card);
					player.showCards();
					if (player.getTotal() > 21) {
						System.out.println("You busted! Dealer wins.");
						playerTurn = false;
					}
				} else {
					playerTurn = false;
				}
			}

			// Dealer's turn
			if (player.getTotal() <= 21) {
				System.out.println("Dealer's turn:");
				dealer.showCards();
				while (dealer.getTotal() < 17) {
					Card card = deck.deal();
					System.out.println("Dealer drew:");
					card.show();
					dealer.addCard(card);
					dealer.showCards();
					if (dealer.getTotal() > 21) {
						System.out.println("Dealer busted! You win.");
					}
				}
				if (dealer.getTotal() >= 17 && dealer.getTotal() <= 21) {
					if (player.getTotal() == dealer.getTotal()) {
						System.out.println("It's a tie!");
					} else if (player.getTotal() > dealer.getTotal()) {
						System.out.println("You win!");
					} else {
						System.out.println("Dealer wins.");
					}
				}
			}

			// Ask if the player wants to play again
			System.out.println("Do you want to play again? (y/n)");
			String choice = scanner.nextLine();
			if (!choice.equalsIgnoreCase("y")) {
				playAgain = false;
			}

			// Reset the game
			deck.reset();
			player.reset();
			dealer.reset();
		}

		System.out.println("Thanks for playing!");
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

	public void show() {
		System.out.println(rank + " of " + suit);
	}

	public int getValue() {
		return value;
	}
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				Card card = new Card(ranks[i], suits[j], values[i]);
				cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card deal() {
		if (cards.isEmpty()) {
			reset();
			shuffle();
		}
		return cards.remove(0);
	}

	public void reset() {
		cards.clear();
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
		int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				Card card = new Card(ranks[i], suits[j], values[i]);
				cards.add(card);
			}
		}
	}
}

class Player {
	private List<Card> cards;

	public Player() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void showCards() {
		for (Card card : cards) {
			card.show();
		}
		System.out.println("Total: " + getTotal());
	}

	public int getTotal() {
		int total = 0;
		int aces = 0;
		for (Card card : cards) {
			total += card.getValue();
			if (card.getValue() == 11) {
				aces++;
			}
		}
		while (total > 21 && aces > 0) {
			total -= 10;
			aces--;
		}
		return total;
	}

	public void reset() {
		cards.clear();
	}
}

class Dealer {
	private List<Card> cards;

	public Dealer() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void showFirstCard() {
		cards.get(0).show();
	}

	public void showCards() {
		for (Card card : cards) {
			card.show();
		}
		System.out.println("Total: " + getTotal());
	}

	public int getTotal() {
		int total = 0;
		int aces = 0;
		for (Card card : cards) {
			total += card.getValue();
			if (card.getValue() == 11) {
				aces++;
			}
		}
		while (total > 21 && aces > 0) {
			total -= 10;
			aces--;
		}
		return total;
	}

	public void reset() {
		cards.clear();
	}
}