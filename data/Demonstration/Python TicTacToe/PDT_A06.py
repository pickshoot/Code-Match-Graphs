class TicTacToe:
    def __init__(self):
        # Initialize the game board as a 3x3 list
        self.board = [[' ' for _ in range(3)] for _ in range(3)]
        # Initialize the current player as 'X'
        self.current_player = 'X'

    def print_board(self):
        # Print the current state of the game board
        print('  1 2 3')
        for i in range(3):
            row = [self.board[i][j] for j in range(3)]
            print(f"{i+1} {' '.join(row)}")

    def make_move(self, row, col):
        # Check if the specified move is valid
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid move. Please try again.")
            return False
        if self.board[row][col] != ' ':
            print("That spot is already taken. Please try again.")
            return False
        # Update the game board with the current player's move
        self.board[row][col] = self.current_player
        # Check if the current player has won the game
        if self.check_win():
            print(f"{self.current_player} wins!")
            return True
        # Check if the game has ended in a tie
        if self.check_tie():
            print("It's a tie!")
            return True
        # Switch to the other player
        self.current_player = 'O' if self.current_player == 'X' else 'X'
        return True

    def check_win(self):
        # Check for three in a row horizontally, vertically, or diagonally
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != ' ':
                return True
            if self.board[0][i] == self.board[1][i] == self.board[2][i] != ' ':
                return True
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != ' ':
            return True
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != ' ':
            return True
        return False

    def check_tie(self):
        # Check if all spots on the game board are taken
        for i in range(3):
            for j in range(3):
                if self.board[i][j] == ' ':
                    return False
        return True

# Main program
tic_tac_toe = TicTacToe()
print("Welcome to Tic-Tac-Toe!")

# Loop until the game ends
while True:
    # Print the current state of the game board
    tic_tac_toe.print_board()
    # Prompt the current player to make a move
    row = int(input(f"{tic_tac_toe.current_player}'s turn. Enter row (1-3): ")) - 1
    col = int(input(f"{tic_tac_toe.current_player}'s turn. Enter column (1-3): ")) - 1
    # Make the specified move
    if not tic_tac_toe.make_move(row, col):
        continue
    # Check if the game has ended
    if tic_tac_toe.check_win() or tic_tac_toe.check_tie():
        tic_tac_toe.print_board()
        break
