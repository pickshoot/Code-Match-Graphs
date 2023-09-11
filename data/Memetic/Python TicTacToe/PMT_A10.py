# Tic-Tac-Toe game in Python

# Create a 3x3 board
board = [[' ', ' ', ' '],
         [' ', ' ', ' '],
         [' ', ' ', ' ']]

# Print the board
def print_board(board):
    print('   |   |')
    print(' ' + board[0][0] + ' | ' + board[0][1] + ' | ' + board[0][2])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[1][0] + ' | ' + board[1][1] + ' | ' + board[1][2])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[2][0] + ' | ' + board[2][1] + ' | ' + board[2][2])
    print('   |   |')

# Check if the game is over
def is_game_over(board):
    # Check rows
    for row in board:
        if row[0] == row[1] == row[2] != ' ':
            return True

    # Check columns
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] != ' ':
            return True

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return True
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return True

    # Check if the board is full
    if all(' ' not in row for row in board):
        return True

    # Otherwise, the game is not over yet
    return False

# Get the next player's move
def get_move(player):
    while True:
        move = input('Player ' + player + ', enter your move (row, col): ')
        row, col = move.split(',')
        row, col = int(row), int(col)
        if board[row][col] == ' ':
            return row, col
        else:
            print('That space is already taken!')

# Play the game
def play_game():
    # Set up the game
    players = ['X', 'O']
    current_player = players[0]
    print_board(board)

    # Play the game
    while not is_game_over(board):
        row, col = get_move(current_player)
        board[row][col] = current_player
        print_board(board)
        if current_player == players[0]:
            current_player = players[1]
        else:
            current_player = players[0]

    # Print the winner or tie message
    if all(' ' not in row for row in board):
        print('The game is a tie!')
    else:
        print('Player ' + current_player + ' wins!')

# Start the game
play_game()