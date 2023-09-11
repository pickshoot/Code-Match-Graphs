package JDT_A04;

import java.util.Scanner;

public class TicTacToe {
// The game board
	private static char[][] board;

// The current player
	private static char player;

	public static void main(String[] args) {
// Initialize the board
		board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
// Initialize the player
		player = 'X';

// Start the game
		playGame();
	}

	private static void playGame() {
		System.out.println("Welcome to Tic-Tac-Toe!");
		printBoard();
// Loop until the game is over
		while (true) {
			// Get the player's move
			int[] move = getPlayerMove();

			// Make the move
			board[move[0]][move[1]] = player;

			// Print the updated board
			printBoard();

			// Check if the game is over
			if (checkForWin()) {
				System.out.println("Game over! Player " + player + " wins!");
				break;
			} else if (checkForDraw()) {
				System.out.println("Game over! It's a draw!");
				break;
			}

			// Switch to the other player
			player = (player == 'X') ? 'O' : 'X';
		}
	}

	private static int[] getPlayerMove() {
		Scanner scanner = new Scanner(System.in);
// Loop until a valid move is entered
		while (true) {
			System.out.print("Player " + player + ", enter your move (row column): ");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			// Check if the move is valid
			if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
				return new int[] { row, col };
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}
	}

	private static boolean checkForWin() {
// Check rows for a win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
// Check columns for a win
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}

// Check diagonals for a win
		if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}

		return false;
	}

	private static boolean checkForDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					// If there is an empty space, the game is not a draw
					return false;
				}
			}
		}
		// If all spaces are filled and there is no winner, the game is a draw
		return true;
	}

	private static void printBoard() {
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
}