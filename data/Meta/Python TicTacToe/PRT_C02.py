def print_board(board):
    print("   0  1  2")
    for i in range(3):
        row = "{}  ".format(i)
        for j in range(3):
            if board[i][j] is None:
                row += "_  "
            else:
                row += "{}  ".format(board[i][j])
        print(row)

def get_move(player):
    valid_move = False
    while not valid_move:
        move = input("Player {}, enter your move (row,column): ".format(player)).split(",")
        if len(move) != 2:
            print("Invalid move format. Please enter row,column.")
            continue
        row, col = move
        if not row.isdigit() or not col.isdigit():
            print("Invalid move format. Please enter row,column as integers.")
            continue
        row, col = int(row), int(col)
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid move. Please enter row and column values between 0 and 2.")
            continue
        if board[row][col] is not None:
            print("Position already taken. Please choose another.")
            continue
        valid_move = True
    return row, col

def check_win(board):
    # Check rows
    for i in range(3):
        if board[i][0] is not None and board[i][0] == board[i][1] == board[i][2]:
            return board[i][0]
    # Check columns
    for i in range(3):
        if board[0][i] is not None and board[0][i] == board[1][i] == board[2][i]:
            return board[0][i]
    # Check diagonals
    if board[0][0] is not None and board[0][0] == board[1][1] == board[2][2]:
        return board[0][0]
    if board[0][2] is not None and board[0][2] == board[1][1] == board[2][0]:
        return board[0][2]
    # No winner
    return None

play_again = True
while play_again:
    board = [[None, None, None],
             [None, None, None],
             [None, None, None]]
    players = ["X", "O"]
    turn = 0
    winner = None
    while winner is None:
        print_board(board)
        row, col = get_move(players[turn])
        board[row][col] = players[turn]
        winner = check_win(board)
        turn = (turn + 1) % 2
        if winner is not None:
            print_board(board)
            print("Congratulations, Player {} wins!".format(winner))
            break
        if all([all(row) for row in board]):
            print_board(board)
            print("It's a tie!")
            break
    play_again = input("Do you want to play again? (y/n): ").lower() == "y"