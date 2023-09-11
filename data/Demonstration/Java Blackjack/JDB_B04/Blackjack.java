package JDB_B04;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> deck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
		String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}
		Collections.shuffle(deck);
		int playerScore = 0;
		int dealerScore = 0;
		int i = 0;
		while (i < 2) {
			String playerCard = deck.get(0);
			deck.remove(0);
			System.out.println("Player was dealt " + playerCard);
			String dealerCard = deck.get(0);
			deck.remove(0);
			System.out.println("Dealer was dealt " + dealerCard);
			playerScore += getCardValue(playerCard);
			dealerScore += getCardValue(dealerCard);
			i++;
		}
		while (playerScore < 21) {
			System.out.println("Player score: " + playerScore);
			System.out.println("Do you want to hit or stay?");
			String decision = scanner.nextLine();
			if (decision.equalsIgnoreCase("hit")) {
				String playerCard = deck.get(0);
				deck.remove(0);
				System.out.println("Player was dealt " + playerCard);
				playerScore += getCardValue(playerCard);
				if (playerScore > 21) {
					System.out.println("Player busts!");
					System.out.println("Dealer wins!");
					return;
				}
			} else {
				break;
			}
		}
		while (dealerScore < 17) {
			String dealerCard = deck.get(0);
			deck.remove(0);
			System.out.println("Dealer was dealt " + dealerCard);
			dealerScore += getCardValue(dealerCard);
			if (dealerScore > 21) {
				System.out.println("Dealer busts!");
				System.out.println("Player wins!");
				return;
			}
		}
		System.out.println("Player score: " + playerScore);
		System.out.println("Dealer score: " + dealerScore);
		if (playerScore > dealerScore) {
			System.out.println("Player wins!");
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
		scanner.close();
	}

	public static int getCardValue(String card) {
		String[] cardParts = card.split(" ");
		String value = cardParts[0];
		if (value.equalsIgnoreCase("Ace")) {
			return 11;
		} else if (value.equalsIgnoreCase("King") || value.equalsIgnoreCase("Queen")
				|| value.equalsIgnoreCase("Jack")) {
			return 10;
		} else {
			return Integer.parseInt(value);
		}
	}
}
