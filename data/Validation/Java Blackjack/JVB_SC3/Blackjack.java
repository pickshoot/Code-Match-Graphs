package JVB_SC3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

	private static final int BLACKJACK = 21;
	private static final int DEALER_THRESHOLD = 17;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");
		System.out.println();

		while (true) {
			List<Card> deck = createDeck();
			Collections.shuffle(deck);

			List<Card> playerHand = new ArrayList<>();
			List<Card> dealerHand = new ArrayList<>();

			playerHand.add(drawCard(deck));
			dealerHand.add(drawCard(deck));
			playerHand.add(drawCard(deck));
			dealerHand.add(drawCard(deck, false));

			System.out.println("Your hand: " + playerHand);
			System.out.println("Dealer's hand: " + dealerHand.get(0));
			System.out.println();

			// Player's turn
			while (true) {
				System.out.print("Do you want to hit or stand? ");
				String input = scanner.nextLine().toLowerCase();
				System.out.println();

				if (input.equals("hit")) {
					playerHand.add(drawCard(deck));
					System.out.println("Your hand: " + playerHand);

					if (getHandValue(playerHand) > BLACKJACK) {
						System.out.println("You busted! Dealer wins.");
						break;
					}
				} else if (input.equals("stand")) {
					break;
				} else {
					System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
				}
			}

			if (getHandValue(playerHand) <= BLACKJACK) {
				// Dealer's turn
				System.out.println("Dealer's hand: " + dealerHand);

				while (getHandValue(dealerHand) < DEALER_THRESHOLD) {
					dealerHand.add(drawCard(deck));
					System.out.println("Dealer hits: " + dealerHand.get(dealerHand.size() - 1));
				}

				int playerValue = getHandValue(playerHand);
				int dealerValue = getHandValue(dealerHand);

				System.out.println("Your hand: " + playerHand + " (" + playerValue + ")");
				System.out.println("Dealer's hand: " + dealerHand + " (" + dealerValue + ")");
				System.out.println();

				if (dealerValue > BLACKJACK) {
					System.out.println("Dealer busted! You win!");
				} else if (dealerValue > playerValue) {
					System.out.println("Dealer wins!");
				} else if (dealerValue < playerValue) {
					System.out.println("You win!");
				} else {
					System.out.println("It's a tie!");
				}
			}

			System.out.println();
			System.out.print("Do you want to play again? ");
			String input = scanner.nextLine().toLowerCase();
			System.out.println();

			if (!input.equals("yes")) {
				break;
			}
		}

		scanner.close();
	}

	private static List<Card> createDeck() {
		List<Card> deck = new ArrayList<>();

		for (int i = 2; i <= 10; i++) {
			deck.add(new Card(i, Suit.CLUBS));
			deck.add(new Card(i, Suit.DIAMONDS));
			deck.add(new Card(i, Suit.HEARTS));
			deck.add(new Card(i, Suit.SPADES));
		}

		deck.add(new Card(Card.ACE_VALUE, Suit.CLUBS));
		deck.add(new Card(Card.ACE_VALUE, Suit.DIAMONDS));
		deck.add(new Card(Card.ACE_VALUE, Suit.HEARTS));
		deck.add(new Card(Card.ACE_VALUE, Suit.SPADES));
		for (int i = 0; i < deck.size(); i++) {
			int j = (int) (Math.random() * deck.size());
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}

		return deck;
	}

	private static Card drawCard(List<Card> deck) {
		return drawCard(deck, true);
	}

	private static Card drawCard(List<Card> deck, boolean visible) {
		Card card = deck.remove(0);
		card.setVisible(visible);
		return card;
	}

	private static int getHandValue(List<Card> hand) {
		int value = 0;
		int aces = 0;

		for (Card card : hand) {
			value += card.getValue();

			if (card.getValue() == Card.ACE_VALUE) {
				aces++;
			}
		}

		while (aces > 0 && value > BLACKJACK) {
			value -= 10;
			aces--;
		}

		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}

class Card {
	public static final int ACE_VALUE = 11;
	private final int value;
	private final Suit suit;
	private boolean visible;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
		this.visible = true;
	}

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		if (visible) {
			return value + " of " + suit;
		} else {
			return "hidden";
		}
	}
}