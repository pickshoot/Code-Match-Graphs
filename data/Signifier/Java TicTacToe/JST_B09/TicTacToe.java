package JST_B09;

import java.util.*;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY = '-';
	private static final char PLAYER_1 = 'X';
	private static final char PLAYER_2 = 'O';
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static char currentPlayer = PLAYER_1;
	private static boolean gameEnded = false;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();

		while (!gameEnded) {
			int[] move = getNextMove();
			makeMove(move[0], move[1]);
			printBoard();
			checkForEndOfGame();
			switchPlayer();
		}
	}

	private static void initializeBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}
	}

	private static void printBoard() {
		System.out.println();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int[] getNextMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player " + currentPlayer + ", enter row number (0 to 2): ");
		int row = scanner.nextInt();
		System.out.print("Player " + currentPlayer + ", enter column number (0 to 2): ");
		int col = scanner.nextInt();
		return new int[] { row, col };
	}

	private static void makeMove(int row, int col) {
		if (board[row][col] == EMPTY) {
			board[row][col] = currentPlayer;
		} else {
			System.out.println("Invalid move! Try again.");
			int[] move = getNextMove();
			makeMove(move[0], move[1]);
		}
	}

	private static void checkForEndOfGame() {
		if (checkForWin() || checkForDraw()) {
			gameEnded = true;
			System.out.println("Game over! " + currentPlayer + " wins!");
		}
	}

	private static boolean checkForWin() {
		// Check rows
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != EMPTY) {
				return true;
			}
		}

		// Check columns
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != EMPTY) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY) {
			return true;
		}

		return false;
	}

	private static boolean checkForDraw() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private static void switchPlayer() {
		if (currentPlayer == PLAYER_1) {
			currentPlayer = PLAYER_2;
		} else {
			currentPlayer = PLAYER_1;
		}
	}
}