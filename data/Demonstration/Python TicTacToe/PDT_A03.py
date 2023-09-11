class TicTacToe:
    def __init__(self):
        # Initialize the board as an empty 3x3 grid
        self.board = [[' ' for _ in range(3)] for _ in range(3)]
        # Initialize the player as 'X'
        self.current_player = 'X'

    def print_board(self):
        # Print the current state of the board
        for row in self.board:
            print('|'.join(row))

    def make_move(self, row, col):
        # Check if the move is valid
        if row not in [0, 1, 2] or col not in [0, 1, 2]:
            print('Invalid move. Please try again.')
            return

        # Check if the spot is already taken
        if self.board[row][col] != ' ':
            print('Spot already taken. Please try again.')
            return

        # Make the move
        self.board[row][col] = self.current_player

        # Switch to the other player
        self.current_player = 'O' if self.current_player == 'X' else 'X'

    def check_winner(self):
        # Check rows
        for row in self.board:
            if row[0] == row[1] == row[2] != ' ':
                return row[0]

        # Check columns
        for col in range(3):
            if self.board[0][col] == self.board[1][col] == self.board[2][col] != ' ':
                return self.board[0][col]

        # Check diagonals
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != ' ':
            return self.board[0][0]
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != ' ':
            return self.board[0][2]

        # Check if the game is tied
        if all([spot != ' ' for row in self.board for spot in row]):
            return 'Tie'

        # Otherwise, return None
        return None

# Play the game
game = TicTacToe()
print('Welcome to Tic-Tac-Toe!')
game.print_board()

while True:
    # Get the user's move
    row = int(input('Enter the row (0-2): '))
    col = int(input('Enter the column (0-2): '))
    game.make_move(row, col)
    game.print_board()

    # Check for a winner
    winner = game.check_winner()
    if winner is not None:
        if winner == 'Tie':
            print('The game is tied!')
        else:
            print(f'{winner} wins!')
        break
