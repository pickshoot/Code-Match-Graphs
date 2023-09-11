import random

# Define the values of the cards
card_values = {'Ace': 11, 'King': 10, 'Queen': 10, 'Jack': 10,
               '10': 10, '9': 9, '8': 8, '7': 7, '6': 6,
               '5': 5, '4': 4, '3': 3, '2': 2}

# Define a function to get a random card from the deck
def get_card(deck):
    card = deck.pop()
    return card

# Define a function to get the value of a hand
def get_hand_value(hand):
    value = sum(card_values[card] for card in hand)
    if value > 21 and 'Ace' in hand:
        value -= 10
    return value

# Define a function to play the game
def play_blackjack():
    # Create a deck of cards
    deck = list(card_values.keys()) * 4
    random.shuffle(deck)

    # Deal the initial hands
    player_hand = [get_card(deck), get_card(deck)]
    dealer_hand = [get_card(deck), get_card(deck)]

    # Show the hands
    print("Dealer's hand:", dealer_hand[0])
    print("Your hand:", player_hand)

    # Player's turn
    while True:
        choice = input("Do you want to hit or stand? ")
        if choice.lower() == 'hit':
            player_hand.append(get_card(deck))
            print("Your hand:", player_hand)
            if get_hand_value(player_hand) > 21:
                print("Bust! You lose.")
                return
        elif choice.lower() == 'stand':
            break
        else:
            print("Invalid choice. Please try again.")

    # Dealer's turn
    while get_hand_value(dealer_hand) < 17:
        dealer_hand.append(get_card(deck))
    print("Dealer's hand:", dealer_hand)

    # Determine the winner
    player_value = get_hand_value(player_hand)
    dealer_value = get_hand_value(dealer_hand)
    if player_value > 21:
        print("Bust! You lose.")
    elif dealer_value > 21:
        print("Dealer busts! You win.")
    elif player_value > dealer_value:
        print("You win!")
    elif dealer_value > player_value:
        print("Dealer wins.")
    else:
        print("It's a tie.")

# Play the game
play_blackjack()