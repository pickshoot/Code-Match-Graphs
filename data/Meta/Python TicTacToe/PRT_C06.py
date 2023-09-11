import numpy as np

def create_board():
    """Create an empty Tic-Tac-Toe board."""
    return np.zeros((3,3), dtype=int)

def place_marker(board, player, position):
    """Place a marker (X or O) on the board at the given position."""
    row, col = position
    board[row][col] = player

def is_valid_move(board, position):
    """Check if a move is valid (i.e., the given position is empty)."""
    row, col = position
    return board[row][col] == 0

def get_player_move(player, board):
    """Prompt the player to enter their move (position to place their marker)."""
    position_str = input(f"Player {player}, enter a position (row, col) to place your marker (e.g., 1, 2): ")
    position = tuple(map(int, position_str.split(',')))
    if not is_valid_move(board, position):
        print("Invalid move. Please try again.")
        return get_player_move(player, board)
    return position

def print_board(board):
    """Print the Tic-Tac-Toe board."""
    print("   |   |   ")
    print("-----------")
    for row in board:
        print(f" {row[0]} | {row[1]} | {row[2]} ")
        print("-----------")
        print("   |   |   ")

def has_won(player, board):
    """Check if the given player has won the game."""
    # Check rows
    for row in board:
        if np.all(row == player):
            return True
    # Check columns
    for col in board.T:
        if np.all(col == player):
            return True
    # Check diagonals
    if np.all(np.diag(board) == player):
        return True
    if np.all(np.diag(np.fliplr(board)) == player):
        return True
    return False

def is_tie(board):
    """Check if the game has ended in a tie."""
    return np.all(board != 0)

def play_game():
    """Play a game of Tic-Tac-Toe."""
    board = create_board()
    players = [1, 2]
    markers = {1: 'X', 2: 'O'}
    print_board(board)
    while True:
        for player in players:
            marker = markers[player]
            position = get_player_move(player, board)
            place_marker(board, player, position)
            print_board(board)
            if has_won(player, board):
                print(f"Player {player} ({marker}) has won!")
                return
            if is_tie(board):
                print("The game has ended in a tie!")
                return

if __name__ == "__main__":
    while True:
        play_game()
        play_again = input("Do you want to play again? (y/n) ")
        if play_again.lower() != 'y':
            break