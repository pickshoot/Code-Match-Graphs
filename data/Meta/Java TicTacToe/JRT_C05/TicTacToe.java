package C5;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            char[][] board = new char[3][3];
            int turn = 1;
            boolean gameWon = false;
            char winner = ' ';

            initializeBoard(board);

            while (!gameWon) {
                int row = -1, col = -1;

                printBoard(board);

                while (!isValidMove(board, row, col)) {
                    System.out.print("Player " + turn + ", enter row and column number: ");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (!isValidMove(board, row, col)) {
                        System.out.println("Invalid move. Please enter a valid move.");
                    }
                }

                board[row][col] = (turn == 1) ? 'X' : 'O';

                if (checkWin(board)) {
                    gameWon = true;
                    winner = (turn == 1) ? 'X' : 'O';
                } else if (checkTie(board)) {
                    gameWon = true;
                }

                turn = (turn == 1) ? 2 : 1;
            }

            printBoard(board);

            if (winner != ' ') {
                System.out.println("Player " + winner + " wins!");
            } else {
                System.out.println("Game ended in a tie.");
            }

            System.out.print("Do you want to play again? (Y/N) ");
            String response = scanner.next();

            if (response.toUpperCase().equals("N")) {
                playAgain = false;
            }
        }

        scanner.close();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");

            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }

            System.out.println("\n-------------");
        }
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
            return false;
        }

        if (board[row][col] != ' ') {
            return false;
        }

        return true;
    }

    public static boolean checkWin(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }

            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    public static boolean checkTie(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}