package JCB_C04;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");
		System.out.println("----------------------");
		System.out.println();
		System.out.print("Enter the number of players: ");
		int numPlayers = scanner.nextInt();
		scanner.nextLine(); // consume the newline character

		// Create the deck of cards
		ArrayList<Card> deck = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(new Card(i));
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);

		// Create the players
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 1; i <= numPlayers; i++) {
			System.out.print("Enter the name of player " + i + ": ");
			String name = scanner.nextLine();
			players.add(new Player(name));
		}

		// Deal the cards
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				Card card = deck.remove(0);
				player.addCard(card);
			}
		}

		// Show the initial hands
		for (Player player : players) {
			System.out.println(player.getName() + "'s hand: " + player.getHandString());
		}

		// Play the game
		for (Player player : players) {
			while (player.getHandValue() < 21) {
				System.out.println(player.getName() + ", do you want to hit or stand?");
				String choice = scanner.nextLine();
				if (choice.equalsIgnoreCase("hit")) {
					Card card = deck.remove(0);
					player.addCard(card);
					System.out.println(player.getName() + "'s hand: " + player.getHandString());
				} else if (choice.equalsIgnoreCase("stand")) {
					break;
				} else {
					System.out.println("Invalid choice, please enter hit or stand.");
				}
			}
		}

		// Show the final hands
		for (Player player : players) {
			System.out.println(player.getName() + "'s hand: " + player.getHandString());
			if (player.getHandValue() > 21) {
				System.out.println(player.getName() + " busts!");
			}
		}

		// Determine the winner
		int highestScore = 0;
		ArrayList<Player> winners = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getHandValue() <= 21) {
				if (player.getHandValue() > highestScore) {
					highestScore = player.getHandValue();
					winners.clear();
					winners.add(player);
				} else if (player.getHandValue() == highestScore) {
					winners.add(player);
				}
			}
		}
		if (winners.size() == 1) {
			System.out
					.println(winners.get(0).getName() + " wins with a score of " + winners.get(0).getHandValue() + "!");
		} else {
			System.out.print("There is a tie between ");
			for (int i = 0; i < winners.size() - 1; i++) {
				System.out.print(winners.get(i).getName() + ", ");
			}
			System.out.println(
					"and " + winners.get(winners.size() - 1).getName() + " with a score of " + highestScore + "!");
		}
	}
}

class Card {
	private int value;

	public Card(int value) {
		this.value = value;
	}

	public int getValue() {
		if (value == 1) {
			return 11;
		} else if (value >= 10) {
			return 10;
		} else {
			return value;
		}
	}

	public String toString() {
		if (value == 1) {
			return "Ace";
		} else if (value == 11) {
			return "Jack";
		} else if (value == 12) {
			return "Queen";
		} else if (value == 13) {
			return "King";
		} else {
			return Integer.toString(value);
		}
	}
}

class Player {
	private String name;
	private ArrayList<Card> hand;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public String getHandString() {
		String result = "";
		for (Card card : hand) {
			result += card.toString() + ", ";
		}
		result = result.substring(0, result.length() - 2); // remove the last comma and space
		return result;
	}

	public int getHandValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getValue() == 11) {
				numAces++;
			}
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}
}