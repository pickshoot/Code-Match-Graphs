def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", end=" ")
        for j in range(3):
            print(board[i][j], "|", end=" ")
        print()
        print("-------------")

def check_winner(board):
    # Check rows
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != "-":
            return row[0]
    # Check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] != "-":
            return board[0][i]
    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != "-":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != "-":
        return board[0][2]
    return None

def get_move(player, board):
    while True:
        move = input(f"{player}, enter your move (row,column): ")
        row, col = move.split(",")
        row = int(row) - 1
        col = int(col) - 1
        if board[row][col] == "-":
            return row, col
        else:
            print("That spot is taken. Try again.")

def play_game():
    board = [["-", "-", "-"],
             ["-", "-", "-"],
             ["-", "-", "-"]]
    players = ["X", "O"]
    current_player = players[0]
    winner = None
    print_board(board)
    while not winner:
        row, col = get_move(current_player, board)
        board[row][col] = current_player
        print_board(board)
        winner = check_winner(board)
        if not winner:
            if current_player == players[0]:
                current_player = players[1]
            else:
                current_player = players[0]
    print(f"{winner} wins!")

play_game()
