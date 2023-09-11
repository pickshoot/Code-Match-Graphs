package JCT_C08;

import java.util.Scanner;

public class TicTacToe {

	private static final int BOARD_SIZE = 3;
	private static final char EMPTY_CELL = ' ';
	private static final char PLAYER1_SYMBOL = 'X';
	private static final char PLAYER2_SYMBOL = 'O';

	private char[][] board;
	private int currentPlayer;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY_CELL;
			}
		}
		currentPlayer = 1;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Let's play Tic-Tac-Toe!");
		printBoard();
		while (!gameOver()) {
			System.out.printf("Player %d's turn (%c)%n", currentPlayer,
					currentPlayer == 1 ? PLAYER1_SYMBOL : PLAYER2_SYMBOL);
			int row = -1;
			int col = -1;
			while (row == -1 || col == -1) {
				System.out.print("Enter row (1-3): ");
				String rowStr = scanner.next();
				System.out.print("Enter column (1-3): ");
				String colStr = scanner.next();
				try {
					row = Integer.parseInt(rowStr) - 1;
					col = Integer.parseInt(colStr) - 1;
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter numbers between 1 and 3.");
				}
				if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
					System.out.println("Invalid input. Please enter numbers between 1 and 3.");
					row = -1;
					col = -1;
				} else if (board[row][col] != EMPTY_CELL) {
					System.out.println("That cell is already occupied. Please choose another one.");
					row = -1;
					col = -1;
				}
			}
			board[row][col] = currentPlayer == 1 ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
			printBoard();
			switchPlayer();
		}
		if (hasWinner()) {
			System.out.printf("Player %d wins!%n", currentPlayer == 1 ? 2 : 1);
		} else {
			System.out.println("It's a draw!");
		}
	}

	private boolean gameOver() {
		return hasWinner() || isDraw();
	}

	private boolean hasWinner() {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (board[0][j] != EMPTY_CELL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] != EMPTY_CELL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}

		if (board[0][2] != EMPTY_CELL && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean isDraw() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY_CELL) {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
	}

	private void printBoard() {
		System.out.println("   1   2   3");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(" " + board[i][j] + " ");
				if (j < BOARD_SIZE - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < BOARD_SIZE - 1) {
				System.out.println("  ---|---|---");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}