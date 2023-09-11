package JCT_B06;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY_CELL = '-';
	private char[][] board;
	private char currentPlayerMark;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	public void play() {
		System.out.println("Tic Tac Toe Game\n");

		while (true) {
			displayBoard();
			int[] move = getPlayerMove();
			placeMark(move[0], move[1]);

			if (checkForWin()) {
				displayBoard();
				System.out.println("Congratulations! Player " + currentPlayerMark + " wins!");
				break;
			} else if (checkForTie()) {
				displayBoard();
				System.out.println("Game ended in a tie.");
				break;
			}

			changePlayer();
		}
	}

	private void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY_CELL;
			}
		}
	}

	private void displayBoard() {
		System.out.println();
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private int[] getPlayerMove() {
		Scanner scanner = new Scanner(System.in);
		int[] move = new int[2];
		while (true) {
			System.out.print("Enter row number (1-" + BOARD_SIZE + "): ");
			move[0] = scanner.nextInt() - 1;
			System.out.print("Enter column number (1-" + BOARD_SIZE + "): ");
			move[1] = scanner.nextInt() - 1;
			if (isValidMove(move[0], move[1])) {
				break;
			} else {
				System.out.println("Invalid move, try again.");
			}
		}
		return move;
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			return false;
		} else if (board[row][col] != EMPTY_CELL) {
			return false;
		} else {
			return true;
		}
	}

	private void placeMark(int row, int col) {
		board[row][col] = currentPlayerMark;
	}

	private boolean checkForWin() {
		// Check rows for win
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// Check columns for win
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (board[0][j] != EMPTY_CELL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}

		// Check diagonals for win
		if (board[0][0] != EMPTY_CELL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != EMPTY_CELL && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}

		return false;
	}

	private boolean checkForTie() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == EMPTY_CELL) {
					return false;
				}
			}
		}
		return true;
	}

	private void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}
}