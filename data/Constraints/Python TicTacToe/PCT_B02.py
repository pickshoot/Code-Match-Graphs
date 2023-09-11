class TicTacToe:
    def __init__(self):
        self.board = [[' ' for _ in range(3)] for _ in range(3)]
        self.current_player = 'X'

    def print_board(self):
        for row in self.board:
            print('|'.join(row))

    def is_valid_move(self, row, col):
        if row < 0 or row > 2 or col < 0 or col > 2:
            return False
        return self.board[row][col] == ' '

    def make_move(self, row, col):
        if self.is_valid_move(row, col):
            self.board[row][col] = self.current_player
            self.current_player = 'O' if self.current_player == 'X' else 'X'
        else:
            print('Invalid move!')

    def is_game_over(self):
        for row in range(3):
            if self.board[row][0] != ' ' and self.board[row][0] == self.board[row][1] == self.board[row][2]:
                return True
        for col in range(3):
            if self.board[0][col] != ' ' and self.board[0][col] == self.board[1][col] == self.board[2][col]:
                return True
        if self.board[0][0] != ' ' and self.board[0][0] == self.board[1][1] == self.board[2][2]:
            return True
        if self.board[0][2] != ' ' and self.board[0][2] == self.board[1][1] == self.board[2][0]:
            return True
        for row in range(3):
            for col in range(3):
                if self.board[row][col] == ' ':
                    return False
        return True

    def get_winner(self):
        for row in range(3):
            if self.board[row][0] != ' ' and self.board[row][0] == self.board[row][1] == self.board[row][2]:
                return self.board[row][0]
        for col in range(3):
            if self.board[0][col] != ' ' and self.board[0][col] == self.board[1][col] == self.board[2][col]:
                return self.board[0][col]
        if self.board[0][0] != ' ' and self.board[0][0] == self.board[1][1] == self.board[2][2]:
            return self.board[0][0]
        if self.board[0][2] != ' ' and self.board[0][2] == self.board[1][1] == self.board[2][0]:
            return self.board[0][2]
        return None

    def play(self):
        while not self.is_game_over():
            self.print_board()
            row = int(input(f'{self.current_player}, enter row (0-2): '))
            col = int(input(f'{self.current_player}, enter column (0-2): '))
            self.make_move(row, col)
        self.print_board()
        winner = self.get_winner()
        if winner:
            print(f'{winner} wins!')
        else:
            print('Tie game.')


if __name__ == '__main__':
    game = TicTacToe()
    game.play()