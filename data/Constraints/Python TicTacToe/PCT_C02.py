import os

# Function to clear the screen
def clear():
    os.system('cls' if os.name == 'nt' else 'clear')

# Function to draw the board
def draw_board(board):
    clear()
    print("  1   2   3")
    print("1 {} | {} | {}".format(board[0][0], board[0][1], board[0][2]))
    print(" ---+---+---")
    print("2 {} | {} | {}".format(board[1][0], board[1][1], board[1][2]))
    print(" ---+---+---")
    print("3 {} | {} | {}".format(board[2][0], board[2][1], board[2][2]))

# Function to check if there is a winner
def check_win(board, mark):
    for i in range(3):
        if (board[i][0] == board[i][1] == board[i][2] == mark) or \
           (board[0][i] == board[1][i] == board[2][i] == mark):
            return True
    if (board[0][0] == board[1][1] == board[2][2] == mark) or \
       (board[0][2] == board[1][1] == board[2][0] == mark):
        return True
    return False

# Function to check if the board is full
def check_full(board):
    for row in board:
        for cell in row:
            if cell == " ":
                return False
    return True

# Function to get the next player's mark
def get_next_mark(mark):
    if mark == "X":
        return "O"
    else:
        return "X"

# Function to get the user's move
def get_move():
    while True:
        try:
            move = input("Enter your move (row column): ")
            row, col = move.split()
            row = int(row) - 1
            col = int(col) - 1
            if row < 0 or row > 2 or col < 0 or col > 2:
                raise ValueError()
            return (row, col)
        except ValueError:
            print("Invalid input. Please enter row and column numbers (1-3).")

# Function to play the game
def play_game():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    mark = "X"
    while True:
        draw_board(board)
        move = get_move()
        if board[move[0]][move[1]] == " ":
            board[move[0]][move[1]] = mark
            if check_win(board, mark):
                draw_board(board)
                print("{} wins!".format(mark))
                break
            elif check_full(board):
                draw_board(board)
                print("It's a tie!")
                break
            mark = get_next_mark(mark)
        else:
            print("That cell is already occupied. Try again.")

# Run the game
play_game()