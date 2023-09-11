package A7;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3]; //the Tic-Tac-Toe board
    private static char currentPlayer = 'X'; //the current player (X or O)
    private static boolean gameEnded = false; //flag indicating if the game has ended

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();
        while (!gameEnded) {
            playTurn();
            checkForWinner();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; //switch player
        }
    }

    //initialize the board with empty cells
    private static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    //display the current state of the board
    private static void displayBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //allow the current player to make a move
    private static void playTurn() {
        System.out.println("It's " + currentPlayer + "'s turn. Enter row and column (e.g. 1 1): ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid input. Try again.");
            playTurn();
        } else if (board[row][col] != '-') {
            System.out.println("Cell is already occupied. Try again.");
            playTurn();
        } else {
            board[row][col] = currentPlayer;
            displayBoard();
        }
    }

    //check if the game has ended and if there is a winner
    private static void checkForWinner() {
        if (checkRows() || checkColumns() || checkDiagonals()) {
            System.out.println(currentPlayer + " wins!");
            gameEnded = true;
        } else if (isBoardFull()) {
            System.out.println("It's a tie!");
            gameEnded = true;
        }
    }

    //check if any of the rows contains a winning combination
    private static boolean checkRows() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    //check if any of the columns contains a winning combination
    private static boolean checkColumns() {
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    //check if any of the diagonals contains a winning combination
    private static boolean checkDiagonals() {
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    //check if the board is full
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

    //reset the board for a new game
    private static void resetBoard() {
        gameEnded = false;
        currentPlayer = 'X';
        initializeBoard();
        displayBoard();
    }

    //ask if the players want to play again and reset the board if they do
    private static void playAgain() {
        System.out.println("Do you want to play again? (y/n)");
        Scanner scanner = new Scanner(System.in);
        char response = scanner.next().charAt(0);
        if (response == 'y' || response == 'Y') {
            resetBoard();
        } else {
            System.out.println("Thanks for playing!");
            gameEnded = true;
        }
    }
}