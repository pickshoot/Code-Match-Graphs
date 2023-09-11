package JDT_A08;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static char currentPlayer = 'X';
	private static boolean gameEnded = false;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		play();
	}

	private static void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = '-';
			}
		}
	}

	private static void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	private static void play() {
		Scanner scanner = new Scanner(System.in);

		while (!gameEnded) {
			System.out.println("Player " + currentPlayer + ", enter row (1-" + BOARD_SIZE + "):");
			int row = scanner.nextInt() - 1;
			System.out.println("Player " + currentPlayer + ", enter column (1-" + BOARD_SIZE + "):");
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				printBoard();
				if (hasWon()) {
					System.out.println("Player " + currentPlayer + " has won!");
					gameEnded = true;
				} else if (isTie()) {
					System.out.println("It's a tie!");
					gameEnded = true;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			return false;
		}
		return board[row][col] == '-';
	}

	private static boolean hasWon() {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}

		return false;
	}

	private static boolean isTie() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

}
