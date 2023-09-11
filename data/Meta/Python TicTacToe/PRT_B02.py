def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", end=" ")
        for j in range(3):
            print(board[i][j], "|", end=" ")
        print()
        print("-------------")

def get_move(player):
    valid_move = False
    while not valid_move:
        move = input(f"{player}, enter your move (row,column): ")
        try:
            row, col = map(int, move.split(","))
            if row < 1 or row > 3 or col < 1 or col > 3:
                print("Invalid move. Row and column must be between 1 and 3.")
            elif board[row-1][col-1] != " ":
                print("That space is already occupied. Choose another one.")
            else:
                valid_move = True
        except ValueError:
            print("Invalid move. Please enter row and column as integers separated by a comma.")
    return row-1, col-1

def check_win(board):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != " ":
            return True, board[i][0]
        if board[0][i] == board[1][i] == board[2][i] != " ":
            return True, board[0][i]
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return True, board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return True, board[0][2]
    if all([board[i][j] != " " for i in range(3) for j in range(3)]):
        return True, "tie"
    return False, ""

play_again = True
while play_again:
    board = [[" ", " ", " "] for _ in range(3)]
    player1 = input("Enter Player 1 name: ")
    player2 = input("Enter Player 2 name: ")
    current_player = player1
    print_board(board)
    while True:
        row, col = get_move(current_player)
        board[row][col] = "X" if current_player == player1 else "O"
        print_board(board)
        game_over, winner = check_win(board)
        if game_over:
            if winner == "tie":
                print("It's a tie!")
            else:
                print(f"{winner} wins!")
            break
        current_player = player2 if current_player == player1 else player1
    response = input("Do you want to play again? (y/n) ")
    play_again = response.lower() == "y"