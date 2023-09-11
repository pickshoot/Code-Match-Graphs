package B4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player = new Player();
        Dealer dealer = new Dealer();

        while (true) {
            // Deal initial cards
            player.addToHand(deck.drawCard());
            dealer.addToHand(deck.drawCard());
            player.addToHand(deck.drawCard());
            dealer.addToHand(deck.drawCard());

            // Print hands
            System.out.println("Dealer's hand: " + dealer.getHand().get(0));
            System.out.println("Your hand: " + player.getHand());
            
            // Player's turn
            while (true) {
                System.out.print("Do you want to hit or stand? ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("hit")) {
                    player.addToHand(deck.drawCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust! Dealer wins.");
                        player.setBalance(player.getBalance() - 10);
                        break;
                    }
                } else if (input.equalsIgnoreCase("stand")) {
                    break;
                }
            }
            
            // Dealer's turn
            while (dealer.shouldDrawCard()) {
                dealer.addToHand(deck.drawCard());
            }
            System.out.println("Dealer's hand: " + dealer.getHand());
            if (dealer.getHandValue() > 21) {
                System.out.println("Dealer busts! You win.");
                player.setBalance(player.getBalance() + 10);
            } else if (dealer.getHandValue() > player.getHandValue()) {
                System.out.println("Dealer wins.");
                player.setBalance(player.getBalance() - 10);
            } else if (dealer.getHandValue() < player.getHandValue()) {
                System.out.println("You win!");
                player.setBalance(player.getBalance() + 10);
            } else {
                System.out.println("It's a tie.");
            }
            
            // Check if player wants to play again
            System.out.println("Your balance: $" + player.getBalance());
            System.out.print("Do you want to play again? ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                break;
            }
            player.clearHand();
            dealer.clearHand();
            deck.shuffle();
        }
    }
}


class Card {
    private final String suit;
    private final String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }
    
    public String toString() {
        return value + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}

class Player {
    private List<Card> hand;
    private int balance;

    public Player() {
        hand = new ArrayList<Card>();
        balance = 100;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public int getHandValue() {
        int handValue = 0;
        int numAces = 0;
        for (Card card : hand) {
            String value = card.getValue();
            if (value.equals("Ace")) {
                numAces++;
                handValue += 11;
            } else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
                handValue += 10;
            } else {
                handValue += Integer.parseInt(value);
            }
        }
        while (handValue > 21 && numAces > 0) {
            handValue -= 10;
            numAces--;
        }
        return handValue;
    }

    public void clearHand() {
        hand.clear();
    }
}

class Dealer {
    private List<Card> hand;

    public Dealer() {
        hand = new ArrayList<Card>();
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean shouldDrawCard() {
        int handValue = getHandValue();
        return handValue < 17;
    }

    public int getHandValue() {
        int handValue = 0;
        int numAces = 0;
        for (Card card : hand) {
            String value = card.getValue();
            if (value.equals("Ace")) {
                numAces++;
                handValue += 11;
            } else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
                handValue += 10;
            } else {
                handValue += Integer.parseInt(value);
            }
        }
        while (handValue > 21 && numAces > 0) {
            handValue -= 10;
            numAces--;
        }
        return handValue;
    }
    
    public void clearHand() {
        hand.clear();
    }
}