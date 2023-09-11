package JDT_C09;

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

	private boolean checkForWin() {
		// check rows
		for (int i = 0; i < numRows; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < numCols; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void initializeBoard() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				board[i][j] = '-';
			}
		}
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	public void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
	}

	public void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < numRows; i++) {
			System.out.print("| ");
			for (int j = 0; j < numCols; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			// print out the game board
			printBoard();
			// ask the current player to make a move
			System.out.println("Player " + currentPlayer + ", make your move (row [0-" + (numRows - 1) + "] column [0-"
					+ (numCols - 1) + "]):");
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			// check if the move is valid
			if (!isValidMove(row, col)) {
				System.out.println("Invalid move. Try again.");
				continue;
			}
			// make the move
			makeMove(row, col);
			// check for a win
			if (checkForWin()) {
				System.out.println("Player " + currentPlayer + " wins!");
				gameOver = true;
			}
			// check for a draw
			if (checkForDraw()) {
				System.out.println("It's a draw!");
				gameOver = true;
			}
			// switch to the other player
			switchPlayer();
		}
		scanner.close();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(3, 3);
		game.play();
	}
}
