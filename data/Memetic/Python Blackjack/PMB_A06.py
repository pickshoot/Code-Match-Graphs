import random

# initialize the deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}
deck = []
for suit in suits:
    for rank in ranks:
        deck.append((rank, suit))

# initialize the player's and dealer's hands
player_hand = []
dealer_hand = []

# helper function to calculate the value of a hand
def calculate_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        rank = card[0]
        if rank == 'Ace':
            num_aces += 1
        value += values[rank]
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

# deal initial cards to player and dealer
random.shuffle(deck)
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())
player_hand.append(deck.pop())
dealer_hand.append(deck.pop())

# player's turn
while True:
    print("Player's Hand: ", player_hand, "Value: ", calculate_hand_value(player_hand))
    if calculate_hand_value(player_hand) > 21:
        print("Bust! You lose.")
        break
    if calculate_hand_value(player_hand) == 21:
        print("Blackjack! You win!")
        break
    response = input("Do you want to hit or stand? ")
    if response.lower() == 'hit':
        player_hand.append(deck.pop())
    elif response.lower() == 'stand':
        break

# dealer's turn
while calculate_hand_value(dealer_hand) < 17:
    dealer_hand.append(deck.pop())

# determine the winner
player_hand_value = calculate_hand_value(player_hand)
dealer_hand_value = calculate_hand_value(dealer_hand)
print("Player's Hand: ", player_hand, "Value: ", player_hand_value)
print("Dealer's Hand: ", dealer_hand, "Value: ", dealer_hand_value)
if dealer_hand_value > 21:
    print("Dealer bust! You win!")
elif dealer_hand_value == player_hand_value:
    print("Push! It's a tie.")
elif dealer_hand_value > player_hand_value:
    print("Dealer wins!")
else:
    print("You win!")