import random

# Define card values
card_values = {
    '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10,
    'J': 10, 'Q': 10, 'K': 10, 'A': 11
}

# Define a function to create a deck of cards
def create_deck(num_decks):
    deck = []
    for i in range(num_decks):
        for card in card_values:
            deck.append(card)
    random.shuffle(deck)
    return deck

# Define a function to deal a card
def deal_card(deck):
    return deck.pop(0)

# Define a function to calculate the value of a hand
def calculate_hand_value(hand):
    value = 0
    for card in hand:
        value += card_values[card]
    # If the hand contains an Ace and the value is greater than 21, count the Ace as 1 instead of 11
    if 'A' in hand and value > 21:
        value -= 10
    return value

# Define the main function for the game
def play_blackjack():
    # Prompt the user for their name
    name = input("What is your name? ")
    print(f"Welcome to the game, {name}!")

    # Prompt the user for the number of decks to use
    num_decks = int(input("How many decks would you like to play with? "))
    deck = create_deck(num_decks)

    # Initialize the user's bankroll
    bankroll = 100

    # Loop through the game until the user decides to quit or runs out of money
    while bankroll > 0:
        print(f"\nYour bankroll is {bankroll}")
        # Prompt the user for their bet
        bet = int(input("Enter your bet: "))

        # Deal the initial cards
        user_hand = [deal_card(deck), deal_card(deck)]
        computer_hand = [deal_card(deck), deal_card(deck)]

        # Show the user's hand and the computer's face up card
        print(f"\nYour hand: {user_hand}")
        print(f"Dealer's face up card: {computer_hand[0]}")

        # Prompt the user to hit or stand
        while True:
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                user_hand.append(deal_card(deck))
                print(f"\nYour hand: {user_hand}")
                if calculate_hand_value(user_hand) > 21:
                    print("Bust! You lose.")
                    bankroll -= bet
                    break
            elif action.lower() == 'stand':
                break
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

        # Computer's turn
        while calculate_hand_value(computer_hand) < 17:
            computer_hand.append(deal_card(deck))
        print(f"Dealer's hand: {computer_hand}")

        # Determine the winner
        user_value = calculate_hand_value(user_hand)
        computer_value = calculate_hand_value(computer_hand)
        if user_value > 21:
            print("You busted. Dealer wins.")
            bankroll -= bet
        elif computer_value > 21:
            print("Dealer busted. You win!")
            bankroll += bet
        elif user_value > computer_value:
            print("You win!")
            bankroll += bet
        elif user_value < computer_value:
            print("Dealer wins.")
            bankroll -= bet
        else:
            print("It's a tie.")

    # Game over
    print("Game over. You are out of money.")

play_blackjack()
