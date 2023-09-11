package B5;

import java.util.Scanner;

public class TicTacToe {

	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private static final char EMPTY = '-';

	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = PLAYER_X;
		initializeBoard();
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		int row, col;

		System.out.println("Welcome to Tic-Tac-Toe!");
		printBoard();

		while (!gameOver) {
			System.out.println("Player " + currentPlayer + ", enter a row (0-2): ");
			row = scanner.nextInt();
			System.out.println("Player " + currentPlayer + ", enter a column (0-2): ");
			col = scanner.nextInt();

			if (isValidMove(row, col)) {
				makeMove(row, col);
				printBoard();

				if (isWinner()) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else if (isBoardFull()) {
					System.out.println("Game over. It's a tie!");
					gameOver = true;
				} else {
					switchPlayer();
				}
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = EMPTY;
			}
		}
	}

	private void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == EMPTY;
	}

	private void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}

	private boolean isWinner() {
		return checkRows() || checkColumns() || checkDiagonals();
	}

	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumns() {
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals() {
		if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}