import numpy as np
import random

def create_board():
    return np.zeros((3, 3), dtype=int)

def place(board, player, position):
    if board[position] == 0:
        board[position] = player
        return board
    else:
        print("Invalid move. Please try again.")
        return board

def possibilities(board):
    return list(zip(*np.where(board == 0)))

def random_place(board, player):
    position = random.choice(possibilities(board))
    return place(board, player, position)

def row_win(board, player):
    return np.any(np.all(board == player, axis=1))

def col_win(board, player):
    return np.any(np.all(board == player, axis=0))

def diag_win(board, player):
    diagonal1 = np.all(np.diag(board) == player)
    diagonal2 = np.all(np.diag(np.fliplr(board)) == player)
    return diagonal1 or diagonal2

def evaluate(board):
    winner = 0
    for player in [1, 2]:
        if row_win(board, player) or col_win(board, player) or diag_win(board, player):
            winner = player
    if np.all(board != 0) and winner == 0:
        winner = -1
    return winner

def play_game():
    board = create_board()
    player = 1
    while True:
        print(board)
        position = input(f"Player {player}, enter a position (row, column) for your next move (e.g. 0,0): ")
        position = tuple(map(int, position.split(",")))
        board = place(board, player, position)
        winner = evaluate(board)
        if winner != 0:
            print(board)
            if winner == -1:
                print("It's a tie!")
            else:
                print(f"Player {winner} wins!")
            break
        player = 3 - player

play_game()
