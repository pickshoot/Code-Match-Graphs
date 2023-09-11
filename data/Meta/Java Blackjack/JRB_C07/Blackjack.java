package JRB_C07;
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerBalance = 100;
        System.out.println("Welcome to Blackjack! You have 100 credits to start.");

        while (playerBalance > 0) {
            System.out.println("How much would you like to bet?");
            int betAmount = scanner.nextInt();

            int playerHandValue = 0;
            int dealerHandValue = 0;
            int playerCardCount = 0;
            int dealerCardCount = 0;
            boolean playerBusted = false;
            boolean dealerBusted = false;

            int[] playerCards = new int[11];
            int[] dealerCards = new int[11];

            // Deal initial cards
            playerCards[playerCardCount++] = random.nextInt(10) + 1;
            playerCards[playerCardCount++] = random.nextInt(10) + 1;
            playerHandValue = playerCards[0] + playerCards[1];

            dealerCards[dealerCardCount++] = random.nextInt(10) + 1;
            dealerHandValue = dealerCards[0];

            // Player turn
            while (true) {
                System.out.println("Your hand: " + playerHandValue);
                System.out.println("Dealer's hand: " + dealerCards[0]);
                System.out.println("Do you want to hit or stand? (h/s)");
                String choice = scanner.next();

                if (choice.equals("h")) {
                    playerCards[playerCardCount++] = random.nextInt(10) + 1;
                    playerHandValue += playerCards[playerCardCount - 1];

                    if (playerHandValue > 21) {
                        System.out.println("You busted with " + playerHandValue + "!");
                        playerBusted = true;
                        break;
                    }
                } else {
                    break;
                }
            }

            // Dealer turn
            while (!playerBusted && dealerHandValue < 17) {
                dealerCards[dealerCardCount++] = random.nextInt(10) + 1;
                dealerHandValue += dealerCards[dealerCardCount - 1];

                if (dealerHandValue > 21) {
                    System.out.println("Dealer busted with " + dealerHandValue + "!");
                    dealerBusted = true;
                }
            }

            // Compare hands and adjust balance
            if (!playerBusted && !dealerBusted) {
                System.out.println("Your hand: " + playerHandValue);
                System.out.println("Dealer's hand: " + dealerHandValue);

                if (playerHandValue > dealerHandValue) {
                    System.out.println("You win!");
                    playerBalance += betAmount;
                } else if (dealerHandValue > playerHandValue) {
                    System.out.println("Dealer wins!");
                    playerBalance -= betAmount;
                } else {
                    System.out.println("It's a tie!");
                }
            }

            // Check if game is over
            if (playerBalance <= 0) {
                System.out.println("You're out of credits! Thanks for playing!");
                break;
            } else {
                System.out.println("Your balance: " + playerBalance);
                System.out.println("Do you want to play again? (y/n)");
                String playAgain = scanner.next();

                if (playAgain.equals("n")) {
                    System.out.println("Thanks for playing!");
                    break;
                }
            }
        }
    }
}
