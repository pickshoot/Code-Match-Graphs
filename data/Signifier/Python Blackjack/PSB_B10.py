import random

# Set up the deck
suits = ['hearts', 'diamonds', 'clubs', 'spades']
ranks = ['ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'jack', 'queen', 'king']
deck = []
for suit in suits:
    for rank in ranks:
        deck.append((rank, suit))

# Define a function to calculate the value of a hand
def hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        rank = card[0]
        if rank == 'ace':
            num_aces += 1
            value += 11
        elif rank in ['jack', 'queen', 'king']:
            value += 10
        else:
            value += int(rank)
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

# Define a function to show the cards in a hand
def show_cards(hand, dealer=False):
    if dealer:
        print('Dealer shows:', hand[0])
    else:
        print('Player has:', ', '.join([str(card) for card in hand]))

# Deal the initial hands
player_hand = []
dealer_hand = []
for i in range(2):
    player_hand.append(deck.pop())
    dealer_hand.append(deck.pop())

# Show the initial hands
show_cards(player_hand)
show_cards(dealer_hand, dealer=True)

# Player's turn
while hand_value(player_hand) < 21:
    action = input('Would you like to hit or stand? ')
    if action.lower() == 'hit':
        player_hand.append(deck.pop())
        show_cards(player_hand)
    else:
        break

# Dealer's turn
while hand_value(dealer_hand) < 17:
    dealer_hand.append(deck.pop())
    show_cards(dealer_hand, dealer=True)

# Determine the winner
player_value = hand_value(player_hand)
dealer_value = hand_value(dealer_hand)
if player_value > 21:
    print('Player busts, dealer wins!')
elif dealer_value > 21:
    print('Dealer busts, player wins!')
elif player_value > dealer_value:
    print('Player wins!')
elif dealer_value > player_value:
    print('Dealer wins!')
else:
    print('It\'s a tie!')
