package C3;

import java.util.Scanner;

public class TicTacToe {

	// The game board
	private char[][] board = new char[3][3];

	// The current player
	private char currentPlayer;

	// Initialize the game board
	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// Print the game board
	public void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	// Switch the current player
	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// Get the row and column from the player
	public void getMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the row (1-3): ");
		int row = scanner.nextInt() - 1;
		System.out.print("Enter the column (1-3): ");
		int col = scanner.nextInt() - 1;
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			System.out.println("Invalid input. Please try again.");
			getMove();
		} else if (board[row][col] != '-') {
			System.out.println("That cell is already taken. Please try again.");
			getMove();
		} else {
			board[row][col] = currentPlayer;
		}
	}

	// Check if the game is over
	public boolean isGameOver() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		// Check if the board is full
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	// Print the winner
	public void printWinner() {
		if (currentPlayer == 'X') {
			System.out.println("Player O wins!");
		} else {
			System.out.println("Player X wins!");
		}
	}

	// The main game loop
	public void playGame() {
		initializeBoard();
		currentPlayer = 'X';
		while (!isGameOver()) {
			printBoard();
			System.out.println("It's " + currentPlayer + "'s turn.");
			getMove();
			switchPlayer();
		}
		printBoard();
		printWinner();
	}

	// The main method
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.playGame();
	}
}