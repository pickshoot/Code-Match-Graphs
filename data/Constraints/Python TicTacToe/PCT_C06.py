import os

# Function to clear the console
def clear_console():
    os.system('cls' if os.name == 'nt' else 'clear')

# Function to print the game board
def print_board(board):
    clear_console()
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("---+---+---")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("---+---+---")
    print(f" {board[6]} | {board[7]} | {board[8]} ")

# Function to check if a player has won
def check_win(board, player):
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

# Function to get user input
def get_input(board, player):
    while True:
        try:
            position = int(input(f"{player}, enter position (1-9): "))
            if board[position-1] == " ":
                return position
            else:
                print("That position is already taken!")
        except ValueError:
            print("Invalid input, please enter a number between 1-9")

# Function to play the game
def play_game():
    board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    players = ["X", "O"]
    current_player = players[0]
    while True:
        print_board(board)
        position = get_input(board, current_player)
        board[position-1] = current_player
        if check_win(board, current_player):
            print_board(board)
            print(f"{current_player} wins!")
            break
        if " " not in board:
            print_board(board)
            print("Tie game!")
            break
        current_player = players[(players.index(current_player)+1)%2]

# Start the game
play_game()