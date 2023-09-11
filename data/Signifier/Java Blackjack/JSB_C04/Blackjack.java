package JSB_C04;

import java.util.*;

public class Blackjack {
	private Deck deck;
	private List<Card> playerCards;
	private List<Card> dealerCards;
	private int playerScore;
	private int dealerScore;
	private boolean playerDone;
	private boolean dealerDone;

	public Blackjack() {
		deck = new Deck();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		playerDone = false;
		dealerDone = false;
	}

	public void play() {
		System.out.println("Welcome to Blackjack!\n");

		// Deal initial cards
		dealCardToPlayer();
		dealCardToDealer();
		dealCardToPlayer();
		dealCardToDealer();

		// Show initial hands
		System.out.println("Your hand: " + playerCards);
		System.out.println("Dealer's hand: [" + dealerCards.get(0) + ", *]\n");

		// Player's turn
		while (!playerDone) {
			System.out.print("Do you want to hit or stand? ");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine().trim().toLowerCase();
			switch (choice) {
			case "hit":
				dealCardToPlayer();
				System.out.println("Your hand: " + playerCards);
				break;
			case "stand":
				playerDone = true;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
			updatePlayerScore();
			if (playerScore > 21) {
				System.out.println("Bust! You lose.");
				return;
			}
		}

		// Dealer's turn
		while (!dealerDone) {
			if (dealerScore >= 17) {
				dealerDone = true;
			} else {
				dealCardToDealer();
				updateDealerScore();
			}
			if (dealerScore > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare scores
		if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else if (playerScore < dealerScore) {
			System.out.println("You lose.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private void dealCardToPlayer() {
		Card card = deck.drawCard();
		playerCards.add(card);
		updatePlayerScore();
	}

	private void dealCardToDealer() {
		Card card = deck.drawCard();
		dealerCards.add(card);
		updateDealerScore();
	}

	private void updatePlayerScore() {
		playerScore = 0;
		for (Card card : playerCards) {
			playerScore += card.getValue();
		}
		if (playerScore > 21) {
			// Check for aces and convert them from 11 to 1 if necessary
			for (Card card : playerCards) {
				if (card.getValue() == 11) {
					card.setValue(1);
					playerScore -= 10;
					if (playerScore <= 21) {
						break;
					}
				}
			}
		}
	}

	private void updateDealerScore() {
		dealerScore = 0;
		for (Card card : dealerCards) {
			dealerScore += card.getValue();
		}
		if (dealerScore > 21) {
			// Check for aces and convert them from 11 to 1 if necessary
			for (Card card : dealerCards) {
				if (card.getValue() == 11) {
					card.setValue(1);
					dealerScore -= 10;
					if (dealerScore <= 21) {
						break;
					}
				}
			}
		}
	}

	private static class Card {
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

		public void setValue(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return rank + " of " + suit;
		}
	}

	private static class Deck {
		private List<Card> cards;

		public Deck() {
			cards = new ArrayList<>();
			String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
			String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
			int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
			for (String suit : suits) {
				for (int i = 0; i < ranks.length; i++) {
					Card card = new Card(suit, ranks[i], values[i]);
					cards.add(card);
				}
			}
			Collections.shuffle(cards);
		}

		public Card drawCard() {
			return cards.remove(0);
		}
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}