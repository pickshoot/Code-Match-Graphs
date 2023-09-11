package JSB_B08;

import java.util.*;

public class Blackjack {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();

		System.out.print("Enter your name: ");
		String playerName = scanner.nextLine();
		Player player = new Player(playerName);

		Dealer dealer = new Dealer();

		// deal initial cards
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());

		System.out.println("\n" + dealer.getName() + "'s hand: " + dealer.getHand());
		System.out.println(
				player.getName() + "'s hand: " + player.getHand() + " (total: " + player.getHandValue() + ")\n");

		while (true) {
			// check for player bust
			if (player.getHandValue() > 21) {
				System.out.println("Bust! " + player.getName() + " loses.");
				return;
			}

			System.out.print("Hit or stand? (h/s): ");
			String choice = scanner.nextLine().toLowerCase();
			if (choice.equals("h")) {
				player.addCard(deck.deal());
				System.out.println(player.getName() + "'s hand: " + player.getHand() + " (total: "
						+ player.getHandValue() + ")\n");
			} else {
				break;
			}
		}

		// dealer draws cards
		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.deal());
		}

		System.out.println(
				dealer.getName() + "'s hand: " + dealer.getHand() + " (total: " + dealer.getHandValue() + ")\n");

		// check for dealer bust
		if (dealer.getHandValue() > 21) {
			System.out.println("Bust! " + dealer.getName() + " loses.");
			return;
		}

		// determine winner
		int playerHandValue = player.getHandValue();
		int dealerHandValue = dealer.getHandValue();
		if (playerHandValue > dealerHandValue) {
			System.out.println(player.getName() + " wins!");
		} else if (dealerHandValue > playerHandValue) {
			System.out.println(dealer.getName() + " wins!");
		} else {
			System.out.println("Push!");
		}
	}
}

class Deck {
	private List<Card> cards;
	private int currentCardIndex;

	public Deck() {
		cards = new ArrayList<>();
		for (int i = 1; i <= 13; i++) {
			cards.add(new Card("Hearts", i));
			cards.add(new Card("Diamonds", i));
			cards.add(new Card("Clubs", i));
			cards.add(new Card("Spades", i));
		}
		currentCardIndex = 0;
	}

	public void shuffle() {
		Collections.shuffle(cards);
		currentCardIndex = 0;
	}

	public Card deal() {
		return cards.get(currentCardIndex++);
	}
}

class Card {
	private String suit;
	private int value;

	public Card(String suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public int getValue() {
		if (value == 1) {
			return 11;
		} else if (value >= 10) {
			return 10;
		} else {
			return value;
		}
	}

	public String toString() {
		String valueStr;
		if (value == 1) {
			valueStr = "Ace";
		} else if (value == 11) {
			valueStr = "Jack";
		} else if (value == 12) {
			valueStr = "Queen";
		} else if (value == 13) {
			valueStr = "King";
		} else {
			valueStr = Integer.toString(value);
		}
		return valueStr + " of " + suit;
	}
}

class Player {
	private String name;
	private List<Card> hand;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public int getHandValue() {
		int value = 0;
		int numAces = 0;
		for (Card card : hand) {
			value += card.getValue();
			if (card.getValue() == 11) {
				numAces++;
			}
		}
		while (value > 21 && numAces > 0) {
			value -= 10;
			numAces--;
		}
		return value;
	}

	public List<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}
}

class Dealer extends Player {
	public Dealer() {
		super("Dealer");
	}

	public List<Card> getVisibleHand() {
		List<Card> visibleHand = new ArrayList<>();
		visibleHand.add(getHand().get(0));
		return visibleHand;
	}
}