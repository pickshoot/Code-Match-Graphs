import random

def draw_board(board):
    """
    This function prints the current state of the board
    """
    print(board[0] + ' | ' + board[1] + ' | ' + board[2])
    print('--|---|--')
    print(board[3] + ' | ' + board[4] + ' | ' + board[5])
    print('--|---|--')
    print(board[6] + ' | ' + board[7] + ' | ' + board[8])

def get_player_move(board):
    """
    This function gets the player's move and updates the board
    """
    valid_input = False
    while not valid_input:
        player_move = input('Enter your move (0-8): ')
        if player_move.isdigit() and int(player_move) in range(9) and board[int(player_move)] == ' ':
            valid_input = True
            board[int(player_move)] = 'X'
        else:
            print('Invalid input. Please enter a number between 0 and 8 that represents an empty cell.')

def get_computer_move(board):
    """
    This function gets the computer's move and updates the board
    """
    valid_move = False
    while not valid_move:
        computer_move = random.randint(0, 8)
        if board[computer_move] == ' ':
            valid_move = True
            board[computer_move] = 'O'

def check_win(board):
    """
    This function checks if there is a winner or if the game is a tie
    """
    for i in range(0, 9, 3):
        if board[i] == board[i+1] == board[i+2] != ' ':
            return True, board[i]
    for i in range(3):
        if board[i] == board[i+3] == board[i+6] != ' ':
            return True, board[i]
    if board[0] == board[4] == board[8] != ' ':
        return True, board[0]
    if board[2] == board[4] == board[6] != ' ':
        return True, board[2]
    if ' ' not in board:
        return True, 'tie'
    return False, None

def play_game():
    """
    This function plays a single game of Tic-Tac-Toe
    """
    board = [' '] * 9
    draw_board(board)
    while True:
        get_player_move(board)
        draw_board(board)
        game_over, winner = check_win(board)
        if game_over:
            if winner == 'X':
                print('Congratulations! You won!')
            elif winner == 'O':
                print('Sorry, you lost.')
            else:
                print('The game is a tie.')
            break
        get_computer_move(board)
        draw_board(board)
        game_over, winner = check_win(board)
        if game_over:
            if winner == 'X':
                print('Congratulations! You won!')
            elif winner == 'O':
                print('Sorry, you lost.')
            else:
                print('The game is a tie.')
            break

if __name__ == '__main__':
    play_game()