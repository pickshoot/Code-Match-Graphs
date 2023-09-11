package JSB_C07;

import java.util.*;

public class Blackjack {
	private static final int BLACKJACK = 21;
	private static final int DEALER_STANDS_AT = 17;
	private static final int INITIAL_DEAL = 2;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		int playerChips = 100;
		while (playerChips > 0) {
			System.out.println("You have " + playerChips + " chips.");
			int bet = getValidBet(input, playerChips);
			Hand playerHand = new Hand(deck.deal(INITIAL_DEAL));
			Hand dealerHand = new Hand(deck.deal(INITIAL_DEAL));
			System.out.println("Dealer's face-up card: " + dealerHand.getCard(0));
			while (true) {
				System.out.println("Your hand: " + playerHand);
				System.out.print("Hit or stand? (h/s) ");
				char decision = input.next().charAt(0);
				if (decision == 'h') {
					playerHand.addCard(deck.deal());
					if (playerHand.getScore() > BLACKJACK) {
						System.out.println("Bust! Your hand: " + playerHand);
						playerChips -= bet;
						break;
					}
				} else if (decision == 's') {
					break;
				} else {
					System.out.println("Invalid input. Please enter 'h' or 's'.");
				}
			}
			if (playerHand.getScore() <= BLACKJACK) {
				System.out.println("Dealer's hand: " + dealerHand);
				while (dealerHand.getScore() < DEALER_STANDS_AT) {
					dealerHand.addCard(deck.deal());
					System.out.println("Dealer hits: " + dealerHand.getCard(dealerHand.size() - 1));
				}
				if (dealerHand.getScore() > BLACKJACK) {
					System.out.println("Dealer busts! You win.");
					playerChips += bet;
				} else if (dealerHand.getScore() > playerHand.getScore()) {
					System.out.println("Dealer wins. Your hand: " + playerHand);
					playerChips -= bet;
				} else if (dealerHand.getScore() < playerHand.getScore()) {
					System.out.println("You win! Your hand: " + playerHand);
					playerChips += bet;
				} else {
					System.out.println("Push (tie). Your hand: " + playerHand);
				}
			}
			if (playerChips == 0) {
				System.out.println("You're out of chips. Game over.");
				break;
			}
			System.out.print("Play again? (y/n) ");
			char playAgain = input.next().charAt(0);
			if (playAgain != 'y') {
				break;
			}
			deck.reset();
			deck.shuffle();
		}
		input.close();
	}

	private static int getValidBet(Scanner input, int maxBet) {
		while (true) {
			System.out.print("Enter your bet (1-" + maxBet + "): ");
			int bet = input.nextInt();
			if (bet < 1 || bet > maxBet) {
				System.out.println("Invalid bet. Please enter a value between 1 and " + maxBet + ".");
			} else {
				return bet;
			}
		}
	}
}

class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private final List<Card> cards;
	private int nextCardIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		nextCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		nextCardIndex = 0;
	}

	public Card deal() {
		if (nextCardIndex == cards.size()) {
			throw new IllegalStateException("No cards left in the deck.");
		}
		return cards.get(nextCardIndex++);
	}

	public List<Card> deal(int numCards) {
		if (numCards < 0 || nextCardIndex + numCards > cards.size()) {
			throw new IllegalArgumentException("Invalid number of cards to deal.");
		}
		List<Card> dealtCards = new ArrayList<>();
		for (int i = 0; i < numCards; i++) {
			dealtCards.add(deal());
		}
		return dealtCards;
	}

	public void reset() {
		nextCardIndex = 0;
	}
}

class Hand {
	private final List<Card> cards;

	public Hand(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int size() {
		return cards.size();
	}

	public Card getCard(int index) {
		return cards.get(index);
	}

	public int getScore() {
		int score = 0;
		boolean hasAce = false;
		for (Card card : cards) {
			score += card.getValue();
			if (card.getValue() == 1) {
				hasAce = true;
			}
		}
		if (hasAce && score + 10 <= 21) {
			score += 10;
		}
		return score;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(" (").append(getScore()).append(")");
		return sb.toString();
	}
}

enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
	KING(10);

	private final int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES
}