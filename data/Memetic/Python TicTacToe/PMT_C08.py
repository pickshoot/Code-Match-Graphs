# Tic-Tac-Toe game

board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]  # the initial game board
player = 'X'  # the player who moves first

def print_board():
    print('  1 2 3')  # column numbers
    for i in range(3):
        print(f'{i+1} {" ".join(board[i])}')  # row number and board contents

def make_move():
    global player  # use the global player variable
    while True:
        move = input(f'{player}\'s move (row column): ')
        try:
            row, col = map(int, move.split())
            if row < 1 or row > 3 or col < 1 or col > 3:
                raise ValueError()
            if board[row-1][col-1] != ' ':
                print('That square is already occupied. Try again.')
            else:
                board[row-1][col-1] = player
                break
        except ValueError:
            print('Invalid input. Try again.')
    # switch to the other player
    player = 'O' if player == 'X' else 'X'

def check_win():
    # check rows and columns
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return board[i][0]  # winner
        if board[0][i] == board[1][i] == board[2][i] != ' ':
            return board[0][i]  # winner
    # check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]  # winner
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]  # winner
    # check for a tie
    if all(square != ' ' for row in board for square in row):
        return 'Tie'
    # no winner yet
    return None

# main game loop
while True:
    print_board()
    make_move()
    winner = check_win()
    if winner:
        print_board()
        if winner == 'Tie':
            print('It\'s a tie!')
        else:
            print(f'{winner} wins!')
        break