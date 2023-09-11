package JDT_A03;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY = '-';
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static char currentPlayer;

	public static void main(String[] args) {
		initializeBoard();
		currentPlayer = 'X';

		System.out.println("Welcome to Tic-Tac-Toe!");
		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Display the current board
			displayBoard();

			// Prompt the current player to make a move
			System.out.print(currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
			String input = scanner.nextLine();

			// Validate the user input
			int row = input.charAt(0) - '1';
			int col = input.charAt(2) - '1';
			if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || board[row][col] != EMPTY) {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			// Update the board with the current player's move
			board[row][col] = currentPlayer;

			// Check if the game is over
			if (isWin() || isDraw()) {
				break;
			}

			// Switch to the other player
			currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
		}

		// Display the final board and winner (if any)
		displayBoard();
		if (isWin()) {
			System.out.println(currentPlayer + " wins!");
		} else {
			System.out.println("Draw!");
		}
	}

	private static void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY;
			}
		}
	}

	private static void displayBoard() {
		System.out.println();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean isWin() {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}

		return false;
	}

	private static boolean isDraw() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
}
