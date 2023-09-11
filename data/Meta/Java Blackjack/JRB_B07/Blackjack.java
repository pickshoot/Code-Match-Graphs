package JRB_B07;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("What is your name? ");
		String playerName = scanner.nextLine();
		System.out.println("Hello, " + playerName + "! Let's play Blackjack.");

		Deck deck = new Deck();
		deck.shuffle();

		Player player = new Player(playerName);
		Dealer dealer = new Dealer();

		player.addCardToHand(deck.draw());
		player.addCardToHand(deck.draw());

		dealer.addCardToHand(deck.draw());
		dealer.addCardToHand(deck.draw());

		System.out.println("Your cards: " + player.getHandAsString());
		System.out.println("Dealer's cards: " + dealer.getHandAsStringWithFirstCardHidden());

		boolean playerTurn = true;
		while (playerTurn) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				player.addCardToHand(deck.draw());
				System.out.println("Your cards: " + player.getHandAsString());
				if (player.getScore() > 21) {
					System.out.println("Bust! You lose.");
					playerTurn = false;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				playerTurn = false;
			}
		}

		if (player.getScore() <= 21) {
			dealer.revealFirstCard();
			System.out.println("Dealer's cards: " + dealer.getHandAsString());
			while (dealer.getScore() < 17) {
				dealer.addCardToHand(deck.draw());
				System.out.println("Dealer hits: " + dealer.getHandAsString());
			}
			if (dealer.getScore() > 21) {
				System.out.println("Dealer busts! You win.");
			} else if (dealer.getScore() > player.getScore()) {
				System.out.println("Dealer wins.");
			} else if (dealer.getScore() < player.getScore()) {
				System.out.println("You win!");
			} else {
				System.out.println("It's a tie!");
			}
		}
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

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

		cards = new ArrayList<Card>();

		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				int value = i + 1;
				if (i >= 10) {
					value = 10;
				}
				if (i == 0) {
					value = 11;
				}
				Card card = new Card(suit, ranks[i], value);
				cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card draw() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}
}

class Hand {
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		int numAces = 0;
		for (Card card : cards) {
			score += card.getValue();
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		return sb.toString();
	}
}

class Player {
	private String name;
	private Hand hand;

	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
	}

	public void addCardToHand(Card card) {
		hand.addCard(card);
	}

	public String getHandAsString() {
		return hand.toString() + " (score: " + hand.getScore() + ")";
	}

	public int getScore() {
		return hand.getScore();
	}
}

class Dealer {
	private Hand hand;
	private boolean firstCardHidden;

	public Dealer() {
		this.hand = new Hand();
		this.firstCardHidden = true;
	}

	public void addCardToHand(Card card) {
		hand.addCard(card);
	}

	public String getHandAsString() {
		return hand.toString() + " (score: " + hand.getScore() + ")";
	}

	public String getHandAsStringWithFirstCardHidden() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append("hidden card").append(", ");
		for (int i = 1; i < hand.getScore(); i++) {
			sb.append(hand.toString().split(", ")[i]).append(", ");
		}
		if (!firstCardHidden || hand.getScore() > 21) {
			sb = new StringBuilder();
			sb.append(hand.toString());
		}
		sb.append("]");
		return sb.toString();
	}

	public int getScore() {
		return hand.getScore();
	}

	public void revealFirstCard() {
		firstCardHidden = false;
	}
}