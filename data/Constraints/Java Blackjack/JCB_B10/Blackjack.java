package JCB_B10;

import java.util.*;

public class Blackjack {
	private static final int MAX_SCORE = 21;
	private static final int DEALER_STANDS_AT = 17;
	private static final int NUM_DECKS = 6;
	private static final double BLACKJACK_PAYOUT = 1.5;
	private static final double PUSH_PAYOUT = 1.0;

	private static final String[] SUITS = { "Hearts", "Diamonds", "Spades", "Clubs" };
	private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
			"King" };

	private List<Card> deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;
	private int playerScore;
	private int dealerScore;
	private boolean playerBusted;
	private boolean dealerBusted;

	public Blackjack() {
		deck = new ArrayList<>();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		playerBusted = false;
		dealerBusted = false;

		for (int i = 0; i < NUM_DECKS; i++) {
			for (String suit : SUITS) {
				for (String rank : RANKS) {
					deck.add(new Card(suit, rank));
				}
			}
		}

		Collections.shuffle(deck);
	}

	public void dealInitialCards() {
		playerCards.add(drawCard());
		dealerCards.add(drawCard());
		playerCards.add(drawCard());
		dealerCards.add(drawCard());

		playerScore = getScore(playerCards);
		dealerScore = getScore(dealerCards);
	}

	public void playerTurn() {
		while (playerScore < MAX_SCORE) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Your cards: " + playerCards + ", Score: " + playerScore);
			System.out.println("Do you want to Hit or Stand?");
			String choice = scanner.nextLine();
			if (choice.equalsIgnoreCase("hit")) {
				playerCards.add(drawCard());
				playerScore = getScore(playerCards);
			} else if (choice.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid choice, please try again.");
			}
		}

		if (playerScore > MAX_SCORE) {
			playerBusted = true;
			System.out.println("Busted! Your score is " + playerScore);
		} else {
			System.out.println("Your score is " + playerScore);
		}
	}

	public void dealerTurn() {
		while (dealerScore < DEALER_STANDS_AT) {
			dealerCards.add(drawCard());
			dealerScore = getScore(dealerCards);
		}

		if (dealerScore > MAX_SCORE) {
			dealerBusted = true;
			System.out.println("Dealer busted! Dealer's score is " + dealerScore);
		} else {
			System.out.println("Dealer's score is " + dealerScore);
		}
	}

	public void determineWinner() {
		if (playerBusted) {
			System.out.println("Dealer wins!");
		} else if (dealerBusted) {
			System.out.println("Player wins!");
		} else if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("Push!");
		}
	}

	public void payout(double bet) {
		if (playerBusted) {
			System.out.println("You lost your bet of " + bet);
		} else if (dealerBusted) {
			System.out.println("You won your bet of " + bet * BLACKJACK_PAYOUT);
		} else if (playerScore > dealerScore) {
			System.out.println("You won your bet of " + bet * BLACKJACK_PAYOUT);
		} else if (dealerScore > playerScore) {
			System.out.println("You lost your bet of " + bet);
		} else {
			System.out.println("Push! You get your bet of " + bet * PUSH_PAYOUT + " back.");
		}
	}

	public Card drawCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}

	public int getScore(List<Card> hand) {
		int score = 0;
		int numAces = 0;

		for (Card card : hand) {
			if (card.getRank().equals("Ace")) {
				numAces++;
			} else if (card.getRank().equals("Jack") || card.getRank().equals("Queen")
					|| card.getRank().equals("King")) {
				score += 10;
			} else {
				score += Integer.parseInt(card.getRank());
			}
		}

		for (int i = 0; i < numAces; i++) {
			if (score + 11 > MAX_SCORE) {
				score += 1;
			} else {
				score += 11;
			}
		}

		return score;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		while (true) {
			System.out.println("How much would you like to bet?");
			double bet = scanner.nextDouble();
			scanner.nextLine();

			Blackjack game = new Blackjack();
			game.dealInitialCards();
			game.playerTurn();
			game.dealerTurn();
			game.determineWinner();
			game.payout(bet);

			System.out.println("Would you like to play again? (y/n)");
			String choice = scanner.nextLine();
			if (!choice.equalsIgnoreCase("y")) {
				break;
			}
		}

		System.out.println("Thanks for playing!");
	}
}

class Card {
	private String suit;
	private String rank;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public String toString() {
		return rank + " of " + suit;
	}
}