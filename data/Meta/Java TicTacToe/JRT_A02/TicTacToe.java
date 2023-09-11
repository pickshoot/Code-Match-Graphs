package A2;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] grid = new char[3][3]; // the game grid
    private static char player = 'X'; // starting player
    
    // prints the game grid
    private static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println();
            System.out.println("---------");
        }
    }
    
    // checks if a player has won the game
    private static boolean checkWin() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != ' ') {
                return true;
            }
        }
        // check columns
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j] && grid[0][j] != ' ') {
                return true;
            }
        }
        // check diagonals
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != ' ') {
            return true;
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != ' ') {
            return true;
        }
        return false;
    }
    
    // checks if the game is a tie
    private static boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // resets the game grid for a new game
    private static void resetGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            resetGrid();
            boolean gameOn = true;
            while (gameOn) {
                printGrid();
                System.out.println("Player " + player + ", enter row (1-3):");
                int row = scanner.nextInt() - 1;
                System.out.println("Player " + player + ", enter column (1-3):");
                int col = scanner.nextInt() - 1;
                if (grid[row][col] == ' ') {
                    grid[row][col] = player;
                    if (checkWin()) {
                        printGrid();
                        System.out.println("Player " + player + " wins!");
                        gameOn = false;
                    } else if (checkTie()) {
                        printGrid();
                        System.out.println("Tie game!");
                        gameOn = false;
                    } else {
                        player = (player == 'X') ? 'O' : 'X'; // switch players
                    }
                } else {
                    System.out.println("That spot is already taken!");
                }
            }
            System.out.println("Do you want to play again? (y/n)");
            String answer = scanner.next();
            playAgain = answer.equalsIgnoreCase("y");
        }
        scanner.close();
    }
}