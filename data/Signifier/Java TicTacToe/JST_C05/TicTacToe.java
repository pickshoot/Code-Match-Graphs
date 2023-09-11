package JST_C05;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3]; // Create a 3x3 board
	private static char currentPlayer = 'X'; // Player 'X' goes first

	public static void main(String[] args) {
		initializeBoard(); // Initialize the board with empty spaces
		printBoard(); // Print the initial state of the board

		// Keep playing until there's a winner or the board is full
		while (!isGameOver()) {
			getNextMove(); // Ask the player for their next move
			printBoard(); // Print the updated state of the board
			switchPlayer(); // Switch to the other player's turn
		}

		// Print the winner or a tie message
		if (hasWinner()) {
			System.out.println("Player " + currentPlayer + " wins!");
		} else {
			System.out.println("Tie game!");
		}
	}

	// Initialize the board with empty spaces
	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = ' ';
			}
		}
	}

	// Print the current state of the board
	private static void printBoard() {
		System.out.println("-------------");
		for (int row = 0; row < 3; row++) {
			System.out.print("| ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Ask the player for their next move
	private static void getNextMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] col[1-3]):");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));
		board[row][col] = currentPlayer;
	}

	// Check if the given row and column are a valid move
	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false; // out of bounds
		}
		if (board[row][col] != ' ') {
			return false; // space is already taken
		}
		return true;
	}

	// Check if the game is over (board is full or there's a winner)
	private static boolean isGameOver() {
		if (isBoardFull() || hasWinner()) {
			return true;
		}
		return false;
	}

	// Check if the board is full
	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// Check if there's a winner
	private static boolean hasWinner() {
		// Check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
				return true;
			}
		}

		// Check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}

		return false;
	}

	// Switch to the other player's turn
	private static void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
}