package JCT_A05;

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
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true)
				|| (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	}

	private boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game");

		do {
			game.printBoard();
			int row, col;
			do {
				System.out.println("Player " + game.currentPlayerMark);
				System.out.print("Enter row (1-3) and column (1-3) to place your mark: ");
				row = scanner.nextInt() - 1;
				col = scanner.nextInt() - 1;
			} while (!game.placeMark(row, col));
			game.changePlayer();
		} while (!game.checkForWin() && !game.isBoardFull());

		if (game.isBoardFull() && !game.checkForWin()) {
			System.out.println("It's a tie!");
		} else {
			game.printBoard();
			System.out.println("Player " + game.currentPlayerMark + " wins!");
		}
	}
}