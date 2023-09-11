class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def print_board(self):
        print(f'{self.board[0]} | {self.board[1]} | {self.board[2]}')
        print('-' * 9)
        print(f'{self.board[3]} | {self.board[4]} | {self.board[5]}')
        print('-' * 9)
        print(f'{self.board[6]} | {self.board[7]} | {self.board[8]}')

    def get_move(self):
        while True:
            move = input(f"{self.current_player}'s turn. Enter a position (1-9): ")
            try:
                move = int(move) - 1
                if move < 0 or move > 8:
                    print('Invalid move, please enter a number between 1 and 9.')
                    continue
                if self.board[move] != ' ':
                    print('That spot is already taken, please choose another.')
                    continue
                return move
            except ValueError:
                print('Invalid move, please enter a number between 1 and 9.')

    def make_move(self, move):
        self.board[move] = self.current_player
        self.current_player = 'O' if self.current_player == 'X' else 'X'

    def check_win(self):
        for i in range(0, 9, 3):
            if self.board[i] == self.board[i+1] == self.board[i+2] != ' ':
                return True
        for i in range(3):
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return True
        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return True
        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return True
        return False

    def play_game(self):
        while True:
            self.print_board()
            move = self.get_move()
            self.make_move(move)
            if self.check_win():
                print(f'{self.current_player} wins!')
                break
            if ' ' not in self.board:
                print('Tie game.')
                break

if __name__ == '__main__':
    game = TicTacToe()
    game.play_game()
