package A3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players are there? (1-4)");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        Game game = new Game();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for Player " + (i+1) + ":");
            String name = scanner.nextLine();
            Player player = new Player(name);
            game.addPlayer(player);
        }
        game.start();
    }
}

class Card {
	private String rank;
	private String suit;

	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String getRank() {
		return this.rank;
	}

	public String getSuit() {
		return this.suit;
	}
}

class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		this.cards = new ArrayList<Card>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		for (String suit : suits) {
			for (String rank : ranks) {
				Card card = new Card(rank, suit);
				this.cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	public Card dealCard() {
		return this.cards.remove(0);
	}

	public int cardsRemaining() {
		return this.cards.size();
	}
}

class Player {
	private String name;
	private ArrayList<Card> hand;
	private int handValue;

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.handValue = 0;
	}

	public void addCard(Card card) {
		this.hand.add(card);
		if (card.getRank().equals("Ace")) {
			if (this.handValue + 11 > 21) {
				this.handValue += 1;
			} else {
				this.handValue += 11;
			}
		} else if (card.getRank().equals("Jack") || card.getRank().equals("Queen") || card.getRank().equals("King")) {
			this.handValue += 10;
		} else {
			this.handValue += Integer.parseInt(card.getRank());
		}
	}

	public int getHandValue() {
		return this.handValue;
	}

	public void clearHand() {
		this.hand.clear();
		this.handValue = 0;
	}
	
	public String getName() {
	    return name;
	}
}

class Game {
	private Deck deck;
	private Player dealer;
	private ArrayList<Player> players;

	public Game() {
		this.deck = new Deck();
		this.dealer = new Player("Dealer");
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player) {
	    players.add(player);
	}

	public void start() {
		deck.shuffle();
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				Card card = deck.dealCard();
				player.addCard(card);
				System.out.println(player.getName() + " was dealt " + card.getRank() + " of " + card.getSuit());
			}
			Card card = deck.dealCard();
			dealer.addCard(card);
			if (i == 0) {
				System.out.println("Dealer was dealt a hidden card");
			} else {
				System.out.println("Dealer was dealt " + card.getRank() + " of " + card.getSuit());
			}
		}
		for (Player player : players) {
			takeTurn(player);
		}
		takeTurn(dealer);
		determineWinner();
		playAgain();
	}

	public void takeTurn(Player player) {
		Scanner scanner = new Scanner(System.in);
		while (player.getHandValue() < 17) {
			System.out.println(player.getName() + ", would you like to hit or stay? (h/s)");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h")) {
				Card card = deck.dealCard();
				player.addCard(card);
				System.out.println(player.getName() + " was dealt " + card.getRank() + " of " + card.getSuit());
			} else {
				break;
			}
		}
	}

	public void dealerTurn() {
		while (dealer.getHandValue() < 17) {
			Card card = deck.dealCard();
			dealer.addCard(card);
			System.out.println("Dealer was dealt " + card.getRank() + " of " + card.getSuit());
		}
	}

	public void determineWinner() {
		for (Player player : players) {
			if (player.getHandValue() > 21) {
				System.out.println(player.getName() + " busts! Dealer wins!");
			} else if (dealer.getHandValue() > 21) {
				System.out.println("Dealer busts! " + player.getName() + " wins!");
			} else if (player.getHandValue() > dealer.getHandValue()) {
				System.out.println(player.getName() + " wins!");
			} else if (dealer.getHandValue() > player.getHandValue()) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}
	}

	public void playAgain() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Would you like to play again? (y/n)");
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("y")) {
			for (Player player : players) {
				player.clearHand();
			}
			dealer.clearHand();
			start();
		} else {
			System.out.println("Thanks for playing!");
		}
	}
}