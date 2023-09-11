package JSB_B05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		List<String> deck = new ArrayList<>();
		String[] suits = { "hearts", "diamonds", "spades", "clubs" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		int playerScore = 0;
		int dealerScore = 0;

		// Deal two cards to player
		String playerCard1 = dealCard(deck, random);
		String playerCard2 = dealCard(deck, random);
		playerScore += getCardValue(playerCard1) + getCardValue(playerCard2);

		System.out.println("Your cards: " + playerCard1 + ", " + playerCard2);
		System.out.println("Your current score is " + playerScore);

		// Deal one card to dealer
		String dealerCard = dealCard(deck, random);
		dealerScore += getCardValue(dealerCard);

		System.out.println("Dealer's card: " + dealerCard);

		// Player's turn
		while (true) {
			System.out.println("Do you want to hit or stand?");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("hit")) {
				String card = dealCard(deck, random);
				playerScore += getCardValue(card);
				System.out.println("You drew the " + card);
				System.out.println("Your current score is " + playerScore);

				if (playerScore > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			String card = dealCard(deck, random);
			dealerScore += getCardValue(card);
			System.out.println("Dealer drew the " + card);
		}

		if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins.");
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a tie.");
		}
	}

	private static String dealCard(List<String> deck, Random random) {
		int index = random.nextInt(deck.size());
		String card = deck.get(index);
		deck.remove(index);
		return card;
	}

	private static int getCardValue(String card) {
		String rank = card.split(" ")[0];

		if (rank.equals("A")) {
			return 11;
		} else if (rank.equals("K") || rank.equals("Q") || rank.equals("J") || rank.equals("10")) {
			return 10;
		} else {
			return Integer.parseInt(rank);
		}
	}
}