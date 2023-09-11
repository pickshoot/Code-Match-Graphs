class TicTacToe:
    def __init__(self):
        self.board = [' '] * 9
        self.current_player = 'X'

    def print_board(self):
        print('-------------')
        for i in range(3):
            print(f'| {self.board[i*3]} | {self.board[i*3+1]} | {self.board[i*3+2]} |')
            print('-------------')

    def play(self):
        while True:
            self.print_board()
            move = input(f"{self.current_player}'s turn. Choose a position (1-9): ")
            try:
                move = int(move) - 1
            except ValueError:
                print("Invalid input. Please enter a number between 1 and 9.")
                continue
            if move < 0 or move > 8:
                print("Invalid input. Please enter a number between 1 and 9.")
                continue
            if self.board[move] != ' ':
                print("That position is already taken. Please choose another position.")
                continue
            self.board[move] = self.current_player
            if self.check_win():
                print(f"{self.current_player} wins!")
                break
            if self.check_tie():
                print("It's a tie!")
                break
            self.current_player = 'O' if self.current_player == 'X' else 'X'

    def check_win(self):
        for i in range(3):
            if self.board[i*3] == self.board[i*3+1] == self.board[i*3+2] != ' ':
                return True
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return True
        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return True
        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return True
        return False

    def check_tie(self):
        return ' ' not in self.board

# Main program
game = TicTacToe()
game.play()
