def print_board(board):
    print('-------------')
    for row in board:
        print('|', end='')
        for cell in row:
            print(' ' + cell + ' |', end='')
        print('\n-------------')

def get_move(board, player):
    while True:
        move = input('Player ' + player + ', enter your move (row, column): ')
        row, col = move.split(',')
        row, col = int(row.strip()), int(col.strip())
        if row < 0 or row > 2 or col < 0 or col > 2:
            print('Invalid move. Row and column must be between 0 and 2.')
        elif board[row][col] != ' ':
            print('Invalid move. Cell already occupied.')
        else:
            return row, col

def check_winner(board):
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != ' ':
            return row[0]
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] != ' ':
            return board[0][col]
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != ' ':
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != ' ':
        return board[0][2]
    return None

def play():
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
    players = ['X', 'O']
    turn = 0
    while True:
        print_board(board)
        player = players[turn % 2]
        row, col = get_move(board, player)
        board[row][col] = player
        winner = check_winner(board)
        if winner is not None:
            print_board(board)
            print('Player', winner, 'wins!')
            return
        if all(cell != ' ' for row in board for cell in row):
            print_board(board)
            print('Draw!')
            return
        turn += 1

play()
