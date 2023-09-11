def print_board(board):
    print("   |   |")
    print(" " + board[0] + " | " + board[1] + " | " + board[2])
    print("___|___|___")
    print("   |   |")
    print(" " + board[3] + " | " + board[4] + " | " + board[5])
    print("___|___|___")
    print("   |   |")
    print(" " + board[6] + " | " + board[7] + " | " + board[8])
    print("   |   |")


def play_game():
    board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    player = "X"
    game_over = False

    while not game_over:
        print_board(board)
        move = input("Enter position (1-9): ")

        if not move.isdigit():
            print("Invalid input. Please enter a number between 1 and 9.")
            continue

        move = int(move) - 1

        if move < 0 or move > 8:
            print("Invalid input. Please enter a number between 1 and 9.")
            continue

        if board[move] != " ":
            print("This position is already taken. Please choose another one.")
            continue

        board[move] = player

        if (board[0] == board[1] == board[2] != " ") or \
                (board[3] == board[4] == board[5] != " ") or \
                (board[6] == board[7] == board[8] != " ") or \
                (board[0] == board[3] == board[6] != " ") or \
                (board[1] == board[4] == board[7] != " ") or \
                (board[2] == board[5] == board[8] != " ") or \
                (board[0] == board[4] == board[8] != " ") or \
                (board[2] == board[4] == board[6] != " "):
            print_board(board)
            print("Player " + player + " wins!")
            game_over = True
        elif " " not in board:
            print_board(board)
            print("Game over! It's a tie!")
            game_over = True
        else:
            player = "O" if player == "X" else "X"


play_game()