package C4;

import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayerMark;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayerMark = 'X';
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    // Set/Reset the board back to all empty values.
    private void initializeBoard() {

        // Loop through rows
        for (int i = 0; i < 3; i++) {

            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board (may be replaced by GUI implementation later)
    private void printBoard() {

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

    // Loop through all cells of the board and if one is found to be empty (contains
    // char '-') then return false.
    // Otherwise the board is full.
    private boolean isBoardFull() {

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

    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    private boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {

        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }

        return false;
    }

    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {

        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }

        return false;
    }

    // Check the two diagonals to see if either is a win. Return true if either
    // wins.
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true)
                || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    // Check to see if all three values are the same (and not empty) indicating a
    // win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-' && c1 == c2 && c2 == c3);
    }

    // Change player marks back and forth.
    private void changePlayer() {
        currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
    }

    // Prompt the player to enter their move.
    private void getPlayerMove() {

        int row = -1, col = -1;

        while (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.print("Player " + currentPlayerMark + ", enter row and column number (e.g. 1 2): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid row or column number. Please try again.");
            } else if (board[row][col] != '-') {
                System.out.println("That cell is already occupied. Please choose another one.");
                row = -1;
                col = -1;
            }
        }

        board[row][col] = currentPlayerMark;
    }

    // Ask players if they want to play again.
    private boolean playAgain() {

        char response = '-';

        while (response != 'Y' && response != 'N') {
            System.out.print("Would you like to play again? (Y/N) ");
            response = scanner.next().charAt(0);

            if (response != 'Y' && response != 'N') {
                System.out.println("Invalid response. Please enter Y or N.");
            }
        }

        return (response == 'Y');
    }

    // The main function that runs the game.
    public void play() {

        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playing = true;

        while (playing) {

            initializeBoard();
            printBoard();

            while (!checkForWin() && !isBoardFull()) {

                getPlayerMove();
                printBoard();
                changePlayer();
            }

            if (isBoardFull() && !checkForWin()) {
                System.out.println("The game is a tie!");
            } else {
                changePlayer();
                System.out.println("Congratulations! Player " + currentPlayerMark + " has won the game!");
            }

            playing = playAgain();
        }

        System.out.println("Thanks for playing!");
    }

    // The main function that starts the program.
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}