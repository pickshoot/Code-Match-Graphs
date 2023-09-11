package JCT_C06;

import java.util.Scanner;

public class TicTacToe {

	private static char[][] board = new char[3][3];
	private static char currentPlayerMark;

	public static void main(String[] args) {
		initializeBoard();
		currentPlayerMark = 'X';
		boolean gameOver = false;

		System.out.println("Welcome to Tic Tac Toe!");

		while (!gameOver) {
			displayBoard();
			int[] move = getUserInput();
			makeMove(move[0], move[1]);

			if (checkForWin()) {
				displayBoard();
				System.out.println("Congratulations! Player " + currentPlayerMark + " wins!");
				gameOver = true;
			} else if (checkForTie()) {
				displayBoard();
				System.out.println("It's a tie!");
				gameOver = true;
			}

			currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
		}
	}

	// Set/Reset the board back to all empty values.
	private static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// Print the current board (may be replaced by GUI implementation later)
	private static void displayBoard() {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Get user input for the row and column
	private static int[] getUserInput() {
		Scanner scanner = new Scanner(System.in);
		int[] move = new int[2];
		boolean validInput = false;

		while (!validInput) {
			System.out.print("Player " + currentPlayerMark + ", enter a row (0, 1, or 2): ");
			if (scanner.hasNextInt()) {
				move[0] = scanner.nextInt();
				if (move[0] >= 0 && move[0] <= 2) {
					validInput = true;
				} else {
					System.out.println("Invalid row number! Please enter a number between 0 and 2.");
				}
			} else {
				System.out.println("Invalid input! Please enter a number between 0 and 2.");
				scanner.next();
			}
		}

		validInput = false;
		while (!validInput) {
			System.out.print("Player " + currentPlayerMark + ", enter a column (0, 1, or 2): ");
			if (scanner.hasNextInt()) {
				move[1] = scanner.nextInt();
				if (move[1] >= 0 && move[1] <= 2) {
					validInput = true;
				} else {
					System.out.println("Invalid column number! Please enter a number between 0 and 2.");
				}
			} else {
				System.out.println("Invalid input! Please enter a number between 0 and 2.");
				scanner.next();
			}
		}

		return move;
	}

	// Check if the given cell is empty
	private static boolean isCellEmpty(int row, int col) {
		return (board[row][col] == '-');
	}

	// Make a move on the board
	private static void makeMove(int row, int col) {
		if (isCellEmpty(row, col)) {
			board[row][col] = currentPlayerMark;
		} else {
			System.out.println("This cell is already occupied! Please choose another.");
			int[] move = getUserInput();
			makeMove(move[0], move[1]);
		}
	}

	// Check for a win, return true if one is found
	private static boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	// Check the rows for a win
	private static boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
				return true;
			}
		}
		return false;
	}

	// Check the columns for a win
	private static boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
				return true;
			}
		}
		return false;
	}

	// Check the diagonals for a win
	private static boolean checkDiagonalsForWin() {
		return (checkRowCol(board[0][0], board[1][1], board[2][2])
				|| checkRowCol(board[0][2], board[1][1], board[2][0]));
	}

	// Check if all three values are the same (and not empty) indicating a win
	private static boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	// Check for a tie, return true if all cells are filled
	private static boolean checkForTie() {
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