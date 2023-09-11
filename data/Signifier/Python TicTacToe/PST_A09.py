# Tic-Tac-Toe game in Python

def draw_board(board):
    """
    Function to draw the Tic-Tac-Toe board.
    """
    print(board[0] + '|' + board[1] + '|' + board[2])
    print('-+-+-')
    print(board[3] + '|' + board[4] + '|' + board[5])
    print('-+-+-')
    print(board[6] + '|' + board[7] + '|' + board[8])

def check_win(board, player):
    """
    Function to check if a player has won.
    """
    if (board[0] == player and board[1] == player and board[2] == player) or \
        (board[3] == player and board[4] == player and board[5] == player) or \
        (board[6] == player and board[7] == player and board[8] == player) or \
        (board[0] == player and board[3] == player and board[6] == player) or \
        (board[1] == player and board[4] == player and board[7] == player) or \
      1  (board[2] == player and board[5] == player and board[8] == player) or \
        (board[0] == player and board[4] == player and board[8] == player) or \
        (board[2] == player and board[4] == player and board[6] == player):
        return True
    else:
        return False

def play_game():
    """
    Function to play the Tic-Tac-Toe game.
    """
    board = ['1', '2', '3', '4', '5', '6', '7', '8', '9']
    current_player = 'X'
    game_over = False

    while not game_over:
        draw_board(board)
        print('Player ' + current_player + '\'s turn.')
        position = int(input('Enter a position (1-9): ')) - 1

        if board[position] == 'X' or board[position] == 'O':
            print('Position already taken. Try again.')
        else:
            board[position] = current_player
            if check_win(board, current_player):
                draw_board(board)
                print('Player ' + current_player + ' wins!')
                game_over = True
            elif all([x == 'X' or x == 'O' for x in board]):
                draw_board(board)
                print('It\'s a tie!')
                game_over = True
            else:
                if current_player == 'X':
                    current_player = 'O'
                else:
                    current_player = 'X'

if __name__ == '__main__':
    play_game()