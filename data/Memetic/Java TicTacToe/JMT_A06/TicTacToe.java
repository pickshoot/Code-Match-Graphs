package A6;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char[][] board = new char[3][3];
		char player = 'X';
		boolean playing = true;

		// Initialize the board with empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}

		// Main game loop
		while (playing) {
			System.out.println("Current board:");
			printBoard(board);

			// Get user input for the next move
			int row, col;
			do {
				System.out.print("Player " + player + ", enter row (1-3): ");
				row = input.nextInt() - 1;
				System.out.print("Player " + player + ", enter column (1-3): ");
				col = input.nextInt() - 1;
			} while (!isValidMove(board, row, col));

			// Update the board with the player's move
			board[row][col] = player;

			// Check for a win or tie
			if (hasWon(board, player)) {
				System.out.println("Player " + player + " has won!");
				playing = false;
			} else if (isTie(board)) {
				System.out.println("Tie game!");
				playing = false;
			}

			// Switch to the other player
			player = (player == 'X') ? 'O' : 'X';
		}

		// Print the final board
		System.out.println("Final board:");
		printBoard(board);
	}

	/**
	 * Print the Tic-Tac-Toe board to the console.
	 */
	public static void printBoard(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
				if (j < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < 2) {
				System.out.println("-+-+-");
			}
		}
	}

	/**
	 * Check if a move is valid (i.e. the space is empty).
	 */
	public static boolean isValidMove(char[][] board, int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == ' ';
	}

	/**
	 * Check if a player has won the game.
	 */
	public static boolean hasWon(char[][] board, char player) {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		// If none of the above conditions are true, the player has not won
		return false;
	}

	/**
	 * Check if the game has ended in a tie.
	 */
	public static boolean isTie(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					// If there is an empty space, the game is not a tie
					return false;
				}
			}
		}
		// If there are no empty spaces, the game is a tie
		return true;
	}
}