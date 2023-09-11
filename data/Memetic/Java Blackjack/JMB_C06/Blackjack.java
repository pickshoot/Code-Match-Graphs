package C6;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<String> deck = createDeck();
		Collections.shuffle(deck);
		int money = 100;
		System.out.println("Welcome to Blackjack!");
		while (money > 0) {
			System.out.println("You have $" + money + ". How much would you like to bet?");
			int bet = input.nextInt();
			if (bet > money) {
				System.out.println("You don't have enough money to make that bet.");
				continue;
			}
			ArrayList<String> playerHand = new ArrayList<>();
			ArrayList<String> dealerHand = new ArrayList<>();
			playerHand.add(deck.get(0));
			deck.remove(0);
			dealerHand.add(deck.get(0));
			deck.remove(0);
			playerHand.add(deck.get(0));
			deck.remove(0);
			dealerHand.add(deck.get(0));
			deck.remove(0);
			System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", ?]");
			System.out.println("Your hand: " + playerHand);
			boolean playerBust = false;
			boolean dealerBust = false;
			boolean playerStand = false;
			while (!playerBust && !dealerBust && !playerStand) {
				System.out.println("Would you like to hit or stand? (Enter 'hit' or 'stand')");
				String choice = input.next();
				if (choice.equals("hit")) {
					playerHand.add(deck.get(0));
					deck.remove(0);
					System.out.println("Your hand: " + playerHand);
					int playerScore = calculateScore(playerHand);
					if (playerScore > 21) {
						System.out.println("Bust! You lose.");
						playerBust = true;
						money -= bet;
					}
				} else if (choice.equals("stand")) {
					playerStand = true;
					int playerScore = calculateScore(playerHand);
					int dealerScore = calculateScore(dealerHand);
					while (dealerScore < 17) {
						dealerHand.add(deck.get(0));
						deck.remove(0);
						dealerScore = calculateScore(dealerHand);
					}
					System.out.println("Dealer's hand: " + dealerHand);
					if (dealerScore > 21) {
						System.out.println("Dealer busts! You win.");
						money += bet;
					} else if (dealerScore > playerScore) {
						System.out.println("Dealer wins. You lose.");
						money -= bet;
					} else if (playerScore > dealerScore) {
						System.out.println("You win!");
						money += bet;
					} else {
						System.out.println("It's a tie!");
					}
				} else {
					System.out.println("Invalid choice. Please enter 'hit' or 'stand'");
				}
			}
		}
		System.out.println("You're out of money! Game over.");
	}

	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<>();
		String[] suits = { "hearts", "diamonds", "clubs", "spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}
		return deck;
	}

	public static int calculateScore(ArrayList<String> hand) {
		int score = 0;
		int numAces = 0;
		for (String card : hand) {
			String rank = card.substring(0, card.indexOf(" "));
			if (rank.equals("A")) {
				numAces++;
				score += 11;
			} else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
				score += 10;
			} else {
				score += Integer.parseInt(rank);
			}
		}
		while (score > 21 && numAces > 0) {
			score -= 10;
			numAces--;
		}
		return score;
	}
}