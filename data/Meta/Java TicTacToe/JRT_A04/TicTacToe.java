package A4;

import java.util.Scanner;

public class TicTacToe {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY = ' ';

    private char[][] board = new char[3][3];
    private char currentPlayer = PLAYER_X;
    private boolean gameEnded = false;

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to Tic Tac Toe!");

        initializeBoard();
        printBoard();

        while (!gameEnded) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            int row = getRow();
            int col = getCol();

            if (board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                printBoard();

                if (hasWon()) {
                    System.out.println("Player " + currentPlayer + " has won!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("That cell is already taken. Please try again.");
            }
        }

        System.out.println("Thanks for playing!");
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i != 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private int getRow() {
        System.out.print("Enter row (0-2): ");
        int row = scanner.nextInt();
        while (row < 0 || row > 2) {
            System.out.println("Invalid row. Please try again.");
            System.out.print("Enter row (0-2): ");
            row = scanner.nextInt();
        }
        return row;
    }

    private int getCol() {
        System.out.print("Enter column (0-2): ");
        int col = scanner.nextInt();
        while (col < 0 || col > 2) {
            System.out.println("Invalid column. Please try again.");
            System.out.print("Enter column (0-2): ");
            col = scanner.nextInt();
        }
        return col;
    }

    private boolean hasWon() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}