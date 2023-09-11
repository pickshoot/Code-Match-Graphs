package JDT_B05;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
		boolean playerOneTurn = true;
		boolean gameEnded = false;
		while (!gameEnded) {
			// Print the board
			for (int i = 0; i < 3; i++) {
				System.out.println(board[i][0] + "|" + board[i][1] + "|" + board[i][2]);
				if (i < 2) {
					System.out.println("-+-+-");
				}
			}
			// Get the player's move
			int row, col;
			do {
				System.out.println("Enter row (1-3) and column (1-3) separated by space:");
				row = scanner.nextInt() - 1;
				col = scanner.nextInt() - 1;
			} while (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ');
			char symbol = playerOneTurn ? 'X' : 'O';
			board[row][col] = symbol;
			// Check if the game is over
			if (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol
					|| board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol
					|| board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol
					|| board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol
					|| board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol
					|| board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol
					|| board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol
					|| board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
				System.out.println(symbol + " wins!");
				gameEnded = true;
			} else if (board[0][0] != ' ' && board[0][1] != ' ' && board[0][2] != ' ' && board[1][0] != ' '
					&& board[1][1] != ' ' && board[1][2] != ' ' && board[2][0] != ' ' && board[2][1] != ' '
					&& board[2][2] != ' ') {
				System.out.println("It's a tie!");
				gameEnded = true;
			} else {
				playerOneTurn = !playerOneTurn;
			}
		}
		scanner.close();
	}
}