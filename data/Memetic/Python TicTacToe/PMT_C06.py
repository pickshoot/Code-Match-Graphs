def print_board(board):
    print("   |   |")
    print(" " + board[0] + " | " + board[1] + " | " + board[2])
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(" " + board[3] + " | " + board[4] + " | " + board[5])
    print("   |   |")
    print("-----------")
    print("   |   |")
    print(" " + board[6] + " | " + board[7] + " | " + board[8])
    print("   |   |")

def check_win(board):
    # check rows
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] and board[i] != " ":
            return True
    # check columns
    for i in range(0, 3):
        if board[i] == board[i+3] == board[i+6] and board[i] != " ":
            return True
    # check diagonals
    if board[0] == board[4] == board[8] and board[0] != " ":
        return True
    if board[2] == board[4] == board[6] and board[2] != " ":
        return True
    return False

def main():
    board = [" "] * 9
    player = "X"
    game_over = False
    
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)
    
    while not game_over:
        move = int(input("Player " + player + ", enter a number (1-9): "))
        if board[move-1] == " ":
            board[move-1] = player
        else:
            print("That space is already taken.")
            continue
            
        print_board(board)
        
        if check_win(board):
            print("Player " + player + " wins!")
            game_over = True
        elif " " not in board:
            print("Tie game!")
            game_over = True
        else:
            player = "O" if player == "X" else "X"

if __name__ == "__main__":
    main()