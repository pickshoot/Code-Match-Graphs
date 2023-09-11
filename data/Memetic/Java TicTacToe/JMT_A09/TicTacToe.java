package A9;

import java.util.Scanner;

public class TicTacToe {
	static char[][] board = new char[3][3];
	static Scanner scanner = new Scanner(System.in);
	static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		while (!gameOver()) {
			playTurn();
			printBoard();
		}
		System.out.println(getWinner() + " wins!");
	}

	public static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	public static void printBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	public static void playTurn() {
		System.out.println("It's " + currentPlayer + "'s turn.");
		int row = getRow();
		int col = getCol();
		while (!isValidMove(row, col)) {
			System.out.println("Invalid move. Try again.");
			row = getRow();
			col = getCol();
		}
		board[row][col] = currentPlayer;
		currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
	}

	public static int getRow() {
		System.out.print("Enter row number (1-3): ");
		int row = scanner.nextInt() - 1;
		return row;
	}

	public static int getCol() {
		System.out.print("Enter column number (1-3): ");
		int col = scanner.nextInt() - 1;
		return col;
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

	public static boolean gameOver() {
		if (getWinner() != '-') {
			return true;
		}
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public static char getWinner() {
		// check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
				return board[row][0];
			}
		}
		// check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
				return board[0][col];
			}
		}
		// check diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return board[0][0];
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return board[0][2];
		}
		return '-';
	}
}
