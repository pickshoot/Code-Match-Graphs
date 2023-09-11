package JCT_B03;

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
		// Fill the board with empty spaces
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
		// Check if there are any empty spaces left on the board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkForWin() {
		// Check rows for a win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}

		// Check columns for a win
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}

		// Check diagonals for a win
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}

		return false;
	}

	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {
		// Make sure the row and column are valid
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			// Make sure the spot is not already taken
			if (board[row][col] == '-') {
				board[row][col] = currentPlayerMark;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;

		while (!gameOver) {
			game.printBoard();

			System.out.println("It's " + game.currentPlayerMark
					+ "'s turn. Enter the row and column numbers (separated by a space) to place your mark:");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			// Try to place the mark on the board
			if (game.placeMark(row, col)) {
				// Check for a win
				if (game.checkForWin()) {
					System.out.println("Congratulations " + game.currentPlayerMark + ", you win!");
					gameOver = true;
				} else if (game.isBoardFull()) {
					System.out.println("It's a tie!");
					gameOver = true;
				} else {
					// Switch players
					game.changePlayer();
				}
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}

		// Print the final board
		game.printBoard();
	}
}