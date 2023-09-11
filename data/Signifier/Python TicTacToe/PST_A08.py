import numpy as np

# Create the board
board = np.array([["-", "-", "-"], ["-", "-", "-"], ["-", "-", "-"]])

# Function to check if the game has ended
def game_over(board):
    for i in range(3):
        if board[i][0] != "-" and board[i][0] == board[i][1] and board[i][1] == board[i][2]:
            return True
        if board[0][i] != "-" and board[0][i] == board[1][i] and board[1][i] == board[2][i]:
            return True
    if board[0][0] != "-" and board[0][0] == board[1][1] and board[1][1] == board[2][2]:
        return True
    if board[0][2] != "-" and board[0][2] == board[1][1] and board[1][1] == board[2][0]:
        return True
    if "-" not in board:
        return True
    return False

# Function to print the board
def print_board(board):
    for row in board:
        print("|".join(row))

# Function to play the game
def play_game():
    # Initialize variables
    current_player = "X"
    game_is_over = False

    # Loop until game is over
    while not game_is_over:
        # Print the board
        print_board(board)

        # Get player input
        row = int(input("Enter row (0-2): "))
        col = int(input("Enter column (0-2): "))

        # Check if input is valid
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid input. Please try again.")
            continue
        if board[row][col] != "-":
            print("That spot is already taken. Please try again.")
            continue

        # Update the board with player input
        board[row][col] = current_player

        # Check if game is over
        game_is_over = game_over(board)

        # Switch to other player
        if current_player == "X":
            current_player = "O"
        else:
            current_player = "X"

    # Print the final board and winner
    print_board(board)
    if "-" not in board:
        print("It's a tie!")
    else:
        print(current_player + " wins!")

# Play the game
play_game()