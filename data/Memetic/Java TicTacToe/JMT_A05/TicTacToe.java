package A5;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		char[][] board = new char[3][3]; // initialize the game board
		boolean gameOver = false; // flag to check if game is over
		char currentPlayer = 'X'; // start with player X

		while (!gameOver) {
			// display the board
			displayBoard(board);

			// get the current player's move
			int row = getRow(currentPlayer);
			int col = getCol(currentPlayer);
			while (board[row][col] != '\u0000') { // check if the cell is empty
				System.out.println("That cell is already taken. Please choose another cell.");
				row = getRow(currentPlayer);
				col = getCol(currentPlayer);
			}

			// mark the cell with the current player's symbol
			board[row][col] = currentPlayer;

			// check if the game is over
			if (checkWin(board)) {
				System.out.println("Player " + currentPlayer + " wins!");
				gameOver = true;
			} else if (checkDraw(board)) {
				System.out.println("The game is a draw.");
				gameOver = true;
			}

			// switch to the other player
			currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
		}
	}

	public static void displayBoard(char[][] board) {
		System.out.println("   0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int getRow(char currentPlayer) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player " + currentPlayer + ", please enter row (0-2): ");
		int row = scanner.nextInt();
		return row;
	}

	public static int getCol(char currentPlayer) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player " + currentPlayer + ", please enter column (0-2): ");
		int col = scanner.nextInt();
		return col;
	}

	public static boolean checkWin(char[][] board) {
		// check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}

		// check columns
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] != '\u0000' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] != '\u0000' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != '\u0000' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}

		// no winner
		return false;
	}

	public static boolean checkDraw(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '\u0000') {
					return false; // there are still empty cells, so game is not a draw
				}
			}
		}
		return true; // all cells are filled and no winner, so game is a draw
	}
}