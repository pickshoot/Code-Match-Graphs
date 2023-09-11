import random

# define the board
board = [' ' for x in range(10)]

# function to display the board
def display_board(board):
    print('   |   |')
    print(' ' + board[1] + ' | ' + board[2] + ' | ' + board[3])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[4] + ' | ' + board[5] + ' | ' + board[6])
    print('   |   |')
    print('-----------')
    print('   |   |')
    print(' ' + board[7] + ' | ' + board[8] + ' | ' + board[9])
    print('   |   |')

# function to check if a player has won
def is_winner(board, player):
    return ((board[1] == player and board[2] == player and board[3] == player) or
            (board[4] == player and board[5] == player and board[6] == player) or
            (board[7] == player and board[8] == player and board[9] == player) or
            (board[1] == player and board[4] == player and board[7] == player) or
            (board[2] == player and board[5] == player and board[8] == player) or
            (board[3] == player and board[6] == player and board[9] == player) or
            (board[1] == player and board[5] == player and board[9] == player) or
            (board[3] == player and board[5] == player and board[7] == player))

# function to check if the board is full
def is_board_full(board):
    return ' ' not in board[1:]

# function for a player to take a turn
def get_player_move(board):
    move = input("Enter a position to place your move (1-9): ")
    while move not in ['1', '2', '3', '4', '5', '6', '7', '8', '9'] or board[int(move)] != ' ':
        move = input("Invalid move. Enter a position to place your move (1-9): ")
    return int(move)

# function for the computer to take a turn
def get_computer_move(board, computer_player):
    if computer_player == 'X':
        player = 'O'
    else:
        player = 'X'

    # check if the computer can win
    for i in range(1, 10):
        if board[i] == ' ':
            board[i] = computer_player
            if is_winner(board, computer_player):
                return i
            else:
                board[i] = ' '

    # check if the player can win and block them
    for i in range(1, 10):
        if board[i] == ' ':
            board[i] = player
            if is_winner(board, player):
                return i
            else:
                board[i] = ' '

    # take a corner if it's available
    corners = [1, 3, 7, 9]
    available_corners = []
    for i in corners:
        if board[i] == ' ':
            available_corners.append(i)
    if len(available_corners) > 0:
        return random.choice(available_corners)
    else:
        return None

# main function to play the game
def play_game():
    print('Welcome to Tic-Tac-Toe!')

    # ask the player if they want to play first or second
    player_choice = input("Do you want to play as X or O? ").upper()
    while player_choice not in ['X', 'O']:
        player_choice = input("Invalid choice. Do you want to play as X or O? ").upper()

    # set up the game
    if player_choice == 'X':
        computer_choice = 'O'
    else:
        computer_choice = 'X'

    turn = 'X'
    game_over = False

    # play the game
    while not game_over:
        if turn == player_choice:
            display_board(board)
            move = get_player_move(board)
            board[move] = player_choice

            if is_winner(board, player_choice):
                display_board(board)
                print('Congratulations! You won!')
                game_over = True
            else:
                if is_board_full(board):
                    display_board(board)
                    print('The game is a tie.')
                    game_over = True
                else:
                    turn = computer_choice

        else:
            move = get_computer_move(board, computer_choice)
            board[move] = computer_choice

            if is_winner(board, computer_choice):
                display_board(board)
                print('Sorry, the computer won. Try again.')
                game_over = True
            else:
                if is_board_full(board):
                    display_board(board)
                    print('The game is a tie.')
                    game_over = True
                else:
                    turn = player_choice

    # ask if the player wants to play again
    play_again = input("Do you want to play again? (y/n) ").lower()
    while play_again not in ['y', 'n']:
        play_again = input("Invalid choice. Do you want to play again? (y/n) ").lower()

    if play_again == 'y':
        for i in range(1, 10):
            board[i] = ' '
        play_game()

# start the game
play_game()