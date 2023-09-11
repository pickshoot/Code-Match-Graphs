import numpy as np

def create_board():
    return np.zeros((3,3), dtype=int)

def print_board(board):
    symbols = {0: ' ', 1: 'X', 2: 'O'}
    print('    1   2   3')
    print('  +---+---+---+')
    for i in range(3):
        row = [symbols[board[i][j]] for j in range(3)]
        print(f'{i+1} | ' + ' | '.join(row) + ' |')
        print('  +---+---+---+')

def get_move(player):
    while True:
        try:
            move = input(f"Player {player}, enter row and column numbers (e.g. 1 2): ")
            row, col = map(int, move.split())
            if row < 1 or row > 3 or col < 1 or col > 3:
                print("Invalid position. Row and column numbers must be between 1 and 3.")
            else:
                return (row-1, col-1)
        except ValueError:
            print("Invalid input. Please enter row and column numbers separated by a space.")

def is_valid_move(board, move):
    return board[move] == 0

def make_move(board, move, player):
    board[move] = player
    return board

def check_win(board, player):
    for i in range(3):
        if np.all(board[i,:] == player):
            return True
        if np.all(board[:,i] == player):
            return True
    if np.all(board.diagonal() == player):
        return True
    if np.all(np.fliplr(board).diagonal() == player):
        return True
    return False

def check_tie(board):
    return np.count_nonzero(board) == 9

def play_game():
    print("Welcome to Tic-Tac-Toe!")
    print_board(create_board())
    while True:
        for player in (1, 2):
            board = create_board()
            symbol = {1: 'X', 2: 'O'}[player]
            while True:
                move = get_move(player)
                if is_valid_move(board, move):
                    break
                else:
                    print("That position is already occupied.")
            board = make_move(board, move, player)
            print_board(board)
            if check_win(board, player):
                print(f"Congratulations! Player {player} ({symbol}) wins!")
                return
            if check_tie(board):
                print("It's a tie!")
                return

if __name__ == '__main__':
    while True:
        play_game()
        again = input("Play again? (y/n) ")
        if again.lower() != 'y':
            break