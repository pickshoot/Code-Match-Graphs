import os

# Create an empty board
board = [' '] * 9

# Define the winning combinations
winning_combinations = [(0, 1, 2), (3, 4, 5), (6, 7, 8), (0, 3, 6), (1, 4, 7), (2, 5, 8), (0, 4, 8), (2, 4, 6)]

# Print the board
def print_board():
    os.system('cls')
    print(f' {board[0]} | {board[1]} | {board[2]} ')
    print('-----------')
    print(f' {board[3]} | {board[4]} | {board[5]} ')
    print('-----------')
    print(f' {board[6]} | {board[7]} | {board[8]} ')

# Check if the game is over
def is_game_over():
    for combination in winning_combinations:
        if board[combination[0]] == board[combination[1]] == board[combination[2]] != ' ':
            return True
    if ' ' not in board:
        return True
    return False

# Get user input
def get_input(player):
    while True:
        try:
            position = int(input(f'Player {player}, choose a position (1-9): ')) - 1
            if position < 0 or position > 8 or board[position] != ' ':
                print('Invalid input, try again.')
            else:
                return position
        except ValueError:
            print('Invalid input, try again.')

# Play the game
def play_game():
    print_board()
    player = 'X'
    while not is_game_over():
        position = get_input(player)
        board[position] = player
        print_board()
        if is_game_over():
            break
        player = 'O' if player == 'X' else 'X'
    if ' ' not in board:
        print('It\'s a tie!')
    else:
        print(f'Player {player} won!')

# Start the game
play_game()