package A10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        // Initialize the deck
        ArrayList<String> deck = new ArrayList<String>();
        String[] suits = { "Spades", "Diamonds", "Clubs", "Hearts" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Initialize the player's hand and the dealer's hand
        ArrayList<String> playerHand = new ArrayList<String>();
        ArrayList<String> dealerHand = new ArrayList<String>();

        // Deal two cards to the player and one card to the dealer
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));

        // Print the initial hands
        System.out.println("Player's hand: " + playerHand);
        System.out.println("Dealer's hand: " + dealerHand.get(0) + ", [Hidden]");

        // Initialize the player's score and the dealer's score
        int playerScore = calculateScore(playerHand);
        int dealerScore = calculateScore(dealerHand);

        // Allow the player to hit or stand
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to hit or stand? ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("hit")) {
                playerHand.add(deck.remove(0));
                System.out.println("Player's hand: " + playerHand);
                playerScore = calculateScore(playerHand);
                if (playerScore > 21) {
                    System.out.println("Player busts! Dealer wins.");
                    return;
                }
            } else if (input.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // Reveal the dealer's hidden card and continue hitting until their score is at
        // least 17
        dealerHand.add(deck.remove(0));
        System.out.println("Dealer's hand: " + dealerHand);
        dealerScore = calculateScore(dealerHand);
        while (dealerScore < 17) {
            dealerHand.add(deck.remove(0));
            System.out.println("Dealer hits: " + dealerHand.get(dealerHand.size() - 1));
            dealerScore = calculateScore(dealerHand);
            if (dealerScore > 21) {
                System.out.println("Dealer busts! Player wins.");
                return;
            }
        }

        // Compare the scores and determine the winner
        System.out.println("Player's hand: " + playerHand + " (" + playerScore + ")");
        System.out.println("Dealer's hand: " + dealerHand + " (" + dealerScore + ")");
        if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else if (dealerScore > playerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static int calculateScore(ArrayList<String> hand) {
        int score = 0;
        int aceCount = 0;
        for (String card : hand) {
            String rank = card.split(" ")[0];
            if (rank.equals("Ace")) {
                aceCount++;
                score += 11;
            } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                score += 10;
            } else {
                score += Integer.parseInt(rank);
            }
        }
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        return score;
    }
}