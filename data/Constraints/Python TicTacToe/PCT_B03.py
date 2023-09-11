def print_board(board):
    """Prints the current state of the board."""
    print(f"| {board[0][0]} | {board[0][1]} | {board[0][2]} |")
    print(f"| {board[1][0]} | {board[1][1]} | {board[1][2]} |")
    print(f"| {board[2][0]} | {board[2][1]} | {board[2][2]} |")

def get_move(player, board):
    """Asks the player for their move and returns it."""
    while True:
        try:
            row = int(input(f"Player {player}, enter row (1-3): ")) - 1
            col = int(input(f"Player {player}, enter column (1-3): ")) - 1
            if board[row][col] == ' ':
                return row, col
            else:
                print("That space is already occupied.")
        except (ValueError, IndexError):
            print("Invalid input.")

def check_win(board):
    """Checks if either player has won the game."""
    for i in range(3):
        # Check rows
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return board[i][0]
        # Check columns
        if board[0][i] == board[1][i] == board[2][i] != ' ':
            return board[0][i]
    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]
    # Check for tie
    for row in board:
        if ' ' in row:
            return None
    return 'tie'

def play_game():
    """Plays a game of Tic-Tac-Toe."""
    board = [[' ' for _ in range(3)] for _ in range(3)]
    player = 'X'
    while True:
        print_board(board)
        if winner := check_win(board):
            if winner == 'tie':
                print("It's a tie!")
            else:
                print(f"Player {winner} wins!")
            break
        row, col = get_move(player, board)
        board[row][col] = player
        player = 'O' if player == 'X' else 'X'

if __name__ == '__main__':
    play_game()