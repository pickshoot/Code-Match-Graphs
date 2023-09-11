def print_board(board):
    """Prints the current state of the board."""
    print("-------------")
    for row in board:
        print(f"| {' | '.join(row)} |")
        print("-------------")

def check_win(board):
    """Checks if a player has won the game."""
    # Check rows
    for row in board:
        if len(set(row)) == 1 and row[0] != " ":
            return row[0]

    # Check columns
    for col in range(3):
        if len(set([board[row][col] for row in range(3)])) == 1 and board[0][col] != " ":
            return board[0][col]

    # Check diagonals
    if len(set([board[i][i] for i in range(3)])) == 1 and board[0][0] != " ":
        return board[0][0]

    if len(set([board[i][2-i] for i in range(3)])) == 1 and board[0][2] != " ":
        return board[0][2]

    # No winner
    return None

def tic_tac_toe():
    """Main function that runs the game."""
    # Initialize board
    board = [[" " for _ in range(3)] for _ in range(3)]
    player = "X"

    # Play game
    while True:
        print_board(board)
        print(f"Player {player}'s turn.")
        row = int(input("Enter row (1-3): ")) - 1
        col = int(input("Enter column (1-3): ")) - 1
        if board[row][col] != " ":
            print("That spot is already taken. Try again.")
            continue
        board[row][col] = player
        winner = check_win(board)
        if winner:
            print_board(board)
            print(f"Congratulations! Player {winner} has won the game!")
            break
        elif all([spot != " " for row in board for spot in row]):
            print_board(board)
            print("The game is a tie!")
            break
        player = "O" if player == "X" else "X"

tic_tac_toe()
