import sys

# Function to print the current board state
def print_board(board):
    print("-------------")
    for row in board:
        print("|", " | ".join(row), "|")
        print("-------------")

# Function to check if the game is over
def game_over(board):
    # Check rows
    for row in board:
        if row.count(row[0]) == len(row) and row[0] != " ":
            return True

    # Check columns
    for col in range(len(board[0])):
        if all(board[row][col] == board[0][col] and board[0][col] != " " for row in range(len(board))):
            return True

    # Check diagonals
    if all(board[i][i] == board[0][0] and board[0][0] != " " for i in range(len(board))) or all(
            board[i][len(board) - i - 1] == board[0][len(board) - 1] and board[0][len(board) - 1] != " " for i in
            range(len(board))):
        return True

    # Check tie
    if all([x != " " for row in board for x in row]):
        print("It's a tie!")
        return True

    return False

# Function to get player move
def get_move(player):
    while True:
        try:
            move = input(f"{player}, enter your move (row, column): ")
            row, col = map(int, move.split(","))
            if board[row - 1][col - 1] != " ":
                print("That space is already occupied. Try again.")
                continue
            return row - 1, col - 1
        except ValueError:
            print("Invalid input. Please enter two integers separated by a comma.")
        except IndexError:
            print("Invalid input. Please enter row and column numbers between 1 and 3.")

# Function to reset the board
def reset_board():
    return [[" " for _ in range(3)] for _ in range(3)]

# Main game loop
while True:
    # Get player names
    player1 = input("Enter player 1 name: ")
    player2 = input("Enter player 2 name: ")

    # Initialize board
    board = reset_board()

    # Initialize current player
    current_player = player1

    # Main game loop
    while True:
        # Print current board state
        print_board(board)

        # Get player move
        row, col = get_move(current_player)
        board[row][col] = "X" if current_player == player1 else "O"

        # Check if game is over
        if game_over(board):
            print_board(board)
            print(f"{current_player} wins!")
            break

        # Switch to other player
        current_player = player2 if current_player == player1 else player1

    # Ask to play again
    play_again = input("Play again? (y/n): ")
    if play_again.lower() != "y":
        sys.exit()