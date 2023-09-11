package JDT_A09;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayerMark;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	public void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
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
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
				}
			}
		}
		return isFull;
	}

	public boolean checkForWin() {
// check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
// check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}

// check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}

		return false;
	}

	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (board[row][col] == '-') {
				board[row][col] = currentPlayerMark;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			System.out.println("Current board layout:");
			game.printBoard();
			int row, col;
			do {
				System.out.println(
						"Player " + game.currentPlayerMark + ", enter an empty row and column to place your mark:");
				row = scanner.nextInt() - 1;
				col = scanner.nextInt() - 1;
			} while (!game.placeMark(row, col));
//check for win
			if (game.checkForWin()) {
				game.printBoard();
				System.out.println("Congratulations! Player " + game.currentPlayerMark + " wins!");
				gameOver = true;
			} else if (game.isBoardFull()) { // check for tie
				game.printBoard();
				System.out.println("It's a tie!");
				gameOver = true;
			} else { // change player
				game.changePlayer();
			}
		}
		scanner.close();
	}
}
