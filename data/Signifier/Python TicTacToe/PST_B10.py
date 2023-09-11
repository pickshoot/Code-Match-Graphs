import numpy as np

def create_board():
    board = np.zeros((3,3), dtype=int)
    return board

def place(board, player, position):
    if board[position] == 0:
        board[position] = player
    else:
        print("This position is already occupied. Please choose another position.")
        return False
    return board

def win(board, player):
    # Check rows
    for row in range(3):
        if np.all(board[row,:] == player):
            return True

    # Check columns
    for col in range(3):
        if np.all(board[:,col] == player):
            return True

    # Check diagonals
    if np.all(board.diagonal() == player):
        return True
    if np.all(np.fliplr(board).diagonal() == player):
        return True

    return False

def play_game():
    board = create_board()
    print(board)
    player = 1
    while True:
        position = input(f"Player {player}'s turn. Please enter a position (row,col): ")
        row, col = position.split(",")
        row = int(row)
        col = int(col)
        board = place(board, player, (row, col))
        if board is False:
            continue
        print(board)
        if win(board, player):
            print(f"Player {player} wins!")
            break
        if np.all(board != 0):
            print("It's a tie!")
            break
        player = 3 - player

play_game()