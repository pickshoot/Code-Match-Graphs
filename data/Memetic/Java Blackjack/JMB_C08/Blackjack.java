package C8;

import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        // Initialize the deck
        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i);
            }
        }
        
        // Shuffle the deck
        Collections.shuffle(deck);
        
        // Initialize the player's hand and the dealer's hand
        ArrayList<Integer> playerHand = new ArrayList<Integer>();
        ArrayList<Integer> dealerHand = new ArrayList<Integer>();
        
        // Deal the initial hands
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        
        // Show the initial hands
        System.out.println("Player's hand: " + playerHand);
        System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", *]");
        
        // Player's turn
        while (true) {
            // Ask the player if they want to hit or stand
            System.out.print("Hit or stand? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            
            // If the player chooses to hit, deal them another card
            if (input.equals("hit")) {
                playerHand.add(deck.remove(0));
                System.out.println("Player's hand: " + playerHand);
                
                // If the player busts, the game is over and the dealer wins
                if (sum(playerHand) > 21) {
                    System.out.println("Player busts. Dealer wins.");
                    return;
                }
            }
            // If the player chooses to stand, end their turn
            else if (input.equals("stand")) {
                break;
            }
            // If the player enters an invalid input, prompt them again
            else {
                System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
            }
        }
        
        // Dealer's turn
        while (sum(dealerHand) < 17) {
            dealerHand.add(deck.remove(0));
        }
        System.out.println("Dealer's hand: " + dealerHand);
        
        // Determine the winner
        if (sum(dealerHand) > 21) {
            System.out.println("Dealer busts. Player wins.");
        } else if (sum(playerHand) > sum(dealerHand)) {
            System.out.println("Player wins.");
        } else if (sum(dealerHand) > sum(playerHand)) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie.");
        }
    }
    
    // Calculates the sum of a hand
    public static int sum(ArrayList<Integer> hand) {
        int sum = 0;
        int numAces = 0;
        for (int card : hand) {
            if (card == 1) {
                numAces++;
            } else if (card >= 10) {
                sum += 10;
            } else {
                sum += card;
            }
        }
        for (int i = 0; i < numAces; i++) {
            if (sum + 11 <= 21) {
                sum += 11;
            } else {
                sum += 1;
            }
        }
        return sum;
    }
}