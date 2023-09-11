def print_board(board):
    """
    Prints the current state of the Tic-Tac-Toe board.
    """
    print(f'{board[0]}|{board[1]}|{board[2]}')
    print('-|-|-')
    print(f'{board[3]}|{board[4]}|{board[5]}')
    print('-|-|-')
    print(f'{board[6]}|{board[7]}|{board[8]}')

def check_win(board):
    """
    Checks if the game has been won.
    """
    win_conditions = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ]

    for condition in win_conditions:
        if board[condition[0]] == board[condition[1]] == board[condition[2]] != ' ':
            return True

    return False

def tic_tac_toe():
    """
    Plays a game of Tic-Tac-Toe.
    """
    board = [' '] * 9
    current_player = 'X'
    game_over = False

    while not game_over:
        print_board(board)

        print(f"Player {current_player}'s turn.")

        move = int(input('Enter a position (1-9): ')) - 1

        if board[move] == ' ':
            board[move] = current_player
        else:
            print('That position is already taken.')
            continue

        if check_win(board):
            print_board(board)
            print(f'Player {current_player} wins!')
            game_over = True
        elif ' ' not in board:
            print_board(board)
            print('Tie game!')
            game_over = True
        else:
            current_player = 'O' if current_player == 'X' else 'X'

if __name__ == '__main__':
    tic_tac_toe()