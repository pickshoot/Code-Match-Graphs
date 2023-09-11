package JCT_C02;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Initialize the game board
		char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

		// Display the initial board
		displayBoard(board);

		// Keep track of whose turn it is
		char currentPlayer = 'X';

		// Play the game until there is a winner or a tie
		while (true) {
			// Ask the current player to make a move
			System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
			int row = scanner.nextInt();
			int column = scanner.nextInt();

			// Check if the move is valid
			if (row < 0 || row > 2 || column < 0 || column > 2 || board[row][column] != ' ') {
				System.out.println("Invalid move. Please try again.");
				continue;
			}

			// Update the game board with the current player's move
			board[row][column] = currentPlayer;

			// Display the updated board
			displayBoard(board);

			// Check if the current player has won
			if (checkWin(board, currentPlayer)) {
				System.out.println("Player " + currentPlayer + " wins!");
				break;
			}

			// Check if the game is a tie
			if (checkTie(board)) {
				System.out.println("The game is a tie!");
				break;
			}

			// Switch to the other player's turn
			currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
		}
	}

	// Display the game board
	public static void displayBoard(char[][] board) {
		System.out.println("   0 1 2");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Check if the current player has won
	public static boolean checkWin(char[][] board, char currentPlayer) {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
				return true;
			}
		}

		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
				return true;
			}
		}

		// Check diagonals
		if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			return true;
		}
		if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			return true;
		}

		// If no winning combination was found, return false
		return false;
	}

	// Check if the game is a tie
	public static boolean checkTie(char[][] board) {
		// Check if all squares are filled
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}

		// If all squares are filled and there is no winner, the game is a tie
		return true;
	}
}