import random

def draw_board(board):
    """
    Draw the Tic-Tac-Toe board.
    """
    print(f" {board[0][0]} | {board[0][1]} | {board[0][2]} ")
    print("---+---+---")
    print(f" {board[1][0]} | {board[1][1]} | {board[1][2]} ")
    print("---+---+---")
    print(f" {board[2][0]} | {board[2][1]} | {board[2][2]} ")

def get_player_move(board):
    """
    Get the player's move and update the board accordingly.
    """
    while True:
        try:
            row = int(input("Enter the row number (1-3): ")) - 1
            col = int(input("Enter the column number (1-3): ")) - 1
            if board[row][col] != " ":
                print("That spot is already taken. Try again.")
            else:
                board[row][col] = "X"
                break
        except (ValueError, IndexError):
            print("Invalid input. Try again.")
    return board

def get_computer_move(board):
    """
    Get the computer's move and update the board accordingly.
    """
    while True:
        row = random.randint(0, 2)
        col = random.randint(0, 2)
        if board[row][col] == " ":
            board[row][col] = "O"
            break
    return board

def check_winner(board):
    """
    Check if there is a winner in the current board configuration.
    """
    # Check rows
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != " ":
            return row[0]

    # Check columns
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] != " ":
            return board[0][col]

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != " ":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != " ":
        return board[0][2]

    # Check for tie
    if " " not in board[0] and " " not in board[1] and " " not in board[2]:
        return "tie"

    return None

def play_game():
    """
    Play a game of Tic-Tac-Toe.
    """
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    draw_board(board)
    while True:
        # Get player move
        board = get_player_move(board)
        draw_board(board)
        winner = check_winner(board)
        if winner:
            break

        # Get computer move
        board = get_computer_move(board)
        print("??????????????")
        draw_board(board)
        winner = check_winner(board)
        if winner:
            break

    if winner == "tie":
        print("It's a tie!")
    else:
        print(f"{winner} wins!")

# Main program
print("Welcome to Tic-Tac-Toe!")
while True:
    choice = input("Press 'p' to play, or Press 'q' to quit: ")
    if choice == "p":
        play_game()
    elif choice == "q":
        print("Thanks for playing!")
        break
    else:
        print("Invalid choice. Try again.")
