import random

# The game board is represented as a list of 9 strings
# Each string represents a cell on the board, either "X", "O", or " "
board = [" "] * 9

# A dictionary mapping player symbols ("X" or "O") to their names
players = {"X": "Player 1", "O": "Player 2"}

# A function that prints the current state of the board
def print_board():
    print("-------------")
    for i in range(3):
        print("|", board[i*3], "|", board[i*3+1], "|", board[i*3+2], "|")
        print("-------------")

# A function that checks if a given player has won the game
def has_won(player):
    # Check rows
    for i in range(3):
        if board[i*3:i*3+3] == [player] * 3:
            return True
    # Check columns
    for i in range(3):
        if board[i:i+7:3] == [player] * 3:
            return True
    # Check diagonals
    if board[0] == player and board[4] == player and board[8] == player:
        return True
    if board[2] == player and board[4] == player and board[6] == player:
        return True
    return False

# A function that prompts the user to make a move and updates the board accordingly
def make_move(player):
    while True:
        try:
            move = int(input(players[player] + ", please enter your move (1-9): "))
            if move < 1 or move > 9 or board[move-1] != " ":
                print("Invalid move, please try again.")
            else:
                board[move-1] = player
                break
        except ValueError:
            print("Invalid input, please enter a number.")
    print_board()
    if has_won(player):
        print(players[player] + " wins!")
        return True
    elif " " not in board:
        print("It's a tie!")
        return True
    else:
        return False

# The main game loop
def play_game():
    print("Welcome to Tic-Tac-Toe!")
    print_board()
    current_player = "X"
    while True:
        if make_move(current_player):
            break
        current_player = "O" if current_player == "X" else "X"

# A function that randomly chooses a player to go first
def choose_first_player():
    return random.choice(["X", "O"])

# The entry point of the program
if __name__ == "__main__":
    play_game()