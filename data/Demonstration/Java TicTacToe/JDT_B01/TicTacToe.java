package JDT_B01;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board = new char[3][3]; // 3x3 game board
	private char currentPlayer; // current player (either 'X' or 'O')

	public TicTacToe() {
		// initialize game board with empty cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
		currentPlayer = 'X'; // player 'X' goes first
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		int row, col;

		// loop until the game is over
		while (!isGameOver()) {
			// print current board
			printBoard();

			// get player's move
			System.out.println("Player " + currentPlayer + ", enter your move (row, col):");
			row = scanner.nextInt();
			col = scanner.nextInt();

			// update board with player's move
			if (makeMove(row, col)) {
				// switch to next player
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			} else {
				System.out.println("Invalid move, try again.");
			}
		}

		// print final board and game result
		printBoard();
		if (isDraw()) {
			System.out.println("It's a draw!");
		} else {
			System.out.println("Player " + currentPlayer + " wins!");
		}

		scanner.close();
	}

	private boolean makeMove(int row, int col) {
		// check if cell is empty and within board limits
		if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
			return false;
		}

		// update cell with player's symbol
		board[row][col] = currentPlayer;
		return true;
	}

	private boolean isGameOver() {
		return isWin() || isDraw();
	}

	private boolean isWin() {
		// check rows for win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}
		// check columns for win
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}
		// check diagonals for win
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}
		return false;
	}

	private boolean isDraw() {
		// check if all cells are filled
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		// no empty cells left, game is a draw
		return true;
	}

	private void printBoard() {
		System.out.println("|---|---|---|");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println("\n|---|---|---|");
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}
