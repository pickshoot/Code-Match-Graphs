import numpy as np

def create_board():
    return np.zeros((3,3), dtype=int)

def place_marker(board, marker, position):
    row, col = position
    board[row][col] = marker

def space_is_free(board, position):
    row, col = position
    return board[row][col] == 0

def is_winner(board, marker):
    # Check rows
    for row in board:
        if np.all(row == marker):
            return True

    # Check columns
    for col in board.T:
        if np.all(col == marker):
            return True

    # Check diagonals
    if np.all(np.diag(board) == marker) or np.all(np.diag(np.fliplr(board)) == marker):
        return True

    return False

def is_tie(board):
    return np.all(board != 0)

def get_player_move(player):
    print(f"Player {player}'s turn.")
    row = int(input("Enter row number (0-2): "))
    col = int(input("Enter column number (0-2): "))
    return (row, col)

def play_again():
    response = input("Do you want to play again? (yes or no): ")
    return response.lower() == 'yes'

def print_board(board):
    print(board)

def main():
    print("Welcome to Tic-Tac-Toe!")
    player1_marker = 'X'
    player2_marker = 'O'
    current_player = 1
    playing = True

    while playing:
        board = create_board()
        print_board(board)

        while True:
            position = get_player_move(current_player)
            if space_is_free(board, position):
                place_marker(board, player1_marker if current_player == 1 else player2_marker, position)
                print_board(board)

                if is_winner(board, player1_marker if current_player == 1 else player2_marker):
                    print(f"Player {current_player} wins!")
                    break

                if is_tie(board):
                    print("The game is a tie!")
                    break

                current_player = 2 if current_player == 1 else 1
            else:
                print("That position is already occupied. Please choose another.")

        playing = play_again()

    print("Thanks for playing Tic-Tac-Toe!")

if __name__ == '__main__':
    main()