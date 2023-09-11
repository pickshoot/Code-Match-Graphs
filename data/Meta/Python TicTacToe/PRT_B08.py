def print_board(board):
    """Prints the current state of the board."""
    for row in board:
        print("|".join(row))

def check_winner(board):
    """Checks if there is a winner and returns the winner's symbol."""
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] and board[i][0] != " ":
            return board[i][0]
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] != " ":
            return board[0][i]
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != " ":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != " ":
        return board[0][2]
    return None

def play_game():
    """Runs one game of Tic-Tac-Toe."""
    # Prompt for player names
    player1 = input("Enter name for Player 1 (X): ")
    player2 = input("Enter name for Player 2 (O): ")

    # Initialize the board
    board = [[" ", " ", " "] for _ in range(3)]
    current_player = player1

    # Play the game
    while True:
        # Print the current state of the board
        print(f"{current_player}'s turn:")
        print_board(board)

        # Prompt for the next move
        while True:
            move = input("Enter row and column (e.g. 1,2): ")
            try:
                row, col = map(int, move.split(","))
            except ValueError:
                print("Invalid input. Try again.")
                continue
            if not (1 <= row <= 3 and 1 <= col <= 3):
                print("Invalid input. Try again.")
                continue
            if board[row-1][col-1] != " ":
                print("That space is already occupied. Try again.")
                continue
            break

        # Update the board
        board[row-1][col-1] = "X" if current_player == player1 else "O"

        # Check for a winner or tie
        winner = check_winner(board)
        if winner is not None:
            print(f"{current_player} wins!")
            print_board(board)
            break
        if all(all(row) for row in board):
            print("Tie!")
            print_board(board)
            break

        # Switch to the other player
        current_player = player2 if current_player == player1 else player1

    # Prompt to play again
    while True:
        play_again = input("Play again? (y/n) ")
        if play_again.lower() not in ["y", "n"]:
            print("Invalid input. Try again.")
            continue
        break
    if play_again.lower() == "y":
        play_game()

# Start the game
play_game()