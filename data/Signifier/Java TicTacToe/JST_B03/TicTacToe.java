package JST_B03;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayer;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayer = 'X';
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
			if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board[0][0], board[1][1], board[2][2]))
				|| (checkRowCol(board[0][2], board[1][1], board[2][0])));
	}

	private boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	public void changePlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	public void placeMark(int row, int col) {
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayer;
				}
			}
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner input = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game\n");
		game.printBoard();
		System.out.println("Player X will go first. Enter a row and column to place X in:");

		int row, col;
		while (!game.isBoardFull() && !game.checkForWin()) {
			System.out.print("Row: ");
			row = input.nextInt();
			System.out.print("Column: ");
			col = input.nextInt();
			game.placeMark(row, col);
			game.printBoard();
			if (game.checkForWin()) {
				System.out.println("Congratulations! " + game.currentPlayer + " wins!");
			} else if (game.isBoardFull()) {
				System.out.println("It's a tie!");
			} else {
				game.changePlayer();
				System.out.println("It's " + game.currentPlayer + "'s turn. Enter a row and column to place "
						+ game.currentPlayer + " in:");
			}
		}
	}
}