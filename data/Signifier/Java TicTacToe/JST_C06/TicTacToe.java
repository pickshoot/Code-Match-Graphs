package JST_C06;

import java.util.Scanner;

public class TicTacToe {

	private char[][] board = new char[3][3];
	private char currentPlayerMark;

	public TicTacToe() {
		currentPlayerMark = 'X';
		initializeBoard();
	}

	public void initializeBoard() {
		// Initialize each cell in the board with a space
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
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
		// Check if every cell in the board is filled with either X or O
		boolean isFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					isFull = false;
				}
			}
		}

		return isFull;
	}

	public boolean checkForWin() {
		// Check for row win
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true;
			}
		}

		// Check for column win
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				return true;
			}
		}

		// Check for diagonal win
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return true;
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return true;
		}

		return false;
	}

	public void changePlayer() {
		if (currentPlayerMark == 'X') {
			currentPlayerMark = 'O';
		} else {
			currentPlayerMark = 'X';
		}
	}

	public boolean placeMark(int row, int col) {
		// Check if the given row and column values are within the board range
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (board[row][col] == ' ') {
				board[row][col] = currentPlayerMark;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Current board layout:");
			game.printBoard();

			System.out.print("Player " + game.currentPlayerMark
					+ ", please enter the row and column where you want to place your mark (row column):");

			int row = scanner.nextInt();
			int col = scanner.nextInt();

			if (!game.placeMark(row, col)) {
				System.out.println("Invalid move. Please try again.");
			} else {
				if (game.checkForWin()) {
					System.out.println("Congratulations! Player " + game.currentPlayerMark + " has won the game!");
					break;
				} else if (game.isBoardFull()) {
					System.out.println("It's a tie!");
					break;
				} else {
					game.changePlayer();
				}
			}
		}
		scanner.close();
	}
}