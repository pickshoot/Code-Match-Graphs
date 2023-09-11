package C3;

import java.util.Scanner;

public class TicTacToe {
    // define the game board
    private static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            char currentPlayer = 'X';
            boolean gameWon = false;

            while (!gameWon) {
                displayBoard();
                int[] move = getValidMove(scanner, currentPlayer);
                int row = move[0];
                int col = move[1];

                // update the board with the move
                board[row][col] = currentPlayer;

                // check if the game has been won
                gameWon = checkWin();

                // switch to the other player
                if (!gameWon) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // display the final board and the winner or tie message
            displayBoard();
            System.out.println("Game Over!");
            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("Tie game!");
            }

            // ask if the players want to play again
            System.out.print("Play again? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            playAgain = answer.equals("y");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("   0 1 2");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getValidMove(Scanner scanner, char player) {
        int[] move = new int[2];
        boolean validMove = false;

        while (!validMove) {
            System.out.print("Player " + player + ", enter row and column number: ");
            String[] input = scanner.nextLine().split(" ");
            if (input.length != 2) {
                System.out.println("Invalid input. Enter two numbers separated by a space.");
                continue;
            }

            try {
                move[0] = Integer.parseInt(input[0]);
                move[1] = Integer.parseInt(input[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter two numbers separated by a space.");
                continue;
            }

            if (move[0] < 0 || move[0] > 2 || move[1] < 0 || move[1] > 2) {
                System.out.println("Invalid move. Enter numbers between 0 and 2.");
                continue;
            }

            if (board[move[0]][move[1]] != '-') {
                System.out.println("Cell already occupied. Enter a valid move.");
                continue;
            }

            validMove = true;
        }

        return move;
    }

    private static boolean checkWin() {
        // check rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // check columns
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        // check diagonals
        if (board[1][1] != '-') {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return true;
            }
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return true;
            }
        }

        return false;
    }
}