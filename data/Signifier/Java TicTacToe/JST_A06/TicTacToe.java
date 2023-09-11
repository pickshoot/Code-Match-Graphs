package ticTacToeSA6;

import java.util.Scanner;

public class TicTacToe {
	// The game board
	private char[][] board;
	// The player who is currently playing
	private char currentPlayer;
	// A flag to keep track of whether the game is over
	private boolean gameOver;

	// Constructor to initialize the game
	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		gameOver = false;
		// Initialize the board with empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	// Method to print the board
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

	// Method to get the player's move
	public void getPlayerMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
		int row = scanner.nextInt() - 1;
		int col = scanner.nextInt() - 1;
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			System.out.println("Invalid input. Please enter a number between 1 and 3.");
			getPlayerMove();
		} else if (board[row][col] != ' ') {
			System.out.println("That cell is already taken. Please choose another cell.");
			getPlayerMove();
		} else {
			board[row][col] = currentPlayer;
		}
	}

	// Method to switch the player
	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// Method to check if the game is over
	public void checkGameOver() {
		// Check for a win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				gameOver = true;
				return;
			}
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				gameOver = true;
				return;
			}
		}
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			gameOver = true;
			return;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			gameOver = true;
			return;
		}
		// Check for a tie
		boolean emptyCellsExist = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					emptyCellsExist = true;
					break;
				}
			}
			if (!emptyCellsExist) {
				gameOver = true;
			}
		}
	}

	// Method to play the game
	public void playGame() {
		System.out.println("Let's play Tic Tac Toe!");
		printBoard();
		while (!gameOver) {
			getPlayerMove();
			printBoard();
			checkGameOver();
			if (gameOver) {
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}
			switchPlayer();
		}
		if (!gameOver) {
			System.out.println("It's a tie!");
		}
	}

	// The main method to run the game
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.playGame();
	}
}