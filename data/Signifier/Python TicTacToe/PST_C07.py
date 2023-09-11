import random

# Define the board as a list of lists
board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]

# Define a function to print the board
def print_board():
    print('  1 2 3')
    print(' +-+-+-+')
    for i in range(3):
        print('{}|{}|{}|{}|'.format(i+1, board[i][0], board[i][1], board[i][2]))
        print(' +-+-+-+')

# Define a function to check if the game is over
def game_over():
    # Check for horizontal wins
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return True
    # Check for vertical wins
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] != ' ':
            return True
    # Check for diagonal wins
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return True
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return True
    # Check for a tie
    for i in range(3):
        for j in range(3):
            if board[i][j] == ' ':
                return False
    return True

# Define a function to get the player's move
def get_move(player):
    while True:
        try:
            row = int(input('Player {}: Enter row (1-3): '.format(player)))
            col = int(input('Player {}: Enter column (1-3): '.format(player)))
            if row < 1 or row > 3 or col < 1 or col > 3:
                print('Invalid row or column. Try again.')
                continue
            if board[row-1][col-1] != ' ':
                print('That spot is already taken. Try again.')
                continue
            return (row-1, col-1)
        except ValueError:
            print('Invalid input. Try again.')

# Define the main function to play the game
def play_game():
    print('Welcome to Tic-Tac-Toe!')
    print_board()
    while not game_over():
        row, col = get_move('X')
        board[row][col] = 'X'
        print_board()
        if game_over():
            break
        row, col = get_move('O')
        board[row][col] = 'O'
        print_board()
    if game_over():
        print('Game over!')
        print_board()
        if ' ' not in board[0] and ' ' not in board[1] and ' ' not in board[2]:
            print('Tie game!')
        else:
            print('{} wins!'.format('X' if player == 'O' else 'O'))

# Call the main function to play the game
play_game()
