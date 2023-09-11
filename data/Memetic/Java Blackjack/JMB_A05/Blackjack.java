package A5;

import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int playerScore = 0;
        int dealerScore = 0;
        
        // Deal initial cards to player and dealer
        int card1 = dealCard();
        int card2 = dealCard();
        playerScore = card1 + card2;
        System.out.println("Your cards: " + card1 + ", " + card2);
        
        int dealerCard1 = dealCard();
        int dealerCard2 = dealCard();
        dealerScore = dealerCard1 + dealerCard2;
        System.out.println("Dealer's card: " + dealerCard1);

        // Player's turn
        while (true) {
            System.out.print("Hit or stand? ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("hit")) {
                int newCard = dealCard();
                playerScore += newCard;
                System.out.println("Your new card: " + newCard);
                if (playerScore > 21) {
                    System.out.println("Bust! You lose.");
                    return;
                }
            } else if (choice.equalsIgnoreCase("stand")) {
                break;
            }
        }

        // Dealer's turn
        while (dealerScore < 17) {
            int newCard = dealCard();
            dealerScore += newCard;
            System.out.println("Dealer's new card: " + newCard);
            if (dealerScore > 21) {
                System.out.println("Dealer busts! You win.");
                return;
            }
        }

        // Compare scores and determine winner
        if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static int dealCard() {
        // Generate a random number between 1 and 10
        return (int) (Math.random() * 10) + 1;
    }
}