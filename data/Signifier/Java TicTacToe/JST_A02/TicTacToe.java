package ticTacToeSA2;

import java.util.Scanner;

public class TicTacToe {
	static char[][] board = new char[3][3];
	static char currentPlayer = 'X';

	public static void main(String[] args) {
		initializeBoard();
		play();
	}

	public static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public static void play() {
		boolean gameWon = false;
		System.out.println("Welcome to Tic-Tac-Toe!");
		while (!gameWon) {
			System.out.println("It's " + currentPlayer + "'s turn.");
			displayBoard();
			makeMove();
			gameWon = checkWin();
			switchPlayer();
		}
		displayBoard();
		System.out.println(currentPlayer + " wins!");
	}

	public static void displayBoard() {
		System.out.println("   0 1 2");
		for (int i = 0; i < 3; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void makeMove() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter row: ");
		int row = scanner.nextInt();
		System.out.print("Enter column: ");
		int col = scanner.nextInt();
		while (board[row][col] != '-') {
			System.out.println("That spot is already taken. Please choose a different spot.");
			System.out.print("Enter row: ");
			row = scanner.nextInt();
			System.out.print("Enter column: ");
			col = scanner.nextInt();
		}
		board[row][col] = currentPlayer;
	}

	public static boolean checkWin() {
		if (checkRows() || checkCols() || checkDiagonals()) {
			return true;
		}
		return false;
	}

	public static boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCols() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return true;
			}
		}
		return false;
	}

	public static boolean checkDiagonals() {
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-')) {
			return true;
		}
		return false;
	}

	public static void switchPlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
}