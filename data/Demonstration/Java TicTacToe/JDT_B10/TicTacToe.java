package JDT_B10;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		char[][] board = new char[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}

		char currentPlayer = 'X';
		boolean gameFinished = false;

		while (!gameFinished) {
			System.out.println("Current board:");
			printBoard(board);

			System.out.println("Player " + currentPlayer + ", enter row and column (e.g. 1 2):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (board[row][col] != '-') {
				System.out.println("That spot is already taken. Please try again.");
			} else {
				board[row][col] = currentPlayer;

				if (checkWin(board, currentPlayer)) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameFinished = true;
				} else if (checkTie(board)) {
					System.out.println("It's a tie!");
					gameFinished = true;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			}
		}

		scanner.close();
	}

	public static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean checkWin(char[][] board, char player) {
		// check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// check columns
		for (int i = 0; i < board[0].length; i++) {
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

	public static boolean checkTie(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}
