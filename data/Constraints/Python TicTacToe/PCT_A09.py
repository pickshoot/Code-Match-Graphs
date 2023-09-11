class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def print_board(self):
        print('-------------')
        for i in range(3):
            print('|', self.board[i * 3], '|', self.board[i * 3 + 1], '|', self.board[i * 3 + 2], '|')
            print('-------------')

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            if self.current_player == 'X':
                self.current_player = 'O'
            else:
                self.current_player = 'X'
        else:
            print('That position is already taken. Try again.')

    def check_winner(self):
        lines = [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]
        for line in lines:
            if self.board[line[0]] == self.board[line[1]] == self.board[line[2]] != ' ':
                return self.board[line[0]]
        if ' ' not in self.board:
            return 'tie'
        return None

    def play(self):
        print('Welcome to Tic Tac Toe!')
        print('Here is the board position map:')
        print('-------------')
        for i in range(3):
            print('|', i * 3, '|', i * 3 + 1, '|', i * 3 + 2, '|')
            print('-------------')

        while True:
            self.print_board()
            position = int(input(f'{self.current_player}, make your move (0-8): '))
            self.make_move(position)
            winner = self.check_winner()
            if winner:
                self.print_board()
                if winner == 'tie':
                    print('It\'s a tie!')
                else:
                    print(f'{winner} wins!')
                break


game = TicTacToe()
game.play()