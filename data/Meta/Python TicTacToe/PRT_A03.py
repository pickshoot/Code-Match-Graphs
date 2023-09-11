import sys

# Define constants for board size and player markers
BOARD_SIZE = 3
PLAYER_ONE_MARKER = 'X'
PLAYER_TWO_MARKER = 'O'

def print_board(board):
    """Prints out the current state of the board."""
    for row in board:
        print(' | '.join(row))
    print()

def is_valid_move(move, board):
    """Returns True if the move is valid, False otherwise."""
    row, col = move
    if row < 0 or row >= BOARD_SIZE or col < 0 or col >= BOARD_SIZE:
        return False
    elif board[row][col] != ' ':
        return False
    else:
        return True

def get_move(player, board):
    """Prompts the player to enter their move, and returns it as a tuple."""
    marker = PLAYER_ONE_MARKER if player == 1 else PLAYER_TWO_MARKER
    while True:
        try:
            row = int(input(f"Player {player} ({marker}), enter row (0-2): "))
            col = int(input(f"Player {player} ({marker}), enter column (0-2): "))
            if is_valid_move((row, col), board):
                return (row, col)
            else:
                print("Invalid move, please try again.")
        except ValueError:
            print("Invalid input, please enter a number.")

def check_win(board):
    """Returns the player number that has won, or 0 if there is no winner yet."""
    for i in range(BOARD_SIZE):
        # Check rows
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return 1 if board[i][0] == PLAYER_ONE_MARKER else 2
        # Check columns
        elif board[0][i] == board[1][i] == board[2][i] != ' ':
            return 1 if board[0][i] == PLAYER_ONE_MARKER else 2
    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return 1 if board[0][0] == PLAYER_ONE_MARKER else 2
    elif board[0][2] == board[1][1] == board[2][0] != ' ':
        return 1 if board[0][2] == PLAYER_ONE_MARKER else 2
    # No winner yet
    return 0

def play_game():
    """Plays a single game of Tic-Tac-Toe."""
    board = [[' ' for _ in range(BOARD_SIZE)] for _ in range(BOARD_SIZE)]
    current_player = 1
    winner = 0
    while True:
        print_board(board)
        move = get_move(current_player, board)
        row, col = move
        board[row][col] = PLAYER_ONE_MARKER if current_player == 1 else PLAYER_TWO_MARKER
        winner = check_win(board)
        if winner != 0:
            break
        elif all(all(cell != ' ' for cell in row) for row in board):
            # Board is full and no winner, so it's a tie
            break
        current_player = 3 - current_player  # Switch players (1 -> 2 or 2 -> 1)
    print_board(board)
    if winner == 0:
        print("It's a tie!")
    else:
        print(f"Player {winner} wins!")
    play_again = input("Do you want to play again? (y/n) ")
    while True:
        choice = input("Enter Y for Yes or N for No: ")
        if choice.upper() == 'Y':
            play_game()
        elif choice.upper() == 'N':
            print("Thanks for playing!")
            sys.exit()
        else:
            print("Invalid input, please try again.")

if __name__ == '__main__':
    play_game()