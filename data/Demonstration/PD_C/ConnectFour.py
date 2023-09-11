class ConnectFour:
    def __init__(self, numRows, numCols):
        self.numRows = numRows
        self.numCols = numCols
        self.board = [['-' for _ in range(numCols)] for _ in range(numRows)]
        self.currentPlayer = 'X'

    def initializeBoard(self):
        for i in range(self.numRows):
            for j in range(self.numCols):
                self.board[i][j] = '-'

    def printBoard(self):
        for i in range(self.numRows - 1, -1, -1):
            for j in range(self.numCols):
                print(self.board[i][j], end=' ')
            print()
        print()

    def isValidMove(self, col):
        if col < 0 or col >= self.numCols:
            return False
        if self.board[self.numRows - 1][col] != '-':
            return False
        return True

    def makeMove(self, col):
        for i in range(self.numRows):
            if self.board[i][col] == '-':
                self.board[i][col] = self.currentPlayer
                break

    def switchPlayer(self):
        if self.currentPlayer == 'X':
            self.currentPlayer = 'O'
        else:
            self.currentPlayer = 'X'

    def checkForWin(self):
        # check for a horizontal win
        for row in range(self.numRows):
            for col in range(self.numCols - 3):
                if self.board[row][col] != '-' and self.board[row][col] == self.board[row][col + 1] == self.board[row][col + 2] == self.board[row][col + 3]:
                    return True

        # check for a vertical win
        for row in range(self.numRows - 3):
            for col in range(self.numCols):
                if self.board[row][col] != '-' and self.board[row][col] == self.board[row + 1][col] == self.board[row + 2][col] == self.board[row + 3][col]:
                    return True

        # check for a diagonal win (top-left to bottom-right)
        for row in range(self.numRows - 3):
            for col in range(self.numCols - 3):
                if self.board[row][col] != '-' and self.board[row][col] == self.board[row + 1][col + 1] == self.board[row + 2][col + 2] == self.board[row + 3][col + 3]:
                    return True

        # check for a diagonal win (bottom-left to top-right)
        for row in range(3, self.numRows):
            for col in range(self.numCols - 3):
                if self.board[row][col] != '-' and self.board[row][col] == self.board[row - 1][col + 1] == self.board[row - 2][col + 2] == self.board[row - 3][col + 3]:
                    return True

        # no win found
        return False


    def checkForDraw(self):
        # check if the board is full
        for row in range(self.numRows):
            for col in range(self.numCols):
                if self.board[row][col] == '-':
                    return False

        # board is full, game is a draw
        return True


    def play(self):
        input_func = input
        gameOver = False
        while not gameOver:
            self.printBoard()
            col = input_func("Player {}, please enter your move (0-{}): ".format(self.currentPlayer, self.numCols - 1))
            try:
                col = int(col)
                if not self.isValidMove(col):
                    print("Invalid move. Please try again.")
                    continue
                self.makeMove(col)
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
    game = ConnectFour(6, 7)
    game.play()
