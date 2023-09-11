package JCB_B08;

import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int playerScore = 0;
		int dealerScore = 0;
		List<Integer> deck = createDeck();
		Collections.shuffle(deck);
		boolean playerWins = false;

		// Deal initial cards
		playerScore += drawCard(deck);
		dealerScore += drawCard(deck);
		playerScore += drawCard(deck);
		dealerScore += drawCard(deck);

		System.out.println("Your score is " + playerScore);

		// Player's turn
		while (playerScore < 21) {
			System.out.println("Do you want to hit or stand? (h/s)");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("h")) {
				playerScore += drawCard(deck);
				System.out.println("Your score is " + playerScore);
			} else if (input.equalsIgnoreCase("s")) {
				break;
			}
		}

		// Dealer's turn
		while (dealerScore < 17) {
			dealerScore += drawCard(deck);
		}

		// Determine winner
		if (playerScore > 21) {
			System.out.println("You bust! Dealer wins.");
		} else if (dealerScore > 21) {
			System.out.println("Dealer busts! You win.");
			playerWins = true;
		} else if (playerScore > dealerScore) {
			System.out.println("You win!");
			playerWins = true;
		} else if (dealerScore > playerScore) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}

		// Display final scores
		System.out.println("Your score: " + playerScore);
		System.out.println("Dealer's score: " + dealerScore);

		// Ask to play again
		System.out.println("Do you want to play again? (y/n)");
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("y")) {
			main(args);
		} else {
			System.out.println("Thanks for playing!");
		}
	}

	public static List<Integer> createDeck() {
		List<Integer> deck = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(i);
			}
		}
		return deck;
	}

	public static int drawCard(List<Integer> deck) {
		int card = deck.get(0);
		deck.remove(0);
		return card;
	}
}