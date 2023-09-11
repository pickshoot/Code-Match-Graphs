package JCT_C09;

import java.util.Scanner;

public class TicTacToe {
	private static final char[][] board = new char[3][3];
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private static final char EMPTY_SLOT = ' ';
	private static char currentPlayer = PLAYER_X;

	public static void main(String[] args) {
		initializeBoard();
		displayBoard();

		while (!gameOver()) {
			makeMove();
			switchPlayer();
		}

		displayResult();
	}

	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = EMPTY_SLOT;
			}
		}
	}

	private static void displayBoard() {
		System.out.println("   1   2   3");
		for (int row = 0; row < 3; row++) {
			System.out.print((row + 1) + " ");
			for (int col = 0; col < 3; col++) {
				System.out.print(" " + board[row][col] + " ");
				if (col < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row < 2) {
				System.out.println("  ---|---|---");
			}
		}
	}

	private static void makeMove() {
		Scanner scanner = new Scanner(System.in);
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				validInput = true;
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == EMPTY_SLOT;
	}

	private static void switchPlayer() {
		if (currentPlayer == PLAYER_X) {
			currentPlayer = PLAYER_O;
		} else {
			currentPlayer = PLAYER_X;
		}
	}

	private static boolean gameOver() {
		if (hasWon(PLAYER_X)) {
			return true;
		}
		if (hasWon(PLAYER_O)) {
			return true;
		}
		return isBoardFull();
	}

	private static boolean hasWon(char player) {
		// check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
				return true;
			}
		}

		// check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == EMPTY_SLOT) {
					return false;
				}
			}
		}
		return true;
	}

	private static void displayResult() {
		displayBoard();
		if (hasWon(PLAYER_X)) {
			System.out.println("Player X has won!");
		} else if (hasWon(PLAYER_O)) {
			System.out.println("Player O has won!");
		} else {
			System.out.println("The game is a draw.");
		}
	}
}