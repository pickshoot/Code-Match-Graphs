def print_board(board):
    for row in board:
        print("|".join(row))

def is_valid_move(move, board):
    row, col = move
    return 0 <= row < 3 and 0 <= col < 3 and board[row][col] == " "

def get_move(player, board):
    while True:
        try:
            row = int(input(f"{player}'s turn. Enter row (0-2): "))
            col = int(input(f"{player}'s turn. Enter column (0-2): "))
            move = (row, col)
            if is_valid_move(move, board):
                return move
            else:
                print("Invalid move. Please try again.")
        except ValueError:
            print("Invalid input. Please enter a number.")

def has_won(player, board):
    for i in range(3):
        if board[i] == [player]*3:
            return True
        if [board[j][i] for j in range(3)] == [player]*3:
            return True
    if [board[i][i] for i in range(3)] == [player]*3:
        return True
    if [board[i][2-i] for i in range(3)] == [player]*3:
        return True
    return False

def play_again():
    while True:
        try:
            answer = input("Do you want to play again? (y/n): ")
            if answer.lower() == "y":
                return True
            elif answer.lower() == "n":
                return False
            else:
                print("Invalid input. Please enter y or n.")
        except ValueError:
            print("Invalid input. Please enter y or n.")

def play_game():
    board = [[" ", " ", " "] for _ in range(3)]
    players = ["X", "O"]
    current_player = 0
    while True:
        print_board(board)
        move = get_move(players[current_player], board)
        row, col = move
        board[row][col] = players[current_player]
        if has_won(players[current_player], board):
            print_board(board)
            print(f"{players[current_player]} has won!")
            if not play_again():
                break
            else:
                board = [[" ", " ", " "] for _ in range(3)]
        elif all([cell != " " for row in board for cell in row]):
            print_board(board)
            print("It's a tie!")
            if not play_again():
                break
            else:
                board = [[" ", " ", " "] for _ in range(3)]
        else:
            current_player = (current_player + 1) % 2

if __name__ == "__main__":
    play_game()