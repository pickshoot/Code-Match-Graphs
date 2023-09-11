package A8;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
		int currentPlayer = 1;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Print board
			System.out.println("-------------");
			for (int i = 0; i < 3; i++) {
				System.out.print("| ");
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + " | ");
				}
				System.out.println();
				System.out.println("-------------");
			}

			// Get user input
			System.out.println("Player " + currentPlayer + ", enter row number:");
			int row = scanner.nextInt();
			System.out.println("Player " + currentPlayer + ", enter column number:");
			int col = scanner.nextInt();

			// Check input validity
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid input! Row and column numbers must be between 0 and 2.");
				continue;
			}
			if (board[row][col] != ' ') {
				System.out.println("Invalid input! This cell is already occupied.");
				continue;
			}

			// Make move
			if (currentPlayer == 1) {
				board[row][col] = 'X';
				currentPlayer = 2;
			} else {
				board[row][col] = 'O';
				currentPlayer = 1;
			}

			// Check for win
			if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != ' ') {
				System.out.println("Player " + board[0][0] + " wins!");
				break;
			}
			if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != ' ') {
				System.out.println("Player " + board[1][0] + " wins!");
				break;
			}
			if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != ' ') {
				System.out.println("Player " + board[2][0] + " wins!");
				break;
			}
			if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != ' ') {
				System.out.println("Player " + board[0][0] + " wins!");
				break;
			}
			if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != ' ') {
				System.out.println("Player " + board[0][1] + " wins!");
				break;
			}
			if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != ' ') {
				System.out.println("Player " + board[0][2] + " wins!");
				break;
			}
			if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') {
				System.out.println("Player " + board[0][0] + " wins!");
				break;
			}
			if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ') {
				System.out.println("Player " + board[0][2] + " wins!");
				break;
			}

			// Check for tie
			boolean tie = true;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == ' ') {
						tie = false;
					}
				}
			}
			if (tie) {
				System.out.println("It's a tie!");
				break;
			}
		}
	}
}