class TicTacToe:
    def __init__(self):
        self.board = [['-' for _ in range(3)] for _ in range(3)]
        self.currentPlayer = 'X'

    def initializeBoard(self):
        for i in range(3):
            for j in range(3):
                self.board[i][j] = '-'

    def printBoard(self):
        for i in range(3):
            for j in range(3):
                print(self.board[i][j], end=' ')
            print()
        print()

    def isValidMove(self, row, col):
        if row < 0 or row > 2 or col < 0 or col > 2:
            return False
        if self.board[row][col] != '-':
            return False
        return True

    def makeMove(self, row, col):
        self.board[row][col] = self.currentPlayer

    def switchPlayer(self):
        if self.currentPlayer == 'X':
            self.currentPlayer = 'O'
        else:
            self.currentPlayer = 'X'

    def checkForWin(self):
        # check for a horizontal win
        for row in range(3):
            if self.board[row][0] != '-' and self.board[row][0] == self.board[row][1] == self.board[row][2]:
                return True

        # check for a vertical win
        for col in range(3):
            if self.board[0][col] != '-' and self.board[0][col] == self.board[1][col] == self.board[2][col]:
                return True

        # check for a diagonal win (top-left to bottom-right)
        if self.board[0][0] != '-' and self.board[0][0] == self.board[1][1] == self.board[2][2]:
            return True

        # check for a diagonal win (bottom-left to top-right)
        if self.board[2][0] != '-' and self.board[2][0] == self.board[1][1] == self.board[0][2]:
            return True

        # no win found
        return False

    def checkForDraw(self):
        for row in range(3):
            for col in range(3):
                if self.board[row][col] == '-':
                    return False
        return True

    def play(self):
        input_func = input
        gameOver = False
        while not gameOver:
            self.printBoard()
            move = input_func("Player {}, please enter your move (row, col): ".format(self.currentPlayer))
            try:
                row, col = map(int, move.split(','))
                if not self.isValidMove(row, col):
                    print("Invalid move. Please try again.")
                    continue
                self.makeMove(row, col)
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
