def print_board(board):
    print("   0  1  2")
    for i in range(3):
        row = str(i) + " "
        for j in range(3):
            if board[i][j] is None:
                row += "  "
            else:
                row += " " + board[i][j] + " "
            if j != 2:
                row += "|"
        print(row)
        if i != 2:
            print("  ---------")

def check_win(board):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] and board[i][0] is not None:
            return board[i][0]
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] is not None:
            return board[0][i]
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] is not None:
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] is not None:
        return board[0][2]
    if all([board[i][j] is not None for i in range(3) for j in range(3)]):
        return "Tie"
    return None

def play_game():
    board = [[None, None, None], [None, None, None], [None, None, None]]
    player = "X"
    while True:
        print_board(board)
        print("Player", player)
        row = input("Enter row (0-2): ")
        if row.lower() == "q":
            return
        col = input("Enter column (0-2): ")
        if col.lower() == "q":
            return
        try:
            row = int(row)
            col = int(col)
            if row < 0 or row > 2 or col < 0 or col > 2:
                raise ValueError
        except ValueError:
            print("Invalid input, please try again.")
            continue
        if board[row][col] is not None:
            print("That square is already taken, please try again.")
            continue
        board[row][col] = player
        winner = check_win(board)
        if winner is not None:
            print_board(board)
            if winner == "Tie":
                print("It's a tie!")
            else:
                print("Player", winner, "wins!")
            play_again = input("Do you want to play again? (y/n): ")
            if play_again.lower() == "y":
                play_game()
            else:
                return
        player = "O" if player == "X" else "X"

play_game()