package JST_B08;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] board = new char[3][3];
		boolean gameOver = false;
		char currentPlayer = 'X';
		int row, col;

		// initialize the board with empty spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}

		// main game loop
		while (!gameOver) {
			// print the current board
			printBoard(board);

			// ask for player input
			System.out.print("Player " + currentPlayer + ", enter row (0-2): ");
			row = scanner.nextInt();
			System.out.print("Player " + currentPlayer + ", enter col (0-2): ");
			col = scanner.nextInt();

			// check if the move is valid
			if (board[row][col] != ' ') {
				System.out.println("Invalid move! Please try again.");
				continue;
			}

			// make the move
			board[row][col] = currentPlayer;

			// check if the game is over
			if (checkWin(board, currentPlayer)) {
				gameOver = true;
				System.out.println("Player " + currentPlayer + " wins!");
			} else if (checkTie(board)) {
				gameOver = true;
				System.out.println("Game over! It's a tie.");
			}

			// switch to the other player
			currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		}
	}

	// method to print the board
	public static void printBoard(char[][] board) {
		System.out.println("   0 1 2");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + board[i][j]);
				if (j < 2) {
					System.out.print(" |");
				}
			}
			System.out.println();
			if (i < 2) {
				System.out.println("  ---+--+---");
			}
		}
	}

	// method to check if the game is won
	public static boolean checkWin(char[][] board, char player) {
		// check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		// if no win condition is met, return false
		return false;
	}

	// method to check if the game is tied
	public static boolean checkTie(char[][] board) {
		// check if there are any empty spaces left
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}

		// if no empty spaces are left, return true
		return true;
	}
}