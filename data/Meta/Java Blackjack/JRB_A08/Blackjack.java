package A8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String playAgain = "y";

        while (playAgain.equalsIgnoreCase("y")) {

            // Initialize deck and hands
            Deck deck = new Deck();
            deck.shuffle();
            ArrayList<Card> userHand = new ArrayList<>();
            ArrayList<Card> dealerHand = new ArrayList<>();

            // Deal initial cards
            userHand.add(deck.dealCard());
            userHand.add(deck.dealCard());
            dealerHand.add(deck.dealCard());
            dealerHand.add(deck.dealCard());

            // Print initial hands
            System.out.println("Your hand: " + userHand);
            System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", HIDDEN CARD]");

            // User's turn
            boolean userBust = false;
            while (true) {
                System.out.print("Do you want to hit or stand? ");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("hit")) {
                    userHand.add(deck.dealCard());
                    System.out.println("Your hand: " + userHand);
                    if (getHandValue(userHand) > 21) {
                        System.out.println("You busted!");
                        userBust = true;
                        break;
                    }
                } else if (answer.equalsIgnoreCase("stand")) {
                    break;
                }
            }

            // Dealer's turn
            if (!userBust) {
                System.out.println("Dealer's hand: " + dealerHand);
                while (getHandValue(dealerHand) < 17) {
                    dealerHand.add(deck.dealCard());
                    System.out.println("Dealer hits: " + dealerHand.get(dealerHand.size() - 1));
                    if (getHandValue(dealerHand) > 21) {
                        System.out.println("Dealer busted! You win!");
                        userBust = true;
                        break;
                    }
                }
            }

            // Determine winner
            if (!userBust) {
                int userValue = getHandValue(userHand);
                int dealerValue = getHandValue(dealerHand);
                if (userValue > dealerValue) {
                    System.out.println("You win!");
                } else if (dealerValue > userValue) {
                    System.out.println("Dealer wins!");
                } else {
                    System.out.println("It's a tie!");
                }
            }

            // Play again?
            System.out.print("Do you want to play again? (y/n) ");
            playAgain = sc.nextLine();
        }

        sc.close();
    }

    public static int getHandValue(ArrayList<Card> hand) {
        int value = 0;
        int numAces = 0;
        for (Card card : hand) {
            value += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }

}

class Card {

    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
        int[] values = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                Card card = new Card(suit, ranks[i], values[i]);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}