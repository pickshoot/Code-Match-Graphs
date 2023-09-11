package A1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    public Blackjack() {
        deck = new Deck();
        deck.shuffle();
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    public void deal() {
        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
        playerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
    }

    public void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        while (playerHand.getScore() < 21) {
            System.out.println("Your hand: " + playerHand.getScore());
            System.out.println("Dealer's hand: " + dealerHand.getScore());
            System.out.println("Hit or stand? (h/s)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("h")) {
                playerHand.addCard(deck.draw());
            } else if (input.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    public void dealerTurn() {
        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.draw());
        }
    }

    public void play() {
        deal();
        playerTurn();
        if (playerHand.getScore() <= 21) {
            dealerTurn();
        }
        System.out.println("Your hand: " + playerHand.getScore());
        System.out.println("Dealer's hand: " + dealerHand.getScore());
        if (playerHand.getScore() > 21 || (dealerHand.getScore() >= playerHand.getScore() && dealerHand.getScore() <= 21)) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("You win!");
        }
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }
}

class Deck {
    private ArrayList<Card> cards;
    private int currentCard;

    public Deck() {
        cards = new ArrayList<Card>();
        currentCard = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        currentCard = 0;
    }

    public Card draw() {
        if (currentCard >= cards.size()) {
            return null;
        }
        Card card = cards.get(currentCard);
        currentCard++;
        return card;
    }
}

class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        int numAces = 0;
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                numAces++;
            } else if (card.getRank() == Rank.JACK || card.getRank() == Rank.QUEEN || card.getRank() == Rank.KING) {
                score += 10;
            } else {
                score += card.getRank().ordinal() + 1;
            }
        }
        for (int i = 0; i < numAces; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }
        return score;
    }
}