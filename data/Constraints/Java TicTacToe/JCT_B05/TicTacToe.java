package JCT_B05;

import java.util.*;

public class TicTacToe {

	private static final int BOARD_SIZE = 3;

	private enum Player {
		X, O
	}

	private enum GameResult {
		X_WINS, O_WINS, TIE, IN_PROGRESS
	}

	private Player[][] board;
	private Player currentPlayer;

	public TicTacToe() {
		board = new Player[BOARD_SIZE][BOARD_SIZE];
		currentPlayer = Player.X;
	}

	public void play() {
		while (getGameResult() == GameResult.IN_PROGRESS) {
			displayBoard();
			makeMove();
			currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
		}
		displayBoard();
		displayGameResult();
	}

	private void displayBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				System.out.print((board[row][col] == null) ? "-" : board[row][col]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private void makeMove() {
		int row = -1;
		int col = -1;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.printf("Player %s, enter your move (row[1-3] column[1-3]): ", currentPlayer);
			row = scanner.nextInt() - 1;
			col = scanner.nextInt() - 1;
		} while (!isValidMove(row, col));
		board[row][col] = currentPlayer;
	}

	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
			System.out.println("Invalid move, row and column must be between 1 and 3");
			return false;
		}
		if (board[row][col] != null) {
			System.out.println("Invalid move, that spot is already taken");
			return false;
		}
		return true;
	}

	private GameResult getGameResult() {
		// Check rows
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][0] != null && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
				return (board[row][0] == Player.X) ? GameResult.X_WINS : GameResult.O_WINS;
			}
		}
		// Check columns
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (board[0][col] != null && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
				return (board[0][col] == Player.X) ? GameResult.X_WINS : GameResult.O_WINS;
			}
		}
		// Check diagonals
		if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return (board[0][0] == Player.X) ? GameResult.X_WINS : GameResult.O_WINS;
		}
		if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return (board[0][2] == Player.X) ? GameResult.X_WINS : GameResult.O_WINS;
		}
		// Check for tie
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col] == null) {
					return GameResult.IN_PROGRESS;
				}
			}
		}
		return GameResult.TIE;
	}

	private void displayGameResult() {
		switch (getGameResult()) {
		case X_WINS:
			System.out.println("Player X wins!");
			break;
		case O_WINS:
			System.out.println("Player O wins!");
			break;
		case TIE:
			System.out.println("It's a tie!");
			break;
		default:
			// Should never get here
			throw new IllegalStateException("Unexpected game result");
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}