package JST_B02;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board = new char[3][3];
	private char currentPlayer;

	public TicTacToe() {
		currentPlayer = 'X';
		initializeBoard();
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;

		while (!gameOver) {
			displayBoard();
			System.out.println("Player " + currentPlayer + ", enter row (1-3):");
			int row = scanner.nextInt() - 1;
			System.out.println("Enter column (1-3):");
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				makeMove(row, col);

				if (isWinner()) {
					displayBoard();
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else if (isDraw()) {
					displayBoard();
					System.out.println("Game is a draw.");
					gameOver = true;
				} else {
					switchPlayer();
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}

		scanner.close();
	}

	private void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	private void displayBoard() {
		System.out.println("-------------");

		for (int row = 0; row < 3; row++) {
			System.out.print("| ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " | ");
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
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}

		return false;
	}

	private boolean isDraw() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}

		return true;
	}

	private void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}