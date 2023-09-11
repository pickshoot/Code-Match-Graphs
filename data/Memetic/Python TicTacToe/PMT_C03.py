# Tic Tac Toe game in Python

# Create the game board as a list of empty strings
board = [" " for i in range(9)]

# Function to print the game board
def print_board():
    print("-------------")
    print("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")
    print("-------------")
    print("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")
    print("-------------")
    print("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")
    print("-------------")

# Function to check if the game is over
def is_game_over():
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return True

    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return True

    # Check diagonals
    if board[0] == board[4] == board[8] != " " or board[2] == board[4] == board[6] != " ":
        return True

    # Check if board is full
    if " " not in board:
        return True

    # Game is not over
    return False

# Function to get the player's move
def get_move(player):
    valid_move = False
    while not valid_move:
        move = input("Player " + player + ", enter your move (1-9): ")
        try:
            move = int(move) - 1
            if move >= 0 and move <= 8:
                if board[move] == " ":
                    valid_move = True
                else:
                    print("That space is already occupied!")
            else:
                print("Please enter a number between 1 and 9.")
        except ValueError:
            print("Please enter a number.")

    # Add the player's move to the board
    board[move] = player

# Function to play the game
def play_game():
    player = "X"
    while not is_game_over():
        print_board()
        get_move(player)
        if player == "X":
            player = "O"
        else:
            player = "X"

    # Game is over, print final board
    print_board()

    # Print the winner or a tie message
    if "X" in board and "O" not in board:
        print("Player X wins!")
    elif "O" in board and "X" not in board:
        print("Player O wins!")
    else:
        print("Tie game.")

# Start the game
play_game()