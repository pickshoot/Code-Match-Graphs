package JDT_C10;

import java.util.Scanner;


public class TicTacToe {
	private char[][] board; // 2D array to represent the game board
	private int size; // size of the game board
	private char currentPlayer; // current player (either 'X' or 'O')

	public TicTacToe(int size) {
		this.size = size;
		board = new char[size][size];
		currentPlayer = 'X';
		initializeBoard();
	}

	private void initializeBoard() {
		// initialize the game board with empty cells
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = '-';
			}
		}
	}

	private boolean checkForWin() {
		// check for a win on the game board
		// check rows
		for (int i = 0; i < size; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < size; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		// check if the game board is full and there is no winner
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidMove(int row, int col) {
		// check if the move is within the game board and the cell is empty
		if (row < 0 || row >= size || col < 0 || col >= size) {
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
				// check if the move is valid
				if (isValidMove(row, col)) {
					// make the move and switch the player
					makeMove(row, col);
					switchPlayer();
					// check if there is a win or draw
					if (checkForWin()) {
						// print out the game board and the winner
						printBoard();
						System.out.println("Congratulations, Player " + currentPlayer + "! You have won the game.");
						gameOver = true;
					} else if (checkForDraw()) {
						// print out the game board and the draw result
						printBoard();
						System.out.println("The game is a draw.");
						gameOver = true;
					}
				} else {
					System.out.println("Invalid move. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter two integers separated by a space.");
				scanner.nextLine(); // clear the scanner buffer
			}
		}
		scanner.close(); // close the scanner
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
	public static void main(String args[]) {
		TicTacToe game = new TicTacToe(3);
		game.play();
	}
}
