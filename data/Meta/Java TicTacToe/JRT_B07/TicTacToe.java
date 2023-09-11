package B7;

import java.util.Scanner;

public class TicTacToe {
    // Declare variables to keep track of the game state
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameEnded = false;

    // Initialize the board with empty cells
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Prompt the current player to make a move and update the board
    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);

        int row;
        int col;

        do {
            System.out.print("Enter the row (0, 1, or 2) and column (0, 1, or 2) where you want to place your piece: ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input. Please enter a row and column within the range of 0 to 2.");
            } else if (board[row][col] != '-') {
                System.out.println("That cell is already occupied. Please try again.");
            } else {
                board[row][col] = currentPlayer;
                break;
            }
        } while (true);
    }

    // Check if the game has ended and declare the winner if one is found
    private static void checkForWinner() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                gameEnded = true;
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }

            // Check columns
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                gameEnded = true;
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            gameEnded = true;
            System.out.println("Player " + currentPlayer + " wins!");
            return;
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            gameEnded = true;
            System.out.println("Player " + currentPlayer + " wins!");
            return;
        }

        // Check if the board is full and declare a tie if no winner is found
        gameEnded = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    gameEnded = false;
                    break;
                }
            }
        }

        if (gameEnded) {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        initializeBoard();

        // Loop until the game ends
        while (!gameEnded) {
            // Print the current state of the board
            printBoard();

            // Prompt the current player to make a move
            System.out.println("Player " + currentPlayer + "'s turn:");
            makeMove();

            // Check if the game has ended
            checkForWinner();

            // Switch to the other player
            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }

        // Print the final state of the board
        printBoard();
    }
}