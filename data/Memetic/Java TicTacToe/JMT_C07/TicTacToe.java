package C7;

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
		Scanner scanner = new Scanner(System.in);
		int row, col;

		System.out.println("Welcome to Tic-Tac-Toe!");
		printBoard();

		while (true) {
			System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
			row = scanner.nextInt() - 1;
			System.out.print("Player " + currentPlayer + ", enter column (1-3): ");
			col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				printBoard();

				if (hasWon()) {
					System.out.println("Player " + currentPlayer + " wins!");
					break;
				}

				if (isBoardFull()) {
					System.out.println("It's a tie!");
					break;
				}

				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			} else {
				System.out.println("Invalid move. Please try again.");
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

		if (board[row][col] != '-') {
			return false;
		}

		return true;
	}

	private boolean hasWon() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}

		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
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

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}