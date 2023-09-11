import random

# Create a deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = []
for suit in suits:
    for rank in ranks:
        deck.append(rank + ' of ' + suit)

# Shuffle the deck
random.shuffle(deck)

# Initialize player and dealer hands
player_hand = []
dealer_hand = []

# Deal two cards to the player and dealer
for i in range(2):
    player_hand.append(deck.pop())
    dealer_hand.append(deck.pop())

# Calculate the value of a hand
def hand_value(hand):
    value = 0
    aces = 0
    for card in hand:
        rank = card.split()[0]
        if rank == 'Ace':
            aces += 1
            value += 11
        elif rank in ['King', 'Queen', 'Jack']:
            value += 10
        else:
            value += int(rank)
    while value > 21 and aces:
        value -= 10
        aces -= 1
    return value

# Print the initial hands
print('Player hand:', player_hand)
print('Dealer hand:', [dealer_hand[0], 'unknown'])

# Player's turn
while True:
    choice = input('Do you want to hit or stand? ')
    if choice.lower() == 'hit':
        player_hand.append(deck.pop())
        print('Player hand:', player_hand)
        if hand_value(player_hand) > 21:
            print('Bust! You lose.')
            break
    elif choice.lower() == 'stand':
        break

# Dealer's turn
if hand_value(player_hand) <= 21:
    while hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        print('Dealer hand:', dealer_hand)
    if hand_value(dealer_hand) > 21:
        print('Dealer busts! You win.')
    elif hand_value(player_hand) > hand_value(dealer_hand):
        print('You win!')
    elif hand_value(player_hand) < hand_value(dealer_hand):
        print('You lose.')
    else:
        print('Push.')
