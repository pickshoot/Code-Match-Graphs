import random

# Define a function to deal a new card
def deal_card():
    """Returns a random card from the deck"""
    cards = [2,3,4,5,6,7,8,9,10,'J','Q','K','A']
    return random.choice(cards)

# Define a function to calculate the value of a hand
def calculate_hand(hand):
    """Calculates the value of a hand"""
    total = 0
    num_aces = 0
    for card in hand:
        if card == 'A':
            num_aces += 1
            total += 11
        elif card in ['K','Q','J']:
            total += 10
        else:
            total += card
    while total > 21 and num_aces:
        total -= 10
        num_aces -= 1
    return total

# Define a function to display the player's hand
def display_player_hand(hand):
    """Displays the player's hand"""
    print("Your cards are: ", end="")
    for card in hand:
        print(card, end=" ")
    print("")

# Define a function to display the computer's hand
def display_computer_hand(hand, hidden=True):
    """Displays the computer's hand"""
    if hidden:
        print("The computer's cards are: ", end="")
        print(hand[0], " * ")
    else:
        print("The computer's cards are: ", end="")
        for card in hand:
            print(card, end=" ")
        print("")

# Define a function to play the game
def play_game():
    """Plays the game"""
    print("Welcome to Blackjack!")
    play_again = True
    while play_again:
        # Initialize the deck and hands
        deck = []
        player_hand = []
        computer_hand = []
        for i in range(4):
            deck.append(deal_card())
        player_hand.append(deck.pop())
        player_hand.append(deck.pop())
        computer_hand.append(deck.pop())
        computer_hand.append(deck.pop())

        # Display the initial hands
        display_player_hand(player_hand)
        display_computer_hand(computer_hand)

        # Player's turn
        while True:
            choice = input("Do you want to hit or stand? ").lower()
            if choice == 'hit':
                player_hand.append(deal_card())
                display_player_hand(player_hand)
                if calculate_hand(player_hand) > 21:
                    print("Bust! You lose.")
                    play_again = input("Do you want to play again? (y/n) ").lower() == 'y'
                    break
            elif choice == 'stand':
                break

        # Computer's turn
        if play_again:
            while calculate_hand(computer_hand) < 17:
                computer_hand.append(deal_card())
                display_computer_hand(computer_hand, hidden=False)

            # Check for winner
            player_score = calculate_hand(player_hand)
            computer_score = calculate_hand(computer_hand)
            if player_score > 21:
                print("Bust! You lose.")
            elif computer_score > 21:
                print("The computer busts! You win.")
            elif player_score == computer_score:
                print("Push.")
            elif player_score > computer_score:
                print("You win!")
            else:
                print("You lose.")
            play_again = input("Do you want to play again? (y/n) ").lower() == 'y'

# Start the game
play_game()
