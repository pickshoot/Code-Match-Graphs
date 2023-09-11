package B3;

import java.util.Scanner;

public class TicTacToe {

	private static char[][] board = new char[3][3];
	private static char currentPlayerMark;

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		currentPlayerMark = 'X';
		while (!checkForWin() && !checkForDraw()) {
			playerTurn(currentPlayerMark);
			printBoard();
			switchPlayer();
		}
		if (checkForDraw()) {
			System.out.println("It's a draw!");
		} else {
			System.out.println("Congratulations! " + currentPlayerMark + " wins!");
		}
	}

	public static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static void printBoard() {
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

	public static void playerTurn(char playerMark) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Player " + playerMark + ", please enter row (0-2):");
		int row = scanner.nextInt();
		System.out.println("Player " + playerMark + ", please enter column (0-2):");
		int col = scanner.nextInt();
		if (board[row][col] == '-') {
			board[row][col] = playerMark;
		} else {
			System.out.println("This cell is already occupied. Please try again.");
			playerTurn(playerMark);
		}
	}

	public static void switchPlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public static boolean checkForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowForWin(i) || checkColForWin(i)) {
				return true;
			}
		}
		if (checkDiagonalForWin()) {
			return true;
		}
		return false;
	}

	public static boolean checkRowForWin(int row) {
		if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-') {
			return true;
		}
		return false;
	}

	public static boolean checkColForWin(int col) {
		if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-') {
			return true;
		}
		return false;
	}

	public static boolean checkDiagonalForWin() {
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-')) {
			return true;
		}
		return false;
	}

	public static boolean checkForDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

}