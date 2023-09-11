package JCT_C01;

import java.util.Scanner;

public class TicTacToe {
	static char[][] board = new char[3][3];
	static char currentPlayer = 'X';
	static int row, col;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		initializeBoard();
		printBoard();
		playGame();
	}

	public static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static void printBoard() {
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

	public static void playGame() {
		boolean gameOver = false;
		while (!gameOver) {
			System.out.println("Player " + currentPlayer + "'s turn.");
			System.out.print("Enter row (1-3): ");
			row = scanner.nextInt() - 1;
			System.out.print("Enter column (1-3): ");
			col = scanner.nextInt() - 1;

			// Check for invalid input
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid input. Please enter numbers between 1 and 3.");
				continue;
			}
			if (board[row][col] != '-') {
				System.out.println("Invalid input. Cell is already occupied.");
				continue;
			}

			board[row][col] = currentPlayer;
			printBoard();

			if (checkForWin()) {
				System.out.println("Congratulations! Player " + currentPlayer + " wins!");
				gameOver = true;
			} else if (checkForTie()) {
				System.out.println("It's a tie!");
				gameOver = true;
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			}
		}
	}

	public static boolean checkForWin() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
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

	public static boolean checkForTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}