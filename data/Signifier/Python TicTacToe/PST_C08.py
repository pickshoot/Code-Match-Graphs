def print_board(board):
    print("-------------")
    print("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")
    print("-------------")
    print("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")
    print("-------------")
    print("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")
    print("-------------")

def check_win(board):
    # check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] and board[i] != "-":
            return board[i]
    # check columns
    for i in range(0, 3):
        if board[i] == board[i+3] == board[i+6] and board[i] != "-":
            return board[i]
    # check diagonals
    if board[0] == board[4] == board[8] and board[0] != "-":
        return board[0]
    if board[2] == board[4] == board[6] and board[2] != "-":
        return board[2]
    return None

def play_game():
    board = ["-", "-", "-",
             "-", "-", "-",
             "-", "-", "-"]
    current_player = "X"
    game_still_going = True
    while game_still_going:
        print_board(board)
        position = input(f"{current_player}'s turn. Enter a position (1-9): ")
        while position not in ["1", "2", "3", "4", "5", "6", "7", "8", "9"]:
            position = input("Invalid input. Enter a position (1-9): ")
        position = int(position) - 1
        if board[position] == "-":
            board[position] = current_player
            if check_win(board):
                print_board(board)
                print(f"{current_player} wins!")
                game_still_going = False
            elif "-" not in board:
                print_board(board)
                print("It's a tie!")
                game_still_going = False
            else:
                current_player = "O" if current_player == "X" else "X"
        else:
            print("That position is already taken. Try again.")
    play_again = input("Do you want to play again? (y/n): ")
    if play_again.lower() == "y":
        play_game()
    else:
        print("Thanks for playing!")

play_game()
