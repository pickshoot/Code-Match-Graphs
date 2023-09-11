def print_board(board):
    """Prints the Tic-Tac-Toe board."""
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("---+---+---")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("---+---+---")
    print(f" {board[6]} | {board[7]} | {board[8]} ")

def get_move(board, player):
    """Asks the player for a move and returns it."""
    while True:
        move = input(f"Player {player}, enter your move (1-9): ")
        if not move.isdigit():
            print("Invalid input. Please enter a number.")
            continue
        move = int(move) - 1
        if move < 0 or move > 8:
            print("Invalid move. Please enter a number between 1 and 9.")
            continue
        if board[move] != " ":
            print("That space is already occupied. Please choose another.")
            continue
        return move

def check_win(board):
    """Checks if someone has won the game."""
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
        return "Tie"
    # No winner yet
    return None

def play_game():
    """Plays a game of Tic-Tac-Toe."""
    board = [" "] * 9
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)
    current_player = "X"
    while True:
        move = get_move(board, current_player)
        board[move] = current_player
        print_board(board)
        winner = check_win(board)
        if winner is not None:
            if winner == "Tie":
                print("The game is a tie.")
            else:
                print(f"Player {winner} wins!")
            return
        current_player = "O" if current_player == "X" else "X"

# Play the game
play_game()
