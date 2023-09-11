package ticTacToeSA4;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] board = new char[3][3];
		boolean gameOn = true;
		char currentPlayer = 'X';

		initializeBoard(board);

		while (gameOn) {
			System.out.println("Current board:");
			printBoard(board);

			int row = getValidInput("row", scanner);
			int col = getValidInput("column", scanner);

			if (board[row][col] != ' ') {
				System.out.println("That space is already taken. Try again.");
			} else {
				board[row][col] = currentPlayer;

				if (hasWon(board, currentPlayer)) {
					System.out.println(currentPlayer + " has won!");
					gameOn = false;
				} else if (isBoardFull(board)) {
					System.out.println("It's a tie!");
					gameOn = false;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			}
		}

		scanner.close();
	}

	public static void initializeBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.print(" ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
				if (j != board[i].length - 1) {
					System.out.print(" | ");
				}
			}
			System.out.println();
			if (i != board.length - 1) {
				System.out.println("---|---|---");
			}
		}
		System.out.println();
	}

	public static int getValidInput(String inputType, Scanner scanner) {
		int input = -1;
		while (input < 0 || input > 2) {
			System.out.print("Enter " + inputType + " (0-2): ");
			if (scanner.hasNextInt()) {
				input = scanner.nextInt();
			} else {
				scanner.next();
			}
		}
		return input;
	}

	public static boolean hasWon(char[][] board, char player) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}
		return false;
	}

	public static boolean isBoardFull(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}