def print_board(board):
    print(board[0] + "|" + board[1] + "|" + board[2])
    print("-+-+-")
    print(board[3] + "|" + board[4] + "|" + board[5])
    print("-+-+-")
    print(board[6] + "|" + board[7] + "|" + board[8])

def check_win(board):
    win_combinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], # horizontal
        [0, 3, 6], [1, 4, 7], [2, 5, 8], # vertical
        [0, 4, 8], [2, 4, 6] # diagonal
    ]
    for combination in win_combinations:
        if board[combination[0]] == board[combination[1]] == board[combination[2]] != " ":
            return True
    return False

def tic_tac_toe():
    board = [" "] * 9
    players = ["X", "O"]
    current_player = players[0]
    winner = False

    while not winner and " " in board:
        print_board(board)
        print("It's " + current_player + "'s turn")
        position = int(input("Enter a number between 1 and 9: ")) - 1
        if board[position] == " ":
            board[position] = current_player
            if check_win(board):
                winner = True
                print_board(board)
                print(current_player + " wins!")
            else:
                current_player = players[(players.index(current_player) + 1) % 2]
        else:
            print("That position is already taken.")

    if not winner:
        print_board(board)
        print("It's a tie!")

tic_tac_toe()