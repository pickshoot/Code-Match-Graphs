package JSB_C01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int playerScore = 0;
        int dealerScore = 0;
        List<String> deck = new ArrayList<String>();
        List<String> playerHand = new ArrayList<String>();
        List<String> dealerHand = new ArrayList<String>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        
        // Build the deck
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        
        // Shuffle the deck
        for (int i = 0; i < deck.size(); i++) {
            int j = rand.nextInt(deck.size());
            String temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
        
        // Deal the cards
        playerHand.add(deck.get(0));
        dealerHand.add(deck.get(1));
        playerHand.add(deck.get(2));
        dealerHand.add(deck.get(3));
        playerScore = getHandValue(playerHand);
        dealerScore = getHandValue(dealerHand);
        
        // Show the initial hands
        System.out.println("Player's hand: " + playerHand.get(0) + ", " + playerHand.get(1) + " (" + playerScore + ")");
        System.out.println("Dealer's hand: " + dealerHand.get(0) + ", *** (" + dealerScore + ")");
        
        // Player's turn
        while (playerScore < 21) {
            System.out.print("Do you want to hit or stand? ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("hit")) {
                playerHand.add(deck.get(0));
                playerScore = getHandValue(playerHand);
                System.out.println("Player's hand: " + playerHand + " (" + playerScore + ")");
                if (playerScore >= 21) {
                    break;
                }
            } else if (choice.equalsIgnoreCase("stand")) {
                break;
            }
        }
        
        // Dealer's turn
        while (dealerScore < 17 && playerScore <= 21) {
            dealerHand.add(deck.get(0));
            dealerScore = getHandValue(dealerHand);
            System.out.println("Dealer's hand: " + dealerHand + " (" + dealerScore + ")");
        }
        
        // Determine the winner
        System.out.println("Player's hand: " + playerHand + " (" + playerScore + ")");
        System.out.println("Dealer's hand: " + dealerHand + " (" + dealerScore + ")");
        if (playerScore > 21) {
            System.out.println("Player busts! Dealer wins!");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busts! Player wins!");
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
    // Get the value of a hand
    public static int getHandValue(List<String> hand) {
        int value = 0;
        int numAces = 0;
        for (String card : hand) {
            String rank = card.split(" ")[0];
            if (rank.equals("Ace")) {
                numAces++;
            } else if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }
        for (int i = 0; i < numAces; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }
        return value;
    }
}