import os

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

def display_board(board):
    print("   |   |")
    print(f" {board[0][0]} | {board[0][1]} | {board[0][2]} ")
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(f" {board[1][0]} | {board[1][1]} | {board[1][2]} ")
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(f" {board[2][0]} | {board[2][1]} | {board[2][2]} ")
    print("   |   |")

def get_player_move(player):
    move = input(f"{player}, enter your move (row[1-3], column[1-3]): ")
    move = move.strip().split()
    move = [int(i) - 1 for i in move]  # adjust for 0-based indexing
    return tuple(move)

def is_valid_move(board, move):
    row, col = move
    if row < 0 or row > 2 or col < 0 or col > 2:
        return False
    if board[row][col] != " ":
        return False
    return True

def make_move(board, move, symbol):
    row, col = move
    board[row][col] = symbol

def is_winner(board, symbol):
    # check rows
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == symbol:
            return True
    # check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] == symbol:
            return True
    # check diagonals
    if board[0][0] == board[1][1] == board[2][2] == symbol:
        return True
    if board[0][2] == board[1][1] == board[2][0] == symbol:
        return True
    return False

def is_board_full(board):
    for row in board:
        for cell in row:
            if cell == " ":
                return False
    return True

def play_game():
    player1 = input("Enter player 1 name: ")
    player2 = input("Enter player 2 name: ")
    symbols = ["X", "O"]
    players = [player1, player2]
    board = [[" " for _ in range(3)] for _ in range(3)]
    display_board(board)
    current_player = 0
    while True:
        move = get_player_move(players[current_player])
        if not is_valid_move(board, move):
            print("Invalid move, try again.")
            continue
        make_move(board, move, symbols[current_player])
        display_board(board)
        if is_winner(board, symbols[current_player]):
            print(f"{players[current_player]} wins!")
            return
        if is_board_full(board):
            print("It's a tie!")
            return
        current_player = (current_player + 1) % 2

while True:
    play_game()
    answer = input("Do you want to play again? (y/n) ")
    if answer.lower() != "y":
        break