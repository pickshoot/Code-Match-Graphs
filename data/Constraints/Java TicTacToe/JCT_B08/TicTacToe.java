package JCT_B08;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final char EMPTY_SPACE = ' ';
	private static final char PLAYER_ONE_SYMBOL = 'X';
	private static final char PLAYER_TWO_SYMBOL = 'O';

	private char[][] board;
	private int currentPlayer;

	public TicTacToe() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		currentPlayer = 1;
		initializeBoard();
	}

	private void initializeBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				board[row][col] = EMPTY_SPACE;
			}
		}
	}

	private void printBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print(board[row][col]);
				if (col != BOARD_SIZE - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row != BOARD_SIZE - 1) {
				System.out.println("-----");
			}
		}
	}

	public boolean isBoardFull() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == EMPTY_SPACE) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWinner(char symbol) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				return true;
			}
			if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
				return true;
			}
		}
		if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
			return true;
		}
		if (board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol) {
			return true;
		}
		return false;
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean isGameOver = false;
		while (!isGameOver) {
			System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] col[1-3]):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
				System.out.println("Invalid move, try again");
				continue;
			}
			if (board[row][col] != EMPTY_SPACE) {
				System.out.println("That spot is already taken, try again");
				continue;
			}
			board[row][col] = currentPlayer == 1 ? PLAYER_ONE_SYMBOL : PLAYER_TWO_SYMBOL;
			printBoard();
			if (isWinner(PLAYER_ONE_SYMBOL)) {
				System.out.println("Player 1 wins!");
				isGameOver = true;
			} else if (isWinner(PLAYER_TWO_SYMBOL)) {
				System.out.println("Player 2 wins!");
				isGameOver = true;
			} else if (isBoardFull()) {
				System.out.println("It's a tie!");
				isGameOver = true;
			} else {
				currentPlayer = currentPlayer == 1 ? 2 : 1;
			}
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.printBoard();
		game.play();
	}
}