import random

# Define the game board
board = [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']

# Define the player symbols
player1_symbol = 'X'
player2_symbol = 'O'

# Define a function to print the game board
def print_board():
    print('   |   |')
    print(' ' + board[0] + ' | ' + board[1] + ' | ' + board[2])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[3] + ' | ' + board[4] + ' | ' + board[5])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[6] + ' | ' + board[7] + ' | ' + board[8])
    print('   |   |')

# Define a function to check if a player has won
def check_win(player):
    if (board[0] == player and board[1] == player and board[2] == player) or \
       (board[3] == player and board[4] == player and board[5] == player) or \
       (board[6] == player and board[7] == player and board[8] == player) or \
       (board[0] == player and board[3] == player and board[6] == player) or \
       (board[1] == player and board[4] == player and board[7] == player) or \
       (board[2] == player and board[5] == player and board[8] == player) or \
       (board[0] == player and board[4] == player and board[8] == player) or \
       (board[2] == player and board[4] == player and board[6] == player):
        return True
    else:
        return False

# Define the main function
def main():
    # Print the initial game board
    print_board()

    # Define the turn variable to keep track of whose turn it is
    turn = 1

    # Loop until the game is over
    while True:
        # Get the current player's symbol
        if turn % 2 == 1:
            player_symbol = player1_symbol
            player_name = 'Player 1'
        else:
            player_symbol = player2_symbol
            player_name = 'Player 2'

        # Get the current player's move
        while True:
            move = input(player_name + ', enter your move (1-9): ')
            if move.isdigit() and int(move) >= 1 and int(move) <= 9 and board[int(move)-1] == ' ':
                move = int(move) - 1
                break
            else:
                print('Invalid move, please try again.')

        # Update the game board
        board[move] = player_symbol

        # Print the updated game board
        print_board()

        # Check if the current player has won
        if check_win(player_symbol):
            print(player_name + ' wins!')
            break

        # Check if the game is a tie
        if ' ' not in board:
            print('Tie game!')
            break

        # Increment the turn counter
        turn += 1

# Call the main function to start the game
main()