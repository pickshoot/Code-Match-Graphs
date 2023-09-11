package JDT_B08;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY_CELL = '_';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';

	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY_CELL;
			}
		}
		currentPlayer = PLAYER_X;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);

		while (!isGameOver()) {
			printBoard();
			System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]):");
			String input = scanner.nextLine();
			try {
				String[] parts = input.split(" ");
				int row = Integer.parseInt(parts[0]) - 1;
				int col = Integer.parseInt(parts[1]) - 1;
				if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
					throw new IllegalArgumentException("Invalid input. Please enter a valid row and column.");
				}
				if (board[row][col] != EMPTY_CELL) {
					throw new IllegalArgumentException("That cell is already taken. Please enter a different cell.");
				}
				board[row][col] = currentPlayer;
				currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		printBoard();
		if (isWinner(PLAYER_X)) {
			System.out.println("Player X wins!");
		} else if (isWinner(PLAYER_O)) {
			System.out.println("Player O wins!");
		} else {
			System.out.println("It's a tie!");
		}

		scanner.close();
	}

	private boolean isGameOver() {
		return isWinner(PLAYER_X) || isWinner(PLAYER_O) || isBoardFull();
	}

	private boolean isWinner(char player) {
		// Check rows
		for (int i = 0; i < BOARD_SIZE; i++) {
			boolean win = true;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] != player) {
					win = false;
					break;
				}
			}
			if (win) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < BOARD_SIZE; j++) {
			boolean win = true;
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (board[i][j] != player) {
					win = false;
					break;
				}
			}
			if (win) {
				return true;
			}
		}

		// Check diagonals
		boolean win = true;
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][i] != player) {
				win = false;
				break;
			}
		}
		if (win) {
			return true;
		}

		win = true;
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][BOARD_SIZE - i - 1] != player) {
				win = false;
				break;
			}
		}
		return win;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY_CELL) {
					return false;
				}
			}
		}
		return true;
	}

	private void printBoard() {
		System.out.println();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String args[]) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}
