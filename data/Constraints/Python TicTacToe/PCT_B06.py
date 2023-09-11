class TicTacToe:
    def __init__(self, board_size=3):
        self.board_size = board_size
        self.board = [[' ' for _ in range(self.board_size)] for _ in range(self.board_size)]

    def print_board(self):
        for row in self.board:
            print('|'.join(row))

    def is_valid_move(self, row, col):
        return row in range(self.board_size) and col in range(self.board_size) and self.board[row][col] == ' '

    def is_game_over(self):
        # Check for row win
        for row in self.board:
            if len(set(row)) == 1 and row[0] != ' ':
                return True

        # Check for column win
        for col in range(self.board_size):
            if len(set([self.board[row][col] for row in range(self.board_size)])) == 1 and self.board[0][col] != ' ':
                return True

        # Check for diagonal win
        if len(set([self.board[i][i] for i in range(self.board_size)])) == 1 and self.board[0][0] != ' ':
            return True

        if len(set([self.board[i][self.board_size - i - 1] for i in range(self.board_size)])) == 1 and self.board[0][self.board_size - 1] != ' ':
            return True

        # Check for tie
        if all([cell != ' ' for row in self.board for cell in row]):
            return True

        return False

    def make_move(self, row, col, player):
        self.board[row][col] = player

    def play(self):
        print(f"Welcome to Tic-Tac-Toe ({self.board_size}x{self.board_size})!")
        print("Players will be represented by X and O.")
        self.print_board()

        current_player = 'X'
        while not self.is_game_over():
            print(f"Player {current_player}'s turn.")
            row, col = map(int, input("Enter row and column numbers separated by space: ").split())

            if self.is_valid_move(row, col):
                self.make_move(row, col, current_player)
                self.print_board()
                current_player = 'O' if current_player == 'X' else 'X'
            else:
                print("Invalid move. Please try again.")

        winner = 'X' if current_player == 'O' else 'O'
        print(f"Game over. {winner} wins!" if not all([cell != ' ' for row in self.board for cell in row]) else "Game over. It's a tie!")


game = TicTacToe(board_size=3)
game.play()