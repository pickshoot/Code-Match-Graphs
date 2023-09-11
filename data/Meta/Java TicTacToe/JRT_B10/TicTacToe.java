package B10;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        while (true) {
            System.out.println("Current board:");
            printBoard();
            System.out.println(currentPlayer + "'s turn.");

            int row = -1;
            int col = -1;

            while (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.print("Enter row (0-2) and column (0-2) separated by a space: ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid input. Please try again.");
                } else if (board[row][col] != ' ') {
                    System.out.println("That spot is already occupied. Please choose a different spot.");
                    row = -1;
                    col = -1;
                }
            }

            board[row][col] = currentPlayer;

            if (hasWon()) {
                System.out.println("Current board:");
                printBoard();
                System.out.println(currentPlayer + " has won!");
                return;
            } else if (isBoardFull()) {
                System.out.println("Current board:");
                printBoard();
                System.out.println("The game is a tie!");
                return;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
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

    private boolean hasWon() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[2][0] != ' ' && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}