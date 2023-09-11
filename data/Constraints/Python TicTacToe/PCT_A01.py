class TicTacToe:
    def __init__(self):
        self.board = [['-' for _ in range(3)] for _ in range(3)]
        self.current_player = 'X'

    def print_board(self):
        for row in self.board:
            print(' '.join(row))

    def make_move(self, row, col):
        if self.board[row][col] == '-':
            self.board[row][col] = self.current_player
            self.current_player = 'O' if self.current_player == 'X' else 'X'
            return True
        else:
            return False

    def has_won(self):
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != '-':
                return self.board[i][0]
            if self.board[0][i] == self.board[1][i] == self.board[2][i] != '-':
                return self.board[0][i]
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != '-':
            return self.board[0][0]
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != '-':
            return self.board[0][2]
        if '-' not in [item for row in self.board for item in row]:
            return 'Tie'

        return None


def play_game():
    game = TicTacToe()

    while True:
        game.print_board()

        row = int(input(f"{game.current_player}, choose a row (0-2): "))
        col = int(input(f"{game.current_player}, choose a column (0-2): "))

        if not game.make_move(row, col):
            print("That space is already taken!")
            continue

        winner = game.has_won()
        if winner is not None:
            game.print_board()
            if winner == 'Tie':
                print("It's a tie!")
            else:
                print(f"{winner} wins!")
            break

if __name__ == '__main__':
    play_game()