package A1;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private char currentPlayerMark;

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'X';
		initializeBoard();
	}

	public void initializeBoard() {
		// Initialize the board with empty cells
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
		// Check if the board is full
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkForWin() {
		// Check for a win horizontally
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}

		// Check for a win vertically
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return true;
			}
		}

		// Check for a win diagonally (top left to bottom right)
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return true;
		}

		// Check for a win diagonally (top right to bottom left)
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
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
		// Check if the cell is empty
		if (board[row][col] == '-') {
			board[row][col] = currentPlayerMark;
			return true;
		} else {
			return false;
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe!");
		System.out.println("Enter row and column number (1-3) to place your mark.");

		while (!isBoardFull()) {
			printBoard();
			int row, col;
			do {
				System.out.printf("Player %s, enter row (1-3): ", currentPlayerMark);
				row = scanner.nextInt() - 1;
				System.out.printf("Player %s, enter column (1-3): ", currentPlayerMark);
				col = scanner.nextInt() - 1;
			} while (!placeMark(row, col));

			if (checkForWin()) {
				printBoard();
				System.out.printf("Player %s wins!", currentPlayerMark);
				break;
			}

			changePlayer();
		}

		if (!checkForWin()) {
			printBoard();
			System.out.println("It's a tie!");
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}