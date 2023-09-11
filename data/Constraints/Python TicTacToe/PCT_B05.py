class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]  # Initialize empty board
        self.current_player = 'X'  # Player X always goes first

    def print_board(self):
        print('-------------')
        for i in range(3):
            print(f'| {self.board[i*3]} | {self.board[i*3+1]} | {self.board[i*3+2]} |')
            print('-------------')

    def get_move(self):
        valid_move = False
        while not valid_move:
            move = input(f"{self.current_player}'s turn. Enter a number (1-9) to place {self.current_player}: ")
            try:
                move = int(move)
                if move >= 1 and move <= 9 and self.board[move-1] == ' ':
                    valid_move = True
                else:
                    print("Invalid move. Try again.")
            except ValueError:
                print("Invalid move. Try again.")
        return move - 1

    def play(self):
        while True:
            self.print_board()
            move = self.get_move()
            self.board[move] = self.current_player
            if self.check_win():
                print(f"{self.current_player} wins!")
                break
            if self.check_draw():
                print("It's a draw!")
                break
            self.current_player = 'O' if self.current_player == 'X' else 'X'

    def check_win(self):
        win_states = [
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            [0, 4, 8],
            [2, 4, 6]
        ]
        for state in win_states:
            if self.board[state[0]] == self.board[state[1]] == self.board[state[2]] != ' ':
                return True
        return False

    def check_draw(self):
        return all([cell != ' ' for cell in self.board])

if __name__ == '__main__':
    game = TicTacToe()
    game.play()