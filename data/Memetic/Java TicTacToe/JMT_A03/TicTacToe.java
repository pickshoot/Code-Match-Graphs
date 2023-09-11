package A3;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();

		System.out.println("Welcome to Tic-Tac-Toe!");

		Scanner scanner = new Scanner(System.in);

		int row, col;
		while (true) {
			printBoard();
			System.out.println("It's " + currentPlayer + "'s turn. Enter row and column number (e.g. 1 1):");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;

				if (isWinner()) {
					System.out.println(currentPlayer + " has won the game!");
					printBoard();
					break;
				} else if (isDraw()) {
					System.out.println("The game is a draw.");
					printBoard();
					break;
				}

				currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}

		scanner.close();
	}

	private static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private static void printBoard() {
		System.out.println("  1 2 3");

		for (int i = 0; i < 3; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == '-';
	}

	private static boolean isWinner() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return true;
			}
		}
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}
		return false;
	}

	private static boolean isDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}