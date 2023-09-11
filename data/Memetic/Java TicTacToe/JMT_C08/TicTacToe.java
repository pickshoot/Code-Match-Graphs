package C8;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board = new char[3][3];
	private char currentPlayer = 'X';

	public TicTacToe() {
		// Initialize the board to empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		int row, col;

		// Main game loop
		while (true) {
			// Print the board
			System.out.println("  1 2 3");
			System.out.println(" -------");
			for (int i = 0; i < 3; i++) {
				System.out.print((i + 1) + "|");
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + "|");
				}
				System.out.println();
				System.out.println(" -------");
			}

			// Ask the player for their move
			System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
			row = scanner.nextInt() - 1;
			System.out.print("Player " + currentPlayer + ", enter column (1-3): ");
			col = scanner.nextInt() - 1;

			// Make the move if the spot is empty
			if (board[row][col] == ' ') {
				board[row][col] = currentPlayer;

				// Check for a win
				if (checkWin()) {
					System.out.println("Player " + currentPlayer + " wins!");
					return;
				}

				// Check for a tie
				if (checkTie()) {
					System.out.println("It's a tie!");
					return;
				}

				// Switch to the other player
				if (currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}
			} else {
				System.out.println("That spot is already taken. Try again.");
			}
		}
	}

	private boolean checkWin() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return true;
			}
		}

		// Check diagonals
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
			return true;
		}

		// No win condition found
		return false;
	}

	private boolean checkTie() {
		// Check for any empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}

		// No empty spaces found, it's a tie
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}