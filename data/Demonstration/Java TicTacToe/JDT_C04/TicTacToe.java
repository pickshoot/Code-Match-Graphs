package JDT_C04;

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
		// check for a win on the game board
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
		// check for a draw on the game board
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
		boolean gameOver = false;
		while (!gameOver) {
			// print out the game board
			printBoard();
			// get the current player's move
			System.out.println("Player " + currentPlayer + ", please enter your move (row, col): ");
			try {
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				// validate the move
				if (!isValidMove(row, col)) {
					System.out.println("Invalid move. Please try again.");
					continue;
				}
				// make the move
				makeMove(row, col);
				// check for a win
				if (checkForWin()) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else if (checkForDraw()) {
					System.out.println("Game over. It's a draw.");
					gameOver = true;
				} else {
					// switch the player
					switchPlayer();
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			}
		}
		// print out the final game board
		printBoard();
		scanner.close();
	}

	private void initializeBoard() {
		// initialize the game board with '-' to represent empty cells
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				// initialize each cell with '-'
				board[i][j] = '-';
			}
		}
	}

	private boolean isValidMove(int row, int col) {
		// check if the move is valid
		if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
			return false;
		}
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private void makeMove(int row, int col) {
		// make the move on the game board
		board[row][col] = currentPlayer;
	}

	private void switchPlayer() {
		// switch the player
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	private void printBoard() {
		// print out the game board
		System.out.println();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// create a TicTacToe object and start the game
		TicTacToe game = new TicTacToe(3, 3);
		game.play();
	}
}
