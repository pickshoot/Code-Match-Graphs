def print_board(board):
    print("-------------")
    for row in range(3):
        print("|", end=" ")
        for col in range(3):
            print(board[row][col], "|", end=" ")
        print("\n-------------")

def check_winner(board):
    # Check rows for winner
    for row in range(3):
        if board[row][0] == board[row][1] == board[row][2] and board[row][0] != " ":
            return board[row][0]

    # Check columns for winner
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] != " ":
            return board[0][col]

    # Check diagonals for winner
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != " ":
        return board[0][0]

    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != " ":
        return board[0][2]

    return None

def play_game():
    # Create an empty board
    board = [[" ", " ", " "],
             [" ", " ", " "],
             [" ", " ", " "]]

    # Set the starting player
    current_player = "X"

    # Loop until the game is over
    while True:
        # Print the current board
        print_board(board)

        # Get the current player's move
        print("It's " + current_player + "'s turn.")
        row = int(input("Enter the row (1-3): ")) - 1
        col = int(input("Enter the column (1-3): ")) - 1

        # Check if the move is valid
        if row < 0 or row > 2 or col < 0 or col > 2 or board[row][col] != " ":
            print("Invalid move. Try again.")
            continue

        # Make the move
        board[row][col] = current_player

        # Check if the game is over
        winner = check_winner(board)
        if winner is not None:
            print_board(board)
            print("Congratulations, " + winner + "! You win!")
            break

        # Check for a tie game
        if all([all(row) for row in board]):
            print_board(board)
            print("It's a tie!")
            break

        # Switch to the other player
        current_player = "O" if current_player == "X" else "X"

if __name__ == "__main__":
    play_game()