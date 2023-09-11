class TicTacToe:
    def __init__(self):
        self.board = ['-' for _ in range(9)]
        self.currentPlayer = 'X'

    def printBoard(self):
        for i in range(0, 9, 3):
            print(self.board[i], self.board[i+1], self.board[i+2])
        print()

    def isValidMove(self, position):
        if position < 0 or position >= 9:
            return False
        if self.board[position] != '-':
            return False
        return True

    def makeMove(self, position):
        self.board[position] = self.currentPlayer

    def switchPlayer(self):
        if self.currentPlayer == 'X':
            self.currentPlayer = 'O'
        else:
            self.currentPlayer = 'X'

    def checkForWin(self):
        # check for a horizontal win
        for row in range(0, 9, 3):
            if self.board[row] != '-' and self.board[row] == self.board[row+1] == self.board[row+2]:
                return True
        # check for a vertical win
        for col in range(3):
            if self.board[col] != '-' and self.board[col] == self.board[col+3] == self.board[col+6]:
                return True

        # check for a diagonal win (top-left to bottom-right)
        if self.board[0] != '-' and self.board[0] == self.board[4] == self.board[8]:
            return True

        # check for a diagonal win (bottom-left to top-right)
        if self.board[2] != '-' and self.board[2] == self.board[4] == self.board[6]:
            return True

        # no win found
        return False

    def checkForDraw(self):
        # check if the board is full
        for i in range(9):
            if self.board[i] == '-':
                return False
        # board is full, game is a draw
        return True

    def play(self):
        input_func = input
        gameOver = False
        while not gameOver:
            self.printBoard()
            position = input_func("Player {}, please enter your move (1-9): ".format(self.currentPlayer))
            try:
                position = int(position) - 1
                if not self.isValidMove(position):
                    print("Invalid move. Please try again.")
                    continue
                self.makeMove(position)
                if self.checkForWin():
                    print("Player", self.currentPlayer, "wins!")
                    gameOver = True
                elif self.checkForDraw():
                    print("Game over. It's a draw.")
                    gameOver = True
                else:
                    self.switchPlayer()
            except ValueError:
                print("Invalid input")
        self.printBoard()

if __name__ == '__main__':
    game = TicTacToe()
    game.play()
