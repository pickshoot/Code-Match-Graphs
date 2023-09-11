package JST_C01;

import java.util.Scanner;

public class TicTacToe {
	private static char[][] board;
	private static char currentPlayer;

	public static void main(String[] args) {
		board = new char[3][3];
		currentPlayer = 'X';
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
		System.out.println("Board:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void playGame() {
		boolean gameInProgress = true;
		while (gameInProgress) {
			System.out.println(currentPlayer + "'s turn.");
			System.out.println("Enter row and column numbers (0-2) separated by a space:");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				displayBoard();

				if (checkForWin()) {
					System.out.println(currentPlayer + " wins!");
					gameInProgress = false;
				} else if (checkForDraw()) {
					System.out.println("Draw.");
					gameInProgress = false;
				} else {
					switchPlayer();
				}
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == '-';
	}

	private static boolean checkForWin() {
		return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
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
		return checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]);
	}

	private static boolean checkRowCol(char c1, char c2, char c3) {
		return c1 != '-' && c1 == c2 && c2 == c3;
	}

	private static boolean checkForDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private static void switchPlayer() {
		currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
	}
}