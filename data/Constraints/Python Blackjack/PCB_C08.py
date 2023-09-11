import random

def create_deck():
    """Create a deck of 52 cards"""
    suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
    ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
    deck = []
    for suit in suits:
        for rank in ranks:
            deck.append(rank + ' of ' + suit)
    random.shuffle(deck)
    return deck

def deal_cards(deck, num_cards):
    """Deal a specified number of cards from the deck"""
    hand = []
    for i in range(num_cards):
        card = deck.pop()
        hand.append(card)
    return hand

def get_card_value(card):
    """Get the numeric value of a card"""
    if card.startswith('Ace'):
        return 11
    elif card.startswith('2'):
        return 2
    elif card.startswith('3'):
        return 3
    elif card.startswith('4'):
        return 4
    elif card.startswith('5'):
        return 5
    elif card.startswith('6'):
        return 6
    elif card.startswith('7'):
        return 7
    elif card.startswith('8'):
        return 8
    elif card.startswith('9'):
        return 9
    else:
        return 10

def get_hand_value(hand):
    """Get the total value of a hand"""
    value = 0
    num_aces = 0
    for card in hand:
        value += get_card_value(card)
        if card.startswith('Ace'):
            num_aces += 1
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

def print_hand(hand, hide_first_card=False):
    """Print a hand of cards"""
    if hide_first_card:
        print('Hidden card')
        print(hand[1])
    else:
        for card in hand:
            print(card)

def blackjack():
    """Play a game of blackjack"""
    print('Welcome to Blackjack!\n')
    deck = create_deck()
    player_hand = deal_cards(deck, 2)
    dealer_hand = deal_cards(deck, 2)
    print('Your hand:')
    print_hand(player_hand)
    print('Dealer showing:')
    print(dealer_hand[0])

    # Player turn
    while True:
        choice = input('\nDo you want to hit or stand? ')
        if choice.lower() == 'hit':
            player_hand += deal_cards(deck, 1)
            print('Your hand:')
            print_hand(player_hand)
            if get_hand_value(player_hand) > 21:
                print('Bust! You lose.')
                return
        elif choice.lower() == 'stand':
            break
        else:
            print('Invalid choice. Please enter hit or stand.')

    # Dealer turn
    print('\nDealer turn:')
    print_hand(dealer_hand)
    while get_hand_value(dealer_hand) < 17:
        dealer_hand += deal_cards(deck, 1)
        print('Dealer hits:')
        print_hand(dealer_hand)

    # Determine winner
    player_value = get_hand_value(player_hand)
    dealer_value = get_hand_value(dealer_hand)
    print('\nPlayer hand value:', player_value)
    print('Dealer hand value:', dealer_value)
    if player_value > 21:
        print('Bust! You lose.')
    elif dealer_value > 21:
        print('Dealer busts! You win.')
    elif player_value > dealer_value:
        print('You win!')
    elif dealer_value > player_value:
        print('Dealer wins.')
    else:
        print('It\'s a tie.')

blackjack()