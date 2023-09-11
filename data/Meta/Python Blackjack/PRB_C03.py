import random

# Define the deck of cards
deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4

# Define the value of each card
card_values = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10, 'A': 11}

# Define the function to shuffle the deck
def shuffle_deck(deck):
    random.shuffle(deck)

# Define the function to deal a card
def deal_card(deck):
    return deck.pop(0)

# Define the function to calculate the value of a hand
def calculate_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        value += card_values[card]
        if card == 'A':
            num_aces += 1
    while value > 21 and num_aces:
        value -= 10
        num_aces -= 1
    return value

# Define the function to ask the user for their name and bankroll
def get_user_info():
    name = input("What's your name? ")
    bankroll = int(input("How much money do you want to start with? "))
    return name, bankroll

# Define the function to ask the user how many decks to use
def get_num_decks():
    num_decks = int(input("How many decks do you want to use? "))
    return num_decks

# Define the function to ask the user for their bet
def get_bet(bankroll):
    while True:
        bet = int(input(f"You have ${bankroll}. What's your bet? "))
        if bet <= bankroll:
            return bet
        else:
            print("Sorry, you don't have enough money for that bet.")

# Define the function to play a hand
def play_hand(deck, bankroll, bet):
    # Initialize the hands and the deck
    user_hand = [deal_card(deck), deal_card(deck)]
    computer_hand = [deal_card(deck), deal_card(deck)]

    # Initialize the flags for user input and game end
    user_input = ''
    game_end = False

    # Play the user's turn
    while user_input != 'stand':
        # Show the user's hand and the computer's up card
        print(f"Your hand: {user_hand}")
        print(f"Computer's up card: {computer_hand[0]}")

        # Ask the user to hit or stand
        user_input = input("Do you want to hit or stand? ")
        if user_input == 'hit':
            # Deal another card to the user
            user_hand.append(deal_card(deck))

            # Check if the user has gone over 21
            if calculate_hand_value(user_hand) > 21:
                print("You busted!")
                game_end = True
                break

    # Play the computer's turn
    if not game_end:
        while calculate_hand_value(computer_hand) < 17:
            # Deal another card to the computer
            computer_hand.append(deal_card(deck))

        # Determine the winner and adjust the bankroll accordingly
        user_value = calculate_hand_value(user_hand)
        computer_value = calculate_hand_value(computer_hand)
        print(f"Your hand: {user_hand}")
        print(f"Computer's hand: {computer_hand}")
        if user_value > computer_value or computer_value > 21:
            print("You win!")
            bankroll += bet
        elif user_value < computer_value:
            print("You lose!")
            bankroll -= bet
        else:
            print("It's a tie!")

    # Return the updated bankroll
    return bankroll

# Define the main function
def main():
    # Get the user's name and bankroll
    name, bankroll = get_user_info()
    print(f"Welcome to Blackjack, {name}! Let's get started.")

    # Play the game until the user quits or runs out of money
    while True:
        # Ask the user how many decks to use and shuffle the deck
        num_decks = get_num_decks()
        dek = deck * num_decks
        shuffle_deck(deck)

        # Ask the user for their bet and play the hand
        bet = get_bet(bankroll)
        bankroll = play_hand(deck, bankroll, bet)
        print(f"You now have ${bankroll}.")

        # Ask the user if they want to play again
        if bankroll <= 0:
            print("Sorry, you're out of money.")
            break
        play_again = input("Do you want to play another hand? ")
        if play_again.lower() != 'yes':
            break

    # Say goodbye to the user
    print(f"Thanks for playing, {name}! Goodbye.")

# Run the main function
if __name__ == '__main__':
    main()

