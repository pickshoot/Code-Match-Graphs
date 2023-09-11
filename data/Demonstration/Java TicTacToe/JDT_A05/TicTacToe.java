package JDT_A05;

import java.util.Scanner;

public class TicTacToe {
// Define a 2D array to represent the game board
	private static char[][] board = new char[3][3];
//Define constants to represent the players
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';

//Define a variable to keep track of the current player
	private static char currentPlayer;

	public static void main(String[] args) {
		// Initialize the game board with empty spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}

		// Set the first player to be X
		currentPlayer = PLAYER_X;

		// Create a Scanner to read input from the console
		Scanner scanner = new Scanner(System.in);

		// Loop until the game is over
		while (!isGameOver()) {
			// Print the current state of the game board
			printBoard();

			// Prompt the current player to make a move
			System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
			int row = scanner.nextInt();
			int column = scanner.nextInt();

			// Check if the move is valid
			if (isValidMove(row, column)) {
				// Make the move
				board[row][column] = currentPlayer;

				// Switch to the other player
				currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
			} else {
				// Prompt the user to enter a valid move
				System.out.println("Invalid move. Please try again.");
			}
		}

		// Print the final state of the game board
		printBoard();

		// Print the outcome of the game
		if (isWinner(PLAYER_X)) {
			System.out.println("Player X wins!");
		} else if (isWinner(PLAYER_O)) {
			System.out.println("Player O wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	// Method to print the current state of the game board
	private static void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Method to check if the game is over
	private static boolean isGameOver() {
		return isBoardFull() || isWinner(PLAYER_X) || isWinner(PLAYER_O);
	}

	// Method to check if the game board is full
	private static boolean isBoardFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// Method to check if a move is valid
	private static boolean isValidMove(int row, int column) {
		if (row < 0 || row > 2 || column < 0 || column > 2) {
			return false;
		}
		return board[row][column] == ' ';
	}

	// Method to check if a player has won
	private static boolean isWinner(char player) {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < board[0].length; j++) {
			if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
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

		// If no winner has been found, return false
		return false;
	}
}
