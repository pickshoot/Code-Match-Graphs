package JDT_C01;

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
		// check if the given row and column is a valid move
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
		// check if the current player has won
		// check rows
		for (int i = 0; i < numRows; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < numCols; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		// check if the game is a draw
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
		// start the game loop
		while (true) {
			// print the game board
			printBoard();
			// ask the current player to make a move
			System.out.println("Player " + currentPlayer + ", make your move (row column): ");
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
				printBoard();
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}

			// check for a draw
			if (checkForDraw()) {
				printBoard();
				System.out.println("The game is a draw.");
				break;
			}

			// switch to the next player
			switchPlayer();
		}
		scanner.close();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(3, 3); // create a new game with 3 rows and 3 columns
		game.play(); // start the game
	}
}
