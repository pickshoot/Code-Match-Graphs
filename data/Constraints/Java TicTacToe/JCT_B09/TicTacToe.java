package JCT_B09;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();
		displayBoard();
		playGame();
	}

	private static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private static void displayBoard() {
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

	private static void playGame() {
		boolean gameFinished = false;
		while (!gameFinished) {
			System.out.println(
					"Player " + currentPlayer + ", please enter row (1-3) and column (1-3) separated by a space:");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				displayBoard();
				if (hasWon()) {
					System.out.println("Congratulations Player " + currentPlayer + ", you win!");
					gameFinished = true;
				} else if (isBoardFull()) {
					System.out.println("It's a tie!");
					gameFinished = true;
				} else {
					switchPlayers();
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private static boolean hasWon() {
		if (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()) {
			return true;
		}
		return false;
	}

	private static boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
		return false;
	}

	private static boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return true;
			}
		}
		return false;
	}

	private static boolean checkDiagonalsForWin() {
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-')) {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private static void switchPlayers() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
}