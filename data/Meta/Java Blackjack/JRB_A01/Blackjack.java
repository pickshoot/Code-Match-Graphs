package A1;

import java.util.*;

public class Blackjack {
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
    private static final int BLACKJACK = 21;
    private static final int DEALER_STANDS_AT = 17;
    private static final int INITIAL_CARDS = 2;

    private final Scanner scanner;
    private List<String> deck;
    private List<String> userHand;
    private List<String> dealerHand;
    private int userScore;
    private int dealerScore;

    public Blackjack() {
        scanner = new Scanner(System.in);
        deck = new ArrayList<>(Arrays.asList(RANKS));
        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        userScore = 0;
        dealerScore = 0;
    }

    public void play() {
        shuffleDeck();
        dealInitialCards();
        printHands();
        playUserTurn();
        playDealerTurn();
        determineWinner();
        askForNewGame();
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void dealInitialCards() {
        for (int i = 0; i < INITIAL_CARDS; i++) {
            userHand.add(drawCard());
            dealerHand.add(drawCard());
        }
        dealerScore = getValue(dealerHand.get(0));
    }

    private String drawCard() {
        String card = deck.get(0);
        deck.remove(0);
        return card;
    }

    private int getValue(String card) {
        int index = Arrays.asList(RANKS).indexOf(card);
        return VALUES[index];
    }

    private void printHands() {
        System.out.println("Your hand: " + userHand);
        System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", ?]");
    }

    private void playUserTurn() {
        while (userScore < BLACKJACK) {
            System.out.print("Do you want to hit or stand? ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("hit")) {
                userHand.add(drawCard());
                userScore += getValue(userHand.get(userHand.size() - 1));
                System.out.println("Your hand: " + userHand);
                if (userScore >= BLACKJACK) {
                    break;
                }
            } else if (input.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        System.out.println("Your final score: " + userScore);
    }

    private void playDealerTurn() {
        System.out.println("Dealer's hand: " + dealerHand);
        while (dealerScore < DEALER_STANDS_AT) {
            dealerHand.add(drawCard());
            dealerScore += getValue(dealerHand.get(dealerHand.size() - 1));
            System.out.println("Dealer hits, new card: " + dealerHand.get(dealerHand.size() - 1));
        }
        System.out.println("Dealer's final score: " + dealerScore);
    }

    private void determineWinner() {
        if (userScore > BLACKJACK) {
            System.out.println("You bust, dealer wins!");
        } else if (dealerScore > BLACKJACK) {
            System.out.println("Dealer busts, you win!");
        } else if (userScore > dealerScore) {
            System.out.println("You win!");
        } else if (dealerScore > userScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void askForNewGame() {
        System.out.print("Do you want to play again? ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            resetGame();
            play();
        } else {
            System.out.println("Thanks for playing!");
            scanner.close();
        }
    }

    private void resetGame() {
        deck = new ArrayList<>(Arrays.asList(RANKS));
        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        userScore = 0;
        dealerScore = 0;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        Blackjack game = new Blackjack();
        game.play();
    }
}