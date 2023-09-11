package A4;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		char[][] board = new char[3][3];
		initializeBoard(board);
		printBoard(board);
		char currentPlayer = 'X';
		boolean gameOver = false;

		while (!gameOver) {
			int[] move = getMove(currentPlayer);
			if (isValidMove(move, board)) {
				makeMove(move, currentPlayer, board);
				printBoard(board);
				if (hasWinner(board)) {
					System.out.println(currentPlayer + " wins!");
					gameOver = true;
				} else if (isBoardFull(board)) {
					System.out.println("Tie game.");
					gameOver = true;
				} else {
					currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}
	}

	public static void initializeBoard(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static void printBoard(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int[] getMove(char player) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(player + "'s turn. Enter row (1-3): ");
		int row = scanner.nextInt() - 1;
		System.out.print(player + "'s turn. Enter column (1-3): ");
		int col = scanner.nextInt() - 1;
		return new int[] { row, col };
	}

	public static boolean isValidMove(int[] move, char[][] board) {
		int row = move[0];
		int col = move[1];
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	public static void makeMove(int[] move, char player, char[][] board) {
		int row = move[0];
		int col = move[1];
		board[row][col] = player;
	}

	public static boolean hasWinner(char[][] board) {
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

	public static boolean isBoardFull(char[][] board) {
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