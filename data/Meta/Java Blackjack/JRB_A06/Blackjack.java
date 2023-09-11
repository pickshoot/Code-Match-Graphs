package A6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_HIT_THRESHOLD = 17;
    private static final int ACE_VALUE = 11;
    private static final int FACE_CARD_VALUE = 10;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            playBlackjack(input);
            System.out.println("Do you want to play again? (Y/N)");
            String answer = input.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }
    }

    private static void playBlackjack(Scanner input) {
        Deck deck = new Deck();
        deck.shuffle();

        ArrayList<Card> userHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        // Deal two cards to the user and one card to the dealer
        userHand.add(deck.dealCard());
        userHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());

        System.out.println("Your hand: " + userHand);
        System.out.println("Dealer's hand: " + dealerHand.get(0) + " [HIDDEN]");

        boolean userTurn = true;
        boolean userBust = false;
        while (userTurn) {
            System.out.println("Do you want to hit or stand?");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("hit")) {
                Card newCard = deck.dealCard();
                userHand.add(newCard);
                System.out.println("You drew a " + newCard);
                int handValue = calculateHandValue(userHand);
                if (handValue > BLACKJACK_VALUE) {
                    System.out.println("BUST! Your hand value is " + handValue);
                    userBust = true;
                    userTurn = false;
                } else {
                    System.out.println("Your hand: " + userHand);
                }
            } else {
                userTurn = false;
            }
        }

        // Dealer's turn
        if (!userBust) {
            dealerHand.add(deck.dealCard());
            System.out.println("Dealer's hand: " + dealerHand);

            while (calculateHandValue(dealerHand) < DEALER_HIT_THRESHOLD) {
                Card newCard = deck.dealCard();
                dealerHand.add(newCard);
                System.out.println("Dealer drew a " + newCard);
                System.out.println("Dealer's hand: " + dealerHand);
            }

            int userHandValue = calculateHandValue(userHand);
            int dealerHandValue = calculateHandValue(dealerHand);

            if (dealerHandValue > BLACKJACK_VALUE) {
                System.out.println("Dealer BUSTED! You win!");
            } else if (dealerHandValue > userHandValue) {
                System.out.println("Dealer wins with a hand value of " + dealerHandValue);
            } else if (userHandValue > dealerHandValue) {
                System.out.println("You win with a hand value of " + userHandValue);
            } else {
                System.out.println("It's a tie!");
            }
        }
    }

    private static int calculateHandValue(ArrayList<Card> hand) {
        int handValue = 0;
        int aceCount = 0;
        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                aceCount++;
                handValue += ACE_VALUE;
            } else if (card.getRank().getValue() >= 10) {
                handValue += FACE_CARD_VALUE;
            } else {
                handValue += card.getRank().getValue();
            }
        }
        while (handValue > BLACKJACK_VALUE && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }
        return handValue;
    }

    private static class Deck {
        private final ArrayList<Card> cards;
        private int nextCardIndex;

        public Deck() {
            cards = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
            nextCardIndex = 0;
        }

        public void shuffle() {
            Collections.shuffle(cards);
            nextCardIndex = 0;
        }

        public Card dealCard() {
            Card card = cards.get(nextCardIndex);
            nextCardIndex++;
            return card;
        }
    }

    private static class Card {
        private final Rank rank;
        private final Suit suit;

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

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    private enum Rank {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private enum Suit {
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES
    }
}