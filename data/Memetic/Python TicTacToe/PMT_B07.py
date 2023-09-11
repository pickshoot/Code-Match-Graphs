import os

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

def draw_board(board):
    print(f" {board[0]} | {board[1]} | {board[2]} ")
    print("---+---+---")
    print(f" {board[3]} | {board[4]} | {board[5]} ")
    print("---+---+---")
    print(f" {board[6]} | {board[7]} | {board[8]} ")

def get_player_move(player, board):
    while True:
        try:
            move = int(input(f"Player {player}, please choose a square (1-9): "))
            if board[move-1] == " ":
                return move-1
            else:
                print("That square is already taken!")
        except ValueError:
            print("Invalid input. Please enter a number between 1 and 9.")
        except IndexError:
            print("Invalid input. Please enter a number between 1 and 9.")

def check_for_win(board):
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != " ":
            return board[i]
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != " ":
            return board[i]
    if board[0] == board[4] == board[8] != " ":
        return board[0]
    if board[2] == board[4] == board[6] != " ":
        return board[2]
    if " " not in board:
        return "tie"
    return None

def play_game():
    board = [" "] * 9
    player = "X"
    while True:
        clear_screen()
        draw_board(board)
        winner = check_for_win(board)
        if winner:
            if winner == "tie":
                print("The game is a tie!")
            else:
                print(f"Player {winner} wins!")
            return
        move = get_player_move(player, board)
        board[move] = player
        player = "O" if player == "X" else "X"

if __name__ == '__main__':
    play_game()