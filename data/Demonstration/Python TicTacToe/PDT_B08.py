def print_board(board):
    #print(" | |")
    print(" " + board[0] + " | " + board[1] + " | " + board[2])
    #print(" | |")
    print("-----------")
    #print(" | |")
    print(" " + board[3] + " | " + board[4] + " | " + board[5])
    #print(" | |")
    print("-----------")
    #print(" | |")
    print(" " + board[6] + " | " + board[7] + " | " + board[8])
    #print(" | |")

def check_win(board, player):
    return ((board[0] == player and board[1] == player and board[2] == player) or
            (board[3] == player and board[4] == player and board[5] == player) or
            (board[6] == player and board[7] == player and board[8] == player) or
            (board[0] == player and board[3] == player and board[6] == player) or
            (board[1] == player and board[4] == player and board[7] == player) or
            (board[2] == player and board[5] == player and board[8] == player) or
            (board[0] == player and board[4] == player and board[8] == player) or
            (board[2] == player and board[4] == player and board[6] == player))

def tic_tac_toe():
    board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    players = ["X", "O"]
    current_player = players[0]
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)

    for i in range(9):
        move = int(input(f"{current_player}, choose your move (1-9): ")) - 1
        if board[move] != " ":
            print("Invalid move! Try again.")
            continue

        board[move] = current_player
        print_board(board)

        if check_win(board, current_player):
            print(f"Congratulations, {current_player}! You win!")
            return

        if i == 8:
            print("It's a tie!")
            return

        current_player = players[i % 2]

tic_tac_toe()
