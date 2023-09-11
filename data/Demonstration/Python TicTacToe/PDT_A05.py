def print_board(board):
    print("   |   |")
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[6]} | {board[7]} | {board[8]} ")
    print("   |   |")

def get_move(player, board):
    while True:
        move = input(f"{player}, enter a position (1-9): ")
        if move.isdigit() and int(move) in range(1, 10) and board[int(move) - 1] == " ":
            return int(move) - 1
        else:
            print("Invalid move. Please try again.")

def check_win(board):
    for i in range(0, 9, 3):
        if board[i] != " " and board[i] == board[i+1] == board[i+2]:
            return True
    for i in range(0, 3):
        if board[i] != " " and board[i] == board[i+3] == board[i+6]:
            return True
    if board[0] != " " and board[0] == board[4] == board[8]:
        return True
    if board[2] != " " and board[2] == board[4] == board[6]:
        return True
    return False

def play_game():
    board = [" "] * 9
    players = ["X", "O"]
    current_player = players[0]
    while True:
        print_board(board)
        move = get_move(current_player, board)
        board[move] = current_player
        if check_win(board):
            print_board(board)
            print(f"{current_player} wins!")
            break
        if " " not in board:
            print_board(board)
            print("It's a tie!")
            break
        current_player = players[(players.index(current_player) + 1) % 2]

play_game()
