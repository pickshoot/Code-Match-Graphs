import itertools

class TicTacToe:
    def __init__(self, board_size=3, win_length=3):
        self.board_size = board_size
        self.win_length = win_length
        self.board = [[' ' for _ in range(board_size)] for _ in range(board_size)]
        self.current_player = 'X'

    def play(self):
        self.print_board()
        while not self.is_game_over():
            row, col = self.get_move()
            self.make_move(row, col)
            self.print_board()
            self.switch_player()

        winner = self.get_winner()
        if winner:
            print(f"{winner} wins!")
        else:
            print("It's a tie!")

    def print_board(self):
        print('\n'.join(['|'.join(row) for row in self.board]))

    def get_move(self):
        while True:
            try:
                move = input(f"{self.current_player}'s move (row, col): ")
                row, col = map(int, move.split(','))
                if self.board[row][col] != ' ':
                    raise ValueError("Cell is already occupied")
                return row, col
            except ValueError as e:
                print(e)

    def make_move(self, row, col):
        self.board[row][col] = self.current_player

    def switch_player(self):
        self.current_player = 'O' if self.current_player == 'X' else 'X'

    def is_game_over(self):
        return self.get_winner() or all(self.board[i][j] != ' ' for i, j in itertools.product(range(self.board_size), repeat=2))

    def get_winner(self):
        lines = self.get_lines()
        for line in lines:
            if self.is_winning_line(line):
                return line[0]
        return None

    def get_lines(self):
        rows = self.board
        cols = [[self.board[j][i] for j in range(self.board_size)] for i in range(self.board_size)]
        diags = [[self.board[i][i] for i in range(self.board_size)], [self.board[i][self.board_size - i - 1] for i in range(self.board_size)]]
        return rows + cols + diags

    def is_winning_line(self, line):
        for i in range(len(line) - self.win_length + 1):
            if all(cell == line[i] for cell in line[i:i+self.win_length]) and line[i] != ' ':
                return True
        return False


if __name__ == '__main__':
    game = TicTacToe()
    game.play()