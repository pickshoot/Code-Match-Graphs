package ticTacToeSA3;

import java.util.Scanner;

public class TicTacToe {
	static Scanner sc = new Scanner(System.in);

	static char[][] board = new char[3][3];
	static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();

		boolean gameOver = false;
		int moves = 0;

		while (!gameOver) {
			displayBoard();

			int row = getRow();
			int col = getCol();

			if (board[row][col] == '-') {
				board[row][col] = currentPlayer;
				moves++;
				gameOver = checkForWin(row, col);

				if (currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}
			} else {
				System.out.println("That spot is already taken.");
			}

			if (moves == 9) {
				gameOver = true;
				System.out.println("Tie game!");
			}
		}
	}

	public static void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static void displayBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	public static int getRow() {
		System.out.print("Enter row (0-2): ");
		int row = sc.nextInt();
		return row;
	}

	public static int getCol() {
		System.out.print("Enter column (0-2): ");
		int col = sc.nextInt();
		return col;
	}

	public static boolean checkForWin(int row, int col) {
		// check for row win
		if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
			System.out.println(currentPlayer + " wins!");
			return true;
		}

		// check for column win
		if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
			System.out.println(currentPlayer + " wins!");
			return true;
		}

		// check for diagonal win
		if (row == col && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			System.out.println(currentPlayer + " wins!");
			return true;
		}

		if (row + col == 2 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			System.out.println(currentPlayer + " wins!");
			return true;
		}

		return false;
	}
}