import random

# Define the card deck
deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11] * 4

# Define a function to deal the cards
def deal_cards():
    # Shuffle the deck
    random.shuffle(deck)
    # Deal two cards to the player and two cards to the computer
    player_hand = [deck.pop(), deck.pop()]
    computer_hand = [deck.pop(), deck.pop()]
    return player_hand, computer_hand

# Define a function to calculate the value of a hand
def calculate_hand(hand):
    # Initialize the total value of the hand
    total = sum(hand)
    # Check for aces and adjust the total value accordingly
    if 11 in hand and total > 21:
        total -= 10
    # Return the total value of the hand
    return total

# Define a function for the player's turn
def player_turn(player_hand, computer_hand):
    while True:
        # Display the player's hand and ask for their decision
        print("Your hand:", player_hand, "Total value:", calculate_hand(player_hand))
        decision = input("Do you want to hit or stand? ")
        # If the player chooses to hit, deal another card and add it to their hand
        if decision.lower() == "hit":
            player_hand.append(deck.pop())
            # If the player's hand is worth more than 21, they lose
            if calculate_hand(player_hand) > 21:
                print("Bust! You lose.")
                return "computer"
        # If the player chooses to stand, break out of the loop
        elif decision.lower() == "stand":
            break
    # If the player's hand is worth less than or equal to 21, return None to indicate that the game should continue
    return None

# Define a function for the computer's turn
def computer_turn(computer_hand):
    while calculate_hand(computer_hand) < 17:
        # If the computer's hand is worth less than 17, deal another card and add it to their hand
        computer_hand.append(deck.pop())
    # If the computer's hand is worth more than 21, the player wins
    if calculate_hand(computer_hand) > 21:
        print("Computer's hand:", computer_hand, "Total value:", calculate_hand(computer_hand))
        print("Computer busts! You win.")
        return "player"
    # If the computer's hand is worth 17 or more, return None to indicate that the game should continue
    return None

# Define a function to play the game
def play_game():
    # Deal the cards
    player_hand, computer_hand = deal_cards()
    # Show the initial hands
    print("Your hand:", player_hand, "Total value:", calculate_hand(player_hand))
    print("Computer's hand:", [computer_hand[0], "*"])
    # Play the player's turn
    result = player_turn(player_hand, computer_hand)
    if result:
        return result
    # Play the computer's turn
    result = computer_turn(computer_hand)
    if result:
        return result
    # Determine the winner
    player_total = calculate_hand(player_hand)
    computer_total = calculate_hand(computer_hand)
    print("Your hand:", player_hand, "Total value:", player_total)
    print("Computer's hand:", computer_hand, "Total value:", computer_total)
    if player_total > computer_total:
        print("You win!")
        return "player"
    elif player_total < computer_total:
        print("You lose.")
        return "computer"
    else:
        print("Push.")
        return "push"

# Define a function to ask the player if they want to play again
def play_again():
    while True:
        decision = input("Do you want to play again? (y/n) ")
        if decision.lower() == "y":
            return True
        elif decision.lower() == "n":
            return False
        else:
            print("Invalid input. Please enter 'y' or 'n'.")

# Play the game
while True:
    result = play_game()
    if result == "push":
        print("It's a tie!")
    if not play_again():
        break
