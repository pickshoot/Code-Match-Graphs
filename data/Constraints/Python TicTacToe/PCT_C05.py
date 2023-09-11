import sys

# Create the Tic-Tac-Toe board as a dictionary
board = {
    "1": " ", "2": " ", "3": " ",
    "4": " ", "5": " ", "6": " ",
    "7": " ", "8": " ", "9": " "
}

# Create a function to print the board
def print_board(board):
    print(f"{board['1']}|{board['2']}|{board['3']}")
    print("-|-|-")
    print(f"{board['4']}|{board['5']}|{board['6']}")
    print("-|-|-")
    print(f"{board['7']}|{board['8']}|{board['9']}")
    print("\n")

# Create a function to check if the game is over
def game_over(board):
    if (board["1"] == board["2"] == board["3"] != " " or
        board["4"] == board["5"] == board["6"] != " " or
        board["7"] == board["8"] == board["9"] != " " or
        board["1"] == board["4"] == board["7"] != " " or
        board["2"] == board["5"] == board["8"] != " " or
        board["3"] == board["6"] == board["9"] != " " or
        board["1"] == board["5"] == board["9"] != " " or
        board["3"] == board["5"] == board["7"] != " "):
        return True
    for key in board:
        if board[key] == " ":
            return False
    return True

# Create a function to handle the player's turn
def player_turn(player, board):
    valid_move = False
    while not valid_move:
        move = input(f"Player {player}, choose a position (1-9): ")
        if move not in board:
            print("Invalid input. Please enter a number between 1 and 9.")
            continue
        if board[move] != " ":
            print("That position is already taken. Please choose another.")
            continue
        valid_move = True
    board[move] = player
    print_board(board)

# Play the game!
print("Welcome to Tic-Tac-Toe!")
print("To make a move, enter a number between 1 and 9.")
print_board(board)

while not game_over(board):
    player_turn("X", board)
    if game_over(board):
        break
    player_turn("O", board)

print("Game over!")
if game_over(board):
    print("Congratulations, you won!")
else:
    print("It's a tie.")