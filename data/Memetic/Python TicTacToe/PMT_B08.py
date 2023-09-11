import random

def print_board(board):
    """
    Prints the current state of the board.
    """
    print(f"{board[0]} | {board[1]} | {board[2]}")
    print("--|---|--")
    print(f"{board[3]} | {board[4]} | {board[5]}")
    print("--|---|--")
    print(f"{board[6]} | {board[7]} | {board[8]}")

def get_player_move(board):
    """
    Asks the user to input their move and returns it.
    """
    valid_move = False
    while not valid_move:
        try:
            move = int(input("Enter your move (1-9): ")) - 1
            if move < 0 or move > 8:
                print("Invalid move. Please enter a number between 1 and 9.")
            elif board[move] != " ":
                print("That space is already taken. Please choose another.")
            else:
                valid_move = True
        except ValueError:
            print("Invalid input. Please enter a number between 1 and 9.")
    return move

def get_computer_move(board):
    """
    Generates a random move for the computer.
    """
    possible_moves = [i for i in range(9) if board[i] == " "]
    return random.choice(possible_moves)

def check_win(board):
    """
    Checks if the game has been won and returns the winner ("X" or "O") or None if the game is not over.
    """
    for i in range(3):
        row = board[i*3:i*3+3]
        if all([x == "X" for x in row]) or all([x == "O" for x in row]):
            return row[0]
    for i in range(3):
        col = [board[i+j*3] for j in range(3)]
        if all([x == "X" for x in col]) or all([x == "O" for x in col]):
            return col[0]
    diag1 = [board[0], board[4], board[8]]
    if all([x == "X" for x in diag1]) or all([x == "O" for x in diag1]):
        return diag1[0]
    diag2 = [board[2], board[4], board[6]]
    if all([x == "X" for x in diag2]) or all([x == "O" for x in diag2]):
        return diag2[0]
    if " " not in board:
        return "tie"
    return None

def tic_tac_toe():
    """
    Plays a game of tic-tac-toe.
    """
    board = [" " for i in range(9)]
    player_turn = True
    while True:
        print_board(board)
        if player_turn:
            move = get_player_move(board)
            board[move] = "X"
        else:
            move = get_computer_move(board)
            board[move] = "O"
            print(f"The computer played move {move+1}")
        winner = check_win(board)
        if winner:
            print_board(board)
            if winner == "tie":
                print("It's a tie!")
            else:
                print(f"{winner} wins!")
            break
        player_turn = not player_turn

if __name__ == "__main__":
    tic_tac_toe()