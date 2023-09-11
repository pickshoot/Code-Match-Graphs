import random

def print_board(board):
    """Prints the current board state."""
    print("   |   |   ")
    print(" " + board[0] + " | " + board[1] + " | " + board[2] + " ")
    print("___|___|___")
    print("   |   |   ")
    print(" " + board[3] + " | " + board[4] + " | " + board[5] + " ")
    print("___|___|___")
    print("   |   |   ")
    print(" " + board[6] + " | " + board[7] + " | " + board[8] + " ")
    print("   |   |   ")


def check_win(board):
    """Checks if the game has been won and returns the winning player ('X' or 'O') or None."""
    wins = ((0, 1, 2), (3, 4, 5), (6, 7, 8), (0, 3, 6), (1, 4, 7), (2, 5, 8), (0, 4, 8), (2, 4, 6))
    for win in wins:
        if board[win[0]] == board[win[1]] == board[win[2]] != ' ':
            return board[win[0]]
    return None


def check_tie(board):
    """Checks if the game has ended in a tie."""
    return ' ' not in board


def get_player_move(player, board):
    """Asks the player for their move and returns the updated board state."""
    valid_move = False
    while not valid_move:
        move = input(f"{player}'s move (1-9): ")
        if not move.isdigit() or int(move) not in range(1, 10):
            print("Invalid input. Please enter a number between 1 and 9.")
            continue
        move = int(move) - 1
        if board[move] != ' ':
            print("That square is already taken. Please choose another.")
            continue
        valid_move = True
    board[move] = player
    return board


def play_game():
    """Plays a game of Tic-Tac-Toe."""
    board = [' '] * 9
    players = ['X', 'O']
    current_player = random.choice(players)
    while True:
        print_board(board)
        board = get_player_move(current_player, board)
        winner = check_win(board)
        if winner is not None:
            print_board(board)
            print(f"{winner} wins!")
            break
        elif check_tie(board):
            print_board(board)
            print("It's a tie!")
            break
        current_player = players[(players.index(current_player) + 1) % len(players)]

play_game()
