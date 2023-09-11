import random

def display_board(board):
    """
    Display the current state of the board
    """
    print("\n")
    print(board[1] + "|" + board[2] + "|" + board[3])
    print("-+-+-")
    print(board[4] + "|" + board[5] + "|" + board[6])
    print("-+-+-")
    print(board[7] + "|" + board[8] + "|" + board[9])
    print("\n")

def player_input():
    """
    Prompt the user to choose their marker (X or O)
    """
    marker = ""
    while marker != "X" and marker != "O":
        marker = input("Player 1, choose X or O: ").upper()
    if marker == "X":
        return ("X", "O")
    else:
        return ("O", "X")

def place_marker(board, marker, position):
    """
    Place the marker on the board at the given position
    """
    board[position] = marker

def win_check(board, mark):
    """
    Check if the given marker has won
    """
    return ((board[1] == mark and board[2] == mark and board[3] == mark) or
            (board[4] == mark and board[5] == mark and board[6] == mark) or
            (board[7] == mark and board[8] == mark and board[9] == mark) or
            (board[1] == mark and board[4] == mark and board[7] == mark) or
            (board[2] == mark and board[5] == mark and board[8] == mark) or
            (board[3] == mark and board[6] == mark and board[9] == mark) or
            (board[1] == mark and board[5] == mark and board[9] == mark) or
            (board[3] == mark and board[5] == mark and board[7] == mark))

def choose_first():
    """
    Randomly choose which player goes first
    """
    if random.randint(0, 1) == 0:
        return "Player 2"
    else:
        return "Player 1"

def space_check(board, position):
    """
    Check if the given position on the board is free
    """
    return board[position] == " "

def full_board_check(board):
    """
    Check if the board is full
    """
    for i in range(1, 10):
        if space_check(board, i):
            return False
    return True

def player_choice(board):
    """
    Prompt the player to choose a position on the board
    """
    position = 0
    while position not in range(1, 10) or not space_check(board, position):
        position = int(input("Choose a position (1-9): "))
    return position

def replay():
    """
    Prompt the user to play again
    """
    return input("Do you want to play again? Enter Yes or No: ").lower().startswith("y")

# Game logic
print("Welcome to Tic Tac Toe!")

while True:
    # Set up the board
    the_board = [" "] * 10
    player1_marker, player2_marker = player_input()
    turn = choose_first()
    print(turn + " will go first.")

    play_game = input("Are you ready to play? Enter Yes or No.")

    if play_game.lower()[0] == "y":
        game_on = True

    else:
        game_on = False

    while game_on:
        if turn == "Player 1":
            # Player 1's turn
            display_board(the_board)
            position = player_choice(the_board)
            place_marker(the_board, player1_marker, position)

            if win_check(the_board, player1_marker):
                display_board(the_board)
                print("Congratulations! You have won the game!")
                game_on = False
            else:
                if full_board_check(the_board):
                    display_board(the_board)
                    print("The game is a draw!")
                    break
                else:
                    turn = "Player 2"

        else:
            # Player 2's turn
            display_board(the_board)
            position = player_choice(the_board)
            place_marker(the_board, player2_marker, position)

            if win_check(the_board, player2_marker):
                display_board(the_board)
                print("Congratulations! You have won the game!")
                game_on = False
            else:
                if full_board_check(the_board):
                    display_board(the_board)
                    print("The game is a draw!")
                    break
                else:
                    turn = "Player 1"

    if not replay():
        break