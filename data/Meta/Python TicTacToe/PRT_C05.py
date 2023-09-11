def print_board(board):
    print("   |   |")
    print(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2])
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2])
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2])
    print("   |   |")

def get_move(player):
    row = int(input("Player " + player + ", enter row number (1-3): ")) - 1
    col = int(input("Player " + player + ", enter column number (1-3): ")) - 1
    return (row, col)

def is_valid_move(board, row, col):
    if row < 0 or row > 2 or col < 0 or col > 2:
        return False
    if board[row][col] != " ":
        return False
    return True

def is_winner(board, player):
    for i in range(3):
        if board[i][0] == player and board[i][1] == player and board[i][2] == player:
            return True
        if board[0][i] == player and board[1][i] == player and board[2][i] == player:
            return True
    if board[0][0] == player and board[1][1] == player and board[2][2] == player:
        return True
    if board[0][2] == player and board[1][1] == player and board[2][0] == player:
        return True
    return False

def play_game():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    print_board(board)
    player = "X"
    while True:
        row, col = get_move(player)
        if not is_valid_move(board, row, col):
            print("Invalid move! Try again.")
            continue
        board[row][col] = player
        print_board(board)
        if is_winner(board, player):
            print("Player " + player + " wins!")
            break
        if " " not in board[0] and " " not in board[1] and " " not in board[2]:
            print("Tie game!")
            break
        if player == "X":
            player = "O"
        else:
            player = "X"

while True:
    play_game()
    answer = input("Do you want to play again? (yes or no): ")
    if answer.lower() != "yes":
        break