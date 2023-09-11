def print_board(board):
    print("   |   |")
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("___|___|___")
    print("   |   |")
    print(f" {board[6]} | {board[7]} | {board[8]} ")
    print("   |   |")

def check_win(board):
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] and board[i] != "_":
            return True
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] and board[i] != "_":
            return True
    if board[0] == board[4] == board[8] and board[0] != "_":
        return True
    if board[2] == board[4] == board[6] and board[2] != "_":
        return True
    return False

def tic_tac_toe():
    board = ["_", "_", "_", "_", "_", "_", "_", "_", "_"]
    player = "X"

    while True:
        print_board(board)
        print(f"Player {player}'s turn.")
        move = input("Choose a position (1-9): ")

        if not move.isdigit() or int(move) < 1 or int(move) > 9:
            print("Invalid input. Please enter a number between 1 and 9.")
            continue

        index = int(move) - 1

        if board[index] != "_":
            print("That position is already taken. Please choose another one.")
            continue

        board[index] = player

        if check_win(board):
            print_board(board)
            print(f"Congratulations! Player {player} wins!")
            break

        if "_" not in board:
            print_board(board)
            print("It's a tie!")
            break

        player = "O" if player == "X" else "X"

if __name__ == "__main__":
    tic_tac_toe()
