# Define a class for the Tic-Tac-Toe game
class TicTacToe:
    
    # Initialize the game board and player turn
    def __init__(self):
        self.board = [' ']*9
        self.player = 'X'
    
    # Draw the game board
    def draw_board(self):
        print('   |   |')
        print(' ' + self.board[0] + ' | ' + self.board[1] + ' | ' + self.board[2])
        print('   |   |')
        print('-----------')
        print('   |   |')
        print(' ' + self.board[3] + ' | ' + self.board[4] + ' | ' + self.board[5])
        print('   |   |')
        print('-----------')
        print('   |   |')
        print(' ' + self.board[6] + ' | ' + self.board[7] + ' | ' + self.board[8])
        print('   |   |')
    
    # Check if the game has ended
    def is_game_over(self):
        # Check for horizontal wins
        for i in range(0, 9, 3):
            if self.board[i] == self.board[i+1] == self.board[i+2] != ' ':
                return True
        # Check for vertical wins
        for i in range(3):
            if self.board[i] == self.board[i+3] == self.board[i+6] != ' ':
                return True
        # Check for diagonal wins
        if self.board[0] == self.board[4] == self.board[8] != ' ':
            return True
        if self.board[2] == self.board[4] == self.board[6] != ' ':
            return True
        # Check for tie game
        if ' ' not in self.board:
            return True
        return False
    
    # Play a turn of the game
    def play_turn(self):
        # Print the game board
        self.draw_board()
        # Get the player's move
        while True:
            move = input('Player ' + self.player + ', enter a move (1-9): ')
            if move.isdigit() and int(move) in range(1, 10) and self.board[int(move)-1] == ' ':
                break
            else:
                print('Invalid move. Please try again.')
        # Update the game board
        self.board[int(move)-1] = self.player
        # Switch the player turn
        if self.player == 'X':
            self.player = 'O'
        else:
            self.player = 'X'
    
    # Start the game
    def start_game(self):
        while not self.is_game_over():
            self.play_turn()
        self.draw_board()
        if ' ' not in self.board:
            print('Tie game!')
        else:
            print('Player ' + self.player + ' wins!')

game = TicTacToe()
game.start_game()