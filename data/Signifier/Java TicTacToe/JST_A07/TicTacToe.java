package ticTacToeSA7;

import java.util.Scanner;

public class TicTacToe {

	private static char[][] board = new char[3][3]; // Create a 3x3 board
	private static char currentPlayer = 'X'; // X goes first
	private static boolean gameFinished = false;

	public static void main(String[] args) {
		initBoard(); // Initialize the board
		printBoard(); // Print the board
		while (!gameFinished) {
			makeMove(); // Ask the player to make a move
			printBoard(); // Print the board again
			checkWinner(); // Check if there is a winner
			checkTie(); // Check if the game is tied
			switchPlayer(); // Switch to the next player
		}
	}

	// Initialize the board with empty spaces
	private static void initBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	// Print the board
	private static void printBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
			if (i != 2) {
				System.out.println("-----------");
			}
		}
	}

	// Ask the player to make a move
	private static void makeMove() {
		Scanner scanner = new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Player " + currentPlayer + ", enter row (1-3) and column (1-3) separated by a space: ");
			row = scanner.nextInt() - 1; // Subtract 1 because the array indices start from 0
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));
		board[row][col] = currentPlayer; // Set the player's symbol on the board
	}

	// Check if the move is valid
	private static boolean isValidMove(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) { // Check if the row and column are within the board
			System.out.println("Invalid row or column.");
			return false;
		} else if (board[row][col] != ' ') { // Check if the cell is already occupied
			System.out.println("That cell is already occupied.");
			return false;
		}
		return true;
	}

	// Check if there is a winner
	private static void checkWinner() {
		for (int i = 0; i < 3; i++) {
			// Check rows
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				System.out.println("Player " + currentPlayer + " wins!");
				gameFinished = true;
				return;
			}
			// Check columns
			if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
				System.out.println("Player " + currentPlayer + " wins!");
				gameFinished = true;
				return;
			}
		}
		// Check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			System.out.println("Player " + currentPlayer + " wins!");
			gameFinished = true;
			return;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			System.out.println("Player " + currentPlayer + " wins!");
			gameFinished = true;
			return;
		}
	}

	// Check if the game is tied
	private static void checkTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') { // If there is an empty cell, the game is not tied yet
					return;
				}
			}
		}
		System.out.println("The game is tied!");
		gameFinished = true;
	}

	// Switch to the next player
	private static void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
}