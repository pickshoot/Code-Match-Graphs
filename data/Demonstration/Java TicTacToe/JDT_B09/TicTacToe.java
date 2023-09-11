package JDT_B09;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY = '-';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static char currentPlayer = PLAYER_X;

	public static void main(String[] args) {
		initializeBoard();
		displayBoard();
		while (!gameOver()) {
			getPlayerMove();
			displayBoard();
			currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
		}
		displayGameResult();
	}

	private static void initializeBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}
	}

	private static void displayBoard() {
		System.out.println("Board:");
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void getPlayerMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Player " + currentPlayer + " enter row (1-" + BOARD_SIZE + "):");
			row = scanner.nextInt() - 1;
			System.out.println("Player " + currentPlayer + " enter column (1-" + BOARD_SIZE + "):");
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));
		board[row][col] = currentPlayer;
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			System.out.println("Invalid move: row and column must be between 1 and " + BOARD_SIZE);
			return false;
		}
		if (board[row][col] != EMPTY) {
			System.out.println("Invalid move: cell already occupied.");
			return false;
		}
		return true;
	}

	private static boolean gameOver() {
		return isWinningMove() || isBoardFull();
	}

	private static boolean isWinningMove() {
		// Check rows
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] == board[row][1] && board[row][0] == board[row][2] && board[row][0] != EMPTY) {
				return true;
			}
		}
		// Check columns
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] == board[1][col] && board[0][col] == board[2][col] && board[0][col] != EMPTY) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != EMPTY) {
			return true;
		}
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != EMPTY) {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private static void displayGameResult() {
		if (isWinningMove()) {
			System.out.println("Player " + currentPlayer + " wins!");
		} else {
			System.out.println("Game over: tie.");
		}
	}
}
