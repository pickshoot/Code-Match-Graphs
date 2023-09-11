package JSB_B06;

import java.util.*;

public class Blackjack {
	private static final int DEALER_THRESHOLD = 17;
	private static final int BLACKJACK_VALUE = 21;

	private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
			"King" };
	private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
	private static final int[] VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

	private static final int NUM_DECKS = 1;
	private static final int MAX_CARDS = NUM_DECKS * RANKS.length * SUITS.length;

	private static final Random RANDOM = new Random();

	private List<Card> deck;

	public Blackjack() {
		deck = new ArrayList<Card>(MAX_CARDS);
		for (int i = 0; i < NUM_DECKS; i++) {
			for (String suit : SUITS) {
				for (int j = 0; j < RANKS.length; j++) {
					deck.add(new Card(RANKS[j], suit, VALUES[j]));
				}
			}
		}
		Collections.shuffle(deck, RANDOM);
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");

		Scanner scanner = new Scanner(System.in);
		int money = 100;

		while (money > 0) {
			System.out.println("You have $" + money + ". How much would you like to bet?");
			int bet = scanner.nextInt();

			if (bet > money) {
				System.out.println("You can't bet more than you have.");
				continue;
			}

			List<Card> playerCards = new ArrayList<Card>();
			List<Card> dealerCards = new ArrayList<Card>();
			playerCards.add(drawCard());
			playerCards.add(drawCard());
			dealerCards.add(drawCard());

			System.out.println("Dealer's up card:");
			dealerCards.get(0).print();

			while (true) {
				System.out.println("Your cards:");
				for (Card card : playerCards) {
					card.print();
				}

				System.out.println("Your total is " + getHandValue(playerCards));
				System.out.println("Would you like to hit or stand?");
				String input = scanner.next();

				if (input.equalsIgnoreCase("hit")) {
					playerCards.add(drawCard());
					if (getHandValue(playerCards) > BLACKJACK_VALUE) {
						System.out.println("Busted! You lose.");
						money -= bet;
						break;
					}
				} else if (input.equalsIgnoreCase("stand")) {
					int dealerValue = getHandValue(dealerCards);
					while (dealerValue < DEALER_THRESHOLD) {
						dealerCards.add(drawCard());
						dealerValue = getHandValue(dealerCards);
					}
					System.out.println("Dealer's cards:");
					for (Card card : dealerCards) {
						card.print();
					}
					System.out.println("Dealer's total is " + dealerValue);

					int playerValue = getHandValue(playerCards);
					if (playerValue > dealerValue || dealerValue > BLACKJACK_VALUE) {
						System.out.println("You win!");
						money += bet;
					} else if (playerValue < dealerValue) {
						System.out.println("You lose.");
						money -= bet;
					} else {
						System.out.println("Push.");
					}
					break;
				} else {
					System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
				}
			}
		}

		System.out.println("Game over. You are out of money.");
	}

	private Card drawCard() {
		if (deck.size() == 0) {
			deck = new ArrayList<Card>(MAX_CARDS);
			for (int i = 0; i < NUM_DECKS; i++) {
				for (String suit : SUITS) {
					for (int j = 0; j < RANKS.length; j++) {
						deck.add(new Card(RANKS[j], suit, VALUES[j]));
					}
				}
			}
			Collections.shuffle(deck, RANDOM);
		}
		return deck.remove(0);
	}

	private int getHandValue(List<Card> hand) {
		int value = 0;
		boolean hasAce = false;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getRank().equals("Ace")) {
				hasAce = true;
			}
		}
		if (hasAce && value + 10 <= BLACKJACK_VALUE) {
			value += 10;
		}
		return value;
	}

	private static class Card {
		private String rank;
		private String suit;
		private int value;

		public Card(String rank, String suit, int value) {
			this.rank = rank;
			this.suit = suit;
			this.value = value;
		}

		public void print() {
			System.out.println(rank + " of " + suit);
		}

		public String getRank() {
			return rank;
		}

		public int getValue() {
			return value;
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}