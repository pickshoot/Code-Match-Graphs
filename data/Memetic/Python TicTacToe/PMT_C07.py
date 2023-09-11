# Tic-Tac-Toe game

# Initialize the board with empty spaces
board = [' '] * 9

# Print the current state of the board
def print_board():
    print('-------------')
    print('| ' + board[0] + ' | ' + board[1] + ' | ' + board[2] + ' |')
    print('-------------')
    print('| ' + board[3] + ' | ' + board[4] + ' | ' + board[5] + ' |')
    print('-------------')
    print('| ' + board[6] + ' | ' + board[7] + ' | ' + board[8] + ' |')
    print('-------------')

# Check if a player has won
def check_win(player):
    if (board[0] == player and board[1] == player and board[2] == player) or \
       (board[3] == player and board[4] == player and board[5] == player) or \
       (board[6] == player and board[7] == player and board[8] == player) or \
       (board[0] == player and board[3] == player and board[6] == player) or \
       (board[1] == player and board[4] == player and board[7] == player) or \
       (board[2] == player and board[5] == player and board[8] == player) or \
       (board[0] == player and board[4] == player and board[8] == player) or \
       (board[2]== player and board[4] == player and board[6] == player):
        return True
    else:
        return False
    
def tic_tac_toe():
    # Print initial board
    print_board()
    # Game loop
    current_player = 'X'
    while True:
        print(f"It's {current_player}'s turn.")
        position = int(input("Enter a position (1-9): "))

        # Check if position is valid
        if position < 1 or position > 9:
            print("Invalid position. Please try again.")
            continue
        if board[position-1] != ' ':
            print("That position is already taken. Please try again.")
            continue

        # Update board and print new state
        board[position-1] = current_player
        print_board()

        # Check if current player has won
        if check_win(current_player):
            print(f"{current_player} wins!")
            break

        # Check for tie
        if all(x != ' ' for x in board):
            print("It's a tie!")
            break

        # Switch to the other player's turn
        current_player = 'O' if current_player == 'X' else 'X'

tic_tac_toe()