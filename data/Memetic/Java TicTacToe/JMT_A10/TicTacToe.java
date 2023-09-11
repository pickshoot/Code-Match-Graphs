package A10;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		initializeBoard();
	}

	public void initializeBoard() {
		// Initialize each cell of the board to a space character
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

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

	public boolean isBoardFull() {
		// Check if any cell of the board is empty (contains a space character)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkForWin() {
		// Check rows for a win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns for a win
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}

		// Check diagonals for a win
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}

		return false;
	}

	public void changePlayer() {
		// Change the current player to the other player
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	public void placeMark(int row, int col) {
		// Place the current player's mark at the specified row and column
		board[row][col] = currentPlayer;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TicTacToe game = new TicTacToe();

		System.out.println("Welcome to Tic-Tac-Toe!");
		game.printBoard();

		// Game loop
		while (true) {
			System.out.println("Player " + game.currentPlayer + ", enter row and column (0-2): ");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			if (row < 0 || row > 2 || col < 0 || col > 2 || game.board[row][col] != ' ') {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			game.placeMark(row, col);
			game.printBoard();

			if (game.checkForWin()) {
				System.out.println("Congratulations, player " + game.currentPlayer + " wins!");
				break;
			}

			if (game.isBoardFull()) {
				System.out.println("It's a tie!");
				break;
			}

			game.changePlayer();
		}
	}
}