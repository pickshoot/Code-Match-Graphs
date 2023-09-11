import numpy as np

import numpy as np

class TicTacToe:
    def __init__(self):
        self.board = np.zeros((3, 3), dtype=int)
        self.current_player = 1
        self.winner = None

    def print_board(self):
        print("-------------")
        for row in self.board:
            print("|", end=" ")
            for val in row:
                if val == 0:
                    print(" ", end=" ")
                elif val == 1:
                    print("X", end=" ")
                else:
                    print("O", end=" ")
                print("|", end=" ")
            print()
            print("-------------")

    def play(self, row, col):
        if self.board[row][col] == 0:
            self.board[row][col] = self.current_player
            self.check_winner()
            if self.current_player == 1:
                self.current_player = 2
            else:
                self.current_player = 1
            return True
        else:
            return False

    def check_winner(self):
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != 0:
                self.winner = self.current_player
            if self.board[0][i] == self.board[1][i] == self.board[2][i] != 0:
                self.winner = self.current_player
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != 0:
            self.winner = self.current_player
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != 0:
            self.winner = self.current_player

    def game_over(self):
        if np.count_nonzero(self.board) == 9 or self.winner is not None:
            return True
        else:
            return False


if __name__ == '__main__':
    game = TicTacToe()
    while not game.game_over():
        game.print_board()
        print("Player ", game.current_player, " turn:")
        row = int(input("Enter row number (0, 1, 2): "))
        col = int(input("Enter column number (0, 1, 2): "))
        while not game.play(row, col):
            print("Invalid move!")
            row = int(input("Enter row number (0, 1, 2): "))
            col = int(input("Enter column number (0, 1, 2): "))

    game.print_board()
    if game.winner is not None:
        print("Player ", game.winner, " wins!")
    else:
        print("Game over. It's a tie!")