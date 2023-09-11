def print_board(board):
    print("  1 2 3")
    for i in range(3):
        print(f"{chr(97+i)} {' '.join(board[i])}")

def check_winner(board):
    # Check rows
    for row in board:
        if len(set(row)) == 1 and row[0] != " ":
            return row[0]
    # Check columns
    for i in range(3):
        if len(set([board[j][i] for j in range(3)])) == 1 and board[0][i] != " ":
            return board[0][i]
    # Check diagonals
    if len(set([board[i][i] for i in range(3)])) == 1 and board[0][0] != " ":
        return board[0][0]
    if len(set([board[i][2-i] for i in range(3)])) == 1 and board[0][2] != " ":
        return board[0][2]
    return None

def play_game():
    board = [[" "]*3 for _ in range(3)]
    player = "X"
    while True:
        print_board(board)
        row = input(f"{player}, enter row (a-c): ")
        col = input(f"{player}, enter column (1-3): ")
        if not (row.isalpha() and len(row) == 1 and col.isdigit() and len(col) == 1):
            print("Invalid input, please try again.")
            continue
        i, j = ord(row)-97, int(col)-1
        if not (0 <= i < 3 and 0 <= j < 3 and board[i][j] == " "):
            print("Invalid move, please try again.")
            continue
        board[i][j] = player
        winner = check_winner(board)
        if winner:
            print_board(board)
            print(f"Congratulations, {winner} wins!")
            break
        elif all(all(cell != " " for cell in row) for row in board):
            print_board(board)
            print("It's a tie!")
            break
        player = "O" if player == "X" else "X"

play_game()
