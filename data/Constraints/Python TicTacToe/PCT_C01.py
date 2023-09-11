# Tic-Tac-Toe game

# Board
board = [' ' for _ in range(9)]

# Print the board
def print_board():
    row1 = '|'.join(board[0:3])
    row2 = '|'.join(board[3:6])
    row3 = '|'.join(board[6:9])
    print(row1)
    print('-' * 5)
    print(row2)
    print('-' * 5)
    print(row3)

# Check if the game is over
def is_game_over():
    return is_winner('X') or is_winner('O') or len(get_empty_squares()) == 0

# Check if there is a winner
def is_winner(player):
    if (board[0] == player and board[1] == player and board[2] == player) or \
        (board[3] == player and board[4] == player and board[5] == player) or \
        (board[6] == player and board[7] == player and board[8] == player) or \
        (board[0] == player and board[3] == player and board[6] == player) or \
        (board[1] == player and board[4] == player and board[7] == player) or \
        (board[2] == player and board[5] == player and board[8] == player) or \
        (board[0] == player and board[4] == player and board[8] == player) or \
        (board[2] == player and board[4] == player and board[6] == player):
        return True
    else:
        return False

# Get the empty squares
def get_empty_squares():
    return [i for i in range(9) if board[i] == ' ']

# Main game loop
def play_game():
    print("Welcome to Tic-Tac-Toe!")
    print_board()
    player = 'X'
    while not is_game_over():
        square = input(f"Player {player}, choose a square (0-8): ")
        if not square.isdigit() or int(square) < 0 or int(square) > 8:
            print("Invalid input. Please enter a number between 0 and 8.")
            continue
        square = int(square)
        if board[square] != ' ':
            print("That square is already taken. Please choose a different square.")
            continue
        board[square] = player
        print_board()
        if is_winner(player):
            print(f"Congratulations, Player {player} has won the game!")
            return
        player = 'O' if player == 'X' else 'X'
    print("The game is a tie!")

# Start the game
play_game()