package JSB_B01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static final String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
	private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
			"Ace" };
	private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");
		System.out.print("How many players? ");
		int numPlayers = scanner.nextInt();

		// create the deck and shuffle it
		ArrayList<Card> deck = createDeck();
		Collections.shuffle(deck);

		// create the players and deal the cards
		ArrayList<Player> players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Enter name for player " + (i + 1) + ": ");
			String name = scanner.next();
			players.add(new Player(name));
		}
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				Card card = deck.remove(0);
				player.addCard(card);
			}
		}

		// deal the dealer's cards
		Dealer dealer = new Dealer();
		Card card = deck.remove(0);
		dealer.addCard(card);
		card = deck.remove(0);
		dealer.addCard(card);

		// play the game
		boolean gameEnd = false;
		while (!gameEnd) {
			// show the players' hands
			for (Player player : players) {
				System.out.println(player.getName() + "'s hand: " + player.showHand());
			}
			System.out.println("Dealer's hand: " + dealer.showHand());

			// players take turns hitting or standing
			for (Player player : players) {
				boolean turnEnd = false;
				while (!turnEnd) {
					System.out.print(player.getName() + ", do you want to hit or stand? (h/s) ");
					String choice = scanner.next();
					if (choice.equals("h")) {
						// player hits
						Card hitCard = deck.remove(0);
						player.addCard(hitCard);
						System.out.println(player.getName() + " hits!");
						System.out.println(player.getName() + "'s hand: " + player.showHand());
						// check if player busts
						if (player.getHandValue() > 21) {
							System.out.println(player.getName() + " busts!");
							turnEnd = true;
						}
					} else if (choice.equals("s")) {
						// player stands
						System.out.println(player.getName() + " stands.");
						turnEnd = true;
					} else {
						System.out.println("Invalid choice, please enter 'h' or 's'.");
					}
				}
			}

			// dealer hits until hand value is 17 or higher
			while (dealer.getHandValue() < 17) {
				Card hitCard = deck.remove(0);
				dealer.addCard(hitCard);
				System.out.println("Dealer hits!");
				System.out.println("Dealer's hand: " + dealer.showHand());
				// check if dealer busts
				if (dealer.getHandValue() > 21) {
					System.out.println("Dealer busts!");
					gameEnd = true;
				}
			}

			// determine the winner
			if (!gameEnd) {
				int dealerHandValue = dealer.getHandValue();
				for (Player player : players) {
					int playerHandValue = player.getHandValue();
					if (playerHandValue > 21) {
						System.out.println(player.getName() + " busts, dealer wins!");
					} else if (dealerHandValue > 21) {
						System.out.println("Dealer busts, " + player.getName() + " wins!");
					} else if (playerHandValue > dealerHandValue) {
						System.out.println(player.getName() + " wins!");
					} else if (playerHandValue < dealerHandValue) {
						System.out.println("Dealer wins!");
					} else {
						System.out.println(player.getName() + " and dealer tie.");
					}
				}
				gameEnd = true;
			}
		}
		System.out.println("Thanks for playing Blackjack!");
	}

	// creates a deck of 52 cards
	private static ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<>();
		for (String suit : SUITS) {
			for (int i = 0; i < RANKS.length; i++) {
				String rank = RANKS[i];
				int value = VALUES[i];
				Card card = new Card(suit, rank, value);
				deck.add(card);
			}
		}
		return deck;
	}
}

class Card {
	private String suit;
	private String rank;
	private int value;

	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Player {
	private String name;
	protected ArrayList<Card> hand; // HAD TO CHANGE THIS!! from private

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public int getHandValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	public String showHand() {
		StringBuilder sb = new StringBuilder();
		for (Card card : hand) {
			sb.append(card.toString());
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
}

class Dealer extends Player {
	public Dealer() {
		super("Dealer");
	}

	public String showHand() {
		return hand.get(0).toString() + ", [hidden]";
	}
}