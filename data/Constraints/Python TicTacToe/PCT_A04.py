class TicTacToe:
    def __init__(self):
        self.board = [' '] * 9
        self.current_player = 'X'

    def display_board(self):
        print("-------------")
        print("| {} | {} | {} |".format(self.board[0], self.board[1], self.board[2]))
        print("-------------")
        print("| {} | {} | {} |".format(self.board[3], self.board[4], self.board[5]))
        print("-------------")
        print("| {} | {} | {} |".format(self.board[6], self.board[7], self.board[8]))
        print("-------------")

    def make_move(self, position):
        self.board[position] = self.current_player
        if self.current_player == 'X':
            self.current_player = 'O'
        else:
            self.current_player = 'X'

    def is_valid_move(self, position):
        if position < 0 or position > 8:
            return False
        elif self.board[position] != ' ':
            return False
        else:
            return True

    def check_for_winner(self):
        win_conditions = [
            (0, 1, 2), (3, 4, 5), (6, 7, 8),  # rows
            (0, 3, 6), (1, 4, 7), (2, 5, 8),  # columns
            (0, 4, 8), (2, 4, 6)  # diagonals
        ]
        for condition in win_conditions:
            if self.board[condition[0]] == self.board[condition[1]] == self.board[condition[2]] != ' ':
                return self.board[condition[0]]
        if ' ' not in self.board:
            return 'Tie'
        return None

    def play_game(self):
        print("Welcome to Tic-Tac-Toe!")
        print("Player 1 is X, Player 2 is O.")
        while True:
            self.display_board()
            position = int(input("Enter position (0-8): "))
            if self.is_valid_move(position):
                self.make_move(position)
                winner = self.check_for_winner()
                if winner:
                    self.display_board()
                    if winner == 'Tie':
                        print("Tie game.")
                    else:
                        print("{} wins!".format(winner))
                    break
            else:
                print("Invalid move. Try again.")

game = TicTacToe()
game.play_game()