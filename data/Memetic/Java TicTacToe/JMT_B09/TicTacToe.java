package B9;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		initializeBoard();
	}

	public void play() {
		boolean playing = true;
		System.out.println("Welcome to Tic-Tac-Toe!");
		printBoard();

		while (playing) {
			System.out.println("Player " + currentPlayer + ", please enter your move (row[1-3] column[1-3]):");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				makeMove(row, col);
				printBoard();
				if (isWinner()) {
					System.out.println("Congratulations! Player " + currentPlayer + " wins!");
					playing = false;
				} else if (isBoardFull()) {
					System.out.println("It's a tie!");
					playing = false;
				} else {
					switchPlayer();
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
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

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == '-';
	}

	private void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}

	private boolean isWinner() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}

		return false;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
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