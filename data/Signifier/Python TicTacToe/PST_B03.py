import random

# Function to print the Tic Tac Toe board
def print_board(board):
    print("-------------")
    print("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")
    print("-------------")
    print("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")
    print("-------------")
    print("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")
    print("-------------")

# Function to check if any player has won
def check_win(board, player):
    if (board[0] == player and board[1] == player and board[2] == player) or \
        (board[3] == player and board[4] == player and board[5] == player) or \
        (board[6] == player and board[7] == player and board[8] == player) or \
        (board[0] == player and board[3] == player and board[6] == player) or \
        (board[1] == player and board[4] == player and board[7] == player) or \
        (board[2] == player and board[5] == player and board[8] == player) or \
        (board[0] == player and board[4] == player and board[8] == player) or \
        (board[2] == player and board[4] == player and board[6] == player):
        return True
    else:
        return False

# Function to play a move
def play_move(board, player, position):
    board[position] = player

# Function to get the list of valid moves
def get_valid_moves(board):
    valid_moves = []
    for i in range(9):
        if board[i] == " ":
            valid_moves.append(i)
    return valid_moves

# Function for the computer's move
def computer_move(board, computer_player, human_player):
    valid_moves = get_valid_moves(board)

    for move in valid_moves:
        board_copy = board.copy()
        play_move(board_copy, computer_player, move)
        if check_win(board_copy, computer_player):
            return move

    for move in valid_moves:
        board_copy = board.copy()
        play_move(board_copy, human_player, move)
        if check_win(board_copy, human_player):
            return move

    corners = [0, 2, 6, 8]
    available_corners = []
    for corner in corners:
        if corner in valid_moves:
            available_corners.append(corner)
    if len(available_corners) > 0:
        return random.choice(available_corners)

    if 4 in valid_moves:
        return 4

    edges = [1, 3, 5, 7]
    available_edges = []
    for edge in edges:
        if edge in valid_moves:
            available_edges.append(edge)
    if len(available_edges) > 0:
        return random.choice(available_edges)

# Function to play the game
def play_game():
    board = [" "] * 9
    computer_player = "O"
    human_player = "X"

    print("Welcome to Tic Tac Toe!")
    print_board(board)

    while True:
        # Human's move
        human_move = int(input("Enter a number between 0 and 8: "))
        if board[human_move] != " ":
            print("Invalid move. Please try again.")
            continue

        play_move(board, human_player, human_move)
        print_board(board)

        if check_win(board, human_player):
            print("Congratulations! You have won!")
            break

        if len(get_valid_moves(board)) == 0:
            print("It's a tie!")
            break

        # Computer's move
        print("Computer's turn...")
        computer_move_index = computer_move(board, computer_player, human_player)
        play_move(board, computer_player, computer_move_index)
        print_board(board)

        if check_win(board, computer_player):
            print("Sorry, the computer has won.")
            break

        if len(get_valid_moves(board)) == 0:
            print("It's a tie!")
            break

play_game()