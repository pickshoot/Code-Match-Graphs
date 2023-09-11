package C6;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board = new char[3][3];
    private char currentPlayerMark;
    private boolean gameFinished = false;
    private int movesCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public TicTacToe() {
        initializeBoard();
        currentPlayerMark = 'X';
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

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

    private boolean isBoardFull() {
        return movesCount == 9;
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true;
        }
        return false;
    }

    private void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        } else {
            currentPlayerMark = 'X';
        }
    }

    private void placeMark(int row, int col) {
        board[row][col] = currentPlayerMark;
        movesCount++;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move. Row and column numbers should be between 0 and 2.");
            return false;
        } else if (board[row][col] != '-') {
            System.out.println("Invalid move. This cell is already occupied.");
            return false;
        } else {
            return true;
        }
    }

    private void play() {
        while (!gameFinished) {
            System.out.println("Current board:");
            printBoard();
            System.out.println("Player " + currentPlayerMark + ", enter row and column number:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (isValidMove(row, col)) {
                placeMark(row, col);
                if (checkForWin()) {
                    System.out.println("Congratulations! Player " + currentPlayerMark + " has won!");
                    gameFinished = true;
                } else if (isBoardFull()) {
                    System.out.println("The game has ended in a tie.");
                    gameFinished = true;
                } else {
                    changePlayer();
                }
            }
        }
    }

    public void start() {
        do {
            play();
            System.out.println("Do you want to play again? (yes/no)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("no")) {
                System.out.println("Thanks for playing!");
                break;
            }
            gameFinished = false;
            movesCount = 0;
            initializeBoard();
            currentPlayerMark = 'X';
        } while (true);
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}