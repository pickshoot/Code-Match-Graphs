package JCT_C04;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY = '-';
	private static final char X_SYMBOL = 'X';
	private static final char O_SYMBOL = 'O';

	private char[][] board;
	private char currentPlayerSymbol;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		currentPlayerSymbol = X_SYMBOL;
		initializeBoard();
	}

	private void initializeBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tic-Tac-Toe!");

		while (true) {
			printBoard();
			int row = getRowInput(scanner);
			int col = getColInput(scanner);

			if (board[row][col] != EMPTY) {
				System.out.println("That spot is already taken. Please try again.");
				continue;
			}

			board[row][col] = currentPlayerSymbol;

			if (hasWon()) {
				printBoard();
				System.out.println(currentPlayerSymbol + " has won!");
				return;
			}

			if (isTie()) {
				printBoard();
				System.out.println("The game is a tie.");
				return;
			}

			currentPlayerSymbol = (currentPlayerSymbol == X_SYMBOL) ? O_SYMBOL : X_SYMBOL;
		}
	}

	private int getRowInput(Scanner scanner) {
		System.out.print("Enter row (1-" + BOARD_SIZE + "): ");
		int row = scanner.nextInt() - 1;
		if (row < 0 || row >= BOARD_SIZE) {
			System.out.println("Invalid input. Please try again.");
			return getRowInput(scanner);
		}
		return row;
	}

	private int getColInput(Scanner scanner) {
		System.out.print("Enter column (1-" + BOARD_SIZE + "): ");
		int col = scanner.nextInt() - 1;
		if (col < 0 || col >= BOARD_SIZE) {
			System.out.println("Invalid input. Please try again.");
			return getColInput(scanner);
		}
		return col;
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

	private boolean isTie() {
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
		System.out.println();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " ");
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