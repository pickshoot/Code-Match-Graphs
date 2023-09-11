package JDT_A06;

import java.util.Scanner;

public class TicTacToe {
// 2D array to represent the board
	private static char[][] board = new char[3][3];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Initialize the board with empty cells
		initializeBoard();
		// Flag to keep track of whose turn it is
		boolean playerOneTurn = true;
		// Loop until there is a winner or the board is full
		while (true) {
			// Print the current state of the board
			printBoard();
			// Get the input from the user
			System.out.print("Player " + (playerOneTurn ? "1" : "2")
					+ ", enter the row and column to place your mark (e.g. 1 2): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			// Make sure the cell is empty
			if (board[row][col] != ' ') {
				System.out.println("That cell is already occupied. Try again.");
				continue;
			}
			// Place the player's mark on the board
			board[row][col] = playerOneTurn ? 'X' : 'O';
			// Check if the player has won
			if (hasWon(playerOneTurn ? 'X' : 'O')) {
				printBoard();
				System.out.println("Player " + (playerOneTurn ? "1" : "2") + " wins!");
				break;
			}
			// Check if the board is full
			if (isBoardFull()) {
				printBoard();
				System.out.println("It's a tie!");
				break;
			}
			// Switch to the other player's turn
			playerOneTurn = !playerOneTurn;
		}
	}

// Method to initialize the board with empty cells
	private static void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

// Method to print the current state of the board
	private static void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println("\n-------------");
		}
	}

// Method to check if a player has won
	private static boolean hasWon(char mark) {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
				return true;
			}
		}
		// Check columns
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
			return true;
		}
		if (board[0][2] == mark && board[2][0] == mark && board[1][1] == mark) {
			return true;
		}
		return false;
	}

	// Method to check if the board is full
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
}
