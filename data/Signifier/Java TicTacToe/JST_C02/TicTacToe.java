package JST_C02;

import java.util.Scanner;

public class TicTacToe {

	static int[][] board = new int[3][3];
	static int player = 1;

	public static void main(String[] args) {
		initBoard();
		printBoard();

		int status = 0;
		while (status == 0) {
			makeMove();
			printBoard();
			status = checkWin();
			if (status == 1) {
				System.out.println("Player 1 wins!");
			} else if (status == 2) {
				System.out.println("Player 2 wins!");
			} else if (status == 3) {
				System.out.println("Tie game!");
			}
			player = 3 - player; // switch player
		}
	}

	public static void initBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = 0;
			}
		}
	}

	public static void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(getBoardSymbol(board[i][j]));
				if (j < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < 2) {
				System.out.println("-+-+-");
			}
		}
	}

	public static String getBoardSymbol(int value) {
		if (value == 0) {
			return " ";
		} else if (value == 1) {
			return "X";
		} else if (value == 2) {
			return "O";
		}
		return "?";
	}

	public static void makeMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player " + player + ", enter row (1-3): ");
		int row = scanner.nextInt() - 1;
		System.out.print("Player " + player + ", enter column (1-3): ");
		int col = scanner.nextInt() - 1;
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			System.out.println("Invalid input, try again!");
			makeMove();
		} else if (board[row][col] != 0) {
			System.out.println("That space is already occupied, try again!");
			makeMove();
		} else {
			board[row][col] = player;
		}
	}

	public static int checkWin() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return player;
			}
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return player;
			}
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return player;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return player;
		}
		boolean tie = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {
					tie = false;
				}
			}
		}
		if (tie) {
			return 3;
		}
		return 0;
	}
}