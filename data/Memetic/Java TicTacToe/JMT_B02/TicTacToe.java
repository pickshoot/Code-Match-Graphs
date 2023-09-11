package B2;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	private static char[][] board = new char[3][3];
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();

	private static char currentPlayer = 'X';
	private static boolean isGameOver = false;

	public static void main(String[] args) {
		// Initialize the board
		initializeBoard();

		// Play the game
		while (!isGameOver) {
			displayBoard();
			makeMove(currentPlayer);
			checkGameOver();
			switchPlayer();
		}
		displayBoard();
		System.out.println("Game over!");
	}

	private static void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	private static void displayBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static void makeMove(char player) {
		int row, col;

		if (player == 'X') {
			System.out.println("Your turn! Enter row (1-3) and column (1-3) separated by a space: ");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} else {
			System.out.println("Computer's turn!");
			row = random.nextInt(3);
			col = random.nextInt(3);
		}

		if (isValidMove(row, col)) {
			board[row][col] = player;
		} else {
			System.out.println("Invalid move! Try again.");
			makeMove(player);
		}
	}

	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		return board[row][col] == '-';
	}

	private static void checkGameOver() {
		if (checkRows() || checkColumns() || checkDiagonals()) {
			isGameOver = true;
			System.out.println(currentPlayer + " wins!");
		} else if (isBoardFull()) {
			isGameOver = true;
			System.out.println("It's a tie!");
		}
	}

	private static boolean checkRows() {
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkColumns() {
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkDiagonals() {
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private static void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
}