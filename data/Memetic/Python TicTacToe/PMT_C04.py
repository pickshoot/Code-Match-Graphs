# Tic-Tac-Toe game application

board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]  # Initialize the game board

def print_board():
    print(" %s | %s | %s " % (board[0], board[1], board[2]))
    print("-----------")
    print(" %s | %s | %s " % (board[3], board[4], board[5]))
    print("-----------")
    print(" %s | %s | %s " % (board[6], board[7], board[8]))

def check_win():
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return board[i]
    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return board[i]
    # Check diagonals
    if board[0] == board[4] == board[8] != " " or board[2] == board[4] == board[6] != " ":
        return board[4]
    # Check for tie
    if " " not in board:
        return "Tie"
    return None

def play_game():
    player = "X"
    while True:
        print_board()
        move = input("It's " + player + "'s turn. Enter a number from 1-9: ")
        try:
            move = int(move) - 1
            if move < 0 or move > 8 or board[move] != " ":
                raise ValueError
        except ValueError:
            print("Invalid move. Try again.")
            continue
        board[move] = player
        winner = check_win()
        if winner is not None:
            print_board()
            if winner == "Tie":
                print("It's a tie!")
            else:
                print(winner + " wins!")
            break
        player = "O" if player == "X" else "X"

if __name__ == "__main__":
    play_game()