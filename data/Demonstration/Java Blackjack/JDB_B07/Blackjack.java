package JDB_B07;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Initialize the deck
		List<String> deck = new ArrayList<>();
		for (int i = 2; i <= 10; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(Integer.toString(i));
			}
		}
		for (int j = 0; j < 4; j++) {
			deck.add("A");
			deck.add("K");
			deck.add("Q");
			deck.add("J");
		}
		Collections.shuffle(deck);

		// Initialize the player and dealer hands
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();

		// Deal two cards to the player and dealer
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Print the initial hands
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: " + dealerHand.get(0) + ", ?");

		// Player's turn
		while (true) {
			System.out.println("Hit or stand? (h/s)");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("h")) {
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);
				if (getHandValue(playerHand) > 21) {
					System.out.println("Bust! You lose.");
					return;
				}
			} else if (input.equals("s")) {
				break;
			} else {
				System.out.println("Invalid input. Please enter 'h' or 's'.");
			}
		}

		// Dealer's turn
		while (getHandValue(dealerHand) < 17) {
			dealerHand.add(deck.remove(0));
			System.out.println("Dealer's hand: " + dealerHand);
			if (getHandValue(dealerHand) > 21) {
				System.out.println("Dealer busts! You win.");
				return;
			}
		}

		// Compare hands
		int playerValue = getHandValue(playerHand);
		int dealerValue = getHandValue(dealerHand);
		if (playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (dealerValue > playerValue) {
			System.out.println("Dealer wins.");
		} else {
			System.out.println("It's a tie.");
		}

		scanner.close();
	}

	private static int getHandValue(List<String> hand) {
		int value = 0;
		int numAces = 0;
		for (String card : hand) {
			if (card.equals("A")) {
				numAces++;
				value += 11;
			} else if (card.equals("K") || card.equals("Q") || card.equals("J")) {
				value += 10;
			} else {
				value += Integer.parseInt(card);
			}
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}
}
