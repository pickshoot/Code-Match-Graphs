package JDT_A02;

import java.util.Scanner;

public class TicTacToe {
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;
	private static final char EMPTY = ' ';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = EMPTY;
			}
		}
		currentPlayer = PLAYER_X;
	}

	public void printBoard() {
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

	public void play() {
		Scanner scanner = new Scanner(System.in);
		int row, column;
		boolean isDraw = false;

		while (true) {
			System.out.println("Player " + currentPlayer
					+ ", enter the row and column where you want to place your mark (e.g. 1 2): ");
			row = scanner.nextInt() - 1;
			column = scanner.nextInt() - 1;
			if (isValidMove(row, column)) {
				board[row][column] = currentPlayer;
				printBoard();
				if (hasWon()) {
					System.out.println("Congratulations, Player " + currentPlayer + " has won the game!");
					break;
				} else if (isDraw()) {
					System.out.println("The game is a draw!");
					break;
				}
				switchPlayer();
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
	}

	private boolean isValidMove(int row, int column) {
		if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
			return false;
		}
		return board[row][column] == EMPTY;
	}

	private boolean hasWon() {
		for (int i = 0; i < ROWS; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY) {
				return true;
			}
		}
		for (int j = 0; j < COLUMNS; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != EMPTY) {
				return true;
			}
		}
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY) {
			return true;
		}
		return false;
	}

	private boolean isDraw() {
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
		if (currentPlayer == PLAYER_X) {
			currentPlayer = PLAYER_O;
		} else {
			currentPlayer = PLAYER_X;
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}
