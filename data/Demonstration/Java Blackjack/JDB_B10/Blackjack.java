package JDB_B10;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Initialize deck and shuffle it
		List<String> deck = new ArrayList<>();
		for (int i = 2; i <= 10; i++) {
			deck.add(Integer.toString(i));
		}
		deck.addAll(Arrays.asList("J", "Q", "K", "A"));
		Collections.shuffle(deck);

		// Initialize players and their hands
		List<String> dealerHand = new ArrayList<>();
		List<String> playerHand = new ArrayList<>();
		dealerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));

		// Game loop
		boolean playerBusted = false;
		boolean dealerBusted = false;
		while (true) {
			// Player's turn
			System.out.println("Dealer's hand: " + dealerHand.get(0) + ", ?");
			System.out.println("Your hand: " + playerHand);
			System.out.print("Do you want to hit or stand? ");
			String input = scanner.nextLine().toLowerCase();
			while (!input.equals("hit") && !input.equals("stand")) {
				System.out.print("Invalid input. Do you want to hit or stand? ");
				input = scanner.nextLine().toLowerCase();
			}
			if (input.equals("hit")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your new hand: " + playerHand);
				int playerSum = calculateHandSum(playerHand);
				if (playerSum > 21) {
					System.out.println("Busted! Your hand sum is " + playerSum);
					playerBusted = true;
					break;
				}
			} else {
				break;
			}
		}

		// Dealer's turn
		if (!playerBusted) {
			System.out.println("Dealer's turn.");
			System.out.println("Dealer's hand: " + dealerHand);
			while (calculateHandSum(dealerHand) < 17) {
				dealerHand.add(deck.remove(0));
				System.out.println("Dealer's new hand: " + dealerHand);
				int dealerSum = calculateHandSum(dealerHand);
				if (dealerSum > 21) {
					System.out.println("Dealer busted! Dealer's hand sum is " + dealerSum);
					dealerBusted = true;
					break;
				}
			}
		}

		// Determine winner
		if (playerBusted) {
			System.out.println("You lost!");
		} else if (dealerBusted) {
			System.out.println("You won!");
		} else {
			int playerSum = calculateHandSum(playerHand);
			int dealerSum = calculateHandSum(dealerHand);
			if (playerSum > dealerSum) {
				System.out.println("You won!");
			} else if (dealerSum > playerSum) {
				System.out.println("You lost!");
			} else {
				System.out.println("It's a tie!");
			}
		}

		scanner.close();
	}

	private static int calculateHandSum(List<String> hand) {
		int sum = 0;
		int numAces = 0;
		for (String card : hand) {
			if (card.equals("A")) {
				numAces++;
			} else if (card.equals("J") || card.equals("Q") || card.equals("K")) {
				sum += 10;
			} else {
				sum += Integer.parseInt(card);
			}
		}
		for (int i = 0; i < numAces; i++) {
			sum += (sum + 11 <= 21) ? 11 : 1;
		}
		return sum;
	}
}