# Tic-Tac-Toe game
import random

board = [' ' for _ in range(9)]
player, computer = '', ''

# Corners, Center and Others, respectively
moves = ((1, 7, 3, 9), (5,), (2, 4, 6, 8))
# Winner combinations
winners = ((1, 2, 3), (4, 5, 6), (7, 8, 9), (1, 4, 7), (2, 5, 8), (3, 6, 9), (1, 5, 9), (3, 5, 7))

# Function to print the Tic-Tac-Toe board
def print_board():
    print("\n")
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("---|---|---")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("---|---|---")
    print(f" {board[6]} | {board[7]} | {board[8]} ")
    print("\n")

# Function to select a symbol for the player
def select_symbol():
    symbols = ('X', 'O')
    global player, computer
    while True:
        player = input("Select your symbol (X/O) : ").upper()
        if player in symbols:
            computer = symbols[1 - symbols.index(player)]
            break

# Function to check if a move is valid
def is_valid(move):
    return board[move - 1] == ' '

# Function to check if the board is full
def is_full():
    return ' ' not in board

# Function to check if a player has won
def check_winner(symbol):
    for w in winners:
        if all(board[i-1] == symbol for i in w):
            return True
    return False

# Function to make the computer's move
def computer_move():
    for symbol in (computer, player):
        for move in moves:
            for m in move:
                if is_valid(m):
                    board[m - 1] = symbol
                    if check_winner(symbol):
                        return
                    board[m - 1] = ' '
        board[random.randint(0, 8)] = symbol
        return

# Function to play the game
def play():
    select_symbol()
    print_board()
    while True:
        move = int(input("Your move (1-9) : "))
        if is_valid(move):
            board[move - 1] = player
            if check_winner(player):
                print_board()
                print("You won!")
                return
            if is_full():
                print_board()
                print("It's a tie!")
                return
            computer_move()
            print_board()
            if check_winner(computer):
                print("You lost!")
                return
            if is_full():
                print("It's a tie!")
                return

play()