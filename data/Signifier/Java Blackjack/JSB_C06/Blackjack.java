package JSB_C06;

import java.util.*;

public class Blackjack {
	private final int DEALER_MIN_POINTS = 17;
	private final int BLACKJACK_POINTS = 21;
	private final int INITIAL_CARDS = 2;

	private final List<String> SUITS = Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades");
	private final List<String> RANKS = Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
			"Queen", "King");

	private List<String> deck;
	private List<String> playerCards;
	private List<String> dealerCards;
	private int playerPoints;
	private int dealerPoints;
	private Scanner scanner;

	public Blackjack() {
		deck = new ArrayList<>();
		playerCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
		playerPoints = 0;
		dealerPoints = 0;
		scanner = new Scanner(System.in);
	}

	public void play() {
		System.out.println("Welcome to Blackjack!");

		// Shuffle the deck
		shuffleDeck();

		// Deal initial cards
		dealCards(playerCards, INITIAL_CARDS);
		dealCards(dealerCards, INITIAL_CARDS);

		// Show the player's cards and points
		System.out.println("Your cards: " + playerCards);
		playerPoints = calculatePoints(playerCards);
		System.out.println("Your points: " + playerPoints);

		// Check if the player has blackjack
		if (playerPoints == BLACKJACK_POINTS) {
			System.out.println("Congratulations, you have blackjack!");
			return;
		}

		// Ask the player if they want to hit or stand
		while (true) {
			System.out.print("Do you want to hit or stand? ");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("hit")) {
				// Deal another card to the player
				dealCards(playerCards, 1);

				// Show the player's updated cards and points
				System.out.println("Your cards: " + playerCards);
				playerPoints = calculatePoints(playerCards);
				System.out.println("Your points: " + playerPoints);

				// Check if the player busts
				if (playerPoints > BLACKJACK_POINTS) {
					System.out.println("Sorry, you busted!");
					return;
				}
			} else if (choice.equals("stand")) {
				// The player chooses to stand, move on to the dealer's turn
				break;
			} else {
				System.out.println("Invalid choice, please try again.");
			}
		}

		// Dealer's turn
		System.out.println("Dealer's turn:");
		System.out.println("Dealer's cards: " + dealerCards);
		dealerPoints = calculatePoints(dealerCards);
		System.out.println("Dealer's points: " + dealerPoints);

		// Check if the dealer has blackjack
		if (dealerPoints == BLACKJACK_POINTS) {
			System.out.println("Sorry, the dealer has blackjack!");
			return;
		}

		// Dealer must hit until their points reach at least DEALER_MIN_POINTS
		while (dealerPoints < DEALER_MIN_POINTS) {
			System.out.println("Dealer hits.");
			dealCards(dealerCards, 1);
			dealerPoints = calculatePoints(dealerCards);
			System.out.println("Dealer's cards: " + dealerCards);
			System.out.println("Dealer's points: " + dealerPoints);
		}
		// Check if the dealer busts
		if (dealerPoints > BLACKJACK_POINTS) {
			System.out.println("Congratulations, the dealer busted! You win!");
			return;
		}

		// Determine the winner
		if (playerPoints > dealerPoints) {
			System.out.println("Congratulations, you win!");
		} else if (playerPoints < dealerPoints) {
			System.out.println("Sorry, the dealer wins.");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private void shuffleDeck() {
		deck.clear();

		// Generate a new deck of cards
		for (String suit : SUITS) {
			for (String rank : RANKS) {
				deck.add(rank + " of " + suit);
			}
		}

		// Shuffle the deck
		Collections.shuffle(deck);
	}

	private void dealCards(List<String> hand, int numCards) {
		for (int i = 0; i < numCards; i++) {
			String card = deck.remove(0);
			hand.add(card);
		}
	}

	private int calculatePoints(List<String> hand) {
		int points = 0;
		int aceCount = 0;

		for (String card : hand) {
			String rank = card.split(" ")[0];

			if (rank.equals("Ace")) {
				aceCount++;
				points += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				points += 10;
			} else {
				points += Integer.parseInt(rank);
			}
		}

		// Handle aces
		while (points > BLACKJACK_POINTS && aceCount > 0) {
			points -= 10;
			aceCount--;
		}

		return points;
	}

	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.play();
	}
}