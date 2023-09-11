package A2;

import java.util.*;

public class Blackjack {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int playerTotal = 0;
        int dealerTotal = 0;
        int playerWins = 0;
        int dealerWins = 0;
        boolean playAgain = true;

        while (playAgain) {
            // Deal cards to player and dealer
            int card1 = dealCard();
            int card2 = dealCard();
            playerTotal = card1 + card2;

            int dealerCard1 = dealCard();
            int dealerCard2 = dealCard();
            dealerTotal = dealerCard1 + dealerCard2;

            System.out.println("Your cards are: " + card1 + " and " + card2);
            System.out.println("Dealer's card is: " + dealerCard1);

            // Player's turn
            while (playerTotal <= 21) {
                System.out.print("Hit or stand? (h/s): ");
                String decision = input.next();
                if (decision.equals("h")) {
                    int newCard = dealCard();
                    playerTotal += newCard;
                    System.out.println("Your new card is: " + newCard);
                } else {
                    break;
                }
            }

            // Dealer's turn
            while (dealerTotal <= 16) {
                int newCard = dealCard();
                dealerTotal += newCard;
                System.out.println("Dealer's new card is: " + newCard);
            }

            // Determine winner
            if (playerTotal > 21) {
                System.out.println("You busted! Dealer wins.");
                dealerWins++;
            } else if (dealerTotal > 21) {
                System.out.println("Dealer busted! You win.");
                playerWins++;
            } else if (playerTotal > dealerTotal) {
                System.out.println("You win!");
                playerWins++;
            } else if (dealerTotal > playerTotal) {
                System.out.println("Dealer wins.");
                dealerWins++;
            } else {
                System.out.println("It's a tie!");
            }

            // Ask if player wants to play again
            System.out.print("Play again? (y/n): ");
            String again = input.next();
            if (again.equals("n")) {
                playAgain = false;
            }
        }

        // Print final results
        System.out.println("Player wins: " + playerWins);
        System.out.println("Dealer wins: " + dealerWins);
    }

    public static int dealCard() {
        Random rand = new Random();
        int card = rand.nextInt(10) + 1;
        return card;
    }
}