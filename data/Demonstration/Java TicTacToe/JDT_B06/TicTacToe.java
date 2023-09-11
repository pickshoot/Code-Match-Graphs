package JDT_B06;

import java.util.Scanner;

public class TicTacToe {
	// The game board
	private char[][] board;
	// The current player (either 'X' or 'O')
	private char currentPlayer;
	// Whether the game is over or not
	private boolean gameOver;

	public TicTacToe() {
		// Initialize the board
		board = new char[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
		// Set the starting player to 'X'
		currentPlayer = 'X';
		// The game is not over yet
		gameOver = false;
	}

	// Play the game
	public void play() {
		Scanner scanner = new Scanner(System.in);
		while (!gameOver) {
			// Print the board
			printBoard();
			// Ask the current player to make a move
			System.out.println("Player " + currentPlayer + ", make a move (row column):");
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			// Make the move if it is valid
			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				// Check if the game is over
				if (checkGameOver()) {
					gameOver = true;
					printBoard();
					System.out.println("Player " + currentPlayer + " wins!");
				} else if (checkTie()) {
					gameOver = true;
					printBoard();
					System.out.println("It's a tie!");
				} else {
					// Switch to the other player
					currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move, try again.");
			}
		}
		scanner.close();
	}

	// Print the current state of the board
	private void printBoard() {
		System.out.println("  0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Check if the given row and column represent a valid move
	private boolean isValidMove(int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == '-';
	}

	// Check if the game is over (i.e., if one player has won)
	private boolean checkGameOver() {
		// Check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < board[0].length; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}
		// If none of the above conditions is true, the game is not over
		return false;
	}

	// Check if the game is a tie (i.e., if all the cells are filled)
	private boolean checkTie() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}
