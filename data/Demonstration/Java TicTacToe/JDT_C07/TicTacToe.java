package JDT_C07;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board; // 2D array to represent the game board
	private int numRows; // number of rows on the game board
	private int numCols; // number of columns on the game board
	private char currentPlayer; // current player (either 'X' or 'O')
	private int numMoves; // number of moves made in the game

	public TicTacToe(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		board = new char[numRows][numCols];
		currentPlayer = 'X';
		numMoves = 0;
		initializeBoard();
	}

	private void initializeBoard() {
		// fill the board with blank spaces
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private void switchPlayer() {
		// switch the current player
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	private boolean isValidMove(int row, int col) {
		// check if the move is valid
		if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
			return false;
		}
		if (board[row][col] != ' ') {
			return false;
		}
		return true;
	}

	private void makeMove(int row, int col) {
		// make the move
		board[row][col] = currentPlayer;
		numMoves++;
	}

	private boolean checkForWin() {
		// check for a win on the game board
		// check rows
		for (int i = 0; i < numRows; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < numCols; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		// check if the game is a draw
		if (numMoves == numRows * numCols) {
			return true;
		}
		return false;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			// print out the game board
			printBoard();
			// get the current player's move
			System.out.println("Player " + currentPlayer + ", please enter your move (row col): ");
			try {
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				// check if the move is valid, and make the move if it is
				if (isValidMove(row, col)) {
					makeMove(row, col);
					// check for a win
					if (checkForWin()) {
						// print out the game board and the winner
						printBoard();
						System.out.println("Player " + currentPlayer + " wins!");
						gameOver = true;
					} else if (checkForDraw()) {
						// print out the game board and that the game is a draw
						printBoard();
						System.out.println("Game is a draw.");
						gameOver = true;
					} else {
						// switch to the other player
						switchPlayer();
					}
				} else {
					System.out.println("Invalid move. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please try again.");
				scanner.nextLine(); // consume the invalid input
			}
		}
		scanner.close();
	}

	private void printBoard() {
		// print out the game board
		System.out.println();
		for (int i = 0; i < numRows; i++) {
			System.out.print(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
			if (i != numRows - 1) {
				System.out.println("\n-----------");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// create a new TicTacToe game and play it
		TicTacToe game = new TicTacToe(3, 3);
		game.play();
	}
}