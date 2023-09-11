def print_board(board):
    print(" ")
    print("   |   |   ")
    print(" "+board[0]+" | "+board[1]+" | "+board[2]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[3]+" | "+board[4]+" | "+board[5]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[6]+" | "+board[7]+" | "+board[8]+" ")
    print("   |   |   ")
    print(" ")

def check_win(board):
    for i in range(0, 9, 3):
        if board[i] == board[i+1] and board[i] == board[i+2]:
            return True
    for i in range(0, 3):
        if board[i] == board[i+3] and board[i] == board[i+6]:
            return True
    if board[0] == board[4] and board[0] == board[8]:
        return True
    if board[2] == board[4] and board[2] == board[6]:
        return True
    return False

def tic_tac_toe():
    board = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
    player = "X"
    while True:
        print_board(board)
        move = input("Player "+player+", enter your move: ")
        if move.isdigit() and int(move) >= 1 and int(move) <= 9:
            if board[int(move)-1] == "X" or board[int(move)-1] == "O":
                print("That spot is already taken!")
            else:
                board[int(move)-1] = player
                if check_win(board):
                    print_board(board)
                    print("Player "+player+" wins!")
                    break
                if player == "X":
                    player = "O"
                else:
                    player = "X"
        else:
            print("Invalid input. Please enter a number between 1 and 9.")

tic_tac_toe()