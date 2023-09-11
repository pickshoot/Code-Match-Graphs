package JCB_B06;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();

		// Initialize game state
		int playerMoney = 100;
		int bet = 0;
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();

		while (true) {
			// Check if the player has enough money to continue playing
			if (playerMoney == 0) {
				System.out.println("You have no more money. Game over.");
				break;
			}

			// Ask the player for their bet
			System.out.println("You have $" + playerMoney + ". How much would you like to bet?");
			while (true) {
				bet = input.nextInt();
				if (bet > playerMoney) {
					System.out.println("You cannot bet more than you have. Please enter a valid bet.");
				} else {
					break;
				}
			}

			// Deal cards to the player and dealer
			playerHand.addCard(deck.deal());
			dealerHand.addCard(deck.deal());
			playerHand.addCard(deck.deal());
			dealerHand.addCard(deck.deal());

			// Show the player's hand and one of the dealer's cards
			System.out.println("Your hand: " + playerHand.toString());
			System.out.println("Dealer's hand: " + dealerHand.getCard(0) + " [hidden]");

			// Check if the player has blackjack
			if (playerHand.getScore() == 21) {
				System.out.println("Blackjack! You win $" + (bet * 1.5));
				playerMoney += (int) (bet * 1.5);
			} else {
				// Ask the player if they want to hit or stand
				while (true) {
					System.out.println("Do you want to hit or stand? (h/s)");
					String choice = input.next();
					if (choice.equalsIgnoreCase("h")) {
						playerHand.addCard(deck.deal());
						System.out.println("Your hand: " + playerHand.toString());
						if (playerHand.getScore() > 21) {
							System.out.println("Bust! You lose $" + bet);
							playerMoney -= bet;
							break;
						}
					} else if (choice.equalsIgnoreCase("s")) {
						// Dealer's turn
						System.out.println("Dealer's hand: " + dealerHand.toString());
						while (dealerHand.getScore() < 17) {
							dealerHand.addCard(deck.deal());
							System.out.println("Dealer hits. Dealer's hand: " + dealerHand.toString());
						}
						if (dealerHand.getScore() > 21) {
							System.out.println("Dealer busts! You win $" + bet);
							playerMoney += bet;
							break;
						} else if (dealerHand.getScore() > playerHand.getScore()) {
							System.out.println("Dealer wins! You lose $" + bet);
							playerMoney -= bet;
							break;
						} else if (dealerHand.getScore() == playerHand.getScore()) {
							System.out.println("Push! You get your money back.");
							break;
						} else {
							System.out.println("You win! You win $" + bet);
							playerMoney += bet;
							break;
						}
					} else {
						System.out.println("Please enter a valid choice.");
					}
				}
			}

			// Clear hands and reshuffle deck if necessary
			playerHand.clear();
			dealerHand.clear();
			if (deck.shouldReshuffle()) {
				deck.shuffle();
			}
		}
	}
}

class Card {
	private final String suit;
	private final String rank;

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

	public int getValue() {
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

class Deck {
	private final List<Card> cards;
	private int currentIndex;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				cards.add(new Card(suit, rank));
			}
		}
		currentIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		currentIndex = 0;
	}

	public Card deal() {
		Card card = cards.get(currentIndex);
		currentIndex++;
		return card;
	}

	public boolean shouldReshuffle() {
		return (cards.size() - currentIndex) < 20;
	}
}

class Hand {
	private final List<Card> cards;

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
			if (card.getRank().equals("Ace")) {
				numAces++;
			}
			score += card.getValue();
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	public Card getCard(int index) {
		return cards.get(index);
	}

	public void clear() {
		cards.clear();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
}