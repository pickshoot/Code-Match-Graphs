import numpy as np

def create_board():
    return np.zeros((3, 3), dtype=int)

def place(board, player, position):
    if board[position] == 0:
        board[position] = player
    else:
        print("Invalid move! Try again.")

def get_winner(board):
    for player in [1, 2]:
        # check rows
        if np.all(board == player, axis=1).any():
            return player
        # check columns
        elif np.all(board == player, axis=0).any():
            return player
        # check diagonals
        elif np.all(np.diag(board) == player) or np.all(np.diag(np.fliplr(board)) == player):
            return player
    return 0

def print_board(board):
    print(" 0 1 2")
    print("0: " + str(board[0]))
    print("1: " + str(board[1]))
    print("2: " + str(board[2]))

def play_game():
    board = create_board()
    player = 1
    print_board(board)
    winner = 0
    while not winner:
        position = input("Player " + str(player) + " turn, enter coordinates (row,col) separated by comma: ")
        row, col = position.split(",")
        row, col = int(row), int(col)
        place(board, player, (row, col))
        print_board(board)
        winner = get_winner(board)
        if not winner:
            player = 3 - player
            print("Player " + str(winner) + " wins!")

play_game()
