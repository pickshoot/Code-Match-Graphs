package B3;

import java.util.*;

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
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				int value = i + 1;
				if (value > 10) {
					value = 10;
				}
				if (i == 0) {
					value = 11;
				}
				cards.add(new Card(suit, ranks[i], value));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card dealCard() {
		return cards.remove(0);
	}
}

class Hand {
	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : cards) {
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.getRank()).append(" of ").append(card.getSuit()).append("\n");
		}
		return sb.toString();
	}
}

class Player {
	private String name;
	private Hand hand;

	public Player(String name) {
		this.name = name;
		hand = new Hand();
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public int getValue() {
		return hand.getValue();
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + "'s hand:\n" + hand.toString();
	}
}

public class Blackjack {
	private Deck deck;
	private List<Player> players;
	private Player dealer;

	public Blackjack(String[] playerNames) {
		deck = new Deck();
		deck.shuffle();
		players = new ArrayList<>();
		for (String playerName : playerNames) {
			players.add(new Player(playerName));
		}
		dealer = new Player("Dealer");
	}

	public void playRound() {
		for (Player player : players) {
			player.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			System.out.println(player);

			while (player.getValue() < 21) {
				String action = promptForAction(player);
				if (action.equals("hit")) {
					player.addCard(deck.dealCard());
					System.out.println(player);
				} else {
					break;
				}
			}
		}
	}

	public void playDealer() {
		dealer.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		System.out.println(dealer);

		while (dealer.getValue() < 17) {
			dealer.addCard(deck.dealCard());
			System.out.println(dealer);
		}
	}

	public void determineWinners() {
		int dealerValue = dealer.getValue();
		for (Player player : players) {
			int playerValue = player.getValue();
			if (playerValue > 21) {
				System.out.println(player.getName() + " busts!");
			} else if (dealerValue > 21) {
				System.out.println(player.getName() + " wins!");
			} else if (playerValue > dealerValue) {
				System.out.println(player.getName() + " wins!");
			} else if (playerValue < dealerValue) {
				System.out.println(player.getName() + " loses!");
			} else {
				System.out.println(player.getName() + " pushes!");
			}
		}
	}

	public String promptForAction(Player player) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(player.getName() + ", do you want to hit or stay? ");
		String input = scanner.nextLine();
		while (!input.equalsIgnoreCase("hit") && !input.equalsIgnoreCase("stay")) {
			System.out.print("Invalid input. Please enter 'hit' or 'stay': ");
			input = scanner.nextLine();
		}
		return input.toLowerCase();
	}

	public static void main(String[] args) {
		String[] playerNames = { "Alice", "Bob", "Charlie" };
		Blackjack game = new Blackjack(playerNames);
		game.playRound();
		game.playDealer();
		game.determineWinners();
	}
}