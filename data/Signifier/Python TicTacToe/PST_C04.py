def print_board(board):
    """
    This function prints the current state of the board.
    """
    for row in board:
        print("|".join(row))

def check_win(board, player):
    """
    This function checks if a player has won the game.
    """
    for i in range(3):
        # check rows
        if board[i][0] == board[i][1] == board[i][2] == player:
            return True
        # check columns
        elif board[0][i] == board[1][i] == board[2][i] == player:
            return True
    # check diagonals
    if board[0][0] == board[1][1] == board[2][2] == player:
        return True
    elif board[0][2] == board[1][1] == board[2][0] == player:
        return True
    else:
        return False

def tic_tac_toe():
    """
    This function runs the Tic-Tac-Toe game.
    """
    # initialize the board
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    # initialize the players
    players = ["X", "O"]
    current_player = players[0]
    # start the game loop
    while True:
        # print the current state of the board
        print_board(board)
        # ask the current player to make a move
        row = int(input("Enter row number (0-2): "))
        col = int(input("Enter column number (0-2): "))
        # check if the move is valid
        if board[row][col] == " ":
            board[row][col] = current_player
        else:
            print("Invalid move. Try again.")
            continue
        # check if the current player has won the game
        if check_win(board, current_player):
            print_board(board)
            print(f"{current_player} wins!")
            break
        # check if the game is a tie
        if all(board[i][j] != " " for i in range(3) for j in range(3)):
            print_board(board)
            print("Tie game!")
            break
        # switch to the other player
        current_player = players[(players.index(current_player) + 1) % 2]

# start the game
tic_tac_toe()
