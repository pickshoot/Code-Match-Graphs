package C7;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static int turns = 0;
    private static boolean gameover = false;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (!gameover) {
            initializeBoard();
            turns = 0;
            System.out.println("New game! Player 1 is X and Player 2 is O.");

            while (!gameover) {
                displayBoard();
                int player = (turns % 2 == 0) ? 1 : 2;
                System.out.print("Player " + player + ", enter row and column number (e.g. 1 1): ");
                int row = input.nextInt() - 1;
                int col = input.nextInt() - 1;

                if (isValidMove(row, col)) {
                    makeMove(row, col, player);
                    if (checkWin()) {
                        displayBoard();
                        System.out.println("Player " + player + " wins!");
                        gameover = true;
                    } else if (turns == 8) {
                        displayBoard();
                        System.out.println("It's a tie!");
                        gameover = true;
                    }
                    turns++;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            String answer = input.next();
            if (answer.equals("n")) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            gameover = false;
        }
    }

    private static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Current board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }

    private static void makeMove(int row, int col, int player) {
        if (player == 1) {
            board[row][col] = 'X';
        } else {
            board[row][col] = 'O';
        }
    }

    private static boolean checkWin() {
        // check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-') {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-') {
                return true;
            }
        }

        // check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true;
        }

        return false;
    }
}