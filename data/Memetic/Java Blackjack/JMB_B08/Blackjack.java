package B8;

import java.util.*;

enum Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES
}

class Card {
    private final Suit suit;
    private final String value;
    private final int score;

    public Card(Suit suit, String value, int score) {
        this.suit = suit;
        this.value = value;
        this.score = score;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return value + " of " + suit.toString();
    }
}

class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                cards.add(new Card(suit, Integer.toString(i), i));
            }
            cards.add(new Card(suit, "Jack", 10));
            cards.add(new Card(suit, "Queen", 10));
            cards.add(new Card(suit, "King", 10));
            cards.add(new Card(suit, "Ace", 11));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(0);
    }
}

class Player {
    private List<Card> hand;

    public Player() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        int score = 0;
        int numAces = 0;
        for (Card card : hand) {
            score += card.getScore();
            if (card.getValue().equals("Ace")) {
                numAces++;
            }
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }
}

class Gameer {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private boolean gameOver;

    public Gameer() {
        deck = new Deck();
        player = new Player();
        dealer = new Player();
        deck.shuffle();
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        gameOver = false;
    }

    public void playerHit() {
        if (!gameOver) {
            Card card = deck.dealCard();
            player.addCard(card);
            System.out.println("Player drew: " + card.toString());
            if (player.getScore() > 21) {
                System.out.println("Player busts! Dealer wins!");
                gameOver = true;
            }
        }
    }


    public void playerStand() {
        if (!gameOver) {
            while (dealer.getScore() < 17) {
                dealer.addCard(deck.dealCard());
            }
            if (dealer.getScore() > 21 || player.getScore() > dealer.getScore()) {
                System.out.println("Player wins!");
            } else if (player.getScore() < dealer.getScore()) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("It's a tie!");
            }
            gameOver = true;
        }
    }

    public void play() {
        while (!gameOver) {
            System.out.println("Player's hand: ");
            for (Card card : player.getHand()) {
                System.out.println(card.toString());
            }
            System.out.println("Player's score: " + player.getScore());
            System.out.println("Dealer's hand: ");
            System.out.println(dealer.getHand().get(0).toString());
            System.out.println("Dealer's score: " + dealer.getHand().get(0).getScore());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 'hit' or 'stand': ");
            String input = scanner.nextLine();
            if (input.equals("hit")) {
                playerHit();
            } else if (input.equals("stand")) {
                playerStand();
            }
        }
    }
}

public class Blackjack {
    public static void main(String[] args) {
        Gameer game = new Gameer();
        game.play();
    }
}