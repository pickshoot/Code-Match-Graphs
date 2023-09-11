package C5;

import java.util.*;

public class Blackjack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int playerTotal = 0;
        int dealerTotal = 0;

        System.out.println("Welcome to Blackjack!");

        // Deal cards to player
        int playerCard1 = rand.nextInt(11) + 1;
        int playerCard2 = rand.nextInt(11) + 1;
        playerTotal = playerCard1 + playerCard2;
        System.out.println("You were dealt a " + playerCard1 + " and a " + playerCard2);
        System.out.println("Your total is " + playerTotal);

        // Deal cards to dealer
        int dealerCard1 = rand.nextInt(11) + 1;
        int dealerCard2 = rand.nextInt(11) + 1;
        dealerTotal = dealerCard1 + dealerCard2;
        System.out.println("The dealer was dealt a " + dealerCard1 + " and a hidden card");

        // Player's turn
        while (playerTotal <= 21) {
            System.out.println("Do you want to hit or stand? (h/s)");
            String choice = sc.next();
            if (choice.equals("h")) {
                int newCard = rand.nextInt(11) + 1;
                playerTotal += newCard;
                System.out.println("You were dealt a " + newCard);
                System.out.println("Your total is " + playerTotal);
            } else {
                break;
            }
        }

        // Dealer's turn
        if (playerTotal <= 21) {
            System.out.println("Dealer's turn");
            System.out.println("The dealer's hidden card was a " + dealerCard2);
            while (dealerTotal < 17) {
                int newCard = rand.nextInt(11) + 1;
                dealerTotal += newCard;
                System.out.println("The dealer was dealt a " + newCard);
                System.out.println("The dealer's total is " + dealerTotal);
            }
        }

        // Determine winner
        if (playerTotal > 21) {
            System.out.println("You bust, dealer wins!");
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busts, you win!");
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}