#Create the board as a nested list with empty strings as placeholders

board = [['', '', ''], ['', '', ''], ['', '', '']]
#Define a function to print the board

def print_board(board):
    for row in board:
        print('|'.join(row))
#Define a function to check if there's a winner

def check_winner(board):
    # Check rows
    for row in board:
        if all(mark == 'X' for mark in row) or all(mark == 'O' for mark in row):
            return True
        # Check columns
        for i in range(3):
            if board[0][i] == board[1][i] == board[2][i] != '':
                return True

        # Check diagonals
        if board[0][0] == board[1][1] == board[2][2] != '' or board[0][2] == board[1][1] == board[2][0] != '':
            return True

    return False
#Define a variable to keep track of the current player

current_player = 'X'
#Define a variable to keep track of the number of turns played

turns_played = 0
#Start the game loop

while turns_played < 9:
    # Print the board
    print_board(board)
    # Get the current player's move
    while True:
        try:
            move = input(f"{current_player}'s turn. Enter the row and column to place your mark (e.g. '1,2'): ")
            row, col = map(int, move.split(','))
            if board[row-1][col-1] != '':
                print("That spot is already taken. Please choose another.")
                continue
            break
        except:
            print("Invalid input. Please enter the row and column as two numbers separated by a comma (e.g. '1,2').")

    # Place the current player's mark on the board
    board[row-1][col-1] = current_player

    # Check if there's a winner
    if check_winner(board):
        print_board(board)
        print(f"{current_player} wins!")
        break

    # Switch to the other player
    current_player = 'O' if current_player == 'X' else 'X'
    turns_played += 1
    #If no winner is found after 9 turns, it's a tie

    if turns_played == 9:
        print_board(board)
        print("It's a tie!")
