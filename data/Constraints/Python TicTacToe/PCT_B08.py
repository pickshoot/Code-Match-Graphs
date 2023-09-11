import numpy as np

class TicTacToe:
    def __init__(self):
        self.board = np.zeros((3, 3))
        self.current_player = 1

    def play(self, row, col):
        if self.board[row][col] == 0:
            self.board[row][col] = self.current_player
            self.current_player = -self.current_player
        else:
            print("Invalid move. Try again.")

    def check_win(self):
        for i in range(3):
            if sum(self.board[i, :]) == 3 or sum(self.board[:, i]) == 3:
                return 1
            elif sum(self.board[i, :]) == -3 or sum(self.board[:, i]) == -3:
                return -1
        if sum(self.board.diagonal()) == 3 or sum(np.fliplr(self.board).diagonal()) == 3:
            return 1
        elif sum(self.board.diagonal()) == -3 or sum(np.fliplr(self.board).diagonal()) == -3:
            return -1
        if 0 not in self.board:
            return 0
        return None

    def print_board(self):
        print("  0 1 2")
        for i in range(3):
            row = str(i) + " "
            for j in range(3):
                if self.board[i][j] == 1:
                    row += "X"
                elif self.board[i][j] == -1:
                    row += "O"
                else:
                    row += " "
                if j != 2:
                    row += "|"
            print(row)
            if i != 2:
                print("  -+-+-")

def main():
    game = TicTacToe()
    while True:
        game.print_board()
        row = int(input("Enter row: "))
        col = int(input("Enter column: "))
        game.play(row, col)
        result = game.check_win()
        if result is not None:
            game.print_board()
            if result == 0:
                print("Tie!")
            elif result == 1:
                print("Player 1 wins!")
            else:
                print("Player 2 wins!")
            break

if __name__ == '__main__':
    main()