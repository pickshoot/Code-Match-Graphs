package ticTacToeSA9;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] board = new char[3][3];
		boolean isX = true;
		char symbol;
		boolean gameWon = false;

		initializeBoard(board);

		while (!gameWon) {
			symbol = isX ? 'X' : 'O';
			System.out.println("Player " + symbol + " turn:");
			printBoard(board);

			System.out.println("Enter row and column (1-3):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(board, row, col)) {
				board[row][col] = symbol;
				isX = !isX;
				if (checkWin(board, symbol)) {
					printBoard(board);
					System.out.println("Player " + symbol + " wins!");
					gameWon = true;
				} else if (isBoardFull(board)) {
					printBoard(board);
					System.out.println("Tie game!");
					gameWon = true;
				}
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
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isValidMove(char[][] board, int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		} else if (board[row][col] != '-') {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkWin(char[][] board, char symbol) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				return true; // check row
			} else if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
				return true; // check column
			}
		}
		if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
			return true; // check diagonal
		} else if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
			return true; // check diagonal
		} else {
			return false;
		}
	}

	public static boolean isBoardFull(char[][] board) {
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