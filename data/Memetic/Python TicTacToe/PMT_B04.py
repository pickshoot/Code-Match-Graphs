import random

def print_board(board):
    """
    Prints the current state of the Tic-Tac-Toe board.
    """
    print("-------------")
    for row in board:
        print("|", end="")
        for cell in row:
            print(" " + cell + " |", end="")
        print("\n-------------")

def get_player_move(board, player):
    """
    Gets the move from the player and updates the board.
    """
    while True:
        move = input("Enter the row and column for your move (e.g. 1,2): ")
        try:
            row, col = [int(num.strip()) - 1 for num in move.split(",")]
            if board[row][col] == " ":
                board[row][col] = player
                return
            else:
                print("That cell is already occupied. Try again.")
        except:
            print("Invalid input. Try again.")

def get_computer_move(board, computer):
    """
    Gets a move from the computer and updates the board.
    """
    print("Computer is making its move...")
    while True:
        row = random.randint(0, 2)
        col = random.randint(0, 2)
        if board[row][col] == " ":
            board[row][col] = computer
            return

def check_winner(board):
    """
    Checks if there is a winner and returns the winner's symbol (X or O) or None if there is no winner.
    """
    for row in range(3):
        if board[row][0] == board[row][1] == board[row][2] != " ":
            return board[row][0]
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] != " ":
            return board[0][col]
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return board[0][2]
    return None

def play_game():
    """
    Runs the Tic-Tac-Toe game.
    """
    print("Welcome to Tic-Tac-Toe!")
    board = [[" "]*3 for _ in range(3)]
    players = ["X", "O"]
    random.shuffle(players)
    print(f"{players[0]} goes first.")

    while True:
        for player in players:
            print_board(board)
            if player == players[0]:
                get_player_move(board, player)
            else:
                get_computer_move(board, player)
            winner = check_winner(board)
            if winner:
                print_board(board)
                print(f"{winner} wins!")
                return
            if all([cell != " " for row in board for cell in row]):
                print_board(board)
                print("It's a tie!")
                return

if __name__ == "__main__":
    play_game()