import os

def display_board(board):
    print(board[0][0] + "|" + board[0][1] + "|" + board[0][2])
    print("-+-+-")
    print(board[1][0] + "|" + board[1][1] + "|" + board[1][2])
    print("-+-+-")
    print(board[2][0] + "|" + board[2][1] + "|" + board[2][2])

def get_player_move(player, board):
    valid_move = False
    while not valid_move:
        move = input(f"{player}, enter your move (row, column): ")
        row, col = move.split(",")
        row = int(row.strip())
        col = int(col.strip())
        if row < 1 or row > 3 or col < 1 or col > 3:
            print("Invalid input! Row and column must be between 1 and 3.")
            continue
        if board[row-1][col-1] != " ":
            print("That space is already occupied! Try again.")
            continue
        valid_move = True
    return row-1, col-1

def check_winner(board):
    # Check rows
    for row in board:
        if row[0] == row[1] == row[2] and row[0] != " ":
            return row[0]

    # Check columns
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] != " ":
            return board[0][col]

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != " ":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != " ":
        return board[0][2]

    # No winner yet
    return None

def play_game():
    board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
    player1 = input("Enter name of Player 1 (X): ")
    player2 = input("Enter name of Player 2 (O): ")
    current_player = player1
    winner = None
    num_moves = 0

    while num_moves < 9 and not winner:
        os.system("clear")
        print(f"{current_player}'s turn:")
        display_board(board)
        row, col = get_player_move(current_player, board)
        board[row][col] = "X" if current_player == player1 else "O"
        winner = check_winner(board)
        current_player = player2 if current_player == player1 else player1
        num_moves += 1

    os.system("clear")
    display_board(board)
    if winner:
        print(f"{winner} wins!")
    else:
        print("It's a tie!")

    play_again = input("Do you want to play again? (y/n): ")
    return play_again.lower() == "y"

while play_game():
    pass