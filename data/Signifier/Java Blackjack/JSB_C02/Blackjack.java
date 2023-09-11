package JSB_C02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    
    public static void main(String[] args) {
        
        // Create a new deck of cards
        Deck deck = new Deck();
        
        // Create the player and dealer hands
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        
        // Deal the initial hands
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        
        // Print out the initial hands
        System.out.println("Player's Hand: " + playerHand);
        System.out.println("Dealer's Hand: [" + dealerHand.get(0) + ", <hidden>]");
        
        // Player's turn
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Do you want to hit or stand? ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("hit")) {
                playerHand.add(deck.drawCard());
                System.out.println("Player's Hand: " + playerHand);
                
                if (getHandValue(playerHand) > 21) {
                    System.out.println("Player busts! Dealer wins!");
                    return;
                }
            } else if (input.equalsIgnoreCase("stand")) {
                break;
            }
        }
        
        // Dealer's turn
        while (getHandValue(dealerHand) < 17) {
            dealerHand.add(deck.drawCard());
            System.out.println("Dealer's Hand: " + dealerHand);
            
            if (getHandValue(dealerHand) > 21) {
                System.out.println("Dealer busts! Player wins!");
                return;
            }
        }
        
        // Determine the winner
        int playerValue = getHandValue(playerHand);
        int dealerValue = getHandValue(dealerHand);
        
        if (playerValue > dealerValue) {
            System.out.println("Player wins!");
        } else if (dealerValue > playerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("Tie!");
        }
    }
    
    public static int getHandValue(ArrayList<Card> hand) {
        int value = 0;
        boolean hasAce = false;
        
        for (Card card : hand) {
            if (card.getValue() == 1) {
                hasAce = true;
            }
            value += card.getPointValue();
        }
        
        if (hasAce && value + 10 <= 21) {
            value += 10;
        }
        
        return value;
    }
}

class Deck {
    
    private ArrayList<Card> cards;
    
    public Deck() {
        cards = new ArrayList<>();
        
        for (int i = 1; i <= 13; i++) {
            cards.add(new Card(i, "Spades"));
            cards.add(new Card(i, "Hearts"));
            cards.add(new Card(i, "Diamonds"));
            cards.add(new Card(i, "Clubs"));
        }
        
        shuffle();
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card drawCard() {
        return cards.remove(0);
    }
}

class Card {
    
    private int value;
    private String suit;
    
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }
    
    public int getPointValue() {
        if (value >= 10) {
            return 10;
        } else {
            return value;
        }
    }
    public int getValue() {
        return value;
    }
    
    public String getSuit() {
        return suit;
    }
    
    @Override
    public String toString() {
        String faceValue;
        
        switch (value) {
            case 1:
                faceValue = "Ace";
                break;
            case 11:
                faceValue = "Jack";
                break;
            case 12:
                faceValue = "Queen";
                break;
            case 13:
                faceValue = "King";
                break;
            default:
                faceValue = String.valueOf(value);
        }
        
        return faceValue + " of " + suit;
    }
}