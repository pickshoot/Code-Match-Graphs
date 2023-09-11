package C1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        Dealer dealer = new Dealer();
        player.hit(deck);
        player.hit(deck);
        dealer.hit(deck);
        dealer.hit(deck);

        while (true) {
            System.out.println();
            System.out.println("Your cards: ");
            player.printHand();

            System.out.println("Dealer's cards: ");
            dealer.printHand();

            System.out.print("Hit or stand? (h/s) ");
            String input = scanner.nextLine();
            if (input.equals("h")) {
                player.hit(deck);
                if (player.getHandValue() > 21) {
                    System.out.println("You bust! Dealer wins.");
                    break;
                }
            } else {
                while (dealer.getHandValue() < 17) {
                    dealer.hit(deck);
                }

                System.out.println("Dealer's cards: ");
                dealer.printHand();

                if (dealer.getHandValue() > 21) {
                    System.out.println("Dealer busts! You win.");
                } else if (dealer.getHandValue() >= player.getHandValue()) {
                    System.out.println("Dealer wins.");
                } else {
                    System.out.println("You win!");
                }

                break;
            }
        }
    }
}

class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        for (String suit : suits) {
            for (int i = 2; i <= 10; i++) {
                cards.add(new Card(i + " of " + suit, i));
            }
            cards.add(new Card("Jack of " + suit, 10));
            cards.add(new Card("Queen of " + suit, 10));
            cards.add(new Card("King of " + suit, 10));
            cards.add(new Card("Ace of " + suit, 11));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }
}

class Card {
    private String name;
    private int value;

    public Card(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
    }

    public void hit(Deck deck) {
        hand.add(deck.draw());
    }

    public int getHandValue() {
        int value = 0;
        int aces = 0;

        for (Card card : hand) {
            if (card.getValue() == 11) {
                aces++;
            }
            value += card.getValue();
        }

        while (aces > 0 && value > 21) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public void printHand() {
        for (Card card : hand) {
            System.out.println(card.getName());
        }
        System.out.println("Total value: " + getHandValue());
    }
}

class Dealer {
    private ArrayList<Card> hand;

    public Dealer() {
        hand = new ArrayList<Card>();
    }

    public void hit(Deck deck) {
        hand.add(deck.draw());
    }

    public int getHandValue() {
        int value = 0;
        int aces = 0;

        for (Card card : hand) {
            if (card.getValue() == 11) {
                aces++;
            }
            value += card.getValue();
        }

        while (aces > 0 && value > 21) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public void printHand() {
        System.out.println("[" + hand.get(0).getName() + ", Hidden Card]");
    }
}