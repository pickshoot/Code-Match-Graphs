package JDT_B07;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char PLAYER_1_SYMBOL = 'X';
	private static final char PLAYER_2_SYMBOL = 'O';

	private char[][] board;
	private char currentPlayerSymbol;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		currentPlayerSymbol = PLAYER_1_SYMBOL;
		initializeBoard();
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Tic-Tac-Toe!");

		boolean gameOver = false;
		while (!gameOver) {
			printBoard();

			int row, col;
			do {
				System.out.printf("Player %c's turn. Enter row (1-%d) and column (1-%d) separated by space: ",
						currentPlayerSymbol, BOARD_SIZE, BOARD_SIZE);
				row = scanner.nextInt() - 1;
				col = scanner.nextInt() - 1;
			} while (!isValidMove(row, col));

			makeMove(row, col);
			if (isGameOver()) {
				gameOver = true;
				printBoard();
				if (isWinner()) {
					System.out.printf("Player %c wins!\n", currentPlayerSymbol);
				} else {
					System.out.println("It's a draw!");
				}
			} else {
				currentPlayerSymbol = (currentPlayerSymbol == PLAYER_1_SYMBOL) ? PLAYER_2_SYMBOL : PLAYER_1_SYMBOL;
			}
		}

		scanner.close();
	}

	private void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private void printBoard() {
		System.out.println("---------");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("---------");
		}
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			System.out.println("Invalid row or column. Please try again.");
			return false;
		}
		if (board[row][col] != ' ') {
			System.out.println("This cell is already occupied. Please try again.");
			return false;
		}
		return true;
	}

	private void makeMove(int row, int col) {
		board[row][col] = currentPlayerSymbol;
	}

	private boolean isGameOver() {
		if (isWinner() || isBoardFull()) {
			return true;
		}
		return false;
	}

	private boolean isWinner() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			// check rows
			if (board[i][0] == currentPlayerSymbol && board[i][1] == currentPlayerSymbol
					&& board[i][2] == currentPlayerSymbol) {
				return true;
			}
			// check columns
			if (board[0][i] == currentPlayerSymbol && board[1][i] == currentPlayerSymbol
					&& board[2][i] == currentPlayerSymbol) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol
				&& board[2][2] == currentPlayerSymbol) {
			return true;
		}
		if (board[2][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol
				&& board[0][2] == currentPlayerSymbol) {
			return true;
		}
		return false;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
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
