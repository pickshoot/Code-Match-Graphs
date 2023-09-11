package C8;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_ONE_MARK = 'X';
    private static final char PLAYER_TWO_MARK = 'O';

    private char[][] board;
    private boolean playerOneTurn;
    private boolean gameOver;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
        playerOneTurn = true;
        gameOver = false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            // Display the current board
            displayBoard();

            // Prompt the current player to enter their move
            System.out.println((playerOneTurn ? "Player 1" : "Player 2") + ", enter row and column number:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Check if the move is valid
            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Please try again.");
                continue;
            }

            // Update the board with the current player's move
            char mark = (playerOneTurn ? PLAYER_ONE_MARK : PLAYER_TWO_MARK);
            board[row][col] = mark;

            // Check if the game is over
            if (hasWon(mark)) {
                gameOver = true;
                displayBoard();
                System.out.println((playerOneTurn ? "Player 1" : "Player 2") + " wins!");
                continue;
            } else if (isTie()) {
                gameOver = true;
                displayBoard();
                System.out.println("Tie game.");
                continue;
            }

            // Switch to the other player's turn
            playerOneTurn = !playerOneTurn;
        }

        // Ask the players if they want to play again
        System.out.println("Play again? (y/n)");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            // Start a new game
            new TicTacToe().play();
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private void displayBoard() {
        System.out.println();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col < BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < BOARD_SIZE - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        if (board[row][col] != EMPTY_CELL) {
            return false;
        }
        return true;
    }

    private boolean hasWon(char mark) {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == mark && board[row][1] == mark && board[row][2] == mark) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == mark && board[1][col] == mark && board[2][col] == mark) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return true;
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return true;
        }

        return false;
    }

    private boolean isTie() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}