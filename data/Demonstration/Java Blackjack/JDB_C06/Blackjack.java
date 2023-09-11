package JDB_C06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
	private ArrayList<Card> deck; // the deck of cards
	private ArrayList<Card> playerCards; // the player's cards
	private ArrayList<Card> dealerCards; // the dealer's cards
	private int playerScore; // the player's score
	private int dealerScore; // the dealer's score
	private boolean gameOver; // whether the game is over

	public Blackjack() {
		deck = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		playerScore = 0;
		dealerScore = 0;
		gameOver = false;
		initializeDeck();
	}

	private void initializeDeck() {
		// create a standard deck of 52 cards
		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				deck.add(new Card(rank, suit));
			}
		}
		// shuffle the deck
		Collections.shuffle(deck);
	}

	private void dealInitialCards() {
		// deal two cards to the player and one card to the dealer
		playerCards.add(drawCard());
		playerCards.add(drawCard());
		dealerCards.add(drawCard());
	}

	private Card drawCard() {
		// remove a card from the deck and return it
		return deck.remove(deck.size() - 1);
	}

	private int calculateScore(ArrayList<Card> cards) {
		// calculate the score for a given hand of cards
		int score = 0;
		int numAces = 0;
		for (Card card : cards) {
			int rank = card.getRank();
			if (rank == 1) { // Ace
				numAces++;
				score += 11;
			} else if (rank >= 10) { // Face card
				score += 10;
			} else { // Number card
				score += rank;
			}
		}
		// adjust score for Aces
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}

	private void printCards(ArrayList<Card> cards, boolean hideDealerCard) {
		// print the cards in a given hand
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (i == 0 && hideDealerCard) {
				System.out.println("Dealer: [hidden]");
			} else {
				System.out.println(card.toString());
			}
		}
	}

	private void printScores() {
		// print the scores for the player and dealer
		System.out.println("Player score: " + playerScore);
		System.out.println("Dealer score: " + dealerScore);
	}

	private void playerTurn(Scanner scanner) {
		// player's turn
		while (true) {
			// print the player's cards and score
			System.out.println("Your cards:");
			printCards(playerCards, false);
			playerScore = calculateScore(playerCards);
			System.out.println("Your score: " + playerScore);
			// ask the player if they want to hit or stand
			System.out.print("Hit or stand? ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("hit")) {
				// player hits
				playerCards.add(drawCard());
				// check if player busted
				playerScore = calculateScore(playerCards);
				if (playerScore > 21) {
					System.out.println("You busted!");
					gameOver = true;
					break;
				}
			} else if (input.equalsIgnoreCase("stand")) {
				// player stands
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
		}
	}

	private void dealerTurn() {
		// dealer's turn
		while (true) {
			// print the dealer's cards and score
			System.out.println("Dealer cards:");
			printCards(dealerCards, false);
			dealerScore = calculateScore(dealerCards);
			System.out.println("Dealer score: " + dealerScore);
			// check if dealer should hit or stand
			if (dealerScore < 17) {
				// dealer hits
				dealerCards.add(drawCard());
				dealerScore = calculateScore(dealerCards);
				// check if dealer busted
				if (dealerScore > 21) {
					System.out.println("Dealer busted!");
					gameOver = true;
					break;
				}
			} else {
				// dealer stands
				break;
			}
		}
	}

	private void determineWinner() {
		// determine the winner of the game
		if (playerScore > 21) {
			System.out.println("Dealer wins!");
		} else if (dealerScore > 21) {
			System.out.println("Player wins!");
		} else if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");
		while (!gameOver) {
			// deal initial cards
			dealInitialCards();
			// print the player's cards and score
			System.out.println("Your cards:");
			printCards(playerCards, false);
			playerScore = calculateScore(playerCards);
			System.out.println("Your score: " + playerScore);
			// check for player blackjack
			if (playerScore == 21) {
				System.out.println("Blackjack! You win!");
				gameOver = true;
				break;
			}
			// check for dealer blackjack
			dealerScore = calculateScore(dealerCards);
			if (dealerScore == 21) {
				System.out.println("Dealer has Blackjack! Dealer wins!");
				gameOver = true;
				break;
			}
			// player's turn
			playerTurn(scanner);
			if (gameOver) {
				break;
			}
			// dealer's turn
			dealerTurn();
			if (gameOver) {
				break;
			}
			// determine the winner
			determineWinner();
			// ask the player if they want to play again
			System.out.print("Play again? ");
			String input = scanner.nextLine();
			if (!input.equalsIgnoreCase("yes")) {
				gameOver = true;
			} else {
				// reset the game
				deck.clear();
				playerCards.clear();
				dealerCards.clear();
				playerScore = 0;
				dealerScore = 0;
				gameOver = false;
				initializeDeck();
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.playGame();
	}
}

class Card {
	private int rank;
	private int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
		return ranks[rank - 1] + " of " + suits[suit];
	}
}