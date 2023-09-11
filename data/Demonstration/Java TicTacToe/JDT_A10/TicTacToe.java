package JDT_A10;

import java.util.Scanner;

public class TicTacToe {
// Define constants for player symbols
	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';
//Define variables to store the game state
	private static char[][] board;
	private static boolean isPlayer1Turn;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Initialize the game state
		board = new char[3][3];
		isPlayer1Turn = true;

		// Loop until the game is over
		while (true) {
			// Print the current board
			printBoard();

			// Prompt the user to enter the coordinates of their move
			System.out.print("\nPlayer " + (isPlayer1Turn ? "1" : "2") + " ("
					+ (isPlayer1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL)
					+ "): Enter the row and column of your move (e.g., 1 2): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			// Check if the move is valid
			if (isValidMove(row, col)) {
				// Update the board with the move
				board[row][col] = isPlayer1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;

				// Check if the game is over
				if (isGameOver()) {
					// Print the final board and the result
					printBoard();
					System.out.println("\nGame over! " + (isPlayer1Turn ? "Player 1" : "Player 2") + " wins!");
					break;
				} else if (isBoardFull()) {
					// Print the final board and the result
					printBoard();
					System.out.println("\nGame over! It's a tie!");
					break;
				}

				// Switch to the other player's turn
				isPlayer1Turn = !isPlayer1Turn;
			} else {
				// Print an error message and prompt the user again
				System.out.println("\nInvalid move. Please try again.");
			}
		}
	}

//Define a method to print the current board
	private static void printBoard() {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

//Define a method to check if a move is valid
	private static boolean isValidMove(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == 0;
	}

//Define a method to check if the game is over
	private static boolean isGameOver() {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < board.length; i++) {
			if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return true;
			}
			// Check diagonals
			if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
				return true;
			}
			if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
				return true;
			}

		}
		// If no winner is found, return false
		return false;
	}

	// Define a method to check if the board is full
	private static boolean isBoardFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}

// End of code.