import random

# create a deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = []
for suit in suits:
    for rank in ranks:
        deck.append(f'{rank} of {suit}')

# define a function to calculate the value of a hand
def calculate_hand(hand):
    values = {
        'Ace': 11,
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
    value = 0
    num_aces = 0
    for card in hand:
        rank = card.split()[0]
        if rank == 'Ace':
            num_aces += 1
        value += values[rank]
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

# define the main game function
def play_game():
    # shuffle the deck and deal two cards to the player and dealer
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]

    # show the player's hand and the dealer's first card
    print(f'Your hand: {", ".join(player_hand)}')
    print(f'Dealer\'s hand: {dealer_hand[0]} and one hidden card')

    # ask the player to hit or stand
    while True:
        action = input('Do you want to hit or stand? ').lower()
        if action not in ('hit', 'stand'):
            print('Invalid input. Please enter "hit" or "stand".')
            continue
        if action == 'hit':
            player_hand.append(deck.pop())
            print(f'Your hand: {", ".join(player_hand)}')
            if calculate_hand(player_hand) > 21:
                print('Bust! You lose.')
                return
        else:
            break

    # dealer's turn
    print(f'Dealer\'s hand: {", ".join(dealer_hand)}')
    while calculate_hand(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        print(f'Dealer hits and gets the {dealer_hand[-1]}')
        if calculate_hand(dealer_hand) > 21:
            print('Dealer busts! You win.')
            return
    dealer_value = calculate_hand(dealer_hand)

    # determine the winner
    player_value = calculate_hand(player_hand)
    if dealer_value > 21 or player_value > dealer_value:
        print('You win!')
    elif player_value == dealer_value:
        print("It's a tie.")
    else:
        print('You lose.')

play_game()
