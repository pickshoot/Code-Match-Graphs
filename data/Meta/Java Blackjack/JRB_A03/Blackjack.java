package A3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean playing = true;
        
        while (playing) {
            System.out.println("Welcome to Blackjack!");
            Deck deck = new Deck();
            deck.shuffle();
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();
            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());
            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());
            
            // Player's turn
            while (true) {
                System.out.println("Your hand: " + playerHand.toString());
                System.out.println("Dealer's hand: " + dealerHand.getCard(0));
                System.out.print("Do you want to hit or stand? ");
                choice = sc.next();
                if (choice.equalsIgnoreCase("hit")) {
                    playerHand.addCard(deck.draw());
                    if (playerHand.getHandValue() > 21) {
                        System.out.println("Bust! Your hand is over 21.");
                        break;
                    }
                }
                else if (choice.equalsIgnoreCase("stand")) {
                    break;
                }
            }
            
            // Dealer's turn
            while (dealerHand.getHandValue() < 17 && playerHand.getHandValue() <= 21) {
                dealerHand.addCard(deck.draw());
            }
            
            // Determine winner
            System.out.println("Your hand: " + playerHand.toString());
            System.out.println("Dealer's hand: " + dealerHand.toString());
            if (playerHand.getHandValue() > 21) {
                System.out.println("You lose! Your hand is over 21.");
            }
            else if (dealerHand.getHandValue() > 21) {
                System.out.println("You win! Dealer's hand is over 21.");
            }
            else if (playerHand.getHandValue() > dealerHand.getHandValue()) {
                System.out.println("You win! Your hand is higher than the dealer's hand.");
            }
            else if (playerHand.getHandValue() < dealerHand.getHandValue()) {
                System.out.println("You lose! Your hand is lower than the dealer's hand.");
            }
            else {
                System.out.println("Push! Your hand is the same as the dealer's hand.");
            }
            
            System.out.print("Do you want to play again? (Y/N) ");
            choice = sc.next();
            if (choice.equalsIgnoreCase("n")) {
                playing = false;
            }
        }
        sc.close();
    }

}

class Card {
    private String suit;
    private String rank;
    private int value;
    
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }
    
    public String toString() {
        return rank + " of " + suit;
    }
    
    public int getValue() {
        return value;
    }
}

class Deck {
    private ArrayList<Card> cards;
    
    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
            for (int i = 0; i < suits.length; i++) {
                for (int j = 0; j < ranks.length; j++) {
                    Card card = new Card(suits[i], ranks[j], values[j]);
                    cards.add(card);
                }
            }
        }
        
        public void shuffle() {
            Collections.shuffle(cards);
        }
        
        public Card draw() {
            return cards.remove(0);
        }
    }
    
    class Hand {
        private ArrayList<Card> cards;
        
        public Hand() {
            cards = new ArrayList<Card>();
        }
        
        public void addCard(Card card) {
            cards.add(card);
        }
        
        public int getHandValue() {
            int value = 0;
            int aces = 0;
            for (Card card : cards) {
                if (card.getValue() == 11) {
                    aces++;
                }
                value += card.getValue();
            }
            while (value > 21 && aces > 0) {
                value -= 10;
                aces--;
            }
            return value;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Card card : cards) {
                sb.append(card.toString() + ", ");
            }
            sb.delete(sb.length()-2, sb.length());
            return sb.toString();
        }
        
        public Card getCard(int index) {
            return cards.get(index);
        }
    }