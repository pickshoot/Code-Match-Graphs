import itertools

def win(current_game):
    def all_same(l):
        if l.count(l[0]) == len(l) and l[0] != 0:
            return True
        else:
            return False

    # Check rows
    for row in current_game:
        if all_same(row):
            print(f"Player {row[0]} is the winner horizontally!")
            return True

    # Check columns
    for col in range(len(current_game[0])):
        check = []
        for row in current_game:
            check.append(row[col])
        if all_same(check):
            print(f"Player {check[0]} is the winner vertically!")
            return True

    # Check diagonal from top left to bottom right
    diag = []
    for ix in range(len(current_game)):
        diag.append(current_game[ix][ix])
    if all_same(diag):
        print(f"Player {diag[0]} is the winner diagonally (\\)!")
        return True

    # Check diagonal from bottom left to top right
    diag = []
    for col, row in enumerate(reversed(range(len(current_game)))):
        diag.append(current_game[row][col])
    if all_same(diag):
        print(f"Player {diag[0]} is the winner diagonally (/)!")
        return True

    return False

def game_board(game_map, player=0, row=0, column=0, just_display=False):
    try:
        if game_map[row][column] != 0:
            print("This position is already occupied! Choose another!")
            return game_map, False
        print("   "+"  ".join([str(i) for i in range(len(game_map))]))
        if not just_display:
            game_map[row][column] = player
        for count, row in enumerate(game_map):
            print(count, row)
        return game_map, True

    except IndexError as e:
        print("Error: make sure you input row/column as 0, 1 or 2?", e)
        return game_map, False

    except Exception as e:
        print("Something went very wrong!", e)
        return game_map, False

play = True
players = [1, 2]
while play:
    game_size = int(input("What size game of tic tac toe? "))
    game = [[0 for i in range(game_size)] for j in range(game_size)]

    game_won = False
    game, _ = game_board(game, just_display=True)
    player_choice = itertools.cycle([1, 2])
    while not game_won:
        current_player = next(player_choice)
        print(f"Current Player: {current_player}")
        played = False

        while not played:
            column_choice = int(input("What column do you want to play? (0, 1, 2): "))
            row_choice = int(input("What row do you want to play? (0, 1, 2): "))
            game, played = game_board(game, current_player, row_choice, column_choice)

        if win(game):
            game_won = True
            again = input("Do you want to play again? (y/n) ")
            if again.lower() == "y":
                print("restarting...")
            elif again.lower() == "n":
                print("Bye!")
                play = False
            else:
                print("Not a valid answer, Bye!")
                play = False