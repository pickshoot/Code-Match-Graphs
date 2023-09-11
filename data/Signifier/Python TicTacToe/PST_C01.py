import random

# Function to print the board
def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", board[i][0], "|", board[i][1], "|", board[i][2], "|")
        print("-------------")

# Function to check for a winner
def check_win(board, player):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == player:
            return True
        elif board[0][i] == board[1][i] == board[2][i] == player:
            return True
    if board[0][0] == board[1][1] == board[2][2] == player:
        return True
    elif board[0][2] == board[1][1] == board[2][0] == player:
        return True
    return False

# Function to play the game
def play_game():
    # Initialize the board
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    # Initialize the players
    players = ["X", "O"]
    # Choose a random player to start the game
    current_player = random.choice(players)
    # Print the empty board
    print_board(board)
    # Start the game
    while True:
        # Get the player's move
        print(f"Player {current_player}'s turn")
        row = int(input("Enter row number (1-3): ")) - 1
        col = int(input("Enter column number (1-3): ")) - 1
        # Check if the move is valid
        if board[row][col] != " ":
            print("Invalid move. Try again.")
            continue
        # Update the board
        board[row][col] = current_player
        # Print the updated board
        print_board(board)
        # Check if the current player has won
        if check_win(board, current_player):
            print(f"Player {current_player} wins!")
            break
        # Check if the board is full
        if all([all(row) for row in board]):
            print("It's a tie!")
            break
        # Switch to the other player
        current_player = [p for p in players if p != current_player][0]

# Play the game
play_game()
