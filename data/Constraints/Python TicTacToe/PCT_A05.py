class Board:
    def __init__(self):
        self.board = [" " for _ in range(9)]

    def __str__(self):
        return f"{self.board[0]} | {self.board[1]} | {self.board[2]}\n---------\n{self.board[3]} | {self.board[4]} | {self.board[5]}\n---------\n{self.board[6]} | {self.board[7]} | {self.board[8]}"

    def is_full(self):
        return " " not in self.board

    def is_winner(self, player):
        return ((self.board[0] == player and self.board[1] == player and self.board[2] == player) or
                (self.board[3] == player and self.board[4] == player and self.board[5] == player) or
                (self.board[6] == player and self.board[7] == player and self.board[8] == player) or
                (self.board[0] == player and self.board[3] == player and self.board[6] == player) or
                (self.board[1] == player and self.board[4] == player and self.board[7] == player) or
                (self.board[2] == player and self.board[5] == player and self.board[8] == player) or
                (self.board[0] == player and self.board[4] == player and self.board[8] == player) or
                (self.board[2] == player and self.board[4] == player and self.board[6] == player))

    def place(self, position, player):
        self.board[position] = player


class Game:
    def __init__(self):
        self.board = Board()
        self.current_player = "X"

    def switch_player(self):
        if self.current_player == "X":
            self.current_player = "O"
        else:
            self.current_player = "X"

    def get_move(self):
        while True:
            try:
                move = int(input(f"Player {self.current_player}, enter a position (0-8): "))
                if self.board.board[move] == " ":
                    return move
                else:
                    print("That position is already taken.")
            except ValueError:
                print("Invalid input.")
            except IndexError:
                print("Position out of range.")

    def play(self):
        while not self.board.is_full():
            print(self.board)
            move = self.get_move()
            self.board.place(move, self.current_player)
            if self.board.is_winner(self.current_player):
                print(self.board)
                print(f"Player {self.current_player} wins!")
                return
            self.switch_player()
        print(self.board)
        print("It's a tie!")

game = Game()
game.play()