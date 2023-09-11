package A8;

import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int playerTotal = 0;
        int dealerTotal = 0;
        int cardValue;
        boolean playAgain = true;
        
        while (playAgain) {
            playerTotal = 0;
            dealerTotal = 0;
            
            System.out.println("Welcome to Blackjack!");
            
            // Deal the player's cards
            cardValue = random.nextInt(10) + 1;
            playerTotal += cardValue;
            System.out.println("Your first card is a " + cardValue);
            cardValue = random.nextInt(10) + 1;
            playerTotal += cardValue;
            System.out.println("Your second card is a " + cardValue);
            System.out.println("Your total is " + playerTotal);
            
            // Deal the dealer's cards
            cardValue = random.nextInt(10) + 1;
            dealerTotal += cardValue;
            System.out.println("The dealer's first card is a " + cardValue);
            cardValue = random.nextInt(10) + 1;
            dealerTotal += cardValue;
            
            // Player's turn
            while (playerTotal < 21) {
                System.out.print("Do you want to hit or stand? ");
                String choice = input.nextLine();
                
                if (choice.equalsIgnoreCase("hit")) {
                    cardValue = random.nextInt(10) + 1;
                    playerTotal += cardValue;
                    System.out.println("You drew a " + cardValue);
                    System.out.println("Your total is " + playerTotal);
                }
                else if (choice.equalsIgnoreCase("stand")) {
                    break;
                }
                else {
                    System.out.println("Invalid choice. Please choose hit or stand.");
                }
            }
            
            // Dealer's turn
            while (dealerTotal < 17) {
                cardValue = random.nextInt(10) + 1;
                dealerTotal += cardValue;
                System.out.println("The dealer drew a " + cardValue);
            }
            
            // Determine the winner
            if (playerTotal > 21) {
                System.out.println("Bust! You lose.");
            }
            else if (dealerTotal > 21) {
                System.out.println("Dealer busts! You win.");
            }
            else if (playerTotal > dealerTotal) {
                System.out.println("You win!");
            }
            else if (dealerTotal > playerTotal) {
                System.out.println("Dealer wins!");
            }
            else {
                System.out.println("It's a tie.");
            }
            
            // Ask the player if they want to play again
            System.out.print("Do you want to play again? ");
            String choice = input.nextLine();
            
            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("Thanks for playing!");
    }
}