package B10;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	private final Board board;
	private final Player[] players;
	private int currentPlayerIndex;

	public TicTacToe(Player player1, Player player2) {
		this.board = new Board();
		this.players = new Player[] { player1, player2 };
		this.currentPlayerIndex = 0;
	}

	public void play() {
		System.out.println("Let's play Tic-Tac-Toe!");
		System.out.println(board);
		while (!board.isFull() && !board.hasWinner()) {
			Player currentPlayer = getCurrentPlayer();
			System.out.println(currentPlayer.getName() + "'s turn");
			int[] move = currentPlayer.getMove(board);
			board.set(move[0], move[1], currentPlayer.getSymbol());
			System.out.println(board);
			currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		}
		if (board.hasWinner()) {
			System.out.println(getCurrentPlayer().getName() + " wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	private Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}

	public static void main(String[] args) {
		Player player1 = new HumanPlayer("X");
		Player player2 = new HumanPlayer("O");
		TicTacToe game = new TicTacToe(player1, player2);
		game.play();
	}
}

class Board {
    private final int SIZE = 3;
    private final String[][] cells;

    public Board() {
        this.cells = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = " ";
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0].equals(cells[i][1]) && cells[i][0].equals(cells[i][2]) && !cells[i][0].equals(" ")) {
				return true;
			}
			if (cells[0][i].equals(cells[1][i]) && cells[0][i].equals(cells[2][i]) && !cells[0][i].equals(" ")) {
				return true;
			}
		}
		if (cells[0][0].equals(cells[1][1]) && cells[0][0].equals(cells[2][2]) && !cells[0][0].equals(" ")) {
			return true;
		}
		if (cells[0][2].equals(cells[1][1]) && cells[0][2].equals(cells[2][0]) && !cells[0][2].equals(" ")) {
			return true;
		}
		return false;
	}
	
	public void set(int row, int col, String value) {
		cells[row][col] = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  0 1 2\n");
		for (int i = 0; i < SIZE; i++) {
			sb.append(i).append(" ");
			for (int j = 0; j < SIZE; j++) {
				sb.append(cells[i][j]).append("|");
			}
			sb.setCharAt(sb.length() - 1, '\n');
			if (i < SIZE - 1) {
				sb.append("  -----\n");
			}
		}
		return sb.toString();
	}
}

abstract class Player {
    protected final String symbol;
    protected final String name;

    public Player(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public abstract int[] getMove(Board board);
}

class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String symbol) {
        super(symbol, "Human");
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] getMove(Board board) {
        System.out.print("Enter row and column (e.g., 1 2): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
}