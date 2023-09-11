# Tic Tac Toe Game
import random

board = [' ' for x in range(10)]

def print_board(board):
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

def insert_letter(letter, position):
    board[position] = letter

def space_is_free(position):
    return board[position] == ' '

def is_board_full(board):
    return board.count(' ') == 1

def is_winner(b, l):
    return ((b[1] == l and b[2] == l and b[3] == l) or
    (b[4] == l and b[5] == l and b[6] == l) or
    (b[7] == l and b[8] == l and b[9] == l) or
    (b[1] == l and b[4] == l and b[7] == l) or
    (b[2] == l and b[5] == l and b[8] == l) or
    (b[3] == l and b[6] == l and b[9] == l) or
    (b[1] == l and b[5] == l and b[9] == l) or
    (b[3] == l and b[5] == l and b[7] == l))

def player_move():
    run = True
    while run:
        move = input("Please select a position to place an 'X' (1-9): ")
        try:
            move = int(move)
            if move > 0 and move < 10:
                if space_is_free(move):
                    run = False
                    insert_letter('X', move)
                else:
                    print("Sorry, this space is occupied!")
            else:
                print("Please type a number within the range!")
        except:
            print("Please type a number!")

def computer_move():
    possible_moves = [x for x, letter in enumerate(board) if letter == ' ' and x != 0]
    move = 0

    for let in ['O', 'X']:
        for i in possible_moves:
            board_copy = board[:]
            board_copy[i] = let
            if is_winner(board_copy, let):
                move = i
                return move

    corners_open = []
    for i in possible_moves:
        if i in [1, 3, 7, 9]:
            corners_open.append(i)

    if len(corners_open) > 0:
        move = select_random(corners_open)
        return move

    if 5 in possible_moves:
        move = 5
        return move

    edges_open = []
    for i in possible_moves:
        if i in [2, 4, 6, 8]:
            edges_open.append(i)

    if len(edges_open) > 0:
        move = select_random(edges_open)
        return move

    return move

def select_random(lst):
    ln = len(lst)
    r = random.randrange(0, ln)
    return lst[r]

def main():
    print("Welcome to Tic Tac Toe!")
    print_board(board)

    while not is_board_full(board):
        if not is_winner(board, 'O'):
            player_move()
            print_board(board)
        else:
            print("Sorry, O's won this time!")
            break

        if not is_winner(board, 'X'):
            move = computer_move()
            if move == 0:
                print("Tie game!")
            else:
                insert_letter('O', move)
                print(f"Computer placed an 'O' in position {move}:")
                print_board(board)
        else:
            print("X's won this time! Good Job!")
            break

    if is_board_full(board):
        print("Tie game!")

while True:
    answer = input("Do you want to play again? (Y/N)")
    if answer.lower() == 'y' or answer.lower() == 'yes':
        board = [' ' for x in range(10)]
        print('-------------------')
        main()
    else:
        break