package A7;

import java.util.Scanner;

public class TicTacToe {

	private static char[][] board = new char[3][3];
	private static char currentPlayerMark;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		while (!isBoardFull()) {
			System.out.println(
					"It's " + currentPlayerMark + "'s turn. Enter row (1-3) and column (1-3) separated by a space: ");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
				board[row][col] = currentPlayerMark;
				printBoard();
				if (checkForWin()) {
					System.out.println(currentPlayerMark + " wins!");
					return;
				}
				togglePlayer();
			} else {
				System.out.println("Invalid input. Try again.");
			}
		}
		System.out.println("It's a tie!");
	}

	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
		currentPlayerMark = 'X';
	}

	private static void printBoard() {
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

	private static boolean checkForWin() {
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

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private static void togglePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}
}