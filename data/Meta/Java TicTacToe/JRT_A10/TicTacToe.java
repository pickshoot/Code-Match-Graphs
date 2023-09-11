package A10;

import java.util.Scanner;

public class TicTacToe {
    // Define constants for the game board
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char[] PLAYERS = { 'X', 'O' };

    private char[][] board; // 2D array to represent the game board
    private int currentPlayerIndex; // index of the current player in PLAYERS array

    // Constructor to initialize the game board and the current player
    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayerIndex = 0;
    }

    // Method to display the game board
    public void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to get the move from the current player
    public void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + PLAYERS[currentPlayerIndex] + ", enter your move (row[1-3] column[1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));
        board[row][col] = PLAYERS[currentPlayerIndex];
    }

    // Method to check if the move is valid
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        if (board[row][col] != EMPTY) {
            return false;
        }
        return true;
    }

    // Method to switch the current player
    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % PLAYERS.length;
    }

    // Method to check if the game is over (either a player wins or the game is
    // tied)
    public boolean isGameOver() {
        if (checkWin(PLAYERS[currentPlayerIndex])) {
            System.out.println("Player " + PLAYERS[currentPlayerIndex] + " wins!");
            return true;
        }
        if (isTie()) {
            System.out.println("Game is tied!");
            return true;
        }
        return false;
    }

    // Method to check if the current player wins
    public boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Method to check if the game is tied
    public boolean isTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to reset the game board for a new game
    public void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayerIndex = 0;
    }

    // Main method to start the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean playAgain = true;

        while (playAgain) {
            // Play the game
            System.out.println("Let's play Tic-Tac-Toe!");
            game.displayBoard();
            while (!game.isGameOver()) {
                game.getPlayerMove();
                game.displayBoard();
                game.switchPlayer();
            }
            // Ask if the players want to play again
            System.out.print("Do you want to play again? (y/n): ");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
            // Reset the game board for a new game
            game.resetBoard();
        }
        System.out.println("Thanks for playing!");
    }
}