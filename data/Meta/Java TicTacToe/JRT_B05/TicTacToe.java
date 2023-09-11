package B5;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (!isGameOver()) {
            getPlayerMove();
            displayBoard();
            switchPlayer();
        }

        if (hasWinner()) {
            System.out.println("Congratulations, " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private static void displayBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("-----");
            }
        }
        System.out.println();
    }

    private static void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
            row = scanner.nextInt() - 1;
            System.out.print("Player " + currentPlayer + ", enter column (1-3): ");
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move. Row and column must be between 1 and 3.");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("Invalid move. That square is already occupied.");
            return false;
        }
        return true;
    }

    private static boolean isGameOver() {
        return hasWinner() || isBoardFull();
    }

    private static boolean hasWinner() {
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private static boolean checkRowsForWin() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnsForWin() {
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonalsForWin() {
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
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