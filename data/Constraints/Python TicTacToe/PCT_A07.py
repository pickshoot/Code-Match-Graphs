class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]  # Initialize empty board
        self.current_player = 'X'  # X goes first

    def print_board(self):
        print('-------------')
        for i in range(0, 9, 3):
            print(f'| {self.board[i]} | {self.board[i+1]} | {self.board[i+2]} |')
            print('-------------')

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            if self.current_player == 'X':
                self.current_player = 'O'
            else:
                self.current_player = 'X'
        else:
            print('Invalid move, try again')

    def check_winner(self):
        for i in range(0, 9, 3):
            if self.board[i] == self.board[i+1] == self.board[i+2] != ' ':
                return self.board[i]

        for i in range(3):
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return self.board[i]

        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return self.board[0]

        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return self.board[2]

        if ' ' not in self.board:
            return 'tie'

        return None


if __name__ == '__main__':
    game = TicTacToe()
    while True:
        game.print_board()
        position = int(input(f"Player {game.current_player}, make your move (0-8): "))
        game.make_move(position)
        winner = game.check_winner()
        if winner:
            game.print_board()
            if winner == 'tie':
                print("It's a tie!")
            else:
                print(f"Congratulations, {winner} wins!")
            break