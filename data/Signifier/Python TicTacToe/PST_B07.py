def print_board(board):
    """
    Print the current state of the board
    """
    print(f"{board[0]} | {board[1]} | {board[2]}")
    print("--|---|--")
    print(f"{board[3]} | {board[4]} | {board[5]}")
    print("--|---|--")
    print(f"{board[6]} | {board[7]} | {board[8]}")


def check_win(board):
    """
    Check if a player has won the game
    """
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return True
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return True
    if board[0] == board[4] == board[8] != " ":
        return True
    if board[2] == board[4] == board[6] != " ":
        return True
    return False


def tic_tac_toe():
    """
    The main game loop
    """
    print("Welcome to Tic-Tac-Toe!")
    print("To play, enter a number from 1-9 as shown on the board.")
    print("The player who gets 3 in a row wins!")
    board = [" "]*9
    player = "X"
    while True:
        print_board(board)
        move = input(f"{player}'s turn. Enter a position: ")
        while not move.isdigit() or int(move) < 1 or int(move) > 9 or board[int(move)-1] != " ":
            move = input(f"Invalid move. {player}'s turn. Enter a position: ")
        board[int(move)-1] = player
        if check_win(board):
            print_board(board)
            print(f"{player} wins!")
            break
        if " " not in board:
            print_board(board)
            print("It's a tie!")
            break
        player = "O" if player == "X" else "X"


tic_tac_toe()
