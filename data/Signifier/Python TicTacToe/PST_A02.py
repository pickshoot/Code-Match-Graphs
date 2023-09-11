def print_board(board):
    """
    Prints the current state of the board
    """
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("---|---|---")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("---|---|---")
    print(f" {board[6]} | {board[7]} | {board[8]} ")

def check_winner(board):
    """
    Checks the board to see if there is a winner or a tie
    """
    # Check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return True, board[i]
    # Check columns
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return True, board[i]
    # Check diagonals
    if board[0] == board[4] == board[8] != " ":
        return True, board[0]
    if board[2] == board[4] == board[6] != " ":
        return True, board[2]
    # Check for tie
    if " " not in board:
        return True, "tie"
    return False, ""

def get_move(player, board):
    """
    Prompts the player for their move and checks if it is valid
    """
    while True:
        move = input(f"{player}, enter your move (1-9): ")
        if move.isdigit() and int(move) in range(1, 10) and board[int(move)-1] == " ":
            return int(move)-1
        print("Invalid move. Please try again.")

def play_game():
    """
    Plays a game of Tic-Tac-Toe
    """
    board = [" "]*9
    players = ["X", "O"]
    current_player = 0
    while True:
        print_board(board)
        move = get_move(players[current_player], board)
        board[move] = players[current_player]
        winner, winner_player = check_winner(board)
        if winner:
            print_board(board)
            if winner_player == "tie":
                print("It's a tie!")
            else:
                print(f"{winner_player} wins!")
            break
        current_player = (current_player + 1) % 2

if __name__ == "__main__":
    play_game()