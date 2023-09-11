package JSB_B09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		// Initialize deck of cards
		List<String> deck = new ArrayList<>();
		String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		Collections.shuffle(deck); // Shuffle the deck

		// Initialize game variables
		int playerScore = 0;
		int dealerScore = 0;
		boolean playerBust = false;
		boolean dealerBust = false;
		Scanner scanner = new Scanner(System.in);

		// Deal initial cards
		String playerCard1 = dealCard(deck);
		String playerCard2 = dealCard(deck);
		String dealerCard1 = dealCard(deck);
		String dealerCard2 = dealCard(deck);
		playerScore = getCardValue(playerCard1) + getCardValue(playerCard2);
		dealerScore = getCardValue(dealerCard1) + getCardValue(dealerCard2);

		// Player's turn
		while (playerScore < 21) {
			System.out.println("Your cards: " + playerCard1 + ", " + playerCard2);
			System.out.println("Your current score: " + playerScore);
			System.out.println("Dealer's face-up card: " + dealerCard1);
			System.out.print("Do you want to hit or stand? ");
			String action = scanner.next();
			if (action.equals("hit")) {
				String newCard = dealCard(deck);
				playerScore += getCardValue(newCard);
				System.out.println("You drew a " + newCard);
			} else if (action.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
			}
			if (playerScore > 21) {
				playerBust = true;
				break;
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			String newCard = dealCard(deck);
			dealerScore += getCardValue(newCard);
			System.out.println("Dealer drew a " + newCard);
			if (dealerScore > 21) {
				dealerBust = true;
				break;
			}
		}

		// Determine winner
		if (playerBust) {
			System.out.println("You bust! Dealer wins.");
		} else if (dealerBust) {
			System.out.println("Dealer busts! You win.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win with " + playerScore + " points! Dealer had " + dealerScore + " points.");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins with " + dealerScore + " points. You had " + playerScore + " points.");
		} else {
			System.out.println("It's a tie! You and the dealer both have " + playerScore + " points.");
		}
	}

	// Deal a card from the deck and remove it from the list
	public static String dealCard(List<String> deck) {
		String card = deck.get(0);
		deck.remove(0);
		return card;
	}

	// Get the value of a card
	public static int getCardValue(String card) {
		String rank = card.split(" ")[0];
		if (rank.equals("Ace")) {
			return 11;
		} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}

}