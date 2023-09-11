package B6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};

        for (String rank : ranks) {
            for (String suit : suits) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new RuntimeException("Deck is empty!");
        }
        return cards.remove(0);
    }
}

class Player {
    private List<Card> hand;
    private int score;

    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    public void addCard(Card card) {
        hand.add(card);
        score += getCardValue(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    private int getCardValue(Card card) {
        String rank = card.getRank();
        if (rank.equals("A")) {
            return 11;
        } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J") || rank.equals("10")) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }
}

class Dealer extends Player {
    public boolean shouldHit() {
        int score = getScore();
        if (score < 17) {
            return true;
        } else if (score == 17) {
            for (Card card : getHand()) {
                if (card.getRank().equals("A")) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}

public class Blackjack {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public Blackjack() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");
        while (true) {
            deck.shuffle();
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", *]");

            // Player's turn
            while (true) {
                System.out.print("Do you want to hit or stand? ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("hit")) {
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getScore() > 21) {
                        System.out.println("You bust!");
                        return;
                    }
                } else if (choice.equalsIgnoreCase("stand")) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            }

            // Dealer's turn
            while (dealer.shouldHit()) {
                dealer.addCard(deck.dealCard());
            }

            System.out.println("Your hand: " + player.getHand() + ", Score: " + player.getScore());
            System.out.println("Dealer's hand: " + dealer.getHand() + ", Score: " + dealer.getScore());

            if (dealer.getScore() > 21) {
                System.out.println("Dealer busts! You win!");
            } else if (player.getScore() > dealer.getScore()) {
                System.out.println("You win!");
            } else if (player.getScore() < dealer.getScore()) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("It's a tie!");
            }

            System.out.print("Do you want to play again? ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }
}