import random

# Create a deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

deck = []
for suit in suits:
    for rank in ranks:
        card = (rank, suit)
        deck.append(card)

# Shuffle the deck
random.shuffle(deck)

# Deal the cards
player_cards = []
dealer_cards = []

for i in range(2):
    player_cards.append(deck.pop())
    dealer_cards.append(deck.pop())

# Calculate the value of a hand
def calculate_hand(hand):
    hand_value = 0
    aces = 0
    for card in hand:
        rank = card[0]
        value = values[rank]
        hand_value += value
        if rank == 'Ace':
            aces += 1
    while aces > 0 and hand_value > 21:
        hand_value -= 10
        aces -= 1
    return hand_value

# Print the current state of the game
def print_game_state():
    print(f"Dealer's hand: [{dealer_cards[0][0]}, <hidden>]")
    print(f"Your hand: {player_cards} (value: {calculate_hand(player_cards)})")

# Play the game
while True:
    print_game_state()

    # Check if player has blackjack
    if calculate_hand(player_cards) == 21:
        print("Blackjack! You win!")
        break

    # Ask player if they want to hit or stand
    action = input("Do you want to hit or stand? ")
    if action == "hit":
        player_cards.append(deck.pop())
    elif action == "stand":
        while calculate_hand(dealer_cards) < 17:
            dealer_cards.append(deck.pop())
        print_game_state()
        player_hand_value = calculate_hand(player_cards)
        dealer_hand_value = calculate_hand(dealer_cards)
        if dealer_hand_value > 21:
            print("Dealer bust! You win!")
        elif dealer_hand_value >= player_hand_value:
            print("Dealer wins!")
        else:
            print("You win!")
        break
