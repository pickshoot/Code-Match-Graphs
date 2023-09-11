package A7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        Deck.Player player = new Deck.Player();
        Deck.Dealer dealer = new Deck.Dealer();

        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        System.out.println("Your cards: " + player.getHand());
        System.out.println("Dealer's up card: " + dealer.getUpCard());

        boolean playerDone = false;
        boolean dealerDone = false;

        while (!playerDone || !dealerDone) {
            if (!playerDone) {
                System.out.print("Do you want to hit or stand? ");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("hit")) {
                    player.addCard(deck.drawCard());
                    System.out.println("Your cards: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("Bust! You lose.");
                        return;
                    }
                } else {
                    playerDone = true;
                }
            }

            if (!dealerDone) {
                if (dealer.getHandValue() < 17) {
                    dealer.addCard(deck.drawCard());
                    System.out.println("Dealer hits: " + dealer.getHand());
                    if (dealer.getHandValue() > 21) {
                        System.out.println("Dealer busts! You win.");
                        return;
                    }
                } else {
                    dealerDone = true;
                }
            }
        }

        System.out.println("Your cards: " + player.getHand());
        System.out.println("Dealer's cards: " + dealer.getHand());

        int playerHandValue = player.getHandValue();
        int dealerHandValue = dealer.getHandValue();

        if (playerHandValue > dealerHandValue) {
            System.out.println("You win!");
        } else if (playerHandValue < dealerHandValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static class Card {
        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public int getValue() {
            return rank.getValue();
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    private enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    private enum Rank {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static class Deck {
        private final ArrayList<Card> cards;

        public Deck() {
            cards = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
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

        private static class Hand {
            private final ArrayList<Card> cards;
    
            public Hand() {
                cards = new ArrayList<>();
            }
    
            public void addCard(Card card) {
                cards.add(card);
            }
    
            public int getHandValue() {
                int value = 0;
                int numAces = 0;
                for (Card card : cards) {
                    if (card.getValue() == 11) {
                        numAces++;
                    }
                    value += card.getValue();
                }
                while (value > 21 && numAces > 0) {
                    value -= 10;
                    numAces--;
                }
                return value;
            }
    
            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                for (Card card : cards) {
                    sb.append(card.toString()).append(", ");
                }
                return sb.toString();
            }
        }
    
        private static class Player {
            private final Hand hand;
    
            public Player() {
                hand = new Hand();
            }
    
            public void addCard(Card card) {
                hand.addCard(card);
            }
    
            public Hand getHand() {
                return hand;
            }
    
            public int getHandValue() {
                return hand.getHandValue();
            }
        }
    
        private static class Dealer {
            private final Hand hand;
    
            public Dealer() {
                hand = new Hand();
            }
    
            public void addCard(Card card) {
                hand.addCard(card);
            }
    
            public Hand getHand() {
                return hand;
            }
    
            public int getHandValue() {
                return hand.getHandValue();
            }
    
            public Card getUpCard() {
                return hand.cards.get(0);
            }
        }
    }
}