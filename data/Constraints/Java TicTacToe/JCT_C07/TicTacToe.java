package JCT_C07;

import java.util.Scanner;

public class TicTacToe {
	// Define constants for the player tokens
	private static final char EMPTY = '-';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';

	private static final int BOARD_SIZE = 3; // Size of the game board

	private char[][] board; // The game board
	private char currentPlayer; // The player whose turn it is

	private Scanner scanner;

	public TicTacToe() {
		// Initialize the game board and set the starting player
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}

		currentPlayer = PLAYER_X; // Player X goes first

		scanner = new Scanner(System.in);
	}

	public void play() {
		System.out.println("Welcome to Tic-Tac-Toe!");

		// Loop until the game is over
		while (!isGameOver()) {
			printBoard();
			makeMove();
			switchPlayer();
		}

		// Game is over, print final board and winner
		printBoard();
		System.out.println(getWinner() + " wins!");
	}

	private void printBoard() {
		System.out.println();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private void makeMove() {
		// Loop until the user enters a valid move
		while (true) {
			System.out.print(currentPlayer + "'s turn. Enter row (1-" + BOARD_SIZE + "): ");
			int row = readInput();
			System.out.print("Enter column (1-" + BOARD_SIZE + "): ");
			int col = readInput();

			// Check if the move is valid
			if (row < 1 || row > BOARD_SIZE || col < 1 || col > BOARD_SIZE || board[row - 1][col - 1] != EMPTY) {
				System.out.println("Invalid move, try again.");
			} else {
				board[row - 1][col - 1] = currentPlayer;
				break;
			}
		}
	}

	private int readInput() {
		// Loop until the user enters a valid input
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input, try again.");
			scanner.next();
		}
		return scanner.nextInt();
	}

	private void switchPlayer() {
		if (currentPlayer == PLAYER_X) {
			currentPlayer = PLAYER_O;
		} else {
			currentPlayer = PLAYER_X;
		}
	}

	private boolean isGameOver() {
		// Check if the board is full
		boolean isFull = true;
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY) {
					isFull = false;
					break;
				}
			}
			if (!isFull) {
				break;
			}
		}
		if (isFull) {
			return true;
		}

		// Check for a winning row
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
				return true;
			}
		}

		// Check for a winning column
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
				return true;
			}
		}

		// Check for a winning diagonal
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}

		// If no winning conditions are met, the game is not over yet
		return false;
	}

	private char getWinner() {
		// Check for a winning row
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
				return currentPlayer;
			}
		}

		// Check for a winning column
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
				return currentPlayer;
			}
		}

		// Check for a winning diagonal
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return currentPlayer;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return currentPlayer;
		}

		// If no winning conditions are met, there is no winner yet
		return EMPTY;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}