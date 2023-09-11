package C4;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		// Create a 3x3 array to represent the Tic-Tac-Toe board
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

		// Create a Scanner object to read user input
		Scanner scanner = new Scanner(System.in);

		// Keep track of whose turn it is (X goes first)
		char player = 'X';

		// Loop until the game is over
		while (true) {
			// Print the current state of the board
			printBoard(board);

			// Ask the current player to make a move
			System.out.println("Player " + player + ", enter your move (row column):");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			// Check if the move is valid (i.e., the chosen cell is empty)
			if (board[row][col] != ' ') {
				System.out.println("Invalid move, please try again.");
				continue;
			}

			// Update the board with the player's move
			board[row][col] = player;

			// Check if the game is over (i.e., there is a winner or the board is full)
			if (checkWinner(board, player)) {
				printBoard(board);
				System.out.println("Player " + player + " wins!");
				break;
			} else if (boardIsFull(board)) {
				printBoard(board);
				System.out.println("Tie game!");
				break;
			}

			// Switch to the other player's turn
			if (player == 'X') {
				player = 'O';
			} else {
				player = 'X';
			}
		}
	}

	/**
	 * Prints the current state of the Tic-Tac-Toe board.
	 */
	public static void printBoard(char[][] board) {
		System.out.println("  0 1 2");
		for (int row = 0; row < 3; row++) {
			System.out.print(row + " ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Checks if a player has won the game.
	 */
	public static boolean checkWinner(char[][] board, char player) {
		// Check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
				return true;
			}
		}

		// Check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
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

		// If none of the above conditions are true, the game is not over yet
		return false;
	}

	/**
	 * Checks if the board is full.
	 */
	public static boolean boardIsFull(char[][] board) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}