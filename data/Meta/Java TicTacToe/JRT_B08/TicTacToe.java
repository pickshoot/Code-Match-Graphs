package B8;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        char currentPlayer = 'X';
        int movesLeft = 9;
        Scanner input = new Scanner(System.in);

        initializeBoard(board);

        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn");
            displayBoard(board);

            int row, col;
            do {
                System.out.print("Enter row (1-3): ");
                row = input.nextInt() - 1;
                System.out.print("Enter column (1-3): ");
                col = input.nextInt() - 1;
            } while (!isValidMove(board, row, col));

            board[row][col] = currentPlayer;
            movesLeft--;

            if (hasWon(board, currentPlayer)) {
                displayBoard(board);
                System.out.println("Player " + currentPlayer + " has won!");
                break;
            } else if (movesLeft == 0) {
                displayBoard(board);
                System.out.println("It's a tie!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        input.close();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("-------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move. Row and column must be between 1 and 3.");
            return false;
        } else if (board[row][col] != '-') {
            System.out.println("Invalid move. That space is already occupied.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean hasWon(char[][] board, char player) {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        } else if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}