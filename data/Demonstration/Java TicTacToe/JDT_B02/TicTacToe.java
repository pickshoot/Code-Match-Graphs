package JDT_B02;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';

	private static char[][] board;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		initializeBoard();
		boolean player1Turn = true;
		printBoard();
		while (true) {
			char symbol = player1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;
			System.out.println("Player " + (player1Turn ? "1" : "2") + ", enter your move (row column):");
			int row = scanner.nextInt();
			int column = scanner.nextInt();
			if (isValidMove(row, column)) {
				board[row][column] = symbol;
				printBoard();
				if (hasPlayerWon(symbol)) {
					System.out.println("Player " + (player1Turn ? "1" : "2") + " wins!");
					break;
				} else if (isBoardFull()) {
					System.out.println("The game is a tie.");
					break;
				}
				player1Turn = !player1Turn;
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}
		scanner.close();
	}

	private static void initializeBoard() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private static void printBoard() {
		System.out.println("   0 1 2");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean isValidMove(int row, int column) {
		return row >= 0 && row < BOARD_SIZE && column >= 0 && column < BOARD_SIZE && board[row][column] == ' ';
	}

	private static boolean hasPlayerWon(char symbol) {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				return true;
			}
		}
		// Check columns
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
			return true;
		}
		if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
			return true;
		}
		return false;
	}

	private static boolean isBoardFull() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}
