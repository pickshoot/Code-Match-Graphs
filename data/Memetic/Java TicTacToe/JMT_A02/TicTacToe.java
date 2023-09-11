package A2;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] board = new char[3][3];
		char currentPlayer = 'X';

		initializeBoard(board);

		while (true) {
			printBoard(board);
			System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(board, row, col)) {
				board[row][col] = currentPlayer;
				if (hasWon(board, currentPlayer)) {
					printBoard(board);
					System.out.println("Player " + currentPlayer + " has won!");
					break;
				} else if (isDraw(board)) {
					printBoard(board);
					System.out.println("Game is a draw.");
					break;
				}
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
	}

	public static void initializeBoard(char[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = '-';
			}
		}
	}

	public static void printBoard(char[][] board) {
		System.out.println("-------------");
		for (int row = 0; row < board.length; row++) {
			System.out.print("| ");
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	public static boolean isValidMove(char[][] board, int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == '-';
	}

	public static boolean hasWon(char[][] board, char player) {
		// Check rows
		for (int row = 0; row < board.length; row++) {
			if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
				return true;
			}
		}

		// Check columns
		for (int col = 0; col < board[0].length; col++) {
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

		return false;
	}

	public static boolean isDraw(char[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}