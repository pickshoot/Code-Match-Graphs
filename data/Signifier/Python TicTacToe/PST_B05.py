def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", end=" ")
        for j in range(3):
            print(board[i][j], "|", end=" ")
        print()
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
    players = ["X", "O"]
    current_player = players[0]
    winner = False
    moves_left = 9
    print("Let's play Tic-Tac-Toe!")
    print_board(board)
    while moves_left > 0 and not winner:
        print(f"Player {current_player}'s turn.")
        row = int(input("Enter row number (0, 1, 2): "))
        col = int(input("Enter column number (0, 1, 2): "))
        if board[row][col] != " ":
            print("That spot is already taken. Try again.")
            continue
        board[row][col] = current_player
        print_board(board)
        winner = check_win(board, current_player)
        if not winner:
            moves_left -= 1
            current_player = players[moves_left % 2]
    if winner:
        print(f"Player {current_player} wins!")
    else:
        print("It's a tie!")

tic_tac_toe()
