package B1;

import java.util.Scanner;

public class TicTacToe {
    // Constants for the game board dimensions and player tokens
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X_TOKEN = 'X';
    private static final char PLAYER_O_TOKEN = 'O';

    // The game board
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    // The current player (X goes first)
    private static char currentPlayer = PLAYER_X_TOKEN;

    // The main game loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the game board
        initializeBoard();

        // Loop until the game is over
        while (!isGameOver()) {
            // Display the game board
            displayBoard();

            // Prompt the current player to enter their move
            System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            // Check if the move is valid
            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Please try again.");
                continue;
            }

            // Place the player's token on the board
            board[row][col] = currentPlayer;

            // Switch to the next player
            currentPlayer = (currentPlayer == PLAYER_X_TOKEN) ? PLAYER_O_TOKEN : PLAYER_X_TOKEN;
        }

        // Display the final game board
        displayBoard();

        // Declare the winner or a tie
        if (getWinner() == PLAYER_X_TOKEN) {
            System.out.println("Player X wins!");
        } else if (getWinner() == PLAYER_O_TOKEN) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("Tie game!");
        }

        scanner.close();
    }

    // Initializes the game board with empty squares
    private static void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Displays the game board
    private static void displayBoard() {
        System.out.println();
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print(" ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col]);
                if (col < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < BOARD_SIZE - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    // Checks if the specified row and column are valid and not already occupied
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ');
    }

    // Checks if the game is over (either a player has won or the board is full)
    private static boolean isGameOver() {
        return (getWinner() != ' ' || isBoardFull());
    }

    // Checks if the game board is full
    private static boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if a player has won the game
    private static char getWinner() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
                return board[row][0];
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
                return board[0][col];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        // No winner yet
        return ' ';
    }
}