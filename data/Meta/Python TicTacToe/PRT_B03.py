import sys

def print_board(board):
    print("-------------")
    for i in range(3):
        print("|", board[i][0], "|", board[i][1], "|", board[i][2], "|")
        print("-------------")

def check_winner(board, player):
    # check rows
    for i in range(3):
        if board[i][0] == player and board[i][1] == player and board[i][2] == player:
            return True
    # check columns
    for j in range(3):
        if board[0][j] == player and board[1][j] == player and board[2][j] == player:
            return True
    # check diagonals
    if board[0][0] == player and board[1][1] == player and board[2][2] == player:
        return True
    if board[0][2] == player and board[1][1] == player and board[2][0] == player:
        return True
    return False

def play_again():
    play_again = input("Do you want to play again? (y/n): ")
    if play_again == 'y':
        main()
    else:
        sys.exit()

def main():
    # initialize the board
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]

    # get player names
    player1 = input("Enter Player 1 name: ")
    player2 = input("Enter Player 2 name: ")

    # initialize the game
    current_player = player1
    print_board(board)

    # play the game
    for i in range(9):
        print(f"{current_player}'s turn")
        row = int(input("Enter row (1-3): ")) - 1
        col = int(input("Enter column (1-3): ")) - 1
        while board[row][col] != ' ':
            print("This space is already occupied.")
            row = int(input("Enter row (1-3): ")) - 1
            col = int(input("Enter column (1-3): ")) - 1
        board[row][col] = 'X' if current_player == player1 else 'O'
        print_board(board)
        if check_winner(board, 'X'):
            print(f"{player1} wins!")
            play_again()
        elif check_winner(board, 'O'):
            print(f"{player2} wins!")
            play_again()
        current_player = player2 if current_player == player1 else player1

    # if no winner, it's a tie
    print("It's a tie!")
    play_again()

if __name__ == '__main__':
    main()