package JRB_C04;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private static final int BLACKJACK = 21;
    private static final int DEALER_MIN_HAND_VALUE = 17;
    private static final int INITIAL_BALANCE = 100;

    private static List<Card> deck;
    private static Scanner scanner;
    private static int playerBalance;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        playerBalance = INITIAL_BALANCE;
        while (true) {
            System.out.println("Balance: " + playerBalance);
            System.out.print("Enter bet amount: ");
            int betAmount = scanner.nextInt();
            if (betAmount > playerBalance) {
                System.out.println("Bet amount exceeds balance. Try again.");
                continue;
            }
            boolean playerWins = playGame(betAmount);
            if (playerWins) {
                playerBalance += betAmount;
            } else {
                playerBalance -= betAmount;
            }
            if (playerBalance == 0) {
                System.out.println("You are out of money. Game over.");
                break;
            }
            System.out.print("Play again? (Y/N) ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }
        scanner.close();
    }

    private static boolean playGame(int betAmount) {
        deck = createDeck();
        Collections.shuffle(deck);
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        dealCardTo(playerHand);
        dealCardTo(playerHand);
        dealCardTo(dealerHand);
        System.out.println("Your hand: " + playerHand);
        System.out.println("Dealer's hand: " + dealerHand.getVisibleCard());
        boolean playerTurn = true;
        while (true) {
            if (playerTurn) {
                System.out.print("Hit or stand? (H/S) ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("H")) {
                    dealCardTo(playerHand);
                    System.out.println("Your hand: " + playerHand);
                    if (playerHand.getHandValue() > BLACKJACK) {
                        System.out.println("Busted! Dealer wins.");
                        return false;
                    }
                } else {
                    playerTurn = false;
                }
            } else {
                while (dealerHand.getHandValue() < DEALER_MIN_HAND_VALUE) {
                    dealCardTo(dealerHand);
                }
                System.out.println("Dealer's hand: " + dealerHand);
                if (dealerHand.getHandValue() > BLACKJACK) {
                    System.out.println("Dealer busted! You win.");
                    return true;
                } else if (dealerHand.getHandValue() >= playerHand.getHandValue()) {
                    System.out.println("Dealer wins.");
                    return false;
                } else {
                    System.out.println("You win!");
                    return true;
                }
            }
        }
    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }

    private static void dealCardTo(Hand hand) {
        Card card = deck.remove(0);
        hand.addCard(card);
    }
}

enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
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

    public String toString() {
        return name().toLowerCase();
    }
}

class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return rank.getValue();
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

class Hand {
    private final List<Card> cards;

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
            if (card.getValue() == 1) {
                numAces++;
                value += 11;
            } else if (card.getValue() >= 10) {
                value += 10;
            } else {
                value += card.getValue();
            }
        }
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }

    public String toString() {
        return cards.toString() + " (" + getHandValue() + ")";
    }

    public String getVisibleCard() {
        return cards.get(0).toString() + " and one hidden card";
    }
}

