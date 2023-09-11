class TicTacToe:
    def __init__(self):
        self.board = [' ']*9
        self.current_player = 'X'
        self.game_over = False

    def print_board(self):
        print('-------------')
        for i in range(3):
            print(f'| {self.board[i*3]} | {self.board[i*3+1]} | {self.board[i*3+2]} |')
            print('-------------')

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            if self.check_winner():
                print(f'Player {self.current_player} wins!')
                self.game_over = True
            elif ' ' not in self.board:
                print('Tie game!')
                self.game_over = True
            else:
                self.current_player = 'O' if self.current_player == 'X' else 'X'
        else:
            print('Invalid move. Try again.')

    def check_winner(self):
        for i in range(3):
            if self.board[i*3] == self.board[i*3+1] == self.board[i*3+2] != ' ':
                return True
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return True
        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return True
        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return True
        return False

if __name__ == '__main__':
    game = TicTacToe()
    while not game.game_over:
        game.print_board()
        position = int(input(f'Player {game.current_player}, choose your move (1-9): ')) - 1
        game.make_move(position)