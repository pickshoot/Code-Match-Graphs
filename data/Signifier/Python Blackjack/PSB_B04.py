import random

# Create a deck of cards
deck = []
suits = ['hearts', 'diamonds', 'clubs', 'spades']
for suit in suits:
    for value in range(2, 11):
        deck.append(str(value) + ' of ' + suit)
    for face_card in ['Jack', 'Queen', 'King', 'Ace']:
        deck.append(face_card + ' of ' + suit)

# Shuffle the deck
random.shuffle(deck)

# Define the value of each card
card_values = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Deal two cards to the player and dealer
player_hand = []
dealer_hand = []
for i in range(2):
    player_hand.append(deck.pop())
    dealer_hand.append(deck.pop())

# Define the function to calculate the value of a hand
def hand_value(hand):
    total = 0
    aces = 0
    for card in hand:
        value = card.split()[0]
        if value == 'Ace':
            aces += 1
        else:
            total += card_values[value]
    for i in range(aces):
        if total + 11 <= 21:
            total += 11
        else:
            total += 1
    return total

# Define the function to display the hands
def show_hands():
    print("Dealer's hand:")
    print(dealer_hand[0])
    print("<hidden card>")
    print()
    print("Player's hand:")
    for card in player_hand:
        print(card)
    print("Total value:", hand_value(player_hand))
    print()

# Play the game
while True:
    show_hands()
    if hand_value(player_hand) == 21:
        print("Blackjack! You win!")
        break
    elif hand_value(player_hand) > 21:
        print("Bust! You lose.")
        break
    else:
        choice = input("Do you want to hit or stand? ")
        if choice.lower() == 'hit':
            player_hand.append(deck.pop())
        elif choice.lower() == 'stand':
            while hand_value(dealer_hand) < 17:
                dealer_hand.append(deck.pop())
            show_hands()
            if hand_value(dealer_hand) > 21:
                print("Dealer bust! You win!")
            elif hand_value(dealer_hand) >= hand_value(player_hand):
                print("Dealer wins!")
            else:
                print("You win!")
            break
