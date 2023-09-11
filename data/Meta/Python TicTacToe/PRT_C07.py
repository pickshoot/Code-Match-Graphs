import numpy as np

def create_board():
    """Create an empty 3x3 Tic-Tac-Toe board."""
    return np.zeros((3,3), dtype=int)

def print_board(board):
    """Print the current Tic-Tac-Toe board."""
    symbols = {0: ' ', 1: 'X', -1: 'O'}
    print('   1  2  3')
    for i in range(3):
        row_str = str(i+1) + ' '
        for j in range(3):
            row_str += symbols[board[i][j]] + ' '
        print(row_str)

def get_move(player, board):
    """Prompt the player for their move and update the board."""
    while True:
        move = input('Player {} enter row,col to place {}: '.format(player, 'X' if player == 1 else 'O'))
        try:
            row, col = [int(i)-1 for i in move.split(',')]
            if row < 0 or row > 2 or col < 0 or col > 2:
                print('Invalid input. Row and column numbers must be between 1 and 3.')
            elif board[row][col] != 0:
                print('Invalid move. That space is already occupied.')
            else:
                board[row][col] = player
                break
        except ValueError:
            print('Invalid input. Please enter two numbers separated by a comma.')

def check_win(board):
    """Check if there is a winning player or a tie."""
    for player in [-1, 1]:
        # Check rows and columns for a win
        if np.any(np.sum(board, axis=0) == 3*player) or np.any(np.sum(board, axis=1) == 3*player):
            return player
        # Check diagonals for a win
        if np.sum(np.diag(board)) == 3*player or np.sum(np.diag(np.fliplr(board))) == 3*player:
            return player
    # Check for a tie
    if np.all(board != 0):
        return 0
    # Game is still in progress
    return None

def play_game():
    """Play a game of Tic-Tac-Toe."""
    print('Welcome to Tic-Tac-Toe!')
    while True:
        board = create_board()
        player = 1
        print_board(board)
        while True:
            get_move(player, board)
            print_board(board)
            winner = check_win(board)
            if winner is not None:
                break
            player = -player
        if winner == 0:
            print('The game is a tie!')
        else:
            print('Player {} has won the game!'.format('X' if winner == 1 else 'O'))
        play_again = input('Do you want to play again? (y/n): ')
        if play_again.lower() != 'y':
            break
    print('Thanks for playing!')

if __name__ == '__main__':
    play_game()