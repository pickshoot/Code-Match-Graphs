# Tic-Tac-Toe game in Python

def print_board(board):
    print("-------------")
    print("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")
    print("-------------")
    print("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")
    print("-------------")
    print("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")
    print("-------------")

def check_win(board):
    # Check rows
    if board[0] == board[1] == board[2] != " ":
        return True
    if board[3] == board[4] == board[5] != " ":
        return True
    if board[6] == board[7] == board[8] != " ":
        return True

    # Check columns
    if board[0] == board[3] == board[6] != " ":
        return True
    if board[1] == board[4] == board[7] != " ":
        return True
    if board[2] == board[5] == board[8] != " ":
        return True

    # Check diagonals
    if board[0] == board[4] == board[8] != " ":
        return True
    if board[2] == board[4] == board[6] != " ":
        return True

    return False

def main():
    board = [" "]*9
    player = "X"

    while True:
        print_board(board)
        move = input("Player " + player + ", enter a position (1-9): ")
        move = int(move) - 1

        if board[move] == " ":
            board[move] = player
        else:
            print("That position is already taken. Please try again.")
            continue

        if check_win(board):
            print_board(board)
            print("Congratulations! Player " + player + " wins!")
            break

        if " " not in board:
            print_board(board)
            print("It's a tie!")
            break

        player = "O" if player == "X" else "X"

if __name__ == "__main__":
    main()