# Tic-Tac-Toe Game

def print_board(board):
    print("-------------")
    for row in board:
        print("|", " | ".join(row), "|")
        print("-------------")

def check_win(board, player):
    # Check rows
    for row in board:
        if row.count(player) == 3:
            return True

    # Check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] == player:
            return True

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] == player:
        return True
    if board[0][2] == board[1][1] == board[2][0] == player:
        return True

    return False

def play_game():
    board = [[" ", " ", " "] for i in range(3)]
    players = ["X", "O"]
    current_player = 0
    game_over = False

    while not game_over:
        print_board(board)
        player_choice = input("Player " + players[current_player] + ", choose a row and column (e.g. 1 2): ")
        row, col = player_choice.split()
        row, col = int(row) - 1, int(col) - 1

        if board[row][col] == " ":
            board[row][col] = players[current_player]
            if check_win(board, players[current_player]):
                print_board(board)
                print("Player " + players[current_player] + " wins!")
                game_over = True
            elif all(" " not in row for row in board):
                print_board(board)
                print("It's a tie!")
                game_over = True
            else:
                current_player = (current_player + 1) % 2
        else:
            print("That spot is already taken. Try again.")

if __name__ == "__main__":
    play_game()
