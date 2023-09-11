package B4;

import java.util.Scanner;

/**
 * TicTacToe game implementation in Java.
 */
public class TicTacToe {
	private char[][] board;
	private char currentPlayerMark;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	/**
	 * Initialize the board with '-' to represent empty cells.
	 */
	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	/**
	 * Print the current state of the board.
	 */
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

	/**
	 * Check if the board is full.
	 *
	 * @return true if the board is full, false otherwise.
	 */
	public boolean isBoardFull() {
		boolean isFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
					break;
				}
			}
		}

		return isFull;
	}

	/**
	 * Check if there is a winner.
	 *
	 * @return 'X' if player X has won, 'O' if player O has won, '-' if there is no
	 *         winner yet.
	 */
	public char checkForWin() {
		// Check for rows.
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				if (board[i][0] != '-') {
					return board[i][0];
				}
			}
		}

		// Check for columns.
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				if (board[0][j] != '-') {
					return board[0][j];
				}
			}
		}

		// Check for diagonals.
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			if (board[0][0] != '-') {
				return board[0][0];
			}
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			if (board[0][2] != '-') {
				return board[0][2];
			}
		}

		return '-';
	}

	/**
	 * Change the current player mark.
	 */
	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	/**
	 * Place a mark on the board.
	 *
	 * @param row    The row index of the cell.
	 * @param column The column index of the cell.
	 * @return true if the mark was placed, false if the cell was already occupied.
	 */
	public boolean placeMark(int row, int column) {
		if (row >= 0 && row < 3 && column >= 0 && column < 3) {
			if (board[row][column] == '-') {
				board[row][column] = currentPlayerMark;
				return true;
			}
		}

		return false;
	}

	/**
	 * Run the game loop.
	 */
	public void run() {
		System.out.println("Welcome to Tic Tac Toe!");

		while (true) {
			printBoard();
			int row, column;

			// Get the user's input.
			Scanner scanner = new Scanner(System.in);
			System.out.printf("Player %c, enter a row (0, 1, 2): ", currentPlayerMark);
			row = scanner.nextInt();
			System.out.printf("Player %c, enter a column (0, 1, 2): ", currentPlayerMark);
			column = scanner.nextInt();

			// Place the mark on the board.
			if (!placeMark(row, column)) {
				System.out.println("The cell is already occupied. Try again.");
				continue;
			}

			// Check for a win or tie.
			if (checkForWin() == 'X') {
				printBoard();
				System.out.println("Player X wins!");
				break;
			} else if (checkForWin() == 'O') {
				printBoard();
				System.out.println("Player O wins!");
				break;
			} else if (isBoardFull()) {
				printBoard();
				System.out.println("It's a tie!");
				break;
			}

			// Change the player.
			changePlayer();
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.run();
	}
}
