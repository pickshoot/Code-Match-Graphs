package JDT_B04;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board = new char[3][3];
	private char currentPlayerMark;

	public TicTacToe() {
		initializeBoard();
		currentPlayerMark = 'X';
	}

	public void play() {
		boolean gameover = false;
		System.out.println("Welcome to Tic Tac Toe!");
		while (!gameover) {
			displayBoard();
			System.out.println(
					"It's " + currentPlayerMark + "'s turn. Enter row (0-2) and column (0-2) to place your mark:");
			Scanner scanner = new Scanner(System.in);
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			if (row < 0 || row >= 3 || col < 0 || col >= 3) {
				System.out.println("Invalid input. Please enter a row and column number between 0 and 2.");
				continue;
			}
			if (board[row][col] != ' ') {
				System.out.println("That cell is already occupied. Try again.");
				continue;
			}
			board[row][col] = currentPlayerMark;
			if (checkForWin()) {
				displayBoard();
				System.out.println("Congratulations! Player " + currentPlayerMark + " wins!");
				gameover = true;
			} else if (checkForTie()) {
				displayBoard();
				System.out.println("It's a tie!");
				gameover = true;
			} else {
				// Switch player
				currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
			}
		}
	}

	private void initializeBoard() {
		// Initialize each cell to empty space
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private void displayBoard() {
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

	private boolean checkForWin() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true;
			}
		}
		// Check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return true;
			}
		}
		// Check diagonals
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
			return true;
		}
		return false;
	}

	private boolean checkForTie() {
		// Check if all cells are occupied
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}
