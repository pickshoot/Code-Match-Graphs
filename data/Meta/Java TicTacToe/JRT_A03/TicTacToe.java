package A3;

import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static char[][] board = new char[ROWS][COLUMNS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isPlaying = true;
        while (isPlaying) {
            initializeBoard();
            displayBoard();

            while (!isGameOver()) {
                System.out.println(currentPlayer + "'s turn");
                int row = getValidInput("Enter row (1-3): ", scanner);
                int column = getValidInput("Enter column (1-3): ", scanner);

                if (board[row - 1][column - 1] != ' ') {
                    System.out.println("That cell is already occupied. Try again.");
                    continue;
                }

                board[row - 1][column - 1] = currentPlayer;
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

                displayBoard();
            }

            char winner = getWinner();
            if (winner != ' ') {
                System.out.println(winner + " wins!");
            } else {
                System.out.println("It's a tie!");
            }

            System.out.print("Do you want to play again? (Y/N): ");
            String answer = scanner.next();
            isPlaying = answer.equalsIgnoreCase("Y");
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isGameOver() {
        return isBoardFull() || getWinner() != ' ';
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static char getWinner() {
        // Check rows
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < COLUMNS; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        return ' ';
    }

    private static int getValidInput(String message, Scanner scanner) {
        int input;
        do {
            System.out.print(message);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. " + message);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < 1 || input > 3);
        return input;
    }
}