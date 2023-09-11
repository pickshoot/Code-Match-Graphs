package A7;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	private Deck deck;
	private Player dealer;
	private Player player;

	public Blackjack() {
		deck = new Deck();
		dealer = new Player();
		player = new Player();
	}

	public void play() {
		System.out.println("Let's play Blackjack!");
		System.out.println();
		deck.shuffle();
		dealer.addCard(deck.dealCard());
		dealer.displayHand();
		player.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		player.displayHand();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				player.addCard(deck.dealCard());
				player.displayHand();
				if (player.getValue() > 21) {
					System.out.println("You busted! Dealer wins.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			}
		}
		while (dealer.getValue() < 17) {
			dealer.addCard(deck.dealCard());
			dealer.displayHand();
		}
		if (dealer.getValue() > 21) {
			System.out.println("Dealer busted! You win.");
		} else if (dealer.getValue() > player.getValue()) {
			System.out.println("Dealer wins.");
		} else if (dealer.getValue() < player.getValue()) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
	}
}

class Card {
	private String suit;
	private String value;

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

	public void display() {
		System.out.println(value + " of " + suit);
	}
}

class Deck {
	private Card[] cards;
	private int currentCardIndex;

	public Deck() {
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		cards = new Card[52];
		currentCardIndex = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				cards[i * 13 + j] = new Card(suits[i], values[j]);
			}
		}
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = cards.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
		currentCardIndex = 0;
	}

	public Card dealCard() {
		if (currentCardIndex < cards.length) {
			Card card = cards[currentCardIndex];
			currentCardIndex++;
			return card;
		} else {
			return null;
		}
	}
}

class Player {
	private Card[] hand;
	private int numCards;

	public Player() {
		hand = new Card[10];
		numCards = 0;
	}

	public void addCard(Card card) {
		hand[numCards] = card;
		numCards++;
	}

	public int getValue() {
		int value = 0;
		int numAces = 0;
		for (int i = 0; i < numCards; i++) {
			String cardValue = hand[i].getValue();
			if (cardValue.equals("Ace")) {
				numAces++;
				value += 11;
			} else if (cardValue.equals("King") || cardValue.equals("Queen") || cardValue.equals("Jack")) {
				value += 10;
			} else {
				value += Integer.parseInt(cardValue);
			}
		}
		while (numAces > 0 && value > 21) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	public void displayHand() {
		for (int i = 0; i < numCards; i++) {
			hand[i].display();
		}
	}
}