package JCT_B04;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static boolean gameOver = false;
	private static int movesPlayed = 0;

	public static void main(String[] args) {
		initializeBoard();
		displayBoard();
		Scanner scanner = new Scanner(System.in);
		char currentPlayerSymbol = PLAYER_1_SYMBOL;
		while (!gameOver) {
			System.out.println("Player " + currentPlayerSymbol + " enter row and column (1-3) separated by a space:");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (isValidMove(row, col)) {
				board[row][col] = currentPlayerSymbol;
				displayBoard();
				movesPlayed++;
				if (hasWon(currentPlayerSymbol)) {
					System.out.println("Player " + currentPlayerSymbol + " wins!");
					gameOver = true;
				} else if (movesPlayed == BOARD_SIZE * BOARD_SIZE) {
					System.out.println("Game is a draw.");
					gameOver = true;
				} else {
					currentPlayerSymbol = currentPlayerSymbol == PLAYER_1_SYMBOL ? PLAYER_2_SYMBOL : PLAYER_1_SYMBOL;
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}
		scanner.close();
	}

	private static void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private static void displayBoard() {
		System.out.println("  1 2 3");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			return false;
		}
		return board[row][col] == ' ';
	}

	private static boolean hasWon(char playerSymbol) {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[0][i] == playerSymbol && board[1][i] == playerSymbol && board[2][i] == playerSymbol) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) {
			return true;
		}
		if (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol) {
			return true;
		}

		return false;
	}
}