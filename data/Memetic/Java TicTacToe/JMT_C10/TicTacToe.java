package C10;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();
		displayBoard();

		while (!isGameOver()) {
			getPlayerMove();
			displayBoard();
			currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		}

		char winner = getWinner();
		if (winner == ' ') {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Player " + winner + " has won!");
		}
	}

	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = ' ';
			}
		}
	}

	private static void displayBoard() {
		System.out.println("  1 2 3");
		for (int row = 0; row < 3; row++) {
			System.out.print((row + 1) + " ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isGameOver() {
		return isBoardFull() || getWinner() != ' ';
	}

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	private static char getWinner() {
		// Check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
				return board[row][0];
			}
		}

		// Check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
				return board[0][col];
			}
		}

		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return board[0][0];
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return board[0][2];
		}

		return ' ';
	}

	private static void getPlayerMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;
		do {
			System.out.print("Player " + currentPlayer + ", enter row (1-3) and column (1-3) separated by space: ");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));
		makeMove(row, col);
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		if (board[row][col] != ' ') {
			return false;
		}
		return true;
	}

	private static void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}
}