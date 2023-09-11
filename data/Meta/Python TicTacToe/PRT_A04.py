import sys

# Create a 3x3 board with empty cells
board = [['-', '-', '-'],
         ['-', '-', '-'],
         ['-', '-', '-']]

# Print the board
def print_board():
    for row in board:
        print(' '.join(row))

# Check if the board is full
def is_full():
    for row in board:
        if '-' in row:
            return False
    return True

# Check if there is a winner
def has_winner():
    # Check rows
    for row in board:
        if row[0] != '-' and row[0] == row[1] and row[1] == row[2]:
            return True
    # Check columns
    for i in range(3):
        if board[0][i] != '-' and board[0][i] == board[1][i] and board[1][i] == board[2][i]:
            return True
    # Check diagonals
    if board[0][0] != '-' and board[0][0] == board[1][1] and board[1][1] == board[2][2]:
        return True
    if board[0][2] != '-' and board[0][2] == board[1][1] and board[1][1] == board[2][0]:
        return True
    return False

# Get a valid move from the player
def get_move(player):
    while True:
        try:
            row = int(input(f'{player}, enter row (1-3): ')) - 1
            col = int(input(f'{player}, enter column (1-3): ')) - 1
            if row < 0 or row > 2 or col < 0 or col > 2:
                print('Invalid input. Row and column must be between 1 and 3.')
            elif board[row][col] != '-':
                print('That cell is already occupied. Try again.')
            else:
                return row, col
        except ValueError:
            print('Invalid input. Row and column must be numbers.')

# Start the game
while True:
    print_board()
    # Get player moves
    for player in ['X', 'O']:
        row, col = get_move(player)
        board[row][col] = player
        if has_winner():
            print_board()
            print(f'Player {player} wins!')
            sys.exit(0)
        if is_full():
            print_board()
            print('Tie!')
            sys.exit(0)
    # Ask if players want to play again
    choice = input('Play again? (y/n): ')
    if choice.lower() != 'y':
        break
    # Reset the board
    board = [['-', '-', '-'],
             ['-', '-', '-'],
             ['-', '-', '-']]