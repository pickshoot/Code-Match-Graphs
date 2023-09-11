def print_board(board):
    """Print the Tic-Tac-Toe board."""
    print(f"{board[0]}|{board[1]}|{board[2]}")
    print("-|-|-")
    print(f"{board[3]}|{board[4]}|{board[5]}")
    print("-|-|-")
    print(f"{board[6]}|{board[7]}|{board[8]}")

def get_move(player, board):
    """Get a valid move from the player."""
    while True:
        move = input(f"Player {player}, enter a number (1-9) to place your mark: ")
        if not move.isdigit():
            print("Invalid input. Please enter a number.")
            continue
        move = int(move) - 1
        if move < 0 or move > 8:
            print("Invalid move. Please enter a number between 1 and 9.")
            continue
        if board[move] != " ":
            print("That space is already taken. Please choose another.")
            continue
        return move

def check_win(board):
    """Check if a player has won the game."""
    for i in range(3):
        # Check rows
        if board[i*3] == board[i*3+1] == board[i*3+2] != " ":
            return board[i*3]
        # Check columns
        if board[i] == board[i+3] == board[i+6] != " ":
            return board[i]
    # Check diagonals
    if board[0] == board[4] == board[8] != " ":
        return board[0]
    if board[2] == board[4] == board[6] != " ":
        return board[2]
    # Check for tie
    if " " not in board:
        return "tie"
    # No winner yet
    return None

def play_game():
    """Play a game of Tic-Tac-Toe."""
    board = [" "] * 9
    players = ["X", "O"]
    current_player = 0
    while True:
        print_board(board)
        move = get_move(players[current_player], board)
        board[move] = players[current_player]
        winner = check_win(board)
        if winner is not None:
            print_board(board)
            if winner == "tie":
                print("It's a tie!")
            else:
                print(f"Player {winner} wins!")
            return
        current_player = (current_player + 1) % 2

play_game()