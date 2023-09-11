package JDT_C03;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board; // 2D array to represent the game board
	private int numRows; // number of rows on the game board
	private int numCols; // number of columns on the game board
	private char currentPlayer; // current player (either 'X' or 'O')

	public TicTacToe(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		board = new char[numRows][numCols];
		currentPlayer = 'X';
		initializeBoard();
	}

	private void initializeBoard() {
		// set every cell on the board to '-'
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		// print out the game board
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean isValidMove(int row, int col) {
		// check if the given cell is a valid move
		if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private void makeMove(int row, int col) {
		// make a move in the given cell
		board[row][col] = currentPlayer;
	}

	private void switchPlayer() {
		// switch the current player
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	private boolean checkRowsForWin() {
		// check rows for a win
		for (int i = 0; i < numRows; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumnsForWin() {
		// check columns for a win
		for (int j = 0; j < numCols; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalsForWin() {
		// check diagonals for a win
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-')) {
			return true;
		}
		return false;
	}

	private boolean checkForWin() {
		// check if either player has won the game
		return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
	}

	private boolean isBoardFull() {
		// check if the board is full
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		int row, col;

		// game loop
		while (true) {
			System.out.println("Player " + currentPlayer + "'s turn:");
			System.out.print("Enter row (0-2): ");
			row = scanner.nextInt();
			System.out.print("Enter column (0-2): ");
			col = scanner.nextInt();

			if (!isValidMove(row, col)) {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			makeMove(row, col);
			printBoard();

			if (checkForWin()) {
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}

			if (isBoardFull()) {
				System.out.println("It's a tie!");
				break;
			}

			switchPlayer();
		}

		scanner.close();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(3, 3);
		game.play();
	}
}
