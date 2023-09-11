import java.util.Scanner;

public class ConnectFour {
	private char[][] board; // 2D array to represent the game board
	private int numRows; // number of rows on the game board
	private int numCols; // number of columns on the game board
	private char currentPlayer; // current player (either 'X' or 'O')

	public ConnectFour(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		board = new char[numRows][numCols];
		currentPlayer = 'X';
		initializeBoard();
	}

	private void initializeBoard() {
		// set every cell on the board to '-'
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		// print out the game board
		for (int i = numRows - 1; i >= 0; i--) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean isValidMove(int col) {
		// check if the given column is a valid move
		if (col < 0 || col >= numCols) {
			return false;
		}
		if (board[numRows - 1][col] != '-') {
			return false;
		}
		return true;
	}

	private void makeMove(int col) {
		// make a move in the given column
		for (int i = 0; i < numRows; i++) {
			if (board[i][col] == '-') {
				board[i][col] = currentPlayer;
				break;
			}
		}
	}

	private boolean checkForWin() {
		// check for a win on the game board
		// check rows
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j <= numCols - 4; j++) {
				if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer && board[i][j + 2] == currentPlayer
						&& board[i][j + 3] == currentPlayer) {
					return true;
				}
			}
		}
		// check columns
		for (int j = 0; j < numCols; j++) {
			for (int i = 0; i <= numRows - 4; i++) {
				if (board[i][j] == currentPlayer && board[i + 1][j] == currentPlayer && board[i + 2][j] == currentPlayer
						&& board[i + 3][j] == currentPlayer) {
					return true;
				}
			}
		}
		// check diagonals
		for (int i = 0; i <= numRows - 4; i++) {
			for (int j = 0; j <= numCols - 4; j++) {
				if (board[i][j] == currentPlayer && board[i + 1][j + 1] == currentPlayer
						&& board[i + 2][j + 2] == currentPlayer && board[i + 3][j + 3] == currentPlayer) {
					return true;
				}
			}
		}
		for (int i = 0; i <= numRows - 4; i++) {
			for (int j = numCols - 1; j >= 3; j--) {
				if (board[i][j] == currentPlayer && board[i + 1][j - 1] == currentPlayer
						&& board[i + 2][j - 2] == currentPlayer && board[i + 3][j - 3] == currentPlayer) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkForDraw() {
		// check if the game is a draw
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	private void switchPlayer() {
		// switch the current player
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver) {
			// print out the game board
			printBoard();
			// get the current player's move
			System.out.println("Player " + currentPlayer + ", please enter your move (0-" + (numCols - 1) + "): ");
			try {
				int col = scanner.nextInt();
				// validate the move
				if (!isValidMove(col)) {
					System.out.println("Invalid move. Please try again.");
					continue;
				}
				// make the move
				makeMove(col);
				// check for a win
				if (checkForWin()) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameOver = true;
				} else if (checkForDraw()) {
					System.out.println("Game over. It's a draw.");
					gameOver = true;
				} else {
					// switch the player
					switchPlayer();
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			}
		}
		// print out the final game board
		printBoard();
		scanner.close();
	}

	public static void main(String[] args) {
		ConnectFour game = new ConnectFour(6, 7);
		game.play();
	}
}
