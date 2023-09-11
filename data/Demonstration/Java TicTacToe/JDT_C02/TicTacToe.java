package JDT_C02;

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
		// check if the given row and column are a valid move
		if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private void makeMove(int row, int col) {
		// make a move in the given row and column
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

	private boolean checkForWin() {
		// check for a win in the rows
		for (int i = 0; i < numRows; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// check for a win in the columns
		for (int j = 0; j < numCols; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// check for a win in the diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		// check for a draw
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		// if there are no empty cells left, the game is a draw
		return true;
	}

	public void play() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.println("Player 1: X");
		System.out.println("Player 2: O");
		while (true) {
			// print out the game board
			printBoard();
			// get the row and column for the player's move
			System.out.print("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
			int row = input.nextInt() - 1;
			int col = input.nextInt() - 1;
			// check if the move is valid
			if (!isValidMove(row, col)) {
				System.out.println("Invalid move. Please try again.");
				continue;
			}
			// make the move
			makeMove(row, col);
			// check for a win or a draw
			if (checkForWin()) {
				// print out the game board
				printBoard();
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}
			if (checkForDraw()) {
				// print out the game board
				printBoard();
				System.out.println("The game is a draw.");
				break;
			}
			// switch the player
			switchPlayer();
		}
		input.close();
	}

	public static void main(String args[]) {
		TicTacToe game = new TicTacToe(3, 3);
		game.play();
	}
}