package JCT_B02;

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
		boolean isGameOver = false;

		System.out.println("Welcome to Tic Tac Toe!");
		printBoard();

		while (!isGameOver) {
			System.out.println("Player " + currentPlayer + ", enter row (1-3):");
			int row = scanner.nextInt() - 1;
			System.out.println("Player " + currentPlayer + ", enter column (1-3):");
			int col = scanner.nextInt() - 1;

			if (row < 0 || row >= 3 || col < 0 || col >= 3) {
				System.out.println("Invalid row or column, try again.");
				continue;
			}

			if (board[row][col] != '\u0000') {
				System.out.println("That spot is already taken, try again.");
				continue;
			}

			board[row][col] = currentPlayer;
			printBoard();

			if (checkWin()) {
				System.out.println("Congratulations, Player " + currentPlayer + ", you won!");
				isGameOver = true;
			} else if (checkTie()) {
				System.out.println("It's a tie!");
				isGameOver = true;
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			}
		}

		scanner.close();
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '\u0000';
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

	private boolean checkWin() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != '\u0000' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] != '\u0000' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '\u0000' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean checkTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '\u0000') {
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