package JCT_B07;

import java.util.Scanner;

public class TicTacToe {

	private char[][] board; // The Tic-Tac-Toe board
	private char currentPlayer; // The current player (either 'X' or 'O')
	private boolean gameOver; // Whether the game is over or not

	public TicTacToe() {
		board = new char[3][3]; // Create a 3x3 board
		currentPlayer = 'X'; // X goes first
		gameOver = false; // The game starts
		initBoard();
	}

	// Initialize the board with empty cells
	private void initBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = ' ';
			}
		}
	}

	// Print the current board
	private void printBoard() {
		System.out.println("-------------");
		for (int row = 0; row < 3; row++) {
			System.out.print("| ");
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Switch to the other player
	private void switchPlayer() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}

	// Get the move from the current player
	private void getMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;
		do {
			System.out.print("Player " + currentPlayer + ", enter your move (row[1-3] col[1-3]): ");
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');
		board[row][col] = currentPlayer;
	}

	// Check if there is a winner
	private boolean checkWinner() {
		// Check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
				return true;
			}
		}
		// Check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}
		return false;
	}

	// Check if the board is full
	private boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// Play the game
	public void play() {
		while (!gameOver) {
			printBoard(); // Print the board
			getMove(); // Get the move from the current player
			if (checkWinner()) { // Check for a winner
				printBoard();
				System.out.println("Player " + currentPlayer + " wins!");
				gameOver = true;
			} else if (isBoardFull()) { // Check if the board is full
				printBoard();
				System.out.println("It's a tie!");
				gameOver = true;
			} else {
				switchPlayer(); // Switch to the other player
			}
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}