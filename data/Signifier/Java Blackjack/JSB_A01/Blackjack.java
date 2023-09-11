package blackjackSA1;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		// Initialize deck
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		List<Card> deck = new ArrayList<Card>();
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				Card card = new Card(suit, ranks[i], values[i]);
				deck.add(card);
			}
		}

		// Shuffle deck
		Collections.shuffle(deck);

		// Initialize player and dealer hands
		List<Card> playerHand = new ArrayList<Card>();
		List<Card> dealerHand = new ArrayList<Card>();

		// Deal cards
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Player turn
		boolean playerBust = false;
		while (true) {
			System.out.println("Your hand: " + playerHand);
			System.out.println("Dealer's upcard: " + dealerHand.get(0));
			System.out.print("Hit or stand? ");
			String choice = input.nextLine().toLowerCase();
			if (choice.equals("hit")) {
				playerHand.add(deck.remove(0));
				int playerTotal = getHandTotal(playerHand);
				if (playerTotal > 21) {
					playerBust = true;
					break;
				}
			} else if (choice.equals("stand")) {
				break;
			}
		}

		// Dealer turn
		boolean dealerBust = false;
		if (!playerBust) {
			while (getHandTotal(dealerHand) < 17) {
				dealerHand.add(deck.remove(0));
				int dealerTotal = getHandTotal(dealerHand);
				if (dealerTotal > 21) {
					dealerBust = true;
					break;
				}
			}
		}

		// Determine winner
		int playerTotal = getHandTotal(playerHand);
		int dealerTotal = getHandTotal(dealerHand);
		if (playerBust) {
			System.out.println("You bust! Dealer wins.");
		} else if (dealerBust) {
			System.out.println("Dealer busts! You win.");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}
	}

	public static int getHandTotal(List<Card> hand) {
		int total = 0;
		int numAces = 0;
		for (Card card : hand) {
			total += card.getValue();
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
		}
		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}
		return total;
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