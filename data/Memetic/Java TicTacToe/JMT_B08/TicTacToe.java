package B8;

import java.util.Scanner;

class Board {
	private char[][] board;

	public Board() {
		board = new char[3][3];
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
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
				}
			}
		}
		return isFull;
	}

	public boolean checkForWin(char player) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}
		return false;
	}

	public void placeMark(int row, int col, char mark) {
		board[row][col] = mark;
	}

	public char[][] getBoard() {
		return board;
	}
}

class Game {
	private Board board;
	private char currentPlayerMark;

	public Game() {
		board = new Board();
		currentPlayerMark = 'X';
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe!");
		board.printBoard();
		while (!board.isBoardFull()) {
			System.out.println("It's " + currentPlayerMark + "'s turn. Enter row (1-3) and column (1-3): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			if (board.getBoard()[row][col] != '-') {
				System.out.println("That spot is already taken. Try again.");
				continue;
			}
			board.placeMark(row, col, currentPlayerMark);
			board.printBoard();
			if (board.checkForWin(currentPlayerMark)) {
				System.out.println(currentPlayerMark + " wins!");
				return;
			}
			currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
		}
		System.out.println("It's a tie!");
	}
}

public class TicTacToe {
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}