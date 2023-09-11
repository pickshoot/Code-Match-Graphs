package JST_B06;

import java.util.Scanner;

public class TicTacToe {
	// The game board
	private char[][] board;

	// The current player (X or O)
	private char currentPlayer;

	// Initialize the game
	public void init() {
		board = new char[3][3];
		currentPlayer = 'X';
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	// Draw the game board
	public void draw() {
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

	// Switch to the other player
	public void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	// Make a move
	public void makeMove(int row, int col) {
		if (board[row][col] == '-') {
			board[row][col] = currentPlayer;
		} else {
			System.out.println("Invalid move");
			switchPlayer();
		}
	}

	// Check if the game is over
	public boolean isGameOver() {
		if (isWin('X') || isWin('O') || isTie()) {
			return true;
		}
		return false;
	}

	// Check if there is a win
	public boolean isWin(char player) {
		for (int row = 0; row < 3; row++) {
			if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
				return true;
			}
		}
		for (int col = 0; col < 3; col++) {
			if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
				return true;
			}
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}
		return false;
	}

	// Check if there is a tie
	public boolean isTie() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.init();
		Scanner scanner = new Scanner(System.in);
		while (!game.isGameOver()) {
			game.draw();
			System.out.println(game.currentPlayer + "'s turn");
			System.out.print("Enter row number: ");
			int row = scanner.nextInt();
			System.out.print("Enter column number: ");
			int col = scanner.nextInt();
			game.makeMove(row, col);
			game.switchPlayer();
		}
		game.draw();
		if (game.isWin('X')) {
			System.out.println("X wins!");
		} else if (game.isWin('O')) {
			System.out.println("O wins!");
		} else {
			System.out.println("Tie!");
		}
	}
}