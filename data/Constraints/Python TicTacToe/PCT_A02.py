class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def print_board(self):
        for row in [self.board[i*3:(i+1)*3] for i in range(3)]:
            print('| ' + ' | '.join(row) + ' |')

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            if self.current_player == 'X':
                self.current_player = 'O'
            else:
                self.current_player = 'X'
        else:
            raise ValueError("Invalid move")

    def check_winner(self):
        # Check rows
        for row in [self.board[i*3:(i+1)*3] for i in range(3)]:
            if row.count(row[0]) == len(row) and row[0] != ' ':
                return row[0]

        # Check columns
        for col in [self.board[i::3] for i in range(3)]:
            if col.count(col[0]) == len(col) and col[0] != ' ':
                return col[0]

        # Check diagonals
        diagonals = [[self.board[0], self.board[4], self.board[8]], [self.board[2], self.board[4], self.board[6]]]
        for diag in diagonals:
            if diag.count(diag[0]) == len(diag) and diag[0] != ' ':
                return diag[0]

        if ' ' not in self.board:
            return 'Tie'
        return None


game = TicTacToe()

while True:
    game.print_board()
    position = int(input("Enter position (1-9): "))
    try:
        game.make_move(position-1)
        winner = game.check_winner()
        if winner:
            game.print_board()
            print(f"{winner} wins!")
            break
    except ValueError as e:
        print(str(e))