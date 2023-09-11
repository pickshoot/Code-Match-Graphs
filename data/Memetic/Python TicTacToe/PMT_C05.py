import random

board = [" " for _ in range(9)]
player = "X"
computer = "O"

def print_board():
    row1 = "|".join(board[0:3])
    row2 = "|".join(board[3:6])
    row3 = "|".join(board[6:9])
    print(row1)
    print("-" * 5)
    print(row2)
    print("-" * 5)
    print(row3)

def player_move():
    global player
    valid_move = False
    while not valid_move:
        move = input("Enter a position from 1-9: ")
        try:
            move = int(move)
            if move >= 1 and move <= 9:
                if board[move-1] == " ":
                    board[move-1] = player
                    valid_move = True
                else:
                    print("That position is already taken!")
            else:
                print("Invalid position. Enter a number from 1-9.")
        except ValueError:
            print("Invalid input. Enter a number from 1-9.")
    
    print_board()

def computer_move():
    global computer
    valid_move = False
    while not valid_move:
        move = random.randint(1, 9)
        if board[move-1] == " ":
            board[move-1] = computer
            valid_move = True
    
    print_board()

def check_win():
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return True
    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return True
    # Check diagonals
    if board[0] == board[4] == board[8] != " " or board[2] == board[4] == board[6] != " ":
        return True
    return False

def game_loop():
    global player
    while True:
        player_move()
        if check_win():
            print(f"{player} wins!")
            break
        if " " not in board:
            print("It's a tie!")
            break
        computer_move()
        #player, computer = computer, player
        if check_win():
            print(f"{computer} wins!")
            break
        if " " not in board:
            print("It's a tie!")
            break

print("Welcome to Tic-Tac-Toe!")
print_board()
game_loop()