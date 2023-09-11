package JDT_C08;

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
		// fill the board with empty spaces
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private boolean checkForWin() {
		// check for a win on the game board
		// check rows
		for (int i = 0; i < size; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < size; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			return true;
		}
		if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return true;
		}
		return false;
	}

	private boolean checkForDraw() {
		// check if the game is a draw
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidMove(int row, int col) {
		// check if the move is valid
		if (row < 0 || row >= size || col < 0 || col >= size) {
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
				// validate the move
				if (!isValidMove(row, col)) {
					System.out.println("Invalid move, please try again.");
					continue;
				}
				// make the move
				makeMove(row, col);
				// check for a win
				if (checkForWin()) {
					printBoard();
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else if (checkForDraw()) { // check for a draw
					printBoard();
					System.out.println("The game is a draw.");
					gameOver = true;
				} else {
					switchPlayer(); // switch to the other player's turn
				}
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				scanner.nextLine(); // clear the scanner buffer
			}
		}
		scanner.close();
	}

	private void printBoard() {
		// print out the game board
		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j]);
				if (j < size - 1) {
					System.out.print("|");
				}
			}
			if (i < size - 1) {
				System.out.println("\n-+-+-");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(3);
		game.play();
	}
}
