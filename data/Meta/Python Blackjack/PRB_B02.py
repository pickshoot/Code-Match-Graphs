import random

# Define card values
card_values = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10, 'A': 11}

# Define a function to calculate the value of a hand
def hand_value(hand):
    value = sum(card_values[card] for card in hand)
    # Check for aces
    if 'A' in hand and value > 21:
        value -= 10
    return value

# Define a function to deal cards
def deal_cards():
    deck = list(card_values.keys()) * 4
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    return deck, player_hand, dealer_hand

# Define a function to display hands
def show_hands(player_hand, dealer_hand, show_dealer):
    print("Player's hand:", player_hand)
    if show_dealer:
        print("Dealer's hand:", dealer_hand)
    else:
        print("Dealer's hand:", [dealer_hand[0], 'X'])

# Define a function to play the game
def play_game():
    print("Welcome to Blackjack!\n")
    # Set up initial variables
    balance = 100
    bet = 0
    game_over = False
    while not game_over:
        # Ask the player for their bet
        print(f"Your current balance is {balance}")
        while True:
            try:
                bet = int(input("How much would you like to bet? "))
                if bet <= balance:
                    break
                else:
                    print("Sorry, you don't have enough balance. Please try again.")
            except ValueError:
                print("Invalid input. Please enter a number.")
        # Deal the cards
        deck, player_hand, dealer_hand = deal_cards()
        # Show the hands
        show_hands(player_hand, dealer_hand, False)
        # Check for natural blackjack
        if hand_value(player_hand) == 21:
            print("Congratulations! You got a natural blackjack!")
            balance += int(bet * 1.5)
        else:
            # Ask the player to hit or stand
            while True:
                hit_or_stand = input("Would you like to hit or stand? ")
                if hit_or_stand.lower() == 'hit':
                    player_hand.append(deck.pop())
                    show_hands(player_hand, dealer_hand, False)
                    if hand_value(player_hand) > 21:
                        print("Sorry, you busted!")
                        balance -= bet
                        game_over = True
                        break
                elif hit_or_stand.lower() == 'stand':
                    break
                else:
                    print("Invalid input. Please enter 'hit' or 'stand'.")
            # Play the dealer's hand
            if not game_over:
                show_hands(player_hand, dealer_hand, True)
                while hand_value(dealer_hand) < 17:
                    dealer_hand.append(deck.pop())
                    show_hands(player_hand, dealer_hand, True)
                if hand_value(dealer_hand) > 21:
                    print("Dealer busts! You win!")
                    balance += bet
                elif hand_value(player_hand) > hand_value(dealer_hand):
                    print("Congratulations! You win!")
                    balance += bet
                elif hand_value(player_hand) == hand_value(dealer_hand):
                    print("It's a tie!")
                else:
                    print("Sorry, you lose.")
                    balance -= bet
        # Check if the player wants to play again
        if balance == 0:
            print("Sorry, you don't have any balance left.")
            game_over = True
        else:
            while True:
                play_again = input("Would you like to play again? (y/n) ")
                if play_again.lower() == 'y':
                    break
                elif play_again.lower() == 'n':
                    game_over = True
                    break
                else:
                    print("Invalid input. Please enter 'y' or 'n'.")
    print(f"Thanks for playing! Your final balance is {balance}.")

play_game()
