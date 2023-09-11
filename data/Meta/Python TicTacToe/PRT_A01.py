def print_board(board):
    """Prints the current state of the board"""
    print("   0  1  2")
    for i in range(3):
        print(f"{i} ", end="")
        for j in range(3):
            print(f" {board[i][j]} ", end="")
            if j != 2:
                print("|", end="")
        print()
        if i != 2:
            print("  ---+---+---")

def get_move(player, board):
    """Asks the player for their move and validates it"""
    while True:
        try:
            row = int(input(f"Player {player}, enter the row of your move (0-2): "))
            col = int(input(f"Player {player}, enter the column of your move (0-2): "))
            if row < 0 or row > 2 or col < 0 or col > 2:
                print("Invalid input: row and column must be between 0 and 2")
                continue
            if board[row][col] != " ":
                print("Invalid move: that square is already taken")
                continue
            return row, col
        except ValueError:
            print("Invalid input: row and column must be integers")

def check_win(board):
    """Checks if there is a win on the board"""
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != " ":
            return board[i][0]
        if board[0][i] == board[1][i] == board[2][i] != " ":
            return board[0][i]
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return board[0][2]
    return None

def play_again():
    """Asks the players if they want to play again"""
    while True:
        answer = input("Do you want to play again? (y/n): ")
        if answer.lower() == "y":
            return True
        elif answer.lower() == "n":
            return False
        else:
            print("Invalid input: answer must be 'y' or 'n'")

while True:
    # Initialize the game
    board = [[" " for _ in range(3)] for _ in range(3)]
    player = "X"
    print_board(board)

    # Play the game
    while True:
        # Get the player's move
        row, col = get_move(player, board)
        board[row][col] = player
        print_board(board)

        # Check for a win or a tie
        winner = check_win(board)
        if winner is not None:
            print(f"Player {winner} wins!")
            break
        if all(board[i][j] != " " for i in range(3) for j in range(3)):
            print("It's a tie!")
            break

        # Switch to the other player
        player = "O" if player == "X" else "X"

    # Ask if the players want to play again
    if not play_again():
        break