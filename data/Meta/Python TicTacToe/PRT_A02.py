import sys

# Initialize the board
board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]

# Define a function to print the board
def print_board():
    print('  0 1 2')
    for i in range(3):
        print(str(i) + ' ' + '|'.join(board[i]))

# Define a function to check if someone has won
def check_win():
    # Check rows
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] != ' ':
            return board[i][0]
    # Check columns
    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] != ' ':
            return board[0][i]
    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]
    # If no one has won yet
    return None

# Define a function to check if the board is full
def check_tie():
    for i in range(3):
        for j in range(3):
            if board[i][j] == ' ':
                return False
    return True

# Define a function to handle a player's turn
def player_turn(player):
    while True:
        try:
            move = input(f"{player}'s turn. Enter row and column numbers separated by a space: ")
            row, col = move.split()
            row = int(row)
            col = int(col)
            if row < 0 or row > 2 or col < 0 or col > 2:
                print('Invalid input. Row and column numbers must be between 0 and 2.')
                continue
            if board[row][col] != ' ':
                print('That square is already taken. Try again.')
                continue
            board[row][col] = player
            return
        except ValueError:
            print('Invalid input. Please enter two numbers separated by a space.')
        except KeyboardInterrupt:
            print('Game ended by user.')
            sys.exit()

# Define a function to play the game
def play_game():
    # Initialize the players and the starting player
    player1 = 'X'
    player2 = 'O'
    current_player = player1
    print('Welcome to Tic-Tac-Toe!')
    print_board()
    while True:
        # Get the current player's move
        player_turn(current_player)
        # Print the updated board
        print_board()
        # Check for a win or a tie
        winner = check_win()
        if winner is not None:
            print(f'{winner} wins!')
            break
        elif check_tie():
            print('It\'s a tie!')
            break
        # Switch to the other player
        if current_player == player1:
            current_player = player2
        else:
            current_player = player1
    # Ask if the players want to play again
    while True:
        try:
            play_again = input('Do you want to play again? (y/n) ')
            if play_again.lower() == 'y':
                return True
            elif play_again.lower() == 'n':
                return False
        except KeyboardInterrupt:
            print('Game ended by user.')
            sys.exit()

# Define the main function to run the game
def main():
    while True:
        play_game_again = play_game()
        if not play_game_again:
            break
    print('Thanks for playing!')

# Run the main function
if __name__ == '__main__':
    main()