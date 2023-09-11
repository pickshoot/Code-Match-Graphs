package ticTacToeSA10;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayerMark;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		currentPlayerMark = 'X';

		while (!checkForWin() && !checkForTie()) {
			playerMove(currentPlayerMark);
			printBoard();
			switchPlayer();
		}

		if (checkForTie()) {
			System.out.println("The game ended in a tie!");
		} else {
			System.out.println("Congratulations, Player " + currentPlayerMark + ", you won!");
		}
	}

	private static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private static void printBoard() {
		System.out.println("-------------");

		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	private static boolean checkForTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	private static boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
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
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	private static void playerMove(char playerMark) {
		Scanner scanner = new Scanner(System.in);
		int row, col;

		do {
			System.out.print("Player " + playerMark + ", enter an empty row and column for your move: ");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} while (board[row][col] != '-');

		board[row][col] = playerMark;
	}

	private static void switchPlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';

		}
	}
}