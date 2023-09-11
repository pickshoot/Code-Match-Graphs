def print_board(board):
    print("-------------")
    print("| {} | {} | {} |".format(board[0][0], board[0][1], board[0][2]))
    print("-------------")
    print("| {} | {} | {} |".format(board[1][0], board[1][1], board[1][2]))
    print("-------------")
    print("| {} | {} | {} |".format(board[2][0], board[2][1], board[2][2]))
    print("-------------")

def check_winner(board):
    # check rows
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != ' ':
            return True, row[0]
    # check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] != ' ':
            return True, board[0][i]
    # check diagonals
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != ' ':
        return True, board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != ' ':
        return True, board[0][2]
    # no winner yet
    return False, ''

def play_game():
    # initialize board
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
    # get player names
    player1 = input("Enter name for Player 1 (X): ")
    player2 = input("Enter name for Player 2 (O): ")
    current_player = player1
    while True:
        # print current board state
        print_board(board)
        # get input from current player
        row = int(input("{}, enter row (1-3): ".format(current_player))) - 1
        col = int(input("{}, enter column (1-3): ".format(current_player))) - 1
        # check if space is already occupied
        if board[row][col] != ' ':
            print("That space is already occupied. Try again.")
            continue
        # update board with player's move
        board[row][col] = 'X' if current_player == player1 else 'O'
        # check for winner or tie
        winner, winner_mark = check_winner(board)
        if winner:
            print_board(board)
            print("{} wins!".format(player1 if winner_mark == 'X' else player2))
            break
        elif all([space != ' ' for row in board for space in row]):
            print_board(board)
            print("Tie game.")
            break
        # switch to other player
        current_player = player2 if current_player == player1 else player1
    # ask if players want to play again
    play_again = input("Do you want to play again? (y/n): ")
    if play_again.lower() == 'y':
        play_game()
    else:
        print("Thanks for playing Tic-Tac-Toe!")

play_game()