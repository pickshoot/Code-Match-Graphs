package JCT_B01;

import java.util.*;

public class TicTacToe {
	private static char[][] board;
	private static char currentPlayerMark;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
		printBoard();
		while (!checkForWin() && !isBoardFull()) {
			System.out.println("It's " + currentPlayerMark + "'s turn. Enter row (0-2):");
			int row = scanner.nextInt();
			System.out.println("Enter column (0-2):");
			int col = scanner.nextInt();
			if (row < 0 || col < 0 || row > 2 || col > 2 || board[row][col] != '\u0000') {
				System.out.println("Invalid move, try again.");
				continue;
			}
			board[row][col] = currentPlayerMark;
			if (currentPlayerMark == 'X') {
				currentPlayerMark = 'O';
			} else {
				currentPlayerMark = 'X';
			}
			printBoard();
		}
		if (checkForWin()) {
			System.out.println(currentPlayerMark + " wins!");
		} else {
			System.out.println("It's a tie!");
		}
		scanner.close();
	}

	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '\u0000';
			}
		}
	}

	private static void printBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col]);
				if (col < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row < 2) {
				System.out.println("-----");
			}
		}
	}

	private static boolean checkForWin() {
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '\u0000') {
				return true;
			}
		}
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '\u0000') {
				return true;
			}
		}
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '\u0000') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '\u0000') {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '\u0000') {
					return false;
				}
			}
		}
		return true;
	}
}