def print_board(board):
    print(f"{board[0]}|{board[1]}|{board[2]}")
    print("-|-|-")
    print(f"{board[3]}|{board[4]}|{board[5]}")
    print("-|-|-")
    print(f"{board[6]}|{board[7]}|{board[8]}")

def get_move(player, board):
    move = input(f"{player}'s turn (X/O): ")
    while True:
        if not move.isdigit() or int(move) not in range(1, 10):
            move = input("Invalid input, try again: ")
        elif board[int(move)-1] != " ":
            move = input("That spot is already taken, try again: ")
        else:
            break
    return int(move) - 1

def check_win(board):
    wins = [(0,1,2),(3,4,5),(6,7,8),(0,3,6),(1,4,7),(2,5,8),(0,4,8),(2,4,6)]
    for a, b, c in wins:
        if board[a] == board[b] == board[c] != " ":
            return board[a]
    if " " not in board:
        return "Tie"
    return None

def play_game():
    board = [" "] * 9
    players = ["X", "O"]
    turn = 0
    while True:
        print_board(board)
        move = get_move(players[turn], board)
        board[move] = players[turn]
        winner = check_win(board)
        if winner is not None:
            print_board(board)
            if winner == "Tie":
                print("It's a tie!")
            else:
                print(f"{winner} wins!")
            break
        turn = (turn + 1) % 2

if __name__ == '__main__':
    play_game()