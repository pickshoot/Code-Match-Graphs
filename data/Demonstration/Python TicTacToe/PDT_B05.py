def print_board(board):
    print("-------------")
    print("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |")
    print("-------------")
    print("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |")
    print("-------------")
    print("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |")
    print("-------------")

def get_move(player, board):
    while True:
        move = input(f"{player}'s turn. Enter the row and column (e.g. 1 2): ").split()
        if len(move) != 2:
            print("Invalid input. Please enter two numbers separated by a space.")
            continue
        row, col = move
        if not (row.isdigit() and col.isdigit()):
            print("Invalid input. Please enter two numbers.")
            continue
        row, col = int(row), int(col)
        if not (1 <= row <= 3 and 1 <= col <= 3):
            print("Invalid input. Row and column must be between 1 and 3.")
            continue
        if board[row-1][col-1] != " ":
            print("That spot is already taken. Please choose another.")
            continue
        return row-1, col-1

def check_win(board):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != " ":
            return True
        if board[0][i] == board[1][i] == board[2][i] != " ":
            return True
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return True
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return True
    return False

def tic_tac_toe():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    players = ["X", "O"]
    current_player = 0
    while True:
        print_board(board)
        row, col = get_move(players[current_player], board)
        board[row][col] = players[current_player]
        if check_win(board):
            print_board(board)
            print(f"{players[current_player]} wins!")
            break
        if all(" " not in row for row in board):
            print_board(board)
            print("It's a tie!")
            break
        current_player = (current_player + 1) % 2

if __name__ == "__main__":
    tic_tac_toe()
