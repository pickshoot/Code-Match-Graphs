def draw_board(board):
    """Prints the current state of the board"""
    print('   |   |   ')
    print(' ' + board[0][0] + ' | ' + board[0][1] + ' | ' + board[0][2])
    print('   |   |   ')
    print('-----------')
    print('   |   |   ')
    print(' ' + board[1][0] + ' | ' + board[1][1] + ' | ' + board[1][2])
    print('   |   |   ')
    print('-----------')
    print('   |   |   ')
    print(' ' + board[2][0] + ' | ' + board[2][1] + ' | ' + board[2][2])
    print('   |   |   ')

def get_player_move(player_symbol, board):
    """Gets the move of the player"""
    while True:
        move = input(f"{player_symbol}'s turn. Enter position (row, col) to place your symbol: ")
        row, col = move.split(",")
        row = int(row.strip())
        col = int(col.strip())

        if row < 1 or row > 3 or col < 1 or col > 3:
            print("Invalid move! Please enter a number between 1 and 3.")
        elif board[row - 1][col - 1] != " ":
            print("That space is already taken! Please choose a different position.")
        else:
            break

    return row - 1, col - 1

def check_win(board, player_symbol):
    """Checks if the player has won"""
    # check rows
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == player_symbol:
            return True

    # check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] == player_symbol:
            return True

    # check diagonals
    if board[0][0] == board[1][1] == board[2][2] == player_symbol:
        return True
    elif board[0][2] == board[1][1] == board[2][0] == player_symbol:
        return True

    return False

def play_game():
    """Main function to play the game"""
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    player_symbols = ["X", "O"]
    current_player = 0

    while True:
        draw_board(board)

        row, col = get_player_move(player_symbols[current_player], board)
        board[row][col] = player_symbols[current_player]

        if check_win(board, player_symbols[current_player]):
            draw_board(board)
            print(f"{player_symbols[current_player]} wins!")
            break

        # check for tie
        if all(board[i][j] != " " for i in range(3) for j in range(3)):
            draw_board(board)
            print("It's a tie!")
            break

        current_player = (current_player + 1) % 2

if __name__ == "__main__":
    play_game()