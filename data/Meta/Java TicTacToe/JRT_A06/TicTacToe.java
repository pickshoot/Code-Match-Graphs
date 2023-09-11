package A6;

import java.util.Scanner;

public class TicTacToe {
    
    // The game board
    private static char[][] board = new char[3][3];

    // The current player
    private static char currentPlayer = 'X';

    // The scanner for input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the game board
        initBoard();
        
        // Play the game until there is a winner or a tie
        while (true) {
            // Print the board
            printBoard();
            
            // Get the player's move
            int row = getRow();
            int col = getCol();
            
            // Make the move
            makeMove(row, col);
            
            // Check for a winner
            char winner = checkWinner();
            if (winner != '\0') {
                System.out.println(winner + " wins!");
                break;
            }
            
            // Check for a tie
            if (isBoardFull()) {
                System.out.println("Tie game!");
                break;
            }
            
            // Switch to the other player
            switchPlayer();
        }
        
        // Ask the players if they want to play again
        if (playAgain()) {
            main(args);
        } else {
            System.out.println("Thanks for playing!");
        }
    }
    
    // Initialize the game board
    private static void initBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }
    
    // Print the game board
    private static void printBoard() {
        System.out.println("-------");
        for (int row = 0; row < 3; row++) {
            System.out.print("|");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }
    
    // Get the row of the player's move
    private static int getRow() {
        System.out.print(currentPlayer + "'s move (row): ");
        int row = scanner.nextInt();
        while (row < 1 || row > 3) {
            System.out.print("Invalid row. Try again (1-3): ");
            row = scanner.nextInt();
        }
        return row - 1;
    }
    
    // Get the column of the player's move
    private static int getCol() {
        System.out.print(currentPlayer + "'s move (column): ");
        int col = scanner.nextInt();
        while (col < 1 || col > 3) {
            System.out.print("Invalid column. Try again (1-3): ");
            col = scanner.nextInt();
        }
        return col - 1;
    }
    
    // Make the player's move
    private static void makeMove(int row, int col) {
        while (board[row][col] != '-') {
            System.out.print("Invalid move. Try again: ");
            row = getRow();
            col = getCol();
        }
        board[row][col] = currentPlayer;
    }
    
    // Check for a winner
    private static char checkWinner() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return board[row][0];
            }
        }
        
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return board[0][col];
            }
        }
        
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        
        // No winner
        return '\0';
    }
    
    // Check if the board is full
    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Switch to the other player
    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
    
    // Ask the players if they want to play again
    private static boolean playAgain() {
        System.out.print("Play again? (y/n): ");
        String answer = scanner.next();
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            System.out.print("Invalid answer. Try again (y/n): ");
            answer = scanner.next();
        }
        return answer.equalsIgnoreCase("y");
    }
    
}