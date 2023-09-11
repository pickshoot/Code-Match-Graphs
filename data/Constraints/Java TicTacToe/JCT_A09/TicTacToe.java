package JCT_A09;

import java.util.Scanner;

class TicTacToe {
	private char[][] board; // 3x3 game board
	private char currentPlayer; // current player

	// constructor
	public TicTacToe() {
		board = new char[3][3]; // initialize board
		currentPlayer = 'X'; // X plays first
		initializeBoard(); // fill board with empty spaces
	}

	// initialize board with empty spaces
	public void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = ' ';
			}
		}
	}

	// print the board to the console
	public void printBoard() {
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

	// switch players
	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// get the current player
	public char getCurrentPlayer() {
		return currentPlayer;
	}

	// check if the board is full
	public boolean isBoardFull() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// check if the game is won
	public boolean checkForWin() {
		// check rows
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
				return true;
			}
		}

		// check columns
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}

		return false;
	}

	// play the game
	public void play() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe!");

		// loop until the game is over
		while (true) {
			System.out.println("It's " + currentPlayer + "'s turn. Enter row and column (1-3): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			// check if the move is valid
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid move. Try again.");
				continue;
			}
			if (board[row][col] != ' ') {
				System.out.println("That spot is already taken. Try again.");
				continue;
			}

			// make the move
			board[row][col] = currentPlayer;

			// print the board
			printBoard();

			// check for win or tie
			if (checkForWin()) {
				System.out.println(currentPlayer + " wins!");
				break;
			} else if (isBoardFull()) {
				System.out.println("Tie game.");
				break;
			}

			// switch players
			switchPlayer();
		}
	}
}