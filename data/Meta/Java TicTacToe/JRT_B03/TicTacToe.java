package B3;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

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

    private static void playGame() {
        boolean gameFinished = false;
        while (!gameFinished) {
            System.out.println("Player " + currentPlayer + ", enter your move (row[1-3], column[1-3]): ");
            int row = getRow();
            int col = getCol();
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                printBoard();
                if (checkForWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameFinished = true;
                } else if (checkForTie()) {
                    System.out.println("Game over. It's a tie!");
                    gameFinished = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("That space is already occupied. Please enter a valid move.");
            }
        }
    }

    private static int getRow() {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        while (row < 1 || row > 3) {
            System.out.println("Invalid row. Please enter a valid row (1-3): ");
            row = scanner.nextInt();
        }
        return row - 1;
    }

    private static int getCol() {
        Scanner scanner = new Scanner(System.in);
        int col = scanner.nextInt();
        while (col < 1 || col > 3) {
            System.out.println("Invalid column. Please enter a valid column (1-3): ");
            col = scanner.nextInt();
        }
        return col - 1;
    }

    private static boolean checkForWin() {
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private static boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonalsForWin() {
        return checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]);
    }

    private static boolean checkRowCol(char c1, char c2, char c3) {
        return c1 != '-' && c1 == c2 && c2 == c3;
    }

    private static boolean checkForTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}