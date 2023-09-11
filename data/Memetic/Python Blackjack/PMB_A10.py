import random

# Function to calculate the value of a hand
def hand_value(hand):
    value = 0
    aces = 0
    for card in hand:
        if card in ['J', 'Q', 'K']:
            value += 10
        elif card == 'A':
            aces += 1
            value += 11
        else:
            value += int(card)
    while value > 21 and aces:
        value -= 10
        aces -= 1
    return value

# Function to print a hand
def print_hand(hand):
    print("Hand: ", end="")
    for card in hand:
        print(card, end=" ")
    print("")

# Initialize the deck of cards
deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4
random.shuffle(deck)

# Deal the initial hands
player_hand = [deck.pop(), deck.pop()]
dealer_hand = [deck.pop()]

# Print the hands
print_hand(player_hand)
print("Dealer's hand: {}".format(dealer_hand[0]))

# Player's turn
while hand_value(player_hand) < 21:
    action = input("Do you want to hit or stand? ")
    if action == "hit":
        player_hand.append(deck.pop())
        print_hand(player_hand)
    elif action == "stand":
        break

# Dealer's turn
if hand_value(player_hand) <= 21:
    while hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
    print_hand(dealer_hand)

# Determine the winner
player_value = hand_value(player_hand)
dealer_value = hand_value(dealer_hand)
if player_value > 21:
    print("You bust. Dealer wins!")
elif dealer_value > 21:
    print("Dealer busts. You win!")