package JCT_C10;

import java.util.Scanner;

public class TicTacToe {
	// The game board is represented by a 2D array of characters
	// We'll use 'X' and 'O' to represent the players, and '-' for empty cells
	private static char[][] board = { { '-', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };

	// This method prints the game board to the console
	private static void printBoard() {
		System.out.println("  1 2 3");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// This method checks if the game is over (i.e., if there is a winner or a tie)
	private static boolean isGameOver() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		// Check for tie
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	// This method prompts the user to enter a row and a column to make a move
	// It handles invalid user input such as numbers and characters
	private static void makeMove(char player) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Player " + player + ", enter row (1-3) and column (1-3): ");
			if (scanner.hasNextInt()) {
				int row = scanner.nextInt();
				if (scanner.hasNextInt()) {
					int col = scanner.nextInt();
					if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
						if (board[row - 1][col - 1] == '-') {
							board[row - 1][col - 1] = player;
							break;
						} else {
							System.out.println("That cell is already occupied.");
						}
					} else {
						System.out.println("Row and column must be between 1 and 3.");
					}
				} else {
					System.out.println("Invalid input. Column must be an integer.");
				}
			} else {
				System.out.println("Invalid input. Row must be an integer.");
				scanner.next(); // consume invalid input
			}
		}
	}

	public static void main(String[] args) {
		char currentPlayer = 'X';
		while (!isGameOver()) {
			printBoard();
			makeMove(currentPlayer);
			currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
		}
		printBoard();
		System.out.println("Game over.");
	}
}