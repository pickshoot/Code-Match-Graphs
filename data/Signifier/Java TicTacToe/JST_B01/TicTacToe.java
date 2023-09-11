package JST_B01;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayerMark;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		initializeBoard();
		printBoard();
		while (!isBoardFull()) {
			System.out.println("Player " + currentPlayerMark + ", enter row (1-3) and column (1-3) of your move:");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (isValidMove(row, col)) {
				placeMark(row, col);
				printBoard();
				if (checkForWin()) {
					System.out.println("Player " + currentPlayerMark + " wins!");
					break;
				}
				togglePlayer();
			} else {
				System.out.println("Invalid move, try again.");
			}
		}
		if (!checkForWin()) {
			System.out.println("It's a tie!");
		}
	}

	public static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
		currentPlayerMark = 'X';
	}

	public static void printBoard() {
		System.out.println("-------------");
		for (int row = 0; row < 3; row++) {
			System.out.print("| ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	public static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		} else if (board[row][col] != '-') {
			return false;
		} else {
			return true;
		}
	}

	public static void placeMark(int row, int col) {
		board[row][col] = currentPlayerMark;
	}

	public static boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	private static boolean checkRowsForWin() {
		for (int row = 0; row < 3; row++) {
			if (checkRowCol(board[row][0], board[row][1], board[row][2])) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkColumnsForWin() {
		for (int col = 0; col < 3; col++) {
			if (checkRowCol(board[0][col], board[1][col], board[2][col])) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkDiagonalsForWin() {
		return (checkRowCol(board[0][0], board[1][1], board[2][2])
				|| checkRowCol(board[0][2], board[1][1], board[2][0]));
	}

	private static boolean checkRowCol(char c1, char c2, char c3) {
		return (c1 != '-' && c1 == c2 && c2 == c3);
	}

	public static void togglePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}
}