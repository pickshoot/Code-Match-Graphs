import itertools

# Define the game board
board = [[None, None, None],
         [None, None, None],
         [None, None, None]]

# Define the players
players = itertools.cycle(['X', 'O'])

# Define a function to print the current board
def print_board(board):
    print("-------------")
    for row in board:
        print("|", " | ".join([str(cell) if cell is not None else " " for cell in row]), "|")
        print("-------------")

# Define a function to check if a player has won
def check_win(board):
    # Check rows
    for row in board:
        if len(set(row)) == 1 and row[0] is not None:
            return row[0]
    # Check columns
    for col in range(3):
        if len(set([board[row][col] for row in range(3)])) == 1 and board[0][col] is not None:
            return board[0][col]
    # Check diagonals
    if len(set([board[i][i] for i in range(3)])) == 1 and board[0][0] is not None:
        return board[0][0]
    if len(set([board[i][2-i] for i in range(3)])) == 1 and board[0][2] is not None:
        return board[0][2]
    # No winner
    return None

# Play the game
for i in range(9):
    player = next(players)
    print_board(board)
    print("It's", player, "'s turn.")
    row = int(input("Enter row (0-2): "))
    col = int(input("Enter column (0-2): "))
    if board[row][col] is not None:
        print("That space is already taken. Try again.")
        continue
    board[row][col] = player
    winner = check_win(board)
    if winner is not None:
        print_board(board)
        print("Congratulations,", winner, "has won the game!")
        break
else:
    print_board(board)
    print("The game is a tie.")
