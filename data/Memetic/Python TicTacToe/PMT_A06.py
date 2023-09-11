board = [' '] * 9
player = 'X'

# Print the Tic-Tac-Toe board
def print_board():
    print(f"| {board[0]} | {board[1]} | {board[2]} |")
    print(f"| {board[3]} | {board[4]} | {board[5]} |")
    print(f"| {board[6]} | {board[7]} | {board[8]} |")

# Check if the move is valid
def is_valid_move(move):
    if move < 1 or move > 9:
        return False
    if board[move-1] != ' ':
        return False
    return True

# Check if the game is over
def is_game_over():
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != ' ':
            return True
    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != ' ':
            return True
    # Check diagonals
    if board[0] == board[4] == board[8] != ' ':
        return True
    if board[2] == board[4] == board[6] != ' ':
        return True
    # Check if board is full
    if ' ' not in board:
        return True
    return False

# Switch player
def switch_player():
    global player
    if player == 'X':
        player = 'O'
    else:
        player = 'X'

# Main game loop
def play_game():
    print("Welcome to Tic-Tac-Toe!")
    print_board()
    while not is_game_over():
        move = int(input(f"Player {player}, enter your move (1-9): "))
        if is_valid_move(move):
            board[move-1] = player
            print_board()
            if is_game_over():
                print(f"Player {player} wins!")
            else:
                switch_player()
        else:
            print("Invalid move. Please try again.")
    print("Game over!")

# Start the game
play_game()