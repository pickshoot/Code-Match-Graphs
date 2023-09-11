import numpy as np

def create_board():
    board = np.zeros((3, 3))
    return board

def place(board, player, position):
    if board[position] == 0:
        board[position] = player
    else:
        print("This position is already occupied. Please try again.")
        position = tuple(map(int, input("Enter a position to place " + str(player) + " (row, column): ").split()))
        place(board, player, position)

def possibilities(board):
    return list(zip(*np.where(board == 0)))

def random_place(board, player):
    selections = possibilities(board)
    if len(selections) > 0:
        selection = selections[np.random.choice(len(selections))]
        place(board, player, selection)
    else:
        print("No empty positions remaining.")

def row_win(board, player):
    for row in board:
        if np.all(row == player):
            return True
    return False

def col_win(board, player):
    for col in board.T:
        if np.all(col == player):
            return True
    return False

def diag_win(board, player):
    if np.all(np.diag(board) == player):
        return True
    if np.all(np.diag(np.fliplr(board)) == player):
        return True
    return False

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
    print(board)
    winner = 0
    while winner == 0:
        for player in [1, 2]:
            random_place(board, player)
            print("Board after " + str(player) + " plays:")
            print(board)
            winner = evaluate(board)
            if winner != 0:
                break
    if winner == -1:
        print("The game was a tie!")
    else:
        print("Player " + str(winner) + " wins!")

play_game()
