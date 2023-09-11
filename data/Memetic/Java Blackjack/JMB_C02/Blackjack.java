package C2;

import java.util.*;

public class Blackjack {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");
        
        // Initialize the deck
        List<String> deck = new ArrayList<String>();
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        
        // Shuffle the deck
        Collections.shuffle(deck);
        
        // Initialize the player's and dealer's hands
        List<String> playerHand = new ArrayList<String>();
        List<String> dealerHand = new ArrayList<String>();
        
        // Deal the initial hands
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        
        // Show the initial hands
        System.out.println("Your hand: " + playerHand.get(0) + " and " + playerHand.get(1));
        System.out.println("Dealer's hand: " + dealerHand.get(0) + " and [hidden]");
        
        // Player's turn
        while (true) {
            System.out.print("Would you like to hit or stand? ");
            String input = sc.nextLine();
            
            if (input.equalsIgnoreCase("hit")) {
                playerHand.add(deck.remove(0));
                System.out.println("Your new card: " + playerHand.get(playerHand.size()-1));
                
                // Check for bust
                int total = getTotal(playerHand);
                if (total > 21) {
                    System.out.println("Bust! Your total is " + total);
                    System.out.println("Dealer wins!");
                    System.exit(0);
                }
            }
            else if (input.equalsIgnoreCase("stand")) {
                break;
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        
        // Dealer's turn
        System.out.println("Dealer's hidden card: " + dealerHand.get(1));
        while (getTotal(dealerHand) < 17) {
            dealerHand.add(deck.remove(0));
            System.out.println("Dealer hits and gets: " + dealerHand.get(dealerHand.size()-1));
            
            // Check for bust
            int total = getTotal(dealerHand);
            if (total > 21) {
                System.out.println("Dealer busts! Your total is " + getTotal(playerHand));
                System.out.println("You win!");
                System.exit(0);
            }
        }
        
        // Compare hands
        int playerTotal = getTotal(playerHand);
        int dealerTotal = getTotal(dealerHand);
        if (playerTotal > dealerTotal) {
            System.out.println("You win! Your total is " + playerTotal + " and the dealer's total is " + dealerTotal);
        }
        else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins! Your total is " + playerTotal + " and the dealer's total is " + dealerTotal);
        }
        else {
            System.out.println("It's a tie! Your total is " + playerTotal + " and the dealer's total is " + dealerTotal);
        }
    }
    
    // Helper method to calculate the total value of a hand
    public static int getTotal(List<String> hand) {
        int total = 0;
        int numAces = 0;
        
        for (String card : hand) {
            String rank = card.split(" ")[0];
            if (rank.equals("Ace")) {
                numAces++;
                total += 11;
            }
            else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                total += 10;
            }
            else {
                total += Integer.parseInt(rank);
            }
        }
        
        while (total > 21 && numAces > 0) {
            total -= 10;
            numAces--;
        }
        
        return total;
    }
}