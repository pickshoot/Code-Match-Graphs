package JDT_C06;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board; // 2D array to represent the game board
	private int size; // number of rows/columns on the game board
	private char currentPlayer; // current player (either 'X' or 'O')

	public TicTacToe(int size) {
		this.size = size;
		board = new char[size][size];
		currentPlayer = 'X';
		initializeBoard();
	}

	private void initializeBoard() {
		// set all board positions to empty
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		// print out the game board
		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean isValidMove(int row, int col) {
		// check if the specified row and column are within the bounds of the board
		if (row < 0 || row >= size || col < 0 || col >= size) {
			return false;
		}
		// check if the specified position is empty
		if (board[row][col] != '-') {
			return false;
		}
		return true;
	}

	private boolean checkForWin() {
		// check rows
		for (int i = 0; i < size; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < size; j++) {
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
		// check if all positions are filled
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		// switch the current player
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
			// get the current player's move
			System.out.println(
					"Player " + currentPlayer + ",enter your move (row[1-" + size + "] column[1-" + size + "]): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			// check if the move is valid
			if (isValidMove(row, col)) {
				// make the move
				board[row][col] = currentPlayer;
				// check for a win
				if (checkForWin()) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else {
					// check for a draw
					if (checkForDraw()) {
						System.out.println("It's a draw!");
						gameOver = true;
					} else {
						// switch to the next player
						switchPlayer();
					}
				}
			} else {
				System.out.println("Invalid move, try again!");
			}
		}
		// print out the final game board
		printBoard();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(3); // create a new game with size 3
		game.play(); // start playing the game
	}
}
