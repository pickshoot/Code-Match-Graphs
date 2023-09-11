import numpy as np

def create_board():
    return np.zeros((3, 3), dtype=int)

def display_board(board):
    print("   0  1  2")
    for i in range(len(board)):
        print(i, board[i])

def is_valid_move(board, row, col):
    return board[row][col] == 0

def place_move(board, row, col, player):
    board[row][col] = player

def has_won(board, player):
    for i in range(len(board)):
        if (board[i] == [player]*3).all():
            return True
        if (board[:, i] == [player]*3).all():
            return True
    if (np.diag(board) == [player]*3).all():
        return True
    if (np.diag(np.fliplr(board)) == [player]*3).all():
        return True
    return False

def is_board_full(board):
    return (board == 0).sum() == 0

def play_game():
    board = create_board()
    display_board(board)

    player1 = 'X'
    player2 = 'O'

    while True:
        # player 1's turn
        row = int(input(f"{player1}'s turn. Enter row number (0-2): "))
        col = int(input(f"{player1}'s turn. Enter column number (0-2): "))
        if not is_valid_move(board, row, col):
            print("Invalid move! Try again.")
            continue
        place_move(board, row, col, player1)
        display_board(board)
        if has_won(board, player1):
            print(f"{player1} has won!")
            break
        if is_board_full(board):
            print("It's a tie!")
            break

        # player 2's turn
        row = int(input(f"{player2}'s turn. Enter row number (0-2): "))
        col = int(input(f"{player2}'s turn. Enter column number (0-2): "))
        if not is_valid_move(board, row, col):
            print("Invalid move! Try again.")
            continue
        place_move(board, row, col, player2)
        display_board(board)
        if has_won(board, player2):
            print(f"{player2} has won!")
            break
        if is_board_full(board):
            print("It's a tie!")
            break

    play_again = input("Do you want to play again? (y/n): ")
    if play_again.lower() == 'y':
        play_game()

play_game()