package JRB_B09;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();

		Deck deck = new Deck();
		deck.shuffle();

		Player player = new Player(playerName);
		Dealer dealer = new Dealer();

		player.addCardToHand(deck.deal());
		player.addCardToHand(deck.deal());
		System.out.println(playerName + "'s hand: " + player.getHand());

		dealer.addCardToHand(deck.deal());
		dealer.addCardToHand(deck.deal());
		System.out.println("Dealer's hand: " + dealer.getHandWithFirstCardHidden());

		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				player.addCardToHand(deck.deal());
				System.out.println(playerName + "'s hand: " + player.getHand());
				if (player.getHandValue() > 21) {
					System.out.println("Bust! " + playerName + " loses.");
					return;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}

		while (dealer.getHandValue() < 17) {
			dealer.addCardToHand(deck.deal());
			System.out.println("Dealer's hand: " + dealer.getHandWithFirstCardHidden());
		}

		System.out.println("Dealer's hand: " + dealer.getHand());
		if (dealer.getHandValue() > 21) {
			System.out.println("Dealer busts! " + playerName + " wins.");
			return;
		}

		int playerScore = player.getHandValue();
		int dealerScore = dealer.getHandValue();
		if (playerScore > dealerScore && playerScore <= 21) {
			System.out.println(playerName + " wins!");
		} else if (dealerScore > playerScore && dealerScore <= 21) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie.");
		}
	}
}

class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
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

class Card {
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class Hand {
	protected List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getHandValue() {
		int handValue = 0;
		int numAces = 0;
		for (Card card : cards) {
			if (card.getValue() == 1) {
				numAces++;
				handValue += 11;
			} else {
				handValue += card.getValue();
			}
		}
		while (handValue > 21 && numAces > 0) {
			handValue -= 10;
			numAces--;
		}
		return handValue;
	}

	public String getHand() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(" (").append(getHandValue()).append(")");
		return sb.toString();
	}
}

class Player extends Hand {
	private String name;

	public Player(String name) {
		super();
		this.name = name;
	}

	public void addCardToHand(Card card) {
		addCard(card);
	}
}

class Dealer extends Hand {
	public void addCardToHand(Card card) {
		addCard(card);
	}

	public String getHandWithFirstCardHidden() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(cards.get(0)).append(", [hidden card]]");
		return sb.toString();
	}
}