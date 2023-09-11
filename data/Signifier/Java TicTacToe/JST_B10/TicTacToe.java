package JST_B10;

import java.util.Scanner;

public class TicTacToe {
	// game board
	private char[][] board;

	// current player
	private char currentPlayer;

	// game over flag
	private boolean gameOver;

	// constructor
	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
		gameOver = false;
		initBoard();
	}

	// initialize the game board
	private void initBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// print the game board
	private void printBoard() {
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

	// play the game
	public void play() {
		Scanner scanner = new Scanner(System.in);
		while (!gameOver) {
			printBoard();
			System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
			int row = scanner.nextInt() - 1;
			System.out.print("Player " + currentPlayer + ", enter column (1-3): ");
			int col = scanner.nextInt() - 1;
			if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
				System.out.println("Invalid input, please try again.");
			} else {
				board[row][col] = currentPlayer;
				if (checkWin(row, col)) {
					gameOver = true;
					System.out.println("Player " + currentPlayer + " wins!");
				} else if (checkTie()) {
					gameOver = true;
					System.out.println("It's a tie!");
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			}
		}
		scanner.close();
		printBoard();
	}

	// check if the game is a tie
	private boolean checkTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	// check if a player has won
	private boolean checkWin(int row, int col) {
		return (checkRow(row) || checkCol(col) || checkDiagonal(row, col));
	}

	// check if the current player has won in the row
	private boolean checkRow(int row) {
		return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer);
	}

	// check if the current player has won in the column
	private boolean checkCol(int col) {
		return (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer);
	}

	// check if the current player has won in the diagonal
	private boolean checkDiagonal(int row, int col) {
		if (row == col) {
			return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer);
		}
		if (row + col == 2) {
			return (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
		}
		return false;
	}

	// main method
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}