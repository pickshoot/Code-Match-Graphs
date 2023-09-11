package JDT_A07;

import java.util.Scanner;

public class TicTacToe {
	// Define the board size
	private static final int BOARD_SIZE = 3;

	// Define the player symbols
	private static final char PLAYER_X_SYMBOL = 'X';
	private static final char PLAYER_O_SYMBOL = 'O';

	private char[][] board;

	// Define the current player symbol
	private char currentPlayerSymbol;

	// Define the scanner object for reading user input
	private Scanner scanner;

	// Create a new Tic-Tac-Toe game
	public TicTacToe() {
	    board = new char[BOARD_SIZE][BOARD_SIZE];
	    currentPlayerSymbol = PLAYER_X_SYMBOL;
	    scanner = new Scanner(System.in);
	}

	// Run the Tic-Tac-Toe game
	public void run() {
	    // Initialize the board with empty spaces
	    initializeBoard();

	    // Loop until the game is over
	    while (true) {
	        // Print the current board state
	        printBoard();

	        // Prompt the user to make a move
	        System.out.println("Player " + currentPlayerSymbol + ", make your move (row[1-3] column[1-3]):");

	        // Read the user input
	        String input = scanner.nextLine();

	        // Parse the user input
	        int row = Integer.parseInt(input.substring(0, 1)) - 1;
	        int column = Integer.parseInt(input.substring(2, 3)) - 1;

	        // Make the move if it is valid
	        if (isValidMove(row, column)) {
	            makeMove(row, column);

	            // Check if the game is over
	            if (isGameOver()) {
	                // Print the final board state
	                printBoard();

	                // Print the winner or tie message
	                if (hasWinner()) {
	                    System.out.println("Player " + currentPlayerSymbol + " wins!");
	                } else {
	                    System.out.println("It's a tie!");
	                }

	                // Exit the loop to end the game
	                break;
	            }

	            // Switch to the other player's turn
	            switchPlayer();
	        } else {
	            // Print an error message if the move is invalid
	            System.out.println("Invalid move. Please try again.");
	        }
	    }

	    // Close the scanner object
	    scanner.close();
	}

	// Initialize the board with empty spaces
	private void initializeBoard() {
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        for (int j = 0; j < BOARD_SIZE; j++) {
	            board[i][j] = ' ';
	        }
	    }
	}

	// Print the current board state
	private void printBoard() {
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        for (int j = 0; j < BOARD_SIZE; j++) {
	            System.out.print(" " + board[i][j] + " ");
	            if (j < BOARD_SIZE - 1) {
	                System.out.print("|");
	            }
	        }
	        System.out.println();
	        if (i < BOARD_SIZE - 1) {
	            System.out.println("-----------");
	        }
	    }
	}

	// Check if the given move is valid
	private boolean isValidMove(int row, int column) {
	    if (row < 0 || row >= BOARD_SIZE || column < 0 || column >= BOARD_SIZE) {
	        return false;
	    }
	    return board[row][column] == ' ';
	}

	// Make the given move
	private void makeMove(int row, int column) {
	    board[row][column] = currentPlayerSymbol;
	}

	//Switch to the other player's turn
	private void switchPlayer() {
		if (currentPlayerSymbol == PLAYER_X_SYMBOL) {
		currentPlayerSymbol = PLAYER_O_SYMBOL;
		} else {
		currentPlayerSymbol = PLAYER_X_SYMBOL;
		}
		}
	// Check if the game is over
	private boolean isGameOver() {
	    return isBoardFull() || hasWinner();
	}

	// Check if the board is full
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

	// Check if there is a winner
	private boolean hasWinner() {
	    return hasRowWinner() || hasColumnWinner() || hasDiagonalWinner();
	}

	// Check if there is a row winner
	private boolean hasRowWinner() {
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
	            return true;
	        }
	    }
	    return false;
	}

	// Check if there is a column winner
	private boolean hasColumnWinner() {
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
	            return true;
	        }
	    }
	    return false;
	}

	// Check if there is a diagonal winner
	private boolean hasDiagonalWinner() {
	    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
	        return true;
	    }
	    if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
	        return true;
	    }
	    return false;
	}
	public static void main(String args[]) {
		TicTacToe game = new TicTacToe();
		game.run();
	}
}
