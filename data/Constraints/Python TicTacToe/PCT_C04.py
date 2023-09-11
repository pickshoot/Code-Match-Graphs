def display_board(board):
    """
    Display the Tic-Tac-Toe board.
    """
    print(f" {board[7]} | {board[8]} | {board[9]} ")
    print("-----------")
    print(f" {board[4]} | {board[5]} | {board[6]} ")
    print("-----------")
    print(f" {board[1]} | {board[2]} | {board[3]} ")

def player_input():
    """
    Take player input for marker choice (X or O).
    """
    marker = ""
    while marker != "X" and marker != "O":
        marker = input("Player 1: Do you want to be X or O? ").upper()
    if marker == "X":
        return ("X", "O")
    else:
        return ("O", "X")

def place_marker(board, marker, position):
    """
    Place the marker (X or O) on the board at the given position.
    """
    board[position] = marker

def win_check(board, marker):
    """
    Check if the given marker has won the game.
    """
    return ((board[7] == board[8] == board[9] == marker) or
            (board[4] == board[5] == board[6] == marker) or
            (board[1] == board[2] == board[3] == marker) or
            (board[7] == board[4] == board[1] == marker) or
            (board[8] == board[5] == board[2] == marker) or
            (board[9] == board[6] == board[3] == marker) or
            (board[7] == board[5] == board[3] == marker) or
            (board[9] == board[5] == board[1] == marker))

def choose_first():
    """
    Choose which player goes first.
    """
    import random
    if random.randint(0, 1) == 0:
        return "Player 2"
    else:
        return "Player 1"

def space_check(board, position):
    """
    Check if the given position on the board is empty.
    """
    return board[position] == " "

def full_board_check(board):
    """
    Check if the board is full.
    """
    for i in range(1, 10):
        if space_check(board, i):
            return False
    return True

def player_choice(board):
    """
    Take player input for the next position to place a marker on.
    """
    position = 0
    while position not in range(1, 10) or not space_check(board, position):
        try:
            position = int(input("Choose your next position: (1-9) "))
        except ValueError:
            print("Invalid input! Please enter a number between 1 and 9.")
    return position

def replay():
    """
    Ask if the players want to play again.
    """
    return input("Do you want to play again? Enter Yes or No: ").lower().startswith("y")

# The main function to run the game
def play_game():
    print("Welcome to Tic Tac Toe!")
    while True:
        # Reset the board
        the_board = [" "] * 10
        player1_marker, player2_marker = player_input()
        turn = choose_first()
        print(turn + " will go first.")
        play_game = input("Are you ready to play? Enter Yes or No: ")
        if play_game.lower()[0] == "y":
            game_on = True
        else:
            game_on = False

        while game_on:
            # Player 1's turn
            if turn == "Player 1":
                display_board(the_board)
                position = player_choice(the_board)
                place_marker(the_board, player1_marker, position)

                if win_check(the_board, player1_marker):
                    display_board(the_board)
                    print("Congratulations! Player 1 has won the game!")
                    game_on = False
                else:
                    if full_board_check(the_board):
                        display_board(the_board)
                        print("The game is a tie!")
                        break
                    else:
                        turn = "Player 2"

            # Player 2's turn
            else:
                display_board(the_board)
                position = player_choice(the_board)
                place_marker(the_board, player2_marker, position)

                if win_check(the_board, player2_marker):
                    display_board(the_board)
                    print("Congratulations! Player 2 has won the game!")
                    game_on = False
                else:
                    if full_board_check(the_board):
                        display_board(the_board)
                        print("The game is a tie!")
                        break
                    else:
                        turn = "Player 1"

        if not replay():
            break

play_game()