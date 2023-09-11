package JCB_A06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		List<Player> players = new ArrayList<>();
		players.add(new Player("Player 1"));
		players.add(new Player("Dealer"));
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;

		while (!gameOver) {
			for (Player player : players) {
				if (player.getName().equals("Dealer")) {
					// Dealer's turn
					dealerTurn(player, deck);
				} else {
					// Player's turn
					playerTurn(player, deck, scanner);
				}
			}

			// Check for winners
			Player winner = getWinner(players);
			if (winner != null) {
				System.out.println("The winner is " + winner.getName() + "!");
			} else {
				System.out.println("It's a tie!");
			}

			// Play again?
			System.out.print("Play again? (y/n) ");
			String input = scanner.nextLine();
			if (input.equals("n")) {
				gameOver = true;
			} else {
				// Reset game state
				for (Player player : players) {
					player.reset();
				}
				deck = new Deck();
				deck.shuffle();
			}
		}
	}

	private static void playerTurn(Player player, Deck deck, Scanner scanner) {
		System.out.println(player.getName() + "'s turn:");
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		System.out.println("Your cards: " + player.getHand());
		System.out.println("Your total: " + player.getTotal());

		while (player.getTotal() < 21) {
			System.out.print("Hit or stand? (h/s) ");
			String input = scanner.nextLine();
			if (input.equals("h")) {
				player.addCard(deck.deal());
				System.out.println("Your cards: " + player.getHand());
				System.out.println("Your total: " + player.getTotal());
			} else {
				break;
			}
		}
	}

	private static void dealerTurn(Player dealer, Deck deck) {
		System.out.println(dealer.getName() + "'s turn:");
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());
		System.out.println("Dealer's cards: " + dealer.getHand());
		System.out.println("Dealer's total: " + dealer.getTotal());

		while (dealer.getTotal() < 17) {
			dealer.addCard(deck.deal());
			System.out.println("Dealer's cards: " + dealer.getHand());
			System.out.println("Dealer's total: " + dealer.getTotal());
		}
	}

	private static Player getWinner(List<Player> players) {
		Player winner = null;
		int highestTotal = 0;
		boolean tie = false;

		for (Player player : players) {
			int total = player.getTotal();
			if (total > highestTotal && total <= 21) {
				highestTotal = total;
				winner = player;
				tie = false;
			} else if (total == highestTotal) {
				tie = true;
			}
		}

		if (tie) {
			return null;
		} else {
			return winner;
		}
	}
}

class Card {
	private final String suit;
	private final String value;

	public Card(String suit, String value) {
		this.suit = suit;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public String getValue() {
		return value;
	}

	public int getNumericValue() {
		if (value.equals("Ace")) {
			return 11;
		} else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(value);
		}
	}

	public String toString() {
		return value + " of " + suit;
	}
}

class Deck {
	private final List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Jack" };

		for (String suit : suits) {
			for (String value : values) {
				cards.add(new Card(suit, value));
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

class Player {
	private final String name;
	private final List<Card> hand;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getTotal() {
		int total = 0;
		int numAces = 0;

		for (Card card : hand) {
			int value = card.getNumericValue();
			if (value == 11) {
				numAces++;
			}
			total += value;
		}

		// Handle aces
		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}

		return total;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void reset() {
		hand.clear();
	}
}