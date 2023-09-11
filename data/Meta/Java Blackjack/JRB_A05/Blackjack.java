package A5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_MIN_VALUE = 17;

    private List<Card> deck;
    private List<Card> userHand;
    private List<Card> dealerHand;

    private int userScore;
    private int dealerScore;

    public Blackjack() {
        deck = new ArrayList<>();
        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }

        shuffleDeck();
    }

    private void shuffleDeck() {
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    private void dealInitialCards() {
        userHand.add(dealCard());
        dealerHand.add(dealCard());
        userHand.add(dealCard());
        dealerHand.add(dealCard());
    }

    private Card dealCard() {
        return deck.remove(0);
    }

    private int calculateHandScore(List<Card> hand) {
        int score = 0;
        boolean hasAce = false;

        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                hasAce = true;
            }
            score += card.getValue();
        }

        if (hasAce && score + 10 <= BLACKJACK_VALUE) {
            score += 10;
        }

        return score;
    }

    private void displayHands(boolean showDealerHand) {
        System.out.println("Your hand: " + userHand.toString());
        System.out.println("Your score: " + userScore);

        if (showDealerHand) {
            System.out.println("Dealer's hand: " + dealerHand.toString());
            System.out.println("Dealer's score: " + dealerScore);
        } else {
            System.out.println("Dealer's hand: [hidden card, " + dealerHand.get(1) + "]");
        }
    }

    private boolean isBust(List<Card> hand) {
        return calculateHandScore(hand) > BLACKJACK_VALUE;
    }

    private void playUserTurn(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to hit or stand? ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("hit")) {
                userHand.add(dealCard());
                userScore = calculateHandScore(userHand);
                displayHands(true);

                if (isBust(userHand)) {
                    System.out.println("Bust! You lose.");
                    return;
                }
            } else if (input.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private void playDealerTurn() {
        while (dealerScore < DEALER_MIN_VALUE) {
            dealerHand.add(dealCard());
            dealerScore = calculateHandScore(dealerHand);
        }

        displayHands(true);

        if (isBust(dealerHand)) {
            System.out.println("Dealer busts! You win.");
        } else if (userScore > dealerScore) {
            System.out.println("You win!");
        } else if (userScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Welcome to Blackjack!");
            dealInitialCards();
            userScore = calculateHandScore(userHand);
            dealerScore = calculateHandScore(dealerHand);
            displayHands(false);

            if (userScore == BLACKJACK_VALUE) {
                System.out.println("Blackjack! You win.");
            } else {
                playUserTurn(scanner);

                if (!isBust(userHand)) {
                    playDealerTurn();
                }
            }

            System.out.print("Do you want to play again? ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("yes")) {
                playAgain = false;
            }

            reset();
        }
    }

    private void reset() {
        deck.clear();
        userHand.clear();
        dealerHand.clear();
        userScore = 0;
        dealerScore = 0;
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        shuffleDeck();
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }
}

enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
    KING(10);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

class Card {
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

    public int getValue() {
        return rank.getValue();
    }

    public String toString() {
        return rank + " of " + suit;
    }
}