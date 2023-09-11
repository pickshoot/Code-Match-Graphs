package A9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<Integer> userCards = new ArrayList<>();
        List<Integer> dealerCards = new ArrayList<>();
        
        // Deal two cards to the user and one card to the dealer
        userCards.add(dealCard(random));
        userCards.add(dealCard(random));
        dealerCards.add(dealCard(random));
        
        // Display the initial hands
        System.out.println("Your cards: " + userCards);
        System.out.println("Dealer's card: " + dealerCards.get(0));
        
        // Allow the user to hit or stand
        while (true) {
            System.out.print("Hit or stand? (h/s): ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("h")) {
                userCards.add(dealCard(random));
                System.out.println("Your cards: " + userCards);
                if (getHandValue(userCards) > 21) {
                    System.out.println("Bust! You lose.");
                    return;
                }
            } else if (choice.equals("s")) {
                break;
            }
        }
        
        // Dealer reveals their face down card and hits until hand exceeds 17
        System.out.println("Dealer's cards: " + dealerCards);
        while (getHandValue(dealerCards) < 17) {
            dealerCards.add(dealCard(random));
            System.out.println("Dealer hits: " + dealerCards);
            if (getHandValue(dealerCards) > 21) {
                System.out.println("Dealer busts! You win.");
                return;
            }
        }
        
        // Determine the winner
        int userHandValue = getHandValue(userCards);
        int dealerHandValue = getHandValue(dealerCards);
        System.out.println("Your hand: " + userHandValue);
        System.out.println("Dealer's hand: " + dealerHandValue);
        if (userHandValue > dealerHandValue) {
            System.out.println("You win!");
        } else if (userHandValue < dealerHandValue) {
            System.out.println("You lose.");
        } else {
            System.out.println("It's a tie.");
        }
    }
    
    // Returns a random card value from 1 to 10
    private static int dealCard(Random random) {
        return random.nextInt(10) + 1;
    }
    
    // Calculates the value of a hand, accounting for aces being worth 1 or 11
    private static int getHandValue(List<Integer> cards) {
        int value = 0;
        int numAces = 0;
        for (int card : cards) {
            if (card == 1) {
                numAces++;
                value += 11;
            } else if (card >= 10) {
                value += 10;
            } else {
                value += card;
            }
        }
        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }
        return value;
    }
}