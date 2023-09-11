package B1;

import java.util.Scanner;

public class TicTacToe {
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;
	private static final char EMPTY = ' ';
	private static final char X_MARK = 'X';
	private static final char O_MARK = 'O';

	private char[][] board = new char[ROWS][COLUMNS];
	private char currentPlayer;

	public TicTacToe() {
		currentPlayer = X_MARK;
		initializeBoard();
	}

	private void initializeBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = EMPTY;
			}
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			displayBoard();
			int row = getValidRow(scanner);
			int column = getValidColumn(scanner);
			placeMark(row, column);
			if (hasWinner()) {
				displayBoard();
				System.out.println("Player " + currentPlayer + " wins!");
				gameOver = true;
			} else if (boardIsFull()) {
				displayBoard();
				System.out.println("Game over! It's a tie.");
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

	private int getValidRow(Scanner scanner) {
		int row;
		do {
			System.out.print("Enter a row number (1-3) for player " + currentPlayer + ": ");
			row = scanner.nextInt();
		} while (!isValidCoordinate(row));
		return row - 1;
	}

	private int getValidColumn(Scanner scanner) {
		int column;
		do {
			System.out.print("Enter a column number (1-3) for player " + currentPlayer + ": ");
			column = scanner.nextInt();
		} while (!isValidCoordinate(column));
		return column - 1;
	}

	private boolean isValidCoordinate(int coordinate) {
		return coordinate >= 1 && coordinate <= 3;
	}

	private void placeMark(int row, int column) {
		board[row][column] = currentPlayer;
	}

	private boolean hasWinner() {
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
		return (checkRowCol(board[0][0], board[1][1], board[2][2])
				|| checkRowCol(board[0][2], board[1][1], board[2][0]));
	}

	private boolean checkRowCol(char c1, char c2, char c3) {
		return (c1 != EMPTY) && (c1 == c2) && (c2 == c3);
	}

	private boolean boardIsFull() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer == X_MARK) ? O_MARK : X_MARK;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}