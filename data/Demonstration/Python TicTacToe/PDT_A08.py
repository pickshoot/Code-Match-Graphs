import random

def print_board(board):
    print("   |   |")
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[6]} | {board[7]} | {board[8]} ")
    print("   |   |")

def check_win(board):
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != ' ':
            return True
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != ' ':
            return True
    if board[0] == board[4] == board[8] != ' ' or board[2] == board[4] == board[6] != ' ':
        return True
    return False

def get_move(board, player):
    while True:
        move = input(f"{player}, enter a number from 1 to 9 to place your marker: ")
        if not move.isdigit():
            print("Invalid input. Please enter a number from 1 to 9.")
            continue
        move = int(move) - 1
        if move < 0 or move > 8:
            print("Invalid input. Please enter a number from 1 to 9.")
            continue
        if board[move] != ' ':
            print("That space is already taken. Please choose a different space.")
            continue
        break
    return move

def play_game():
    board = [' '] * 9
    players = ['X', 'O']
    current_player = random.choice(players)
    print_board(board)
    while not check_win(board) and ' ' in board:
        move = get_move(board, current_player)
        board[move] = current_player
        print_board(board)
        if check_win(board):
            print(f"{current_player} wins!")
            break
        if ' ' not in board:
            print("It's a tie.")
            break
        current_player = players[(players.index(current_player) + 1) % 2]

play_game()
