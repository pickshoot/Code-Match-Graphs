import numpy as np

def create_board():
    return np.zeros((3,3), dtype=int)

def place(board, player, position):
    if board[position] == 0:
        board[position] = player
    else:
        print("This position is already taken. Please choose another position.")
        position = tuple(map(int, input("Enter a new position in the format row,column: ").split(',')))
        place(board, player, position)

def possibilities(board):
    return list(zip(*np.where(board == 0)))

def random_place(board, player):
    position = possibilities(board)[np.random.randint(len(possibilities(board)))]
    place(board, player, position)

def row_win(board, player):
    for i in range(len(board)):
        if np.all(board[i,:] == player):
            return True
    return False

def col_win(board, player):
    for i in range(len(board)):
        if np.all(board[:,i] == player):
            return True
    return False

def diag_win(board, player):
    if np.all(board.diagonal() == player) or np.all(np.fliplr(board).diagonal() == player):
        return True
    return False

def evaluate(board):
    winner = 0
    for player in [1,2]:
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
        for player in [1,2]:
            random_place(board, player)
            print(f"\nPlayer {player}'s turn:")
            print(board)
            winner = evaluate(board)
            if winner != 0:
                break
    if winner == -1:
        print("\nThe game is a tie!")
    else:
        print(f"\nCongratulations! Player {winner} wins the game!")

play_game()