package JDB_A09;

//Import necessary libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private static Deck deck;
	private static ArrayList<Card> playerCards;
	private static ArrayList<Card> dealerCards;
	private static int playerScore;
	private static int dealerScore;
	private static Scanner scanner;

	public static void main(String[] args) {
		deck = new Deck();
		deck.shuffle();
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		playerScore = 0;
		dealerScore = 0;
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");

		// Deal the first two cards to the player and dealer
		dealCard(playerCards);
		dealCard(dealerCards);
		dealCard(playerCards);
		dealCard(dealerCards);

		// Show the player's initial cards and one of the dealer's cards
		System.out.println("Your cards: " + playerCards);
		System.out.println("Dealer's cards: " + dealerCards.get(0));

		// Check for a natural blackjack (an ace and a 10-point card)
		if (isBlackjack(playerCards)) {
			System.out.println("You have a natural blackjack!");
			endGame();
		} else if (isBlackjack(dealerCards)) {
			System.out.println("Dealer has a natural blackjack.");
			endGame();
		} else {
			// Allow the player to hit or stand until they bust or stand
			while (true) {
				System.out.print("\nWould you like to hit or stand? ");
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("hit")) {
					dealCard(playerCards);
					System.out.println("Your cards: " + playerCards);
					if (isBust(playerCards)) {
						System.out.println("You bust!");
						endGame();
					}
				} else if (input.equalsIgnoreCase("stand")) {
					break;
				}
			}

			// Dealer hits until they have a score of at least 17
			while (dealerScore < 17) {
				dealCard(dealerCards);
			}

			// End the game and determine the winner
			endGame();
		}
	}

	// Deal a card to a hand (player or dealer) and update the score
	private static void dealCard(ArrayList<Card> hand) {
		Card card = deck.drawCard();
		hand.add(card);
		if (hand == playerCards) {
			playerScore += card.getValue();
		} else {
			dealerScore += card.getValue();
		}
	}

	// Determine if a hand has a natural blackjack (an ace and a 10-point card)
	private static boolean isBlackjack(ArrayList<Card> hand) {
		if (hand.size() != 2) {
			return false;
		}
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		if ((firstCard.getValue() == 1 && secondCard.getValue() == 10)
				|| (firstCard.getValue() == 10 && secondCard.getValue() == 1)) {
			return true;
		}
		return false;
	}

	// Determine if a hand has bust (a score over 21)
	private static boolean isBust(ArrayList<Card> hand) {
		int score = 0;
		for (Card card : hand) {
			score += card.getValue();
		}
		return score > 21;
	}

	// End the game and determine the winner
	private static void endGame() {
		// Show the dealer's cards
		System.out.println("Dealer's cards: " + dealerCards);

		// Determine the winner
		if (isBust(playerCards)) {
			System.out.println("Dealer wins, you bust.");
		} else if (isBust(dealerCards)) {
			System.out.println("You win, dealer busts.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win with a score of " + playerScore + "!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins with a score of " + dealerScore + ".");
		} else {
			System.out.println("It's a tie!");
		}

		// Ask the player if they want to play again
		System.out.print("\nWould you like to play again? ");
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("yes")) {
			// Reset the game and play again
			playerCards.clear();
			dealerCards.clear();
			playerScore = 0;
			dealerScore = 0;
			main(null);
		} else {
			// Exit the game
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}
}

//Card class representing a playing card
class Card {
	private int value;
	private String suit;
	private String face;

	public Card(int value, String suit, String face) {
		this.value = value;
		this.suit = suit;
		this.face = face;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return face + " of " + suit;
	}
}

//Deck class representing a deck of playing cards
class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] faces = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (int i = 0; i < faces.length; i++) {
				int value = i + 1;
				if (value > 10) {
					value = 10;
				}
				Card card = new Card(value, suit, faces[i]);
				cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(0);
	}
}