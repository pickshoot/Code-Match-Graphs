package A2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        // Deal two cards to the player and one card to the dealer
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        System.out.println("Your cards: " + playerHand);
        System.out.println("Dealer's card: " + dealerHand.getCard(0));

        // Player's turn
        while (true) {
            System.out.print("Hit or stand? ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("hit")) {
                playerHand.addCard(deck.drawCard());
                System.out.println("Your cards: " + playerHand);

                if (playerHand.getBlackjackValue() > 21) {
                    System.out.println("You bust!");
                    System.out.println("Dealer wins!");
                    return;
                }
            } else if (input.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
            }
        }

        // Dealer's turn
        System.out.println("Dealer's cards: " + dealerHand);
        while (dealerHand.getBlackjackValue() < 17) {
            dealerHand.addCard(deck.drawCard());
            System.out.println("Dealer's cards: " + dealerHand);

            if (dealerHand.getBlackjackValue() > 21) {
                System.out.println("Dealer busts!");
                System.out.println("You win!");
                return;
            }
        }

        // Determine the winner
        int playerScore = playerHand.getBlackjackValue();
        int dealerScore = dealerHand.getBlackjackValue();
        if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("Push!");
        }
    }
}

class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        if (rank.equals("Ace")) {
            return 1;
        } else if (rank.equals("2")) {
            return 2;
        } else if (rank.equals("3")) {
            return 3;
        } else if (rank.equals("4")) {
            return 4;
        } else if (rank.equals("5")) {
            return 5;
        } else if (rank.equals("6")) {
            return 6;
        } else if (rank.equals("7")) {
            return 7;
        } else if (rank.equals("8")) {
            return 8;
        } else if (rank.equals("9")) {
            return 9;
        } else {
            return 10;
        }
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
        cards = new ArrayList<Card>();

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}

class Hand {
    private final ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getBlackjackValue() {
        int value = 0;
        int aces = 0;

        for (Card card : cards) {
            value += card.getValue();

            if (card.getValue() == 1) {
                aces++;
            }
        }

        while (value <= 11 && aces > 0) {
            value += 10;
            aces--;
        }

        return value;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Card card : cards) {
            sb.append(card).append(", ");
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.toString();
    }
}