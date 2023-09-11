def print_board(board):
    print(f"{board[0]} | {board[1]} | {board[2]}")
    print("---------")
    print(f"{board[3]} | {board[4]} | {board[5]}")
    print("---------")
    print(f"{board[6]} | {board[7]} | {board[8]}")

def check_win(board):
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return True

    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return True

    # Check diagonals
    if board[0] == board[4] == board[8] != " ":
        return True
    elif board[2] == board[4] == board[6] != " ":
        return True

    return False

def tic_tac_toe():
    board = [" "] * 9
    players = ["X", "O"]
    turn = 0

    while not check_win(board):
        print_board(board)
        print(f"Player {players[turn]} turn")

        position = int(input("Enter position (1-9): ")) - 1
        while board[position] != " ":
            position = int(input("Position taken. Enter new position: ")) - 1

        board[position] = players[turn]
        turn = (turn + 1) % 2

    print_board(board)
    print(f"Player {players[turn]} wins!")

tic_tac_toe()
