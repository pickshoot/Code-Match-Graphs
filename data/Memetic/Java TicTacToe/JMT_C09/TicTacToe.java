package C9;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board; // The game board
	private char currentPlayerMark; // The current player's mark

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	// Set/Reset the board back to all empty values.
	public void initializeBoard() {
		// Loop through rows
		for (int i = 0; i < 3; i++) {
			// Loop through columns
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// Print the current board (may be replaced by GUI implementation later)
	public void printBoard() {
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

	// Loop through all cells of the board and if one is found to be empty (contains
	// char '-'), return false
	// Otherwise the board is full
	public boolean isBoardFull() {
		boolean isFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
				}
			}
		}

		return isFull;
	}

	// Returns true if there is a win, false otherwise
	// This calls our other win check functions to check the entire board
	public boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	// Loop through rows and see if any are winners
	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	// Loop through columns and see if any are winners
	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	// Check the two diagonals to see if either is a win. Return true if either
	// wins.
	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true)
				|| (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	}

	// Check to see if all three values are the same (and not empty) indicating a
	// win.
	private boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	// Change player marks back and forth.
	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	// Places a mark at the cell specified by row and col with the mark of the
	// current player.
	public boolean placeMark(int row, int col) {
		// Make sure row and column are in bounds of the board.
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);

		// Game loop
		while (true) {
			game.printBoard();

			// Get row and column from player
			System.out.println("Player " + game.currentPlayerMark + ", enter a row (0, 1, or 2): ");
			int row = scanner.nextInt();
			System.out.println("Player " + game.currentPlayerMark + ", enter a column (0, 1, or 2): ");
			int col = scanner.nextInt();

			// Place the mark on the board
			if (game.placeMark(row, col)) {
				if (game.checkForWin()) {
					System.out.println("Congratulations, Player " + game.currentPlayerMark + ", you win!");
					game.printBoard();
					break;
				} else if (game.isBoardFull()) {
					System.out.println("It's a tie!");
					game.printBoard();
					break;
				} else {
					game.changePlayer();
				}
			} else {
				System.out.println("That spot is already taken! Try again.");
			}
		}
	}
}
