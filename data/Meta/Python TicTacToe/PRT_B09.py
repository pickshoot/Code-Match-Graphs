def display_board(board):
    """
    Display the current board state.
    """
    print(board[0][0] + '|' + board[0][1] + '|' + board[0][2])
    print('-+-+-')
    print(board[1][0] + '|' + board[1][1] + '|' + board[1][2])
    print('-+-+-')
    print(board[2][0] + '|' + board[2][1] + '|' + board[2][2])

def get_player_move(player_name, player_mark, board):
    """
    Get the player's move and update the board.
    """
    while True:
        try:
            row = int(input(f'{player_name} ({player_mark}), choose a row (1-3): ')) - 1
            col = int(input(f'{player_name} ({player_mark}), choose a column (1-3): ')) - 1
        except ValueError:
            print('Invalid input. Please enter a number between 1 and 3.')
            continue

        if row < 0 or row > 2 or col < 0 or col > 2:
            print('Invalid input. Please enter a number between 1 and 3.')
            continue

        if board[row][col] != ' ':
            print('That space is already occupied. Please choose another one.')
            continue

        board[row][col] = player_mark
        return

def check_winner(board):
    """
    Check if there is a winner.
    """
    # Check rows
    for row in range(3):
        if board[row][0] == board[row][1] == board[row][2] != ' ':
            return board[row][0]

    # Check columns
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] != ' ':
            return board[0][col]

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]

    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]

    return None

def play_again():
    """
    Ask the players if they want to play again.
    """
    while True:
        answer = input('Do you want to play again? (Y/N): ').lower()

        if answer == 'y':
            return True

        if answer == 'n':
            return False

        print('Invalid input. Please enter Y or N.')

def play_game():
    """
    Play a game of Tic-Tac-Toe.
    """
    board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]

    player1_name = input('Enter the name of Player 1: ')
    player1_mark = 'X'

    player2_name = input('Enter the name of Player 2: ')
    player2_mark = 'O'

    current_player_name = player1_name
    current_player_mark = player1_mark

    while True:
        display_board(board)
        get_player_move(current_player_name, current_player_mark, board)
        winner = check_winner(board)

        if winner is not None:
            print(f'{current_player_name} ({current_player_mark}) wins!')
            break

        if all(board[row][col] != ' ' for row in range(3) for col in range(3)):
            print('It is a tie!')
            break

        if current_player_name == player1_name:
            current_player_name = player2_name
            current_player_mark = player2_mark
        else:
            current_player_name = player1_name
            current_player_mark = player1_mark

    return play_again()

while play_game():
    pass