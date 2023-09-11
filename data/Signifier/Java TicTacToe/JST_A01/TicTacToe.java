package ticTacToeSA1;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		// initialize game board
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
		char player = 'X';

		// loop until the game is over
		while (true) {
			// display the board
			displayBoard(board);

			// get the player's move
			int[] move = getPlayerMove(board, player);
			int row = move[0];
			int col = move[1];

			// make the move
			board[row][col] = player;

			// check if the game is over
			if (isGameOver(board, player)) {
				displayBoard(board);
				System.out.println("Game over. " + player + " wins!");
				break;
			} else if (isBoardFull(board)) {
				displayBoard(board);
				System.out.println("Game over. Tie game!");
				break;
			}

			// switch to the other player
			player = (player == 'X') ? 'O' : 'X';
		}
	}

	// display the game board
	public static void displayBoard(char[][] board) {
		System.out.println("  0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
				if (j < board[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < board.length - 1) {
				System.out.println("  -----");
			}
		}
	}

	// get the player's move
	public static int[] getPlayerMove(char[][] board, char player) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print(player + "'s move (row column): ");
			int row = input.nextInt();
			int col = input.nextInt();
			if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
				System.out.println("Invalid move. Try again.");
			} else if (board[row][col] != ' ') {
				System.out.println("That space is already occupied. Try again.");
			} else {
				return new int[] { row, col };
			}
		}
	}

	// check if the game is over
	public static boolean isGameOver(char[][] board, char player) {
		// check rows
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// check columns
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
			return true;
		}

		return false;
	}

	// check if the board is full
	public static boolean isBoardFull(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

}