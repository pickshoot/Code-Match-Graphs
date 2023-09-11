class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]  # create empty board
    
    def print_board(self):
        print(f"| {self.board[0]} | {self.board[1]} | {self.board[2]} |")
        print(f"| {self.board[3]} | {self.board[4]} | {self.board[5]} |")
        print(f"| {self.board[6]} | {self.board[7]} | {self.board[8]} |")
    
    def make_move(self, position, symbol):
        if self.board[position] == ' ':  # check if position is empty
            self.board[position] = symbol
            return True
        else:
            return False
    
    def check_winner(self):
        # check rows
        for i in range(0, 9, 3):
            if self.board[i] == self.board[i+1] == self.board[i+2] != ' ':
                return self.board[i]
        # check columns
        for i in range(3):
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return self.board[i]
        # check diagonals
        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return self.board[0]
        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return self.board[2]
        # check for tie
        if ' ' not in self.board:
            return 'Tie'
        return None
    
    def play(self):
        print("Welcome to Tic-Tac-Toe!")
        self.print_board()
        current_player = 'X'
        while True:
            print(f"Player {current_player}'s turn.")
            position = int(input("Enter position (1-9): ")) - 1
            if not 0 <= position <= 8:
                print("Invalid position.")
                continue
            if not self.make_move(position, current_player):
                print("Position already taken.")
                continue
            self.print_board()
            winner = self.check_winner()
            if winner:
                if winner == 'Tie':
                    print("It's a tie!")
                else:
                    print(f"Player {winner} wins!")
                break
            current_player = 'O' if current_player == 'X' else 'X'

game = TicTacToe()
game.play()