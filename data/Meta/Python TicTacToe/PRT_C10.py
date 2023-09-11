def print_board(board):
    print("  0 1 2")
    for i in range(3):
        row = str(i) + " "
        for j in range(3):
            row += board[i][j] + " "
        print(row)

def check_win(board, player):
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] == player:
            return True
        if board[0][i] == board[1][i] == board[2][i] == player:
            return True
    if board[0][0] == board[1][1] == board[2][2] == player:
        return True
    if board[0][2] == board[1][1] == board[2][0] == player:
        return True
    return False

def play_game():
    board = [[" " for j in range(3)] for i in range(3)]
    players = ["X", "O"]
    turn = 0
    while True:
        print_board(board)
        player = players[turn]
        print(f"{player}'s turn")
        row = int(input("Enter row: "))
        col = int(input("Enter col: "))
        if board[row][col] != " ":
            print("That position is already occupied. Try again.")
            continue
        board[row][col] = player
        if check_win(board, player):
            print(f"{player} wins!")
            break
        if all([board[i][j] != " " for i in range(3) for j in range(3)]):
            print("Tie game!")
            break
        turn = (turn + 1) % 2
    play_again = input("Do you want to play again? (y/n): ")
    if play_again == "y":
        play_game()

play_game()