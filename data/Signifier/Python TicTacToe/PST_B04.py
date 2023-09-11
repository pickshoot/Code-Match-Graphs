def print_board(board):
    print(f'{board[0]}|{board[1]}|{board[2]}')
    print('-+-+-')
    print(f'{board[3]}|{board[4]}|{board[5]}')
    print('-+-+-')
    print(f'{board[6]}|{board[7]}|{board[8]}')


def check_win(board, player):
    if (board[0] == board[1] == board[2] == player or
            board[3] == board[4] == board[5] == player or
            board[6] == board[7] == board[8] == player or
            board[0] == board[3] == board[6] == player or
            board[1] == board[4] == board[7] == player or
            board[2] == board[5] == board[8] == player or
            board[0] == board[4] == board[8] == player or
            board[2] == board[4] == board[6] == player):
        return True
    else:
        return False


def tic_tac_toe():
    board = [' '] * 9
    current_player = 'X'
    print_board(board)

    while True:
        position = int(input(f"Player '{current_player}', enter a position (1-9): ")) - 1
        if board[position] != ' ':
            print('Position already taken. Try again.')
            continue
        board[position] = current_player
        print_board(board)
        if check_win(board, current_player):
            print(f"Player '{current_player}' wins!")
            return
        if ' ' not in board:
            print('Tie!')
            return
        current_player = 'O' if current_player == 'X' else 'X'


tic_tac_toe()
