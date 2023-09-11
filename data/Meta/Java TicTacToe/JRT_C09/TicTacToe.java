package C9;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private static final char PLAYER_1_SYMBOL = 'X';
    private static final char PLAYER_2_SYMBOL = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            char[][] board = createEmptyBoard();
            boolean player1Turn = true;

            printBoard(board);

            while (!isGameOver(board)) {
                char playerSymbol = player1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;
                int[] move = promptMove(scanner, playerSymbol);

                if (isValidMove(board, move[0], move[1])) {
                    board[move[0]][move[1]] = playerSymbol;
                    printBoard(board);
                    player1Turn = !player1Turn;
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            }

            char winner = getWinner(board);
            if (winner == PLAYER_1_SYMBOL || winner == PLAYER_2_SYMBOL) {
                System.out.println("Player " + winner + " wins!");
            } else {
                System.out.println("It's a tie!");
            }

            System.out.println("Do you want to play again? (y/n)");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }
        }

        scanner.close();
    }

    private static char[][] createEmptyBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] promptMove(Scanner scanner, char playerSymbol) {
        while (true) {
            System.out.println("Player " + playerSymbol + ", enter row and column number:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            scanner.nextLine();
            if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                System.out.println(
                        "Invalid move. Row and column numbers must be between 0 and " + (BOARD_SIZE - 1) + ".");
            } else {
                return new int[] { row, col };
            }
        }
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        return board[row][col] == EMPTY_CELL;
    }

    private static boolean isGameOver(char[][] board) {
        return isBoardFull(board) || getWinner(board) != EMPTY_CELL;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char getWinner(char[][] board) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY_CELL) {
                return board[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != EMPTY_CELL) {
                return board[0][j];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY_CELL) {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY_CELL) {
            return board[0][2];
        }

        return EMPTY_CELL;
    }
}