import itertools

def display_board(board):
    print('   |   |')
    print(' ' + board[0] + ' | ' + board[1] + ' | ' + board[2])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[3] + ' | ' + board[4] + ' | ' + board[5])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[6] + ' | ' + board[7] + ' | ' + board[8])
    print('   |   |')

def get_player_move(player_marker, board):
    while True:
        move = input("Player " + player_marker + ", enter your move (row column): ")
        try:
            row, col = move.split()
            row, col = int(row), int(col)
            if row < 1 or row > 3 or col < 1 or col > 3:
                raise ValueError
        except ValueError:
            print("Invalid input. Please enter row and column numbers separated by a space.")
            continue
        index = (row-1)*3 + (col-1)
        if board[index] != ' ':
            print("That position is already occupied. Please choose a different position.")
            continue
        return index

def check_win(board):
    win_positions = [(0, 1, 2), (3, 4, 5), (6, 7, 8), (0, 3, 6), (1, 4, 7), (2, 5, 8), (0, 4, 8), (2, 4, 6)]
    for pos in win_positions:
        if board[pos[0]] == board[pos[1]] == board[pos[2]] != ' ':
            return board[pos[0]]
    if ' ' not in board:
        return 'tie'
    return None

def play_game():
    print("Welcome to Tic-Tac-Toe!")
    while True:
        board = [' ']*9
        player_markers = itertools.cycle(['X', 'O'])
        display_board(board)
        while True:
            player_marker = next(player_markers)
            index = get_player_move(player_marker, board)
            board[index] = player_marker
            display_board(board)
            winner = check_win(board)
            if winner is not None:
                if winner == 'tie':
                    print("The game ended in a tie.")
                else:
                    print("Player " + winner + " won!")
                break
        play_again = input("Do you want to play again? (y/n) ")
        if play_again.lower() != 'y':
            break

play_game()