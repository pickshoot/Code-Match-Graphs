package JCT_A03;

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

		System.out.println("Welcome to Tic-Tac-Toe!");
		printBoard();

		while (!isGameOver) {
			System.out.printf("Player %s's turn. Enter row (1-3): ", currentPlayer);
			int row = scanner.nextInt() - 1;
			System.out.printf("Enter column (1-3): ");
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				makeMove(row, col);
				printBoard();

				if (hasWon()) {
					System.out.printf("Player %s has won!", currentPlayer);
					isGameOver = true;
				} else if (isBoardFull()) {
					System.out.println("It's a tie!");
					isGameOver = true;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}

		scanner.close();
	}

	private void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = '-';
			}
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

	private boolean hasWon() {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < board[0].length; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
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

	private boolean isBoardFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}

		return true;
	}

	private void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}