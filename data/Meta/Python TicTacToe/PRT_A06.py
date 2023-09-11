import sys

# Initialize the board
board = [['-', '-', '-'], ['-', '-', '-'], ['-', '-', '-']]


# Function to print the board
def print_board():
    for row in board:
        print(' | '.join(row))


# Function to check if a move is valid
def is_valid_move(row, col):
    if row < 0 or row > 2 or col < 0 or col > 2:
        return False
    elif board[row][col] != '-':
        return False
    else:
        return True


# Function to check if the game is over
def is_game_over():
    # Check for horizontal wins
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != '-':
            return True

    # Check for vertical wins
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] != '-':
            return True

    # Check for diagonal wins
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != '-':
        return True
    elif board[0][2] == board[1][1] == board[2][0] and board[0][2] != '-':
        return True

    # Check for ties
    for row in board:
        for square in row:
            if square == '-':
                return False
    return True


# Function to get player input
def get_input(player):
    while True:
        try:
            row = int(input(f"{player}, enter row (0-2): "))
            col = int(input(f"{player}, enter column (0-2): "))
            if is_valid_move(row, col):
                return (row, col)
            else:
                print("Invalid move. Please try again.")
        except ValueError:
            print("Invalid input. Please try again.")
        except KeyboardInterrupt:
            print("\nGoodbye!")
            sys.exit()


# Function to play the game
def play_game():
    print("Welcome to Tic-Tac-Toe!")
    print_board()
    player = 'X'
    while not is_game_over():
        row, col = get_input(player)
        global board
        board[row][col] = player
        print_board()
        if is_game_over():
            if any(row.count('X') == 3 for row in board):
                print("X wins!")
            elif any(row.count('O') == 3 for row in board):
                print("O wins!")
            else:
                print("Tie!")
            play_again = input("Do you want to play again? (y/n) ")
            if play_again.lower() == 'y':
                board = [['-', '-', '-'], ['-', '-', '-'], ['-', '-', '-']]
                print_board()
            else:
                print("Goodbye!")
                sys.exit()
        else:
            player = 'O' if player == 'X' else 'X'


# Start the game
play_game()