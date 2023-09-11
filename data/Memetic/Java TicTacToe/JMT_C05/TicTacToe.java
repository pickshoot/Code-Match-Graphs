package C5;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';
	private static final char EMPTY_SYMBOL = '-';

	private char[][] board;
	private boolean player1Turn;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY_SYMBOL;
			}
		}
		player1Turn = true;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			displayBoard();
			int row = getRowInput(scanner);
			int col = getColInput(scanner);
			if (!makeMove(row, col)) {
				System.out.println("That space is already taken. Please try again.");
				continue;
			}
			if (checkForWinner()) {
				displayBoard();
				System.out.println("Player " + (player1Turn ? "1" : "2") + " wins!");
				return;
			}
			if (checkForTie()) {
				displayBoard();
				System.out.println("It's a tie!");
				return;
			}
			player1Turn = !player1Turn;
		}
	}

	private void displayBoard() {
		System.out.println("-------------");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	private int getRowInput(Scanner scanner) {
		int row;
		do {
			System.out.print("Enter row (1-" + BOARD_SIZE + "): ");
			row = scanner.nextInt() - 1;
		} while (row < 0 || row >= BOARD_SIZE);
		return row;
	}

	private int getColInput(Scanner scanner) {
		int col;
		do {
			System.out.print("Enter column (1-" + BOARD_SIZE + "): ");
			col = scanner.nextInt() - 1;
		} while (col < 0 || col >= BOARD_SIZE);
		return col;
	}

	private boolean makeMove(int row, int col) {
		if (board[row][col] != EMPTY_SYMBOL) {
			return false;
		}
		board[row][col] = player1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;
		return true;
	}

	private boolean checkForWinner() {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] != EMPTY_SYMBOL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (board[0][j] != EMPTY_SYMBOL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] != EMPTY_SYMBOL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != EMPTY_SYMBOL && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean checkForTie() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY_SYMBOL) {
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