package JST_C03;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();

		boolean gameOver = false;
		while (!gameOver) {
			printBoard();
			System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (e.g. 1 2):");

			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				makeMove(row, col);
				if (hasWon(currentPlayer)) {
					gameOver = true;
					System.out.println("Player " + currentPlayer + " has won!");
				} else if (isTie()) {
					gameOver = true;
					System.out.println("Tie game.");
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
	}

	private static void initializeBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = '-';
			}
		}
	}

	private static void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isValidMove(int row, int col) {
		return (row >= 0 && row < board.length && col >= 0 && col < board[row].length && board[row][col] == '-');
	}

	private static void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}

	private static boolean hasWon(char player) {
		for (int i = 0; i < board.length; i++) {
			// check rows
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}

			// check columns
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		return false;
	}

	private static boolean isTie() {
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