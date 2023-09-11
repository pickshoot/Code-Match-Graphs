class TicTacToe:
    def __init__(self):
        self.board = [' ']*9
        self.current_player = 'X'
        
    def print_board(self):
        print("-------------")
        for i in range(3):
            print("|", self.board[i*3], "|", self.board[i*3+1], "|", self.board[i*3+2], "|")
            print("-------------")
            
    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
        else:
            raise ValueError("Invalid move: position already taken")
            
    def switch_player(self):
        if self.current_player == 'X':
            self.current_player = 'O'
        else:
            self.current_player = 'X'
            
    def check_winner(self):
        for i in range(3):
            if self.board[i*3] == self.board[i*3+1] == self.board[i*3+2] != ' ':
                return self.board[i*3]
            elif self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return self.board[i]
            elif self.board[0] == self.board[4] == self.board[8] != ' ':
                return self.board[0]
            elif self.board[2] == self.board[4] == self.board[6] != ' ':
                return self.board[2]
        if ' ' not in self.board:
            return 'tie'
        return None
    
    def play(self):
        while True:
            self.print_board()
            try:
                position = int(input(f"Player {self.current_player}, make your move (1-9): ")) - 1
                self.make_move(position)
            except ValueError as e:
                print(e)
                continue
            except IndexError:
                print("Invalid move: position out of range")
                continue
            self.switch_player()
            winner = self.check_winner()
            if winner is not None:
                self.print_board()
                if winner == 'tie':
                    print("It's a tie!")
                else:
                    print(f"Player {winner} wins!")
                break

game = TicTacToe()
game.play()