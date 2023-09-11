package JST_C07;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board = new char[3][3];
	private char currentPlayer;

	public TicTacToe() {
		// Initialize the board with empty cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
		currentPlayer = 'X';
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameFinished = false;

		while (!gameFinished) {
			displayBoard();
			System.out.println("Player " + currentPlayer + "'s turn. Enter row (0-2): ");
			int row = scanner.nextInt();
			System.out.println("Enter column (0-2): ");
			int col = scanner.nextInt();

			// Make a move
			if (board[row][col] == '-') {
				board[row][col] = currentPlayer;

				// Check if the game is over
				if (isWinningMove(row, col)) {
					displayBoard();
					System.out.println("Player " + currentPlayer + " wins!");
					gameFinished = true;
				} else if (isBoardFull()) {
					displayBoard();
					System.out.println("It's a tie!");
					gameFinished = true;
				} else {
					// Switch to the other player
					currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
	}

	private boolean isWinningMove(int row, int col) {
		// Check row
		if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
			return true;
		}

		// Check column
		if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
			return true;
		}

		// Check diagonal
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
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

	private void displayBoard() {
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

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}