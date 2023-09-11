package A1;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer;
    private static boolean gameFinished;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            startGame();
            playGame(scanner);
            System.out.println("Do you want to play again? (y/n)");
            String input = scanner.nextLine().trim().toLowerCase();
            if (!input.equals("y")) {
                break;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void startGame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        gameFinished = false;
        printBoard();
    }

    private static void playGame(Scanner scanner) {
        while (!gameFinished) {
            System.out.print(currentPlayer + "'s turn. Enter row (1-3) and column (1-3): ");
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");
            if (coordinates.length != 2) {
                System.out.println("Invalid input. Please enter row and column separated by a space.");
                continue;
            }
            int row, col;
            try {
                row = Integer.parseInt(coordinates[0]) - 1;
                col = Integer.parseInt(coordinates[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter row and column as integers.");
                continue;
            }
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input. Row and column must be between 1 and 3.");
                continue;
            }
            if (board[row][col] != ' ') {
                System.out.println("Invalid move. This cell is already occupied.");
                continue;
            }
            board[row][col] = currentPlayer;
            printBoard();
            checkForWinner();
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
    }

    private static void printBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("  -----");
            }
        }
    }

    private static void checkForWinner() {
        for (int i = 0; i < board.length; i++) {
            // check row
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                System.out.println(currentPlayer + " wins!");
                gameFinished = true;
                return;
            }
            // check column
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                System.out.println(currentPlayer + " wins!");
                gameFinished = true;
                return;
            }

            // check diagonal
            if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                System.out.println(currentPlayer + " wins!");
                gameFinished = true;
                return;
            }
            // check reverse diagonal
            if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                System.out.println(currentPlayer + " wins!");
                gameFinished = true;
                return;
            }
            // check for tie
            boolean tie = true;
            for (int k = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[k][j] == ' ') {
                        tie = false;
                        break;
                    }
                }
            }
            if (tie) {
                System.out.println("It's a tie!");
                gameFinished = true;
            }
        }
    }
}