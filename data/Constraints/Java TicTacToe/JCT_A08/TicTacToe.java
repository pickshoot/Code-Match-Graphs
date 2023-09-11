package JCT_A08;

import java.util.Scanner;

public class TicTacToe {
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;
	private char[][] board = new char[ROWS][COLUMNS];
	private char currentPlayer;

	public TicTacToe() {
		currentPlayer = 'X';
		initializeBoard();
	}

	private void initializeBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void play() {
		boolean gameOver = false;
		while (!gameOver) {
			System.out.println("Player " + currentPlayer + "'s turn.");
			displayBoard();
			makeMove();
			if (checkForWin()) {
				System.out.println("Player " + currentPlayer + " wins!");
				gameOver = true;
			} else if (checkForTie()) {
				System.out.println("It's a tie!");
				gameOver = true;
			} else {
				switchPlayer();
			}
		}
	}

	private void displayBoard() {
		System.out.println("-------------");
		for (int i = 0; i < ROWS; i++) {
			System.out.print("| ");
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	private void makeMove() {
		Scanner scanner = new Scanner(System.in);
		int row;
		int column;
		do {
			System.out.print("Enter row (1-3) and column (1-3) separated by a space: ");
			row = scanner.nextInt() - 1;
			column = scanner.nextInt() - 1;
		} while (!isValidMove(row, column));
		board[row][column] = currentPlayer;
	}

	private boolean isValidMove(int row, int column) {
		if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
			System.out.println("Invalid move. Please try again.");
			return false;
		} else if (board[row][column] != '-') {
			System.out.println("That space is already occupied. Please try again.");
			return false;
		} else {
			return true;
		}
	}

	private boolean checkForWin() {
		return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
	}

	private boolean checkRowsForWin() {
		for (int i = 0; i < ROWS; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumnsForWin() {
		for (int i = 0; i < COLUMNS; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalsForWin() {
		return checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]);
	}

	private boolean checkRowCol(char c1, char c2, char c3) {
		return c1 != '-' && c1 == c2 && c2 == c3;
	}

	private boolean checkForTie() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}