package ticTacToeSA8;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char[][] board = new char[3][3];
		int row, col;
		char player = 'X';
		boolean gameover = false;

		// initialize the board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}

		// game loop
		while (!gameover) {
			// print the board
			System.out.println("-------------");
			for (int i = 0; i < 3; i++) {
				System.out.print("| ");
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + " | ");
				}
				System.out.println();
				System.out.println("-------------");
			}

			// prompt the player to make a move
			System.out.print("Player " + player + ", enter row (1-3): ");
			row = input.nextInt() - 1;
			System.out.print("Player " + player + ", enter column (1-3): ");
			col = input.nextInt() - 1;

			// check if the move is valid
			if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
				System.out.println("Invalid move. Please try again.");
				continue;
			}

			// make the move
			board[row][col] = player;

			// check if the game is over
			if (checkWinner(board, player)) {
				System.out.println("Player " + player + " wins!");
				gameover = true;
			} else if (isBoardFull(board)) {
				System.out.println("Game over. It's a tie!");
				gameover = true;
			}

			// switch to the other player
			player = (player == 'X') ? 'O' : 'X';
		}
	}

	// check if the given player has won the game
	public static boolean checkWinner(char[][] board, char player) {
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
		return false;
	}

	// check if the board is full
	public static boolean isBoardFull(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}