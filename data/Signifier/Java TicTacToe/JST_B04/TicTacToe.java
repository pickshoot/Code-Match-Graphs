package JST_B04;

import java.util.Scanner;

public class TicTacToe {

	private static char[][] board = new char[3][3];
	private static char player = 'X';

	public static void main(String[] args) {
		initBoard();
		displayBoard();
		play();
	}

	private static void initBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	private static void displayBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static void play() {
		boolean gameFinished = false;
		while (!gameFinished) {
			System.out.println("Player " + player + " turn.");
			System.out.print("Enter row (1-3): ");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt() - 1;
			System.out.print("Enter column (1-3): ");
			int col = scanner.nextInt() - 1;
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			if (board[row][col] != '-') {
				System.out.println("That spot is already taken. Try again.");
				continue;
			}
			board[row][col] = player;
			displayBoard();
			if (checkWin()) {
				System.out.println("Player " + player + " wins!");
				gameFinished = true;
			} else if (checkDraw()) {
				System.out.println("It's a draw!");
				gameFinished = true;
			} else {
				player = (player == 'X') ? 'O' : 'X';
			}
		}
	}

	private static boolean checkWin() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return true;
			}
		}
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
			return true;
		}
		return false;
	}

	private static boolean checkDraw() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}