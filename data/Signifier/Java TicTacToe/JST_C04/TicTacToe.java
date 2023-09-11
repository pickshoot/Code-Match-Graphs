package JST_C04;

import java.util.Scanner;

public class TicTacToe {

	private char[][] board; // a 3x3 array to represent the board
	private char currentPlayer; // 'X' or 'O'

	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		initializeBoard();
	}

	// Initializes the board with empty spaces
	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	// Prints the board to the console
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

	// Checks if the board is full
	public boolean isBoardFull() {
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					isFull = false;
				}
			}
		}
		return isFull;
	}

	// Checks if a player has won
	public boolean checkForWin() {
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
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}

		return false;
	}

	// Changes the current player
	public void changePlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// Places a player's move on the board
	public void placeMove(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (board[row][col] == ' ') {
				board[row][col] = currentPlayer;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		game.printBoard();

		while (!game.checkForWin() && !game.isBoardFull()) {
			// Ask for player's move
			System.out.println("Player " + game.currentPlayer + ", enter your move (row [1-3] column [1-3]):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			game.placeMove(row, col);

			// Check for win or draw
			if (game.checkForWin()) {
				System.out.println("Player " + game.currentPlayer + " wins!");
				game.printBoard();
				break;
			} else if (game.isBoardFull()) {
				System.out.println("It's a draw!");
				game.printBoard();
				break;
			}

			// Change player
			game.changePlayer();

			// Print board
			game.printBoard();
		}
	}
}