class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def print_board(self):
        print('-------------')
        for i in range(0, 9, 3):
            print('|', self.board[i], '|', self.board[i+1], '|', self.board[i+2], '|')
            print('-------------')

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            if self.current_player == 'X':
                self.current_player = 'O'
            else:
                self.current_player = 'X'
        else:
            print('This position is already taken. Try again.')

    def check_winner(self):
        winning_combinations = [(0, 1, 2), (3, 4, 5), (6, 7, 8),
                                (0, 3, 6), (1, 4, 7), (2, 5, 8),
                                (0, 4, 8), (2, 4, 6)]
        for combination in winning_combinations:
            if self.board[combination[0]] == self.board[combination[1]] == self.board[combination[2]] != ' ':
                return self.board[combination[0]]
        if ' ' not in self.board:
            return 'Tie'
        return None

    def play(self):
        print("Let's play Tic-Tac-Toe!")
        self.print_board()
        while True:
            position = int(input(f"Player {self.current_player}, enter a position (0-8): "))
            self.make_move(position)
            self.print_board()
            winner = self.check_winner()
            if winner is not None:
                if winner == 'Tie':
                    print("It's a tie!")
                else:
                    print(f"Player {winner} wins!")
                return


game = TicTacToe()
game.play()