import sys

# Print an empty 3x3 board
board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
def print_board():
    print('  0 1 2')
    for i in range(3):
        print(str(i) + ' ' + '|'.join(board[i]))
        if i != 2:
            print('  -----')

# Check if the specified move is valid
def is_valid_move(row, col):
    if row < 0 or row > 2 or col < 0 or col > 2:
        return False
    if board[row][col] != ' ':
        return False
    return True

# Check if the game has been won
def check_win(player):
    for i in range(3):
        if board[i][0] == player and board[i][1] == player and board[i][2] == player:
            return True
        if board[0][i] == player and board[1][i] == player and board[2][i] == player:
            return True
    if board[0][0] == player and board[1][1] == player and board[2][2] == player:
        return True
    if board[0][2] == player and board[1][1] == player and board[2][0] == player:
        return True
    return False

# Play the game
print_board()
player = 'X'
while True:
    # Get the player's move
    try:
        print('Player', player)
        row = int(input('Enter row: '))
        col = int(input('Enter column: '))
    except:
        print('Invalid input')
        continue

    # Check if the move is valid
    if not is_valid_move(row, col):
        print('Invalid move')
        continue

    # Update the board
    board[row][col] = player
    print_board()

    # Check for a win or a tie
    if check_win(player):
        print('Player', player, 'wins!')
        break
    elif all([cell != ' ' for row in board for cell in row]):
        print('Tie!')
        break

    # Switch to the other player
    player = 'O' if player == 'X' else 'X'

# Ask if the players want to play again
while True:
    try:
        play_again = input('Play again? (y/n): ')
    except:
        print('Invalid input')
        continue
    if play_again.lower() == 'y':
        board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
        print_board()
        player = 'X'
        break
    elif play_again.lower() == 'n':
        sys.exit()
    else:
        print('Invalid input')