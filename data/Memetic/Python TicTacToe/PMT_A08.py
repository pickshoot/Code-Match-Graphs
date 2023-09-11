def print_board(board):
    for row in board:
        print("|".join(row))

def check_win(board):
    # Check rows
    for row in board:
        if row.count(row[0]) == len(row) and row[0] != "-":
            return True

    # Check columns
    for col in range(len(board[0])):
        if all(board[row][col] == board[0][col] and board[0][col] != "-" for row in range(len(board))):
            return True

    # Check diagonals
    if all(board[i][i] == board[0][0] and board[0][0] != "-" for i in range(len(board))) or all(board[i][len(board)-i-1] == board[0][len(board)-1] and board[0][len(board)-1] != "-" for i in range(len(board))):
        return True

    return False

def play_game():
    board = [["-" for _ in range(3)] for _ in range(3)]
    players = ["X", "O"]
    current_player = players[0]

    while True:
        print_board(board)
        row = int(input(f"{current_player}'s turn. Enter row (1-3): ")) - 1
        col = int(input(f"{current_player}'s turn. Enter column (1-3): ")) - 1

        if board[row][col] == "-":
            board[row][col] = current_player

            if check_win(board):
                print_board(board)
                print(f"{current_player} wins!")
                break
            elif all(all(row) for row in board):
                print_board(board)
                print("Tie game.")
                break
            else:
                current_player = players[1] if current_player == players[0] else players[0]
        else:
            print("That spot is already taken.")

play_game()