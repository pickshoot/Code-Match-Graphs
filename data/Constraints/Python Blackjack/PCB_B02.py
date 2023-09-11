import random

# Define constants for card values
CARD_VALUES = {
    'Ace': 1,
    '2': 2,
    '3': 3,
    '4': 4,
    '5': 5,
    '6': 6,
    '7': 7,
    '8': 8,
    '9': 9,
    '10': 10,
    'Jack': 10,
    'Queen': 10,
    'King': 10
}

# Define constants for card suits
CARD_SUITS = ['Clubs', 'Diamonds', 'Hearts', 'Spades']


# Define a function to create a deck of cards
def create_deck():
    deck = []
    for suit in CARD_SUITS:
        for value in CARD_VALUES:
            card = value + ' of ' + suit
            deck.append(card)
    random.shuffle(deck)
    return deck


# Define a function to deal a card from the deck
def deal_card(deck):
    return deck.pop(0)


# Define a function to calculate the value of a hand
def calculate_hand(hand):
    value = 0
    num_aces = 0
    for card in hand:
        value += CARD_VALUES[card.split()[0]]
        if card.split()[0] == 'Ace':
            num_aces += 1
    while value <= 11 and num_aces > 0:
        value += 10
        num_aces -= 1
    return value


# Define a function to play a game of blackjack
def play_blackjack():
    # Initialize the deck and deal the initial hands
    deck = create_deck()
    player_hand = [deal_card(deck), deal_card(deck)]
    dealer_hand = [deal_card(deck), deal_card(deck)]

    # Play the player's turn
    while True:
        print('Player hand:', player_hand)
        player_hand_value = calculate_hand(player_hand)
        print('Player hand value:', player_hand_value)
        if player_hand_value > 21:
            print('Bust! You lose.')
            return
        elif player_hand_value == 21:
            print('Blackjack! You win!')
            return
        else:
            choice = input('Do you want to hit or stand? ')
            if choice.lower() == 'hit':
                player_hand.append(deal_card(deck))
            elif choice.lower() == 'stand':
                break
            else:
                print('Invalid choice. Please enter hit or stand.')

    # Play the dealer's turn
    while True:
        print('Dealer hand:', dealer_hand)
        dealer_hand_value = calculate_hand(dealer_hand)
        print('Dealer hand value:', dealer_hand_value)
        if dealer_hand_value > 21:
            print('Dealer bust! You win!')
            return
        elif dealer_hand_value >= 17:
            break
        else:
            dealer_hand.append(deal_card(deck))

    # Compare the hands and determine the winner
    player_hand_value = calculate_hand(player_hand)
    dealer_hand_value = calculate_hand(dealer_hand)
    print('Player hand:', player_hand)
    print('Player hand value:', player_hand_value)
    print('Dealer hand:', dealer_hand)
    print('Dealer hand value:', dealer_hand_value)
    if player_hand_value > dealer_hand_value:
        print('You win!')
    elif player_hand_value < dealer_hand_value:
        print('You lose.')
    else:
        print('It\'s a tie.')

# Define a function to start the game
def start_game():
    while True:
        choice = input('Do you want to play a game of Blackjack? (y/n) ')
        if choice.lower() == 'y':
            play_blackjack()
        elif choice.lower() == 'n':
            print('Thanks for playing!')
            return
        else:
            print('Invalid choice. Please enter y or n.')

# Call the start_game function to start the game
start_game()