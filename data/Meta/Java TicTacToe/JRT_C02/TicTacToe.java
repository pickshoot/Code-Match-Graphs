package C2;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain) {
            initBoard();
            playGame();

            System.out.println("Do you want to play again? (y/n)");
            String input = scanner.next().toLowerCase();

            if (!input.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
    }

    private static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void playGame() {
        boolean gameEnded = false;
        int row, col;

        while (!gameEnded) {
            displayBoard();
            row = getRow();
            col = getCol();

            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                gameEnded = checkForWin() || checkForTie();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("That cell is already taken! Please try again.");
            }
        }

        displayBoard();

        if (checkForWin()) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static void displayBoard() {
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

    private static int getRow() {
        int row;

        do {
            System.out.print("Player " + currentPlayer + ", enter row number (0-2): ");
            row = scanner.nextInt();
        } while (row < 0 || row > 2);

        return row;
    }

    private static int getCol() {
        int col;

        do {
            System.out.print("Player " + currentPlayer + ", enter column number (0-2): ");
            col = scanner.nextInt();
        } while (col < 0 || col > 2);

        return col;
    }

    private static boolean checkForWin() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
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
}