import itertools

# function to display the board
def display_board(board):
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

# function to check if there is a winner
def check_winner(board):
    WINNING_COMBOS = [(0, 1, 2), (3, 4, 5), (6, 7, 8),
                      (0, 3, 6), (1, 4, 7), (2, 5, 8),
                      (0, 4, 8), (2, 4, 6)]
    for combo in WINNING_COMBOS:
        if board[combo[0]] == board[combo[1]] == board[combo[2]] != ' ':
            return True
    return False

# function to check if the board is full
def is_board_full(board):
    return not any(x == ' ' for x in board)

# function to get player input
def get_player_input(player_name, marker, board):
    while True:
        try:
            position = int(input(f"{player_name} ({marker}), choose a position (1-9): "))
        except ValueError:
            print("Invalid input. Please enter a number between 1 and 9.")
            continue
        if position < 1 or position > 9:
            print("Invalid input. Please enter a number between 1 and 9.")
            continue
        if board[position-1] != ' ':
            print("That position is already taken. Please choose another one.")
            continue
        break
    return position - 1

# function to play the game
def play_game():
    print("Welcome to Tic-Tac-Toe!")
    player1_name = input("Player 1, enter your name: ")
    player2_name = input("Player 2, enter your name: ")
    player1_marker = 'X'
    player2_marker = 'O'
    current_player_name, current_player_marker = player1_name, player1_marker
    board = [' '] * 9
    while True:
        display_board(board)
        position = get_player_input(current_player_name, current_player_marker, board)
        board[position] = current_player_marker
        if check_winner(board):
            display_board(board)
            print(f"{current_player_name} wins!")
            break
        elif is_board_full(board):
            display_board(board)
            print("It's a tie!")
            break
        if current_player_name == player1_name:
            current_player_name, current_player_marker = player2_name, player2_marker
        else:
            current_player_name, current_player_marker = player1_name, player1_marker
    play_again = input("Do you want to play again? (y/n)").lower()
    if play_again == 'y':
        play_game()
    else:
        print("Thanks for playing!")

play_game()