package JCT_B10;

import java.util.*;

public class TicTacToe {

	private static final int BOARD_SIZE = 3;

	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';

	private char[][] board;
	private char currentPlayerSymbol;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		currentPlayerSymbol = PLAYER_1_SYMBOL;
		initializeBoard();
	}

	private void initializeBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = '-';
			}
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameFinished = false;
		int row, col;

		System.out.println("Welcome to Tic-Tac-Toe!");
		System.out.println("Player 1: " + PLAYER_1_SYMBOL);
		System.out.println("Player 2: " + PLAYER_2_SYMBOL);
		System.out.println();

		while (!gameFinished) {
			printBoard();

			System.out.print("Player " + currentPlayerSymbol + ", enter row number (1-" + BOARD_SIZE + "): ");
			row = scanner.nextInt() - 1;
			System.out.print("Player " + currentPlayerSymbol + ", enter column number (1-" + BOARD_SIZE + "): ");
			col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayerSymbol;

				if (hasCurrentPlayerWon(row, col)) {
					printBoard();
					System.out.println("Congratulations, Player " + currentPlayerSymbol + " has won!");
					gameFinished = true;
				} else if (isBoardFull()) {
					printBoard();
					System.out.println("Game ended in a draw.");
					gameFinished = true;
				} else {
					switchPlayers();
				}
			} else {
				System.out.println("Invalid move, try again!");
			}
		}
	}

	private void switchPlayers() {
		if (currentPlayerSymbol == PLAYER_1_SYMBOL) {
			currentPlayerSymbol = PLAYER_2_SYMBOL;
		} else {
			currentPlayerSymbol = PLAYER_1_SYMBOL;
		}
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private boolean hasCurrentPlayerWon(int row, int col) {
		// Check row
		for (int c = 0; c < BOARD_SIZE; c++) {
			if (board[row][c] != currentPlayerSymbol) {
				break;
			}
			if (c == BOARD_SIZE - 1) {
				return true;
			}
		}

		// Check column
		for (int r = 0; r < BOARD_SIZE; r++) {
			if (board[r][col] != currentPlayerSymbol) {
				break;
			}
			if (r == BOARD_SIZE - 1) {
				return true;
			}
		}

		// Check diagonal
		if (row == col) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (board[i][i] != currentPlayerSymbol) {
					break;
				}
				if (i == BOARD_SIZE - 1) {
					return true;
				}
			}
		}
		// Check anti-diagonal
		if (row + col == BOARD_SIZE - 1) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (board[i][BOARD_SIZE - 1 - i] != currentPlayerSymbol) {
					break;
				}
				if (i == BOARD_SIZE - 1) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isBoardFull() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void printBoard() {
		System.out.print(" ");
		for (int col = 0; col < BOARD_SIZE; col++) {
			System.out.print(" " + (col + 1));
		}
		System.out.println();

		for (int row = 0; row < BOARD_SIZE; row++) {
			System.out.print((row + 1) + " ");
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		ticTacToe.play();
	}
}