package C10;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_1_SYMBOL = 'X';
    private static final char PLAYER_2_SYMBOL = 'O';

    private char[][] board;
    private char currentPlayerSymbol;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayerSymbol = PLAYER_1_SYMBOL;
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i != BOARD_SIZE - 1) {
                System.out.println("\n---+---+---");
            }
        }
        System.out.println();
    }

    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("Invalid move: row and column numbers must be between 0 and " + (BOARD_SIZE - 1));
            return false;
        }
        if (board[row][col] != EMPTY_CELL) {
            System.out.println("Invalid move: cell already occupied");
            return false;
        }
        board[row][col] = currentPlayerSymbol;
        return true;
    }

    private boolean hasWinner() {
        // check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        // check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] != EMPTY_CELL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        // check diagonals
        if (board[1][1] != EMPTY_CELL && ((board[0][0] == board[1][1] && board[1][1] == board[2][2])
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0]))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        if (currentPlayerSymbol == PLAYER_1_SYMBOL) {
            currentPlayerSymbol = PLAYER_2_SYMBOL;
        } else {
            currentPlayerSymbol = PLAYER_1_SYMBOL;
        }
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            resetBoard();
            System.out.println("New game!");
            displayBoard();
            boolean gameEnded = false;
            while (!gameEnded) {
                System.out.println("Player " + currentPlayerSymbol + ", enter row and column number:");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (makeMove(row, col)) {
                    displayBoard();
                    if (hasWinner()) {
                        System.out.println("Player " + currentPlayerSymbol + " wins!");
                        gameEnded = true;
                    } else if (isBoardFull()) {
                        System.out.println("It's a tie!");
                        gameEnded = true;
                    } else {
                        switchPlayer();
                    }
                }
            }
            System.out.println("Do you want to play again? (y/n)");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}