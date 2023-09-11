def print_board(board):
    print(f"{board[0]}|{board[1]}|{board[2]}")
    print("-+-+-")
    print(f"{board[3]}|{board[4]}|{board[5]}")
    print("-+-+-")
    print(f"{board[6]}|{board[7]}|{board[8]}")

def check_win(board):
    # Check horizontal rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] and board[i] != "-":
            return True

    # Check vertical columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] and board[i] != "-":
            return True

    # Check diagonals
    if board[0] == board[4] == board[8] and board[0] != "-":
        return True
    if board[2] == board[4] == board[6] and board[2] != "-":
        return True

    return False

def get_player_move(player, board):
    while True:
        try:
            move = int(input(f"{player}, enter a move (1-9): "))
            if move < 1 or move > 9:
                print("Move must be between 1 and 9.")
                continue
            if board[move-1] != "-":
                print("That space is already taken.")
                continue
            return move
        except ValueError:
            print("Invalid input. Enter a number between 1 and 9.")

def tic_tac_toe():
    board = ["-" for _ in range(9)]
    print_board(board)

    players = ["X", "O"]
    turn = 0

    while True:
        player = players[turn % 2]
        move = get_player_move(player, board)
        board[move-1] = player
        print_board(board)

        if check_win(board):
            print(f"{player} wins!")
            break

        if "-" not in board:
            print("Tie game.")
            break

        turn += 1

tic_tac_toe()
