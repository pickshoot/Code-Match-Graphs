package C2;

import java.util.Scanner;

public class TicTacToe {

	// Board to store current state of game
	private char[][] board;

	// Player symbols
	private char player1Symbol = 'X';
	private char player2Symbol = 'O';

	// Current player
	private char currentPlayerSymbol;

	// Scanner for user input
	private Scanner scanner;

	// Constructor to initialize the game
	public TicTacToe() {
		// Create a 3x3 board
		board = new char[3][3];
		// Fill the board with empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
		// Player 1 starts first
		currentPlayerSymbol = player1Symbol;
		// Create scanner object for user input
		scanner = new Scanner(System.in);
	}

	// Method to display the current state of the board
	public void displayBoard() {
		System.out.println("   1   2   3");
		System.out.println("1  " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
		System.out.println("  ---+---+---");
		System.out.println("2  " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
		System.out.println("  ---+---+---");
		System.out.println("3  " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
	}

	// Method to get user input for their move
	public void makeMove() {
		System.out.println("It is " + currentPlayerSymbol + "'s turn.");
		System.out.print("Enter row number (1-3): ");
		int row = scanner.nextInt() - 1;
		System.out.print("Enter column number (1-3): ");
		int col = scanner.nextInt() - 1;
		// Check if the move is valid
		if (isValidMove(row, col)) {
			board[row][col] = currentPlayerSymbol;
		} else {
			System.out.println("Invalid move. Please try again.");
			makeMove();
		}
	}

	// Method to check if a move is valid
	public boolean isValidMove(int row, int col) {
		// Check if row and column are within the bounds of the board
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		// Check if the space is already taken
		if (board[row][col] != ' ') {
			return false;
		}
		return true;
	}

	// Method to check if there is a winner
	public boolean hasWinner() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return true;
			}
		}
		// Check diagonals
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
			return true;
		}
		// If no winner, return false
		return false;
	}

	// Method to switch the current player
	public void switchPlayer() {
		if (currentPlayerSymbol == player1Symbol) {
			currentPlayerSymbol = player2Symbol;
		} else {
			currentPlayerSymbol = player1Symbol;
		}
	}

	// Method to start the game
	public void startGame() {
		System.out.println("Welcome to Tic-Tac-Toe!");
		while (true) {
			displayBoard();
			makeMove();
			if (hasWinner()) {
				System.out.println("Congratulations, " + currentPlayerSymbol + " has won!");
				displayBoard();
				break;
			}
			switchPlayer();
		}
		scanner.close();
	}

	// Main method to start the program
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.startGame();
	}

}