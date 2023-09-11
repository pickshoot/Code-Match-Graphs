package JVB_SC2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    
    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerScore;
    private int dealerScore;
    private boolean playerBust;
    private boolean dealerBust;
    private boolean gameOver;
    
    public Blackjack() {
        deck = new ArrayList<Card>();
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
        playerScore = 0;
        dealerScore = 0;
        playerBust = false;
        dealerBust = false;
        gameOver = false;
        
        for (int i = 2; i <= 10; i++) {
            deck.add(new Card(i, "hearts"));
            deck.add(new Card(i, "diamonds"));
            deck.add(new Card(i, "clubs"));
            deck.add(new Card(i, "spades"));
        }
        deck.add(new Card(10, "hearts", "J"));
        deck.add(new Card(10, "diamonds", "J"));
        deck.add(new Card(10, "clubs", "J"));
        deck.add(new Card(10, "spades", "J"));
        deck.add(new Card(10, "hearts", "Q"));
        deck.add(new Card(10, "diamonds", "Q"));
        deck.add(new Card(10, "clubs", "Q"));
        deck.add(new Card(10, "spades", "Q"));
        deck.add(new Card(10, "hearts", "K"));
        deck.add(new Card(10, "diamonds", "K"));
        deck.add(new Card(10, "clubs", "K"));
        deck.add(new Card(10, "spades", "K"));
        deck.add(new Card(11, "hearts", "A"));
        deck.add(new Card(11, "diamonds", "A"));
        deck.add(new Card(11, "clubs", "A"));
        deck.add(new Card(11, "spades", "A"));
        
        shuffleDeck();
    }
    
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    private void dealCards() {
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        
        playerScore = calculateScore(playerHand);
        dealerScore = calculateScore(dealerHand);
    }
    
    private int calculateScore(List<Card> hand) {
        int score = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getRank() == 11) {
                aces++;
            }
            score += card.getRank();
        }
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }
    
    private void printHands(boolean showDealerCard) {
        System.out.println("Your hand: " + playerHand);
        System.out.println("Your score: " + playerScore);
        if (showDealerCard) {
            System.out.println("Dealer's hand: " + dealerHand);
            System.out.println("Dealer's score: " + dealerScore);
        } else {
            System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", *]");
        }
    }
    
    private void playerTurn(Scanner scanner) {
        while (playerScore < 21) {
            System.out.print("Do you want to hit or stand? ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("hit")) {
                playerHand.add(deck.remove(0));
                playerScore = calculateScore(playerHand);
                printHands(true);
            } else if (input.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
        if (playerScore > 21) {
            playerBust = true;
            System.out.println("You bust! Game over.");
        }
    }

    private void dealerTurn() {
        while (dealerScore < 17 && !playerBust) {
            dealerHand.add(deck.remove(0));
            dealerScore = calculateScore(dealerHand);
        }
        if (dealerScore > 21) {
            dealerBust = true;
            System.out.println("Dealer busts! You win!");
        }
    }

    private void determineWinner() {
        if (!playerBust && dealerScore > playerScore && !dealerBust) {
            System.out.println("Dealer wins!");
        } else if (!dealerBust && playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerBust && !dealerBust) {
            System.out.println("Dealer wins!");
        } else if (dealerBust && !playerBust) {
            System.out.println("You win!");
        } else {
            System.out.println("Push! Nobody wins.");
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        dealCards();
        printHands(false);
        playerTurn(scanner);
        if (!playerBust) {
            dealerTurn();
        }
        determineWinner();
        scanner.close();
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.playGame();
    }

}

class Card {
	private int rank;
	private String suit;
	private String face;

	public Card(int rank, String suit) {
	    this.rank = rank;
	    this.suit = suit;
	    face = Integer.toString(rank);
	}

	public Card(int rank, String suit, String face) {
	    this.rank = rank;
	    this.suit = suit;
	    this.face = face;
	}

	public int getRank() {
	    return rank;
	}

	public String getSuit() {
	    return suit;
	}

	public String toString() {
	    if (face == null) {
	        return rank + " of " + suit;
	    } else {
	        return face + " of " + suit;
	    }
	}
}
}