package JCT_C05;

import java.util.Scanner;

public class TicTacToe {
	// 2D array to store the board state
	private char[][] board;
	// player symbols
	private char player1Symbol;
	private char player2Symbol;

	public TicTacToe() {
		board = new char[3][3];
		player1Symbol = 'X';
		player2Symbol = 'O';
		// initialize the board with empty cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	// print the current board state
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

	// check if the game is over
	public boolean isGameOver() {
		return (hasWon(player1Symbol) || hasWon(player2Symbol) || isBoardFull());
	}

	// check if the board is full
	private boolean isBoardFull() {
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false;
					break;
				}
			}
		}
		return isFull;
	}

	// check if a player has won
	private boolean hasWon(char symbol) {
		// check rows
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				return true;
			}
		}
		// check columns
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
				return true;
			}
		}
		// check diagonals
		if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
			return true;
		}
		if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
			return true;
		}
		return false;
	}

	// get the next player's move
	public void getNextMove(char symbol) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the row index (0-2): ");
		int row = input.nextInt();
		System.out.print("Enter the column index (0-2): ");
		int col = input.nextInt();
		if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
			System.out.println("Invalid input, please try again");
			getNextMove(symbol);
		} else {
			board[row][col] = symbol;
		}
	}

	// main method to start the game
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		System.out.println("Welcome to Tic-Tac-Toe!");
		System.out.println("Player 1 is X, and Player 2 is O");
		char currentPlayer = game.player1Symbol;
		while (!game.isGameOver()) {
			game.printBoard();
			System.out.println("Player " + currentPlayer + "'s turn");
			game.getNextMove(currentPlayer);
			currentPlayer = (currentPlayer == game.player1Symbol) ? game.player2Symbol : game.player1Symbol;
		}
		game.printBoard();
		if (game.hasWon(game.player1Symbol)) {
			System.out.println("Player 1 wins!");
		} else if (game.hasWon(game.player2Symbol)) {
			System.out.println("Player 2 wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}