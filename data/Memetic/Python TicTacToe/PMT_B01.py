class TicTacToe:
    def __init__(self):
        self.board = [[' ' for _ in range(3)] for _ in range(3)]
        self.current_player = 'X'
        
    def print_board(self):
        print('-------------')
        for i in range(3):
            print(f'| {self.board[i][0]} | {self.board[i][1]} | {self.board[i][2]} |')
            print('-------------')
            
    def make_move(self, row, col):
        if self.board[row][col] == ' ':
            self.board[row][col] = self.current_player
            if self.current_player == 'X':
                self.current_player = 'O'
            else:
                self.current_player = 'X'
        else:
            print('Invalid move, try again.')
    
    def check_win(self):
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != ' ':
                return True
            elif self.board[0][i] == self.board[1][i] == self.board[2][i] != ' ':
                return True
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != ' ':
            return True
        elif self.board[0][2] == self.board[1][1] == self.board[2][0] != ' ':
            return True
        return False
    
    def play(self):
        while True:
            self.print_board()
            row = int(input('Enter row (0-2): '))
            col = int(input('Enter column (0-2): '))
            self.make_move(row, col)
            if self.check_win():
                self.print_board()
                print(f'Player {self.current_player} wins!')
                break
            elif all(' ' not in row for row in self.board):
                self.print_board()
                print('Tie game!')
                break

game = TicTacToe()
game.play()