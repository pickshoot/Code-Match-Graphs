def print_board(board):
    print("   0  1  2")
    for i in range(3):
        row = f"{i}  "
        for j in range(3):
            row += f"{board[i][j]}  "
        print(row)

def get_move(player):
    while True:
        try:
            move = input(f"Player {player}, enter your move (row col): ")
            row, col = move.split()
            row = int(row)
            col = int(col)
            if row < 0 or row > 2 or col < 0 or col > 2:
                print("Invalid move. Row and column must be between 0 and 2.")
            else:
                return row, col
        except ValueError:
            print("Invalid input. Please enter two numbers separated by a space.")

def check_win(board):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != " ":
            return True
        if board[0][i] == board[1][i] == board[2][i] != " ":
            return True
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return True
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return True
    return False

def play_again():
    while True:
        answer = input("Do you want to play again? (y/n): ")
        if answer.lower() == "y":
            return True
        elif answer.lower() == "n":
            return False
        else:
            print("Invalid input. Please enter 'y' or 'n'.")

def play_game():
    print("Welcome to Tic-Tac-Toe!")
    board = [[" " for j in range(3)] for i in range(3)]
    player = "X"
    while True:
        print_board(board)
        row, col = get_move(player)
        if board[row][col] != " ":
            print("That square is already occupied.")
        else:
            board[row][col] = player
            if check_win(board):
                print_board(board)
                print(f"Congratulations, player {player} wins!")
                if not play_again():
                    return
                else:
                    board = [[" " for j in range(3)] for i in range(3)]
                    player = "X"
                    continue
            if all(board[i][j] != " " for i in range(3) for j in range(3)):
                print_board(board)
                print("It's a tie!")
                if not play_again():
                    return
                else:
                    board = [[" " for j in range(3)] for i in range(3)]
                    player = "X"
                    continue
            player = "O" if player == "X" else "X"

if __name__ == "__main__":
    play_game()