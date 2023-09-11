package B9;

import java.util.*;

public class Blackjack {
    private final int BLACKJACK_VALUE = 21;
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private Scanner scanner;

    public Blackjack() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        deck.shuffle();

        // Deal the initial two cards to the player and dealer
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());

        // Print the player's hand and the dealer's upcard
        System.out.println("Player's hand: " + playerHand);
        System.out.println("Dealer's upcard: " + dealerHand.get(0));

        // Player's turn
        while (getPlayerHandValue() < BLACKJACK_VALUE) {
            System.out.print("Hit or stand? (h/s): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("h")) {
                playerHand.add(deck.drawCard());
                System.out.println("Player's hand: " + playerHand);
            } else if (choice.equals("s")) {
                break;
            }
        }

        // Dealer's turn
        while (getDealerHandValue() < 17) {
            dealerHand.add(deck.drawCard());
            System.out.println("Dealer's hand: " + dealerHand);
        }

        // Determine the winner
        int playerValue = getPlayerHandValue();
        int dealerValue = getDealerHandValue();
        if (playerValue > BLACKJACK_VALUE) {
            System.out.println("Player busts! Dealer wins!");
        } else if (dealerValue > BLACKJACK_VALUE) {
            System.out.println("Dealer busts! Player wins!");
        } else if (playerValue > dealerValue) {
            System.out.println("Player wins!");
        } else if (dealerValue > playerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private int getPlayerHandValue() {
        return getHandValue(playerHand);
    }

    private int getDealerHandValue() {
        return getHandValue(dealerHand);
    }

    private int getHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                numAces++;
            } else {
                value += card.getValue();
            }
        }

        for (int i = 0; i < numAces; i++) {
            if (value + 11 > BLACKJACK_VALUE) {
                value += 1;
            } else {
                value += 11;
            }
        }

        return value;
    }

    public static void main(String[] args) {
    	Blackjack game = new Blackjack();
        game.startGame();
    }
}

class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public int getValue() {
        switch (rank) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
            default:
                throw new IllegalStateException("Invalid rank: " + rank);
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;
    private int nextCardIndex;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        nextCardIndex = 0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
        nextCardIndex = 0;
    }

    public Card drawCard() {
        if (nextCardIndex >= cards.size()) {
            throw new IllegalStateException("No more cards in deck");
        }
        return cards.get(nextCardIndex++);
    }
}

enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES
}

enum Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}