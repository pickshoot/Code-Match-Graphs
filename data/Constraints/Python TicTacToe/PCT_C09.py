def print_board(board):
    """Print the current state of the board."""
    print("     |     |")
    print("  {}  |  {}  |  {}".format(board[0], board[1], board[2]))
    print("_____|_____|_____")
    print("     |     |")
    print("  {}  |  {}  |  {}".format(board[3], board[4], board[5]))
    print("_____|_____|_____")
    print("     |     |")
    print("  {}  |  {}  |  {}".format(board[6], board[7], board[8]))
    print("     |     |")

def get_move(board, player):
    """Get the player's move and update the board."""
    while True:
        try:
            move = int(input("Player {}, enter your move (1-9): ".format(player)))
            if move < 1 or move > 9:
                print("Invalid move. Please enter a number from 1 to 9.")
            elif board[move-1] != ' ':
                print("That space is already taken. Please choose another.")
            else:
                board[move-1] = player
                break
        except ValueError:
            print("Invalid move. Please enter a number from 1 to 9.")

def check_win(board):
    """Check if the game has been won."""
    wins = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],  # rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8],  # columns
        [0, 4, 8], [2, 4, 6]             # diagonals
    ]
    for i, j, k in wins:
        if board[i] == board[j] == board[k] != ' ':
            return board[i]
    if ' ' not in board:
        return 'Tie'
    return None

def tic_tac_toe():
    """Play a game of tic-tac-toe."""
    board = [' '] * 9
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)
    players = ['X', 'O']
    turn = 0
    winner = None
    while winner is None:
        get_move(board, players[turn])
        print_board(board)
        winner = check_win(board)
        turn = (turn + 1) % 2
    if winner == 'Tie':
        print("It's a tie!")
    else:
        print("Congratulations, player {} won!".format(winner))

if __name__ == '__main__':
    tic_tac_toe()