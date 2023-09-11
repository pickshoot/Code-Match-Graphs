package C1;

import java.util.Scanner;

public class TicTacToe {
    // The game board
    private char[][] board = new char[3][3];
    // The current player ('X' or 'O')
    private char currentPlayer = 'X';

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain) {
            game.resetBoard();
            game.printBoard();

            boolean gameFinished = false;
            while (!gameFinished) {
                // Prompt the player to enter their move
                System.out.print("Player " + game.currentPlayer + ", enter row and column number: ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Validate the move
                if (!game.isValidMove(row, col)) {
                    System.out.println("Invalid move. Please try again.");
                    continue;
                }

                // Make the move
                game.makeMove(row, col);
                game.printBoard();

                // Check if the game is over
                if (game.checkWin()) {
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    gameFinished = true;
                } else if (game.checkTie()) {
                    System.out.println("Game over. It's a tie!");
                    gameFinished = true;
                } else {
                    // Switch to the other player
                    game.switchPlayer();
                }
            }

            // Prompt the players to play again or quit
            System.out.print("Play again? (Y/N): ");
            String choice = scanner.next().toUpperCase();
            playAgain = choice.equals("Y");
        }

        scanner.close();
    }

    // Reset the game board
    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
        currentPlayer = 'X';
    }

    // Print the game board
    private void printBoard() {
        System.out.println("   0 1 2");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + "  ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Check if a move is valid
    private boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }

    // Make a move
    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    // Check if the game has been won
    private boolean checkWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Check if the game is a tie
    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch to the other player
    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}