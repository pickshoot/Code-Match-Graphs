package JST_B05;

import java.util.Scanner;

public class TicTacToe {

	// initialize a 3x3 game board with empty spaces
	private static char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	// keep track of whose turn it is
	private static char currentPlayer = 'X';

	// method to print the game board to the console
	public static void printBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.println("-------------");
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	// method to check if a player has won the game
	public static boolean checkWin(char player) {
		// check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
				return true;
			}
		}

		// check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		// if no win, return false
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// loop until the game is over
		while (true) {
			// print the board and prompt the player to make a move
			printBoard();
			System.out.println("It is " + currentPlayer + "'s turn. Enter row and column (e.g. 1 2):");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			// check if the move is valid
			if (row < 1 || row > 3 || col < 1 || col > 3) {
				System.out.println("Invalid move. Try again.");
				continue;
			}
			if (board[row - 1][col - 1] != ' ') {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			// make the move and check if the game is over
			board[row - 1][col - 1] = currentPlayer;
			if (checkWin(currentPlayer)) {
				printBoard();
				System.out.println(currentPlayer + " wins!");
				break;
			}
			if (board[0][0] != ' ' && board[0][1] != ' ' && board[0][2] != ' ' && board[1][0] != ' '
					&& board[1][1] != ' ' && board[1][2] != ' ' && board[2][0] != ' ' && board[2][1] != ' '
					&& board[2][2] != ' ') {
				printBoard();
				System.out.println("It is a tie!");
				break;
			}
			// switch to the other player's turn
			if (currentPlayer == 'X') {
				currentPlayer = 'O';
			} else {
				currentPlayer = 'X';
			}
		}

		// close the scanner
		scanner.close();
	}
}