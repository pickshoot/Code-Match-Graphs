def print_board(board):
    print("-------------")
    for row in board:
        print("|", end="")
        for cell in row:
            print(" " + cell + " |", end="")
        print("\n-------------")

def check_win(board, player):
    # check rows
    for row in board:
        if row == [player, player, player]:
            return True
    # check columns
    for col in range(3):
        if board[0][col] == player and board[1][col] == player and board[2][col] == player:
            return True
    # check diagonals
    if board[0][0] == player and board[1][1] == player and board[2][2] == player:
        return True
    if board[0][2] == player and board[1][1] == player and board[2][0] == player:
        return True
    return False

def get_move(board, player):
    while True:
        move = input("Player " + player + ", enter your move (row, column): ")
        row, col = move.split(",")
        row = int(row) - 1
        col = int(col) - 1
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid move, please enter a value between 1 and 3 for row and column.")
            continue
        if board[row][col] != " ":
            print("That cell is already occupied, please choose another.")
            continue
        return row, col

def play_game():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    player = "X"
    while True:
        print_board(board)
        row, col = get_move(board, player)
        board[row][col] = player
        if check_win(board, player):
            print_board(board)
            print("Player", player, "wins!")
            break
        if " " not in [cell for row in board for cell in row]:
            print_board(board)
            print("It's a tie!")
            break
        player = "O" if player == "X" else "X"

if __name__ == "__main__":
    play_game()