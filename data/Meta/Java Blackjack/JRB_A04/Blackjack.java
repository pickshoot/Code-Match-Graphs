package A4;

import java.util.*;

public class Blackjack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        // Deal two cards to the user and one card face up to the dealer
        Hand userHand = new Hand();
        Hand dealerHand = new Hand();
        userHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());

        System.out.println("Welcome to Blackjack!");
        System.out.println("Your hand: " + userHand);
        System.out.println("Dealer's hand: " + dealerHand.getTopCard());

        // User's turn
        while (true) {
            System.out.print("Do you want to hit or stand? (h/s) ");
            String choice = scanner.nextLine();
            if (choice.equals("h")) {
                userHand.addCard(deck.dealCard());
                System.out.println("Your hand: " + userHand);
                if (userHand.getScore() > 21) {
                    System.out.println("You bust! Dealer wins!");
                    return;
                }
            } else if (choice.equals("s")) {
                break;
            } else {
                System.out.println("Invalid choice. Please choose 'h' or 's'.");
            }
        }

        // Dealer's turn
        System.out.println("Dealer's turn:");
        dealerHand.addCard(deck.dealCard());
        System.out.println("Dealer's hand: " + dealerHand);
        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.dealCard());
            System.out.println("Dealer's hand: " + dealerHand);
            if (dealerHand.getScore() > 21) {
                System.out.println("Dealer busts! You win!");
                return;
            }
        }

        // Determine the winner
        int userScore = userHand.getScore();
        int dealerScore = dealerHand.getScore();
        if (userScore > dealerScore) {
            System.out.println("You win!");
        } else if (userScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }

    }

}

class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.remove(0);
    }

}

class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

}

enum Rank {

    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

    private int value;

    private Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

enum Suit {

    CLUBS, DIAMONDS, HEARTS, SPADES
}

class Hand {

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            int value = card.getValue();
            score += value;
            if (value == 1) {
                hasAce = true;
            }
        }
        if (hasAce && score + 10 <= 21) {
            score += 10;
        }
        return score;
    }

    @Override
    public String toString() {
        return cards.toString() + " (score: " + getScore() + ")";
    }

    public String getTopCard() {
        return cards.get(0).toString() + " (hidden)";
    }
}