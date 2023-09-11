def print_board(board):
    print("-------------")
    print("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |")
    print("-------------")
    print("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |")
    print("-------------")
    print("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |")
    print("-------------")

def check_win(board, player):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == player:
            return True
        if board[0][i] == board[1][i] == board[2][i] == player:
            return True
    if board[0][0] == board[1][1] == board[2][2] == player:
        return True
    if board[0][2] == board[1][1] == board[2][0] == player:
        return True
    return False

def tic_tac_toe():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    player = "X"
    game_over = False
    while not game_over:
        print_board(board)
        print("It's " + player + "'s turn.")
        row = int(input("Enter row (0-2): "))
        col = int(input("Enter column (0-2): "))
        if board[row][col] != " ":
            print("That space is already taken. Try again.")
            continue
        board[row][col] = player
        if check_win(board, player):
            print_board(board)
            print(player + " wins!")
            game_over = True
        elif " " not in board[0] and " " not in board[1] and " " not in board[2]:
            print_board(board)
            print("It's a tie!")
            game_over = True
        else:
            player = "O" if player == "X" else "X"

tic_tac_toe()