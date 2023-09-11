package B2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    
    private ArrayList<Card> deck;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;
    private int playerScore;
    private int dealerScore;
    private Scanner scanner;
    
    public Blackjack() {
        deck = new ArrayList<Card>();
        playerCards = new ArrayList<Card>();
        dealerCards = new ArrayList<Card>();
        playerScore = 0;
        dealerScore = 0;
        scanner = new Scanner(System.in);
    }
    
    public void play() {
        System.out.println("Welcome to Blackjack!\n");
        createDeck();
        shuffleDeck();
        dealCards();
        showInitialCards();
        playerTurn();
        if (playerScore <= 21) {
            dealerTurn();
            determineWinner();
        } else {
            System.out.println("You busted. Dealer wins.");
        }
    }
    
    private void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        
        for (String suit : suits) {
            for (String value : values) {
                Card card = new Card(suit, value);
                deck.add(card);
            }
        }
    }
    
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    private void dealCards() {
        for (int i = 0; i < 2; i++) {
            playerCards.add(deck.remove(0));
            dealerCards.add(deck.remove(0));
        }
        playerScore = calculateScore(playerCards);
        dealerScore = calculateScore(dealerCards);
    }
    
    private void showInitialCards() {
        System.out.println("Your cards: ");
        for (Card card : playerCards) {
            System.out.println(card.toString());
        }
        System.out.println("Your score: " + playerScore);
        System.out.println("Dealer's face-up card: " + dealerCards.get(0).toString());
    }
    
    private void playerTurn() {
        while (playerScore < 21) {
            System.out.print("Would you like to hit or stand? ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("hit")) {
                playerCards.add(deck.remove(0));
                playerScore = calculateScore(playerCards);
                System.out.println("Your cards: ");
                for (Card card : playerCards) {
                    System.out.println(card.toString());
                }
                System.out.println("Your score: " + playerScore);
            } else {
                break;
            }
        }
    }
    
    private void dealerTurn() {
        System.out.println("Dealer's cards: ");
        for (Card card : dealerCards) {
            System.out.println(card.toString());
        }
        System.out.println("Dealer's score: " + dealerScore);
        while (dealerScore < 17) {
            dealerCards.add(deck.remove(0));
            dealerScore = calculateScore(dealerCards);
            System.out.println("Dealer hits.");
            System.out.println("Dealer's cards: ");
            for (Card card : dealerCards) {
                System.out.println(card.toString());
            }
            System.out.println("Dealer's score: " + dealerScore);
        }
        if (dealerScore > 21) {
            System.out.println("Dealer busted. You win!");
        }
    }
    
    private void determineWinner() {
        if (dealerScore > 21) {
            System.out.println("Dealer busted. You win!");
        } else if (playerScore > 21) {
            System.out.println("You busted. Dealer wins.");
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie.");
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else {
            System.out.println("Dealer wins.");
        }
    }
    
    private int calculateScore(ArrayList<Card> hand) {
        int score = 0;
        int numAces = 0;
        for (Card card : hand) {
            String value = card.getValue();
            if (value.equals("Ace")) {
                numAces++;
                score += 11;
            } else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
                score += 10;
            } else {
                score += Integer.parseInt(value);
            }
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }
    
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }
}

class Card {
    
    private String suit;
    private String value;
    
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getValue() {
        return value;
    }
    
    public String toString() {
        return value + " of " + suit;
    }
}