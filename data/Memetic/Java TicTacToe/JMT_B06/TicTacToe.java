package B6;

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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tic-Tac-Toe!");
		while (playing) {
			System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (isValidMove(row, col)) {
				makeMove(row, col);
				printBoard();
				if (isWinner()) {
					System.out.println("Player " + currentPlayer + " wins!");
					playing = false;
				} else if (isDraw()) {
					System.out.println("It's a draw.");
					playing = false;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move, try again.");
			}
		}
		scanner.close();
		System.out.println("Thanks for playing!");
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		System.out.println("   1  2  3");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		} else if (board[row][col] != '-') {
			return false;
		} else {
			return true;
		}
	}

	private void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}

	private boolean isWinner() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		} else if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDraw() {
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