package JCT_A10;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY = ' ';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';

	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}
		currentPlayer = PLAYER_X;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameEnded = false;
		while (!gameEnded) {
			printBoard();
			System.out.println("Player " + currentPlayer + " turn, please enter row and column: ");
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				if (hasWon()) {
					gameEnded = true;
					System.out.println("Player " + currentPlayer + " has won!");
				} else if (isBoardFull()) {
					gameEnded = true;
					System.out.println("The game ended in a draw.");
				} else {
					currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
				}
			} else {
				System.out.println("Invalid move, please try again.");
			}
		}
		printBoard();
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			return false;
		}
		return board[row][col] == EMPTY;
	}

	private boolean hasWon() {
		// check rows
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] != EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
				return true;
			}
		}
		// check columns
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] != EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean isBoardFull() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private void printBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(" " + board[row][col] + " ");
				if (col < BOARD_SIZE - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row < BOARD_SIZE - 1) {
				System.out.println("-----------");
			}
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}