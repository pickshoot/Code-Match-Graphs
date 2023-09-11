def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", end="")
        for j in range(3):
            print(" " + str(board[i][j]) + " |", end="")
        print("\n-------------")

def check_win(board, player):
    for i in range(3):
        if (board[i][0] == player and board[i][1] == player and board[i][2] == player):
            return True
        if (board[0][i] == player and board[1][i] == player and board[2][i] == player):
            return True
    if (board[0][0] == player and board[1][1] == player and board[2][2] == player):
        return True
    if (board[0][2] == player and board[1][1] == player and board[2][0] == player):
        return True
    return False

def tic_tac_toe():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    players = ["X", "O"]
    current_player = players[0]
    while True:
        print_board(board)
        print("It's " + current_player + "'s turn.")
        row = int(input("Enter row number (0-2): "))
        col = int(input("Enter column number (0-2): "))
        if (board[row][col] == " "):
            board[row][col] = current_player
            if (check_win(board, current_player)):
                print_board(board)
                print(current_player + " wins!")
                break
            if (current_player == players[0]):
                current_player = players[1]
            else:
                current_player = players[0]
        else:
            print("This cell is already occupied. Try again.")

tic_tac_toe()
