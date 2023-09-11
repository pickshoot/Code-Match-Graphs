package A9;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] grid = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeGrid();

        while (!gameOver) {
            displayGrid();
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] column [1-3]):");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (isValidMove(row, col)) {
                grid[row][col] = currentPlayer;
                if (checkWin()) {
                    displayGrid();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (checkTie()) {
                    displayGrid();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        System.out.println("Do you want to play again? (Y/N)");
        char response = scanner.next().charAt(0);
        if (response == 'Y' || response == 'y') {
            initializeGrid();
            gameOver = false;
            main(args);
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private static void initializeGrid() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = '-';
            }
        }
    }

    private static void displayGrid() {
        System.out.println("-------");
        for (int row = 0; row < 3; row++) {
            System.out.print("|");
            for (int col = 0; col < 3; col++) {
                System.out.print(grid[row][col] + "|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (grid[row][col] != '-') {
            return false;
        }
        return true;
    }

    private static boolean checkWin() {
        // check rows
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] != '-' && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]) {
                return true;
            }
        }
        // check columns
        for (int col = 0; col < 3; col++) {
            if (grid[0][col] != '-' && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]) {
                return true;
            }
        }
        // check diagonals
        if (grid[0][0] != '-' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return true;
        }
        if (grid[0][2] != '-' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return true;
        }
        // no win
        return false;
    }

    private static boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}