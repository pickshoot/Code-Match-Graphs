class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def print_board(self):
        print('-------------')
        for i in range(0, 9, 3):
            print('| {} | {} | {} |'.format(self.board[i], self.board[i+1], self.board[i+2]))
            print('-------------')

    def is_valid_move(self, position):
        if position < 0 or position > 8:
            return False
        if self.board[position] != ' ':
            return False
        return True

    def make_move(self, position):
        self.board[position] = self.current_player

    def switch_player(self):
        if self.current_player == 'X':
            self.current_player = 'O'
        else:
            self.current_player = 'X'

    def check_for_win(self):
        # check rows for win
        for i in range(0, 9, 3):
            if self.board[i] != ' ' and self.board[i] == self.board[i+1] == self.board[i+2]:
                return True

        # check columns for win
        for i in range(3):
            if self.board[i] != ' ' and self.board[i] == self.board[i+3] == self.board[i+6]:
                return True

        # check diagonals for win
        if self.board[0] != ' ' and self.board[0] == self.board[4] == self.board[8]:
            return True
        if self.board[2] != ' ' and self.board[2] == self.board[4] == self.board[6]:
            return True

        # no win found
        return False

    def check_for_draw(self):
        for i in range(9):
            if self.board[i] == ' ':
                return False
        return True

    def play(self):
        input_func = input
        game_over = False
        while not game_over:
            self.print_board()
            position = input_func("Player {}, please enter your move (0-8): ".format(self.current_player))
            try:
                position = int(position)
                if not self.is_valid_move(position):
                    print("Invalid move. Please try again.")
                    continue
                self.make_move(position)
                if self.check_for_win():
                    print("Player", self.current_player, "wins!")
                    game_over = True
                elif self.check_for_draw():
                    print("Game over. It's a draw.")
                    game_over = True
                else:
                    self.switch_player()
            except ValueError:
                print("Invalid input")

        self.print_board()

if __name__ == '__main__':
    game = TicTacToe()
    game.play()
