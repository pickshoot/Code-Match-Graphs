package JST_B07;

import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		char[][] board = new char[3][3];
		initializeBoard(board);
		printBoard(board);

		char currentPlayer = 'X';
		boolean gameFinished = false;

		while (!gameFinished) {
			int[] move = getPlayerMove(currentPlayer, board);
			board[move[0]][move[1]] = currentPlayer;
			printBoard(board);
			if (checkWin(board, currentPlayer)) {
				System.out.println(currentPlayer + " wins!");
				gameFinished = true;
			} else if (isBoardFull(board)) {
				System.out.println("Tie game.");
				gameFinished = true;
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			}
		}
	}

	// Initializes the board to all empty spaces.
	public static void initializeBoard(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	// Prints the current state of the board.
	public static void printBoard(char[][] board) {
		System.out.println("  0 1 2");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Asks the current player for their move and returns the row and column.
	public static int[] getPlayerMove(char player, char[][] board) {
		Scanner scanner = new Scanner(System.in);
		int[] move = new int[2];
		while (true) {
			System.out.print("Player " + player + ", enter your move (row column): ");
			move[0] = scanner.nextInt();
			move[1] = scanner.nextInt();
			if (isValidMove(move, board)) {
				break;
			}
			System.out.println("Invalid move. Try again.");
		}
		return move;
	}

	// Checks if the given move is valid (i.e., the space is empty and on the
	// board).
	public static boolean isValidMove(int[] move, char[][] board) {
		int row = move[0];
		int col = move[1];
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}
		if (board[row][col] != ' ') {
			return false;
		}
		return true;
	}

	// Checks if the board is full.
	public static boolean isBoardFull(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	// Checks if the given player has won.
	public static boolean checkWin(char[][] board, char player) {
		for (int i = 0; i < 3; i++) {
			// Check rows
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
			// Check columns
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}
		// Check diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
			return true;
		}
		return false;
	}
}