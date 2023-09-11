import random

# create the Tic-Tac-Toe board
board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]

# print the Tic-Tac-Toe board
def print_board():
    print("   |   |   ")
    print(" "+board[0]+" | "+board[1]+" | "+board[2]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[3]+" | "+board[4]+" | "+board[5]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[6]+" | "+board[7]+" | "+board[8]+" ")
    print("   |   |   ")

# check if there is a winner
def check_win(player):
    if board[0] == player and board[1] == player and board[2] == player:
        return True
    elif board[3] == player and board[4] == player and board[5] == player:
        return True
    elif board[6] == player and board[7] == player and board[8] == player:
        return True
    elif board[0] == player and board[3] == player and board[6] == player:
        return True
    elif board[1] == player and board[4] == player and board[7] == player:
        return True
    elif board[2] == player and board[5] == player and board[8] == player:
        return True
    elif board[0] == player and board[4] == player and board[8] == player:
        return True
    elif board[2] == player and board[4] == player and board[6] == player:
        return True
    else:
        return False

# play the game
def play():
    # print the board
    print_board()
    # loop until there is a winner or the board is full
    while True:
        # get the user's move
        move = int(input("Enter your move (1-9): "))
        # check if the move is valid
        if board[move-1] == " ":
            # make the move
            board[move-1] = "X"
            # check if there is a winner
            if check_win("X"):
                print_board()
                print("You win!")
                break
            # check if the board is full
            elif " " not in board:
                print_board()
                print("It's a tie!")
                break
            # get the computer's move
            while True:
                move = random.randint(1,9)
                if board[move-1] == " ":
                    # make the move
                    board[move-1] = "O"
                    # check if there is a winner
                    if check_win("O"):
                        print_board()
                        print("Computer wins!")
                        break
                    # check if the board is full
                    elif " " not in board:
                        print_board()
                        print("It's a tie!")
                        break
                    # print the board
                    print_board()
                    break
        else:
            print("That move is not valid. Try again.")

play()