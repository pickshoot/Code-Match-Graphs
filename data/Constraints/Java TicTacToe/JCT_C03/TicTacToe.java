package JCT_C03;

import java.util.Scanner;

public class TicTacToe {

	private char[][] board = new char[3][3];
	private char currentPlayer = 'X';

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

	public boolean placeMark(int row, int col) {
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayer;
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		game.initializeBoard();
		System.out.println("Tic-Tac-Toe Game");
		System.out.println("----------------");
		game.printBoard();
		while (!game.isBoardFull()) {
			System.out.print("Player " + game.currentPlayer + ", enter your row and column (e.g. 1 2): ");
			String input = scanner.nextLine();
			String[] tokens = input.split(" ");
			if (tokens.length == 2) {
				try {
					int row = Integer.parseInt(tokens[0]) - 1;
					int col = Integer.parseInt(tokens[1]) - 1;
					if (game.placeMark(row, col)) {
						game.printBoard();
						if (game.checkForWin()) {
							System.out.println("Congratulations, Player " + game.currentPlayer + " wins!");
							return;
						}
						game.changePlayer();
					} else {
						System.out.println("Invalid move, please try again.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input, please enter two integers separated by a space.");
				}
			} else {
				System.out.println("Invalid input, please enter two integers separated by a space.");
			}
		}
		System.out.println("It's a tie!");
	}
}