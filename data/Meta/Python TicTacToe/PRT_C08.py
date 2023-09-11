def display_board(board):
    """Display the Tic-Tac-Toe board."""
    print(board[0][0] + "|" + board[0][1] + "|" + board[0][2])
    print("-+-+-")
    print(board[1][0] + "|" + board[1][1] + "|" + board[1][2])
    print("-+-+-")
    print(board[2][0] + "|" + board[2][1] + "|" + board[2][2])

def get_move(player):
    """Get the player's move (marker and position) from the command line."""
    marker = input("Player " + player + ", enter your marker (X or O): ")
    row = int(input("Enter the row number (0-2): "))
    col = int(input("Enter the column number (0-2): "))
    return marker, row, col

def is_valid_move(board, row, col):
    """Check if the chosen position is valid (i.e., not already occupied)."""
    if board[row][col] != " ":
        return False
    return True

def update_board(board, marker, row, col):
    """Update the board to show the player's move."""
    board[row][col] = marker
    return board

def check_win(board, marker):
    """Check if the player has won."""
    # Check rows
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == marker:
            return True
    # Check columns
    for j in range(3):
        if board[0][j] == board[1][j] == board[2][j] == marker:
            return True
    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] == marker:
        return True
    if board[0][2] == board[1][1] == board[2][0] == marker:
        return True
    return False

def is_board_full(board):
    """Check if the board is full."""
    for i in range(3):
        for j in range(3):
            if board[i][j] == " ":
                return False
    return True

def play_game():
    """Play a game of Tic-Tac-Toe."""
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    display_board(board)
    player = "X"
    while True:
        marker, row, col = get_move(player)
        if not is_valid_move(board, row, col):
            print("Invalid move. Try again.")
            continue
        board = update_board(board, marker, row, col)
        display_board(board)
        if check_win(board, marker):
            print("Player " + player + " wins!")
            break
        if is_board_full(board):
            print("It's a tie!")
            break
        if player == "X":
            player = "O"
        else:
            player = "X"

if __name__ == "__main__":
    play_game()