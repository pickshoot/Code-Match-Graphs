package JCT_A06;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayerMark;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	public void initializeBoard() {
		// Initialize each position of the board with '-'
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void printBoard() {
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

	public boolean isBoardFull() {
		// Check if any position of the board is still empty
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
				}
			}
		}
		return isFull;
	}

	public boolean checkForWin() {
		// Check rows for a win
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}

		// Check columns for a win
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}

		// Check diagonals for a win
		if (checkRowCol(board[0][0], board[1][1], board[2][2]) == true) {
			return true;
		}
		if (checkRowCol(board[0][2], board[1][1], board[2][0]) == true) {
			return true;
		}

		return false;
	}

	public boolean checkRowCol(char c1, char c2, char c3) {
		// Check if three characters in a row or column are the same
		if (c1 == '-' || c2 == '-' || c3 == '-') {
			return false;
		}
		if (c1 == c2 && c2 == c3) {
			return true;
		}
		return false;
	}

	public void changePlayer() {
		// Change the current player
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {
		// Check if the position is within the board boundaries
		if (row >= 0 && row < 3) {
			if (col >= 0 && col < 3) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		game.printBoard();

		while (true) {
			System.out.println("Player " + game.currentPlayerMark + ", enter row and column (1-3): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (game.placeMark(row, col)) {
				game.printBoard();

				if (game.checkForWin()) {
					System.out.println("Player " + game.currentPlayerMark + " wins!");
					break;
				}

				if (game.isBoardFull()) {
					System.out.println("It's a tie!");
					break;
				}

				game.changePlayer();
			} else {
				System.out.println("Invalid input. Please enter again.");
			}
		}
	}
}