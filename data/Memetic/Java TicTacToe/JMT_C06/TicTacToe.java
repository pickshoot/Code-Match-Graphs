package C6;

import java.util.Scanner;

public class TicTacToe {
	// Define the game board as a 2D array of characters
	private static char[][] board = new char[3][3];
	// Define the current player (X or O)
	private static char currentPlayer = 'X';

	public static void main(String[] args) {
		// Initialize the game board with blank spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}

		// Print the initial game board
		printBoard();

		// Start the game loop
		while (true) {
			// Prompt the current player to make a move
			System.out.print("Player " + currentPlayer + ", make your move (row column): ");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt();
			int column = scanner.nextInt();

			// Check if the move is valid (i.e. within the bounds of the board and not
			// already occupied)
			if (row < 0 || row > 2 || column < 0 || column > 2) {
				System.out.println("Invalid move. Please enter a row and column between 0 and 2.");
				continue;
			} else if (board[row][column] != ' ') {
				System.out.println("That space is already occupied. Please choose another.");
				continue;
			}

			// Update the game board with the current player's move
			board[row][column] = currentPlayer;

			// Check if the game is over (i.e. a player has won or the board is full)
			if (checkWin() || checkDraw()) {
				// Print the final game board
				printBoard();

				// Print the game result
				if (checkWin()) {
					System.out.println("Player " + currentPlayer + " wins!");
				} else {
					System.out.println("The game is a draw.");
				}

				// End the game loop
				break;
			}

			// Switch to the other player
			if (currentPlayer == 'X') {
				currentPlayer = 'O';
			} else {
				currentPlayer = 'X';
			}

			// Print the updated game board
			printBoard();
		}
	}

	// Method to print the current game board
	private static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
				if (j < board[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < board.length - 1) {
				System.out.println("-+-+-");
			}
		}
	}

	// Method to check if a player has won the game
	private static boolean checkWin() {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
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

		// If none of the win conditions are met, return false
		return false;
	}

	// Method to check if the game is a draw (i.e. the board is full)
	private static boolean checkDraw() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}