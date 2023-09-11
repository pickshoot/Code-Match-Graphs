package B2;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        initializeBoard();

        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;
        boolean gameEnded = false;

        while (!gameEnded) {
            char playerSymbol = player1Turn ? 'X' : 'O';
            int[] move = getValidMove(scanner, playerSymbol);

            int row = move[0];
            int col = move[1];

            board[row][col] = playerSymbol;
            printBoard();

            if (hasWon(playerSymbol)) {
                System.out.println("Player " + playerSymbol + " has won!");
                gameEnded = true;
            } else if (isTie()) {
                System.out.println("The game has ended in a tie.");
                gameEnded = true;
            }

            player1Turn = !player1Turn;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private static int[] getValidMove(Scanner scanner, char playerSymbol) {
        int row, col;
        do {
            System.out.print("Player " + playerSymbol + ", enter your move (row[1-3] column[1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));

        return new int[] { row, col };
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("Invalid move! Row and column numbers must be between 1 and " + BOARD_SIZE);
            return false;
        }
        if (board[row][col] != EMPTY_CELL) {
            System.out.println("Invalid move! Cell [" + (row + 1) + ", " + (col + 1) + "] is already occupied.");
            return false;
        }
        return true;
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean hasWon(char playerSymbol) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol) {
                return true; // check rows
            }
            if (board[0][i] == playerSymbol && board[1][i] == playerSymbol && board[2][i] == playerSymbol) {
                return true; // check columns
            }
        }
        if (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) {
            return true; // check the diagonal cells
        }
        if (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol) {
            return true; // check diagonal cells
        }
        return false;
    }

    private static boolean isTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false; // if there is any empty cell on the board, the game is not tied yet
                }
            }
        }
        return true;
    }
}