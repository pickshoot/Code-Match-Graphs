def print_board(board):
    print("   |   |   ")
    print(" "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]+" ")
    print("___|___|___")
    print("   |   |   ")
    print(" "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]+" ")
    print("   |   |   ")

def check_win(board, player):
    for i in range(3):
        if (board[i][0] == board[i][1] == board[i][2] == player) or \
           (board[0][i] == board[1][i] == board[2][i] == player) or \
           (board[0][0] == board[1][1] == board[2][2] == player) or \
           (board[0][2] == board[1][1] == board[2][0] == player):
            return True
    return False

def play():
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
    players = ['X', 'O']
    names = [input("Enter player 1's name: "), input("Enter player 2's name: ")]
    current_player = 0
    while True:
        print_board(board)
        row = int(input(names[current_player] + ", enter row number (1-3): ")) - 1
        col = int(input(names[current_player] + ", enter column number (1-3): ")) - 1
        if board[row][col] != ' ':
            print("That space is already occupied, please choose another one.")
            continue
        board[row][col] = players[current_player]
        if check_win(board, players[current_player]):
            print_board(board)
            print(names[current_player] + " wins!")
            break
        elif all(' ' not in row for row in board):
            print_board(board)
            print("Tie!")
            break
        current_player = (current_player + 1) % 2
    play_again = input("Do you want to play again? (y/n) ")
    if play_again.lower() == 'y':
        play()

play()