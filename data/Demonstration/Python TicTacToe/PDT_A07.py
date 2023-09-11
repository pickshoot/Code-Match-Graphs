import random

def print_board(board):
    # Print the current state of the board
    print(f" {board[0][0]} | {board[0][1]} | {board[0][2]} ")
    print("---+---+---")
    print(f" {board[1][0]} | {board[1][1]} | {board[1][2]} ")
    print("---+---+---")
    print(f" {board[2][0]} | {board[2][1]} | {board[2][2]} ")

def get_move(player, board):
    # Prompt the player for their move and return it
    while True:
        row = int(input(f"Player {player}, choose a row (1-3): ")) - 1
        col = int(input(f"Player {player}, choose a column (1-3): ")) - 1
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid move. Please try again.")
        elif board[row][col] != ' ':
            print("That space is already taken. Please try again.")
        else:
            return row, col

def check_win(board):
    # Check if a player has won or if the game is a tie
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return True, board[i][0]
        elif board[0][i] == board[1][i] == board[2][i] != ' ':
            return True, board[0][i]
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return True, board[0][0]
    elif board[0][2] == board[1][1] == board[2][0] != ' ':
        return True, board[0][2]
    elif all([board[i][j] != ' ' for i in range(3) for j in range(3)]):
        return True, 'T'
    else:
        return False, None

def play_game():
    # Set up the board and choose which player goes first
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
    player = random.choice(['X', 'O'])
    print(f"Player {player} goes first.")

    # Loop until the game is over
    while True:
        # Print the current state of the board
        print_board(board)

        # Get the current player's move
        row, col = get_move(player, board)
        board[row][col] = player

        # Check if the game is over
        is_win, winner = check_win(board)
        if is_win:
            print_board(board)
            if winner == 'T':
                print("The game is a tie.")
            else:
                print(f"Player {winner} wins!")
            break

        # Switch to the other player
        if player == 'X':
            player = 'O'
        else:
            player = 'X'

# Play the game
play_game()
