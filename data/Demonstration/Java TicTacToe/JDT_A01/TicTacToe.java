package JDT_A01;

import java.util.Scanner;

public class TicTacToe {
	// Define constants for the game board size and player symbols
	public static final int BOARD_SIZE = 3;
	public static final char PLAYER_X_SYMBOL = 'X';
	public static final char PLAYER_O_SYMBOL = 'O';
	public static final char EMPTY_SPACE_SYMBOL = ' ';

	// Define variables for the game board and the current player
	private char[][] board;
	private char currentPlayer;

	// Create a new game board and set the current player to X
	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY_SPACE_SYMBOL;
			}
		}
		currentPlayer = PLAYER_X_SYMBOL;
	}

	// Play the game until there is a winner or the board is full
	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameIsOver = false;
		while (!gameIsOver) {
			printBoard();
			int row = getValidInput("Enter row (1-3): ", scanner) - 1;
			int col = getValidInput("Enter column (1-3): ", scanner) - 1;
			if (board[row][col] == EMPTY_SPACE_SYMBOL) {
				board[row][col] = currentPlayer;
				if (checkForWinner(row, col)) {
					System.out.println(currentPlayer + " wins!");
					gameIsOver = true;
				} else if (isBoardFull()) {
					System.out.println("Game over - board is full.");
					gameIsOver = true;
				} else {
					currentPlayer = (currentPlayer == PLAYER_X_SYMBOL) ? PLAYER_O_SYMBOL : PLAYER_X_SYMBOL;
				}
			} else {
				System.out.println("Invalid move - space is already occupied.");
			}
		}
	}

	// Get valid user input for a row or column number
	private int getValidInput(String prompt, Scanner scanner) {
		int input;
		do {
			System.out.print(prompt);
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input - please enter a number between 1 and 3.");
				scanner.next();
				System.out.print(prompt);
			}
			input = scanner.nextInt();
			scanner.nextLine();
		} while (input < 1 || input > 3);
		return input;
	}

	// Print the current state of the game board
	private void printBoard() {
		System.out.println("-------------");
		for (int row = 0; row < BOARD_SIZE; row++) {
			System.out.print("| ");
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Check if there is a winner on the board
	private boolean checkForWinner(int row, int col) {
		// Check row
		boolean rowWin = true;
		for (int c = 0; c < BOARD_SIZE; c++) {
			if (board[row][c] != currentPlayer) {
				rowWin = false;
				break;
			}
		}
		if (rowWin) {
			return true;
		}
		// Check column
		boolean colWin = true;
		for (int r = 0; r < BOARD_SIZE; r++) {
			if (board[r][col] != currentPlayer) {
				colWin = false;
				break;
			}
		}
		if (colWin) {
			return true;
		}
		// Check diagonal
		if (row == col) {
			boolean diagonalWin = true;
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (board[i][i] != currentPlayer) {
					diagonalWin = false;
					break;
				}
			}
			if (diagonalWin) {
				return true;
			}
		}
		// Check anti-diagonal
		if (row + col == BOARD_SIZE - 1) {
			boolean antiDiagonalWin = true;
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (board[i][BOARD_SIZE - 1 - i] != currentPlayer) {
					antiDiagonalWin = false;
					break;
				}
			}
			if (antiDiagonalWin) {
				return true;
			}
		}
		// No winner found
		return false;
	}

	// Check if the board is full
	private boolean isBoardFull() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY_SPACE_SYMBOL) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// Example usage:
		TicTacToe game = new TicTacToe();
		game.play();

	}
}
