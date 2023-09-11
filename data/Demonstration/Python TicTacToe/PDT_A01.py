import random
class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]  # initialize the board

    def print_board(self):
        print('-------------')
        for i in range(3):
            print(f'| {self.board[i*3]} | {self.board[i*3 + 1]} | {self.board[i*3 + 2]} |')
            print('-------------')

    def make_move(self, position, player):
        self.board[position] = player

    def available_moves(self):
        return [i for i, x in enumerate(self.board) if x == ' ']

    def check_win(self, player):
        win_combinations = ((0, 1, 2), (3, 4, 5), (6, 7, 8), (0, 3, 6), (1, 4, 7), (2, 5, 8), (0, 4, 8), (2, 4, 6))
        for combination in win_combinations:
            if all(self.board[i] == player for i in combination):
                return True
        return False

    def check_tie(self):
        return ' ' not in self.board

    def reset_board(self):
        self.board = [' ' for _ in range(9)]

    def play(self):
        print("Welcome to Tic Tac Toe!")
        print("Player 1 goes first. You are X.")
        while True:
            self.print_board()
            available = self.available_moves()
            print(f"Available moves: {available}")
            move = input("Enter position for your move (0-8): ")
            try:
                move = int(move)
            except ValueError:
                print("Invalid input. Please enter an integer between 0 and 8.")
                continue
            if move not in available:
                print("Invalid move. Please try again.")
                continue
            self.make_move(move, 'X')
            if self.check_win('X'):
                self.print_board()
                print("Congratulations! You win!")
                break
            if self.check_tie():
                self.print_board()
                print("It's a tie!")
                break
            move = random.choice(self.available_moves())
            self.make_move(move, 'O')
            if self.check_win('O'):
                self.print_board()
                print("Sorry, you lose.")
                break

if __name__ == "__main__":
    game = TicTacToe()
    game.play()
