package JST_C08;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
		char currentPlayer = 'X';
		int movesLeft = 9;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Tic Tac Toe!");

		while (movesLeft > 0) {
			System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (e.g. 1 2):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid row or column. Try again.");
				continue;
			}

			if (board[row][col] != ' ') {
				System.out.println("That spot is already taken. Try again.");
				continue;
			}

			board[row][col] = currentPlayer;
			movesLeft--;

			printBoard(board);

			if (hasWon(board, currentPlayer)) {
				System.out.println("Player " + currentPlayer + " wins!");
				return;
			}

			currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
		}

		System.out.println("It's a tie!");
	}

	public static void printBoard(char[][] board) {
		System.out.println("   1 2 3");
		System.out.println("  -------");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + 1 + "| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("  -------");
	}

	public static boolean hasWon(char[][] board, char player) {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
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

		return false;
	}
}