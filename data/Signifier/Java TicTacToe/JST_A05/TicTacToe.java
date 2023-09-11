package ticTacToeSA5;

import java.util.Scanner;

public class TicTacToe {

	static char[][] board = new char[3][3];
	static char currentPlayerMark;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		currentPlayerMark = 'X';
		while (!isBoardFull()) {
			System.out.println(
					"It's " + currentPlayerMark + "'s turn. Enter the row and column number (separated by space):");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			if (isValidMove(row, col)) {
				board[row][col] = currentPlayerMark;
				printBoard();
				if (hasWon(currentPlayerMark)) {
					System.out.println(currentPlayerMark + " has won!");
					return;
				}
				switchPlayer();
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
		System.out.println("It's a tie!");
	}

	public static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
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
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	public static boolean hasWon(char playerMark) {
		if (board[0][0] == playerMark && board[0][1] == playerMark && board[0][2] == playerMark) {
			return true;
		}
		if (board[1][0] == playerMark && board[1][1] == playerMark && board[1][2] == playerMark) {
			return true;
		}
		if (board[2][0] == playerMark && board[2][1] == playerMark && board[2][2] == playerMark) {
			return true;
		}
		if (board[0][0] == playerMark && board[1][0] == playerMark && board[2][0] == playerMark) {
			return true;
		}
		if (board[0][1] == playerMark && board[1][1] == playerMark && board[2][1] == playerMark) {
			return true;
		}
		if (board[0][2] == playerMark && board[1][2] == playerMark && board[2][2] == playerMark) {
			return true;
		}
		if (board[0][0] == playerMark && board[1][1] == playerMark && board[2][2] == playerMark) {
			return true;
		}
		if (board[0][2] == playerMark && board[1][1] == playerMark && board[2][0] == playerMark) {
			return true;
		}
		return false;
	}

	public static void switchPlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}
}