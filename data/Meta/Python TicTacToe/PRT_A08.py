import numpy as np

def print_board(board):
    print("   0  1  2")
    for i in range(3):
        row_str = f"{i}  "
        for j in range(3):
            if board[i][j] == 0:
                row_str += " "
            elif board[i][j] == 1:
                row_str += "X"
            else:
                row_str += "O"
            if j != 2:
                row_str += " | "
        print(row_str)
        if i != 2:
            print("  --|---|--")

def get_move(player, board):
    while True:
        try:
            move_str = input(f"Player {player}, enter your move (row column): ")
            move = tuple(map(int, move_str.split()))
            if len(move) != 2 or move[0] not in range(3) or move[1] not in range(3):
                print("Invalid input. Please enter two numbers between 0 and 2 separated by a space.")
                continue
            if board[move[0]][move[1]] != 0:
                print("That square is already taken. Please choose another square.")
                continue
            return move
        except ValueError:
            print("Invalid input. Please enter two numbers between 0 and 2 separated by a space.")

def check_win(board):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != 0:
            return board[i][0]
        if board[0][i] == board[1][i] == board[2][i] != 0:
            return board[0][i]
    if board[0][0] == board[1][1] == board[2][2] != 0:
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != 0:
        return board[0][2]
    return 0

def play_again():
    while True:
        try:
            response = input("Play again? (y/n): ")
            if response.lower() == "y":
                return True
            elif response.lower() == "n":
                return False
            else:
                print("Invalid input. Please enter 'y' or 'n'.")
        except ValueError:
            print("Invalid input. Please enter 'y' or 'n'.")

def play_game():
    board = np.zeros((3, 3), dtype=int)
    print_board(board)
    player = 1
    while True:
        move = get_move(player, board)
        board[move[0]][move[1]] = player
        print_board(board)
        winner = check_win(board)
        if winner != 0:
            print(f"Player {winner} wins!")
            if not play_again():
                return
            else:
                board = np.zeros((3, 3), dtype=int)
                print_board(board)
                player = 1
                continue
        if np.count_nonzero(board) == 9:
            print("Tie!")
            if not play_again():
                return
            else:
                board = np.zeros((3, 3), dtype=int)
                print_board(board)
                player = 1
                continue
        player = 3 - player

if __name__ == "__main__":
    play_game()