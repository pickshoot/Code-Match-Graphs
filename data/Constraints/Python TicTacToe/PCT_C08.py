def print_board(board):
    """
    This function prints the Tic-Tac-Toe board
    """
    print("-------------")
    for i in range(3):
        print("|", end="")
        for j in range(3):
            print(" " + board[i][j] + " |", end="")
        print("\n-------------")


def check_win(board, player):
    """
    This function checks if the given player has won the game
    """
    for i in range(3):
        if (board[i][0] == board[i][1] == board[i][2] == player) or \
                (board[0][i] == board[1][i] == board[2][i] == player):
            return True
    if (board[0][0] == board[1][1] == board[2][2] == player) or \
            (board[0][2] == board[1][1] == board[2][0] == player):
        return True
    return False


def play_game():
    """
    This function plays the Tic-Tac-Toe game
    """
    board = [[" " for _ in range(3)] for _ in range(3)]
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)
    player = "X"
    game_over = False
    while not game_over:
        print(f"Player {player}'s turn")
        row = input("Enter row (1-3): ")
        col = input("Enter column (1-3): ")
        try:
            row = int(row) - 1
            col = int(col) - 1
            if board[row][col] != " ":
                print("That spot is already taken. Please try again.")
                continue
            board[row][col] = player
            print_board(board)
            if check_win(board, player):
                print(f"Player {player} wins!")
                game_over = True
            elif all(board[i][j] != " " for i in range(3) for j in range(3)):
                print("Tie game!")
                game_over = True
            else:
                player = "O" if player == "X" else "X"
        except ValueError:
            print("Invalid input. Please enter a number between 1-3.")
        except IndexError:
            print("Invalid input. Please enter a number between 1-3.")


if __name__ == "__main__":
    play_game()