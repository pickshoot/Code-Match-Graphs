import random

# Initialize the deck of cards
deck = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"] * 4

# Initialize the values of each card
card_values = {"2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "J": 10, "Q": 10, "K": 10, "A": 11}

# Initialize the player's and dealer's hands
player_hand = []
dealer_hand = []

# Function to calculate the total value of a hand
def calculate_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        value += card_values[card]
        if card == "A":
            num_aces += 1
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

# Function to deal a card from the deck
def deal_card():
    return deck.pop(random.randint(0, len(deck)-1))

# Deal the initial hands
for i in range(2):
    player_hand.append(deal_card())
    dealer_hand.append(deal_card())

# Player's turn
while True:
    print(f"Your hand: {player_hand}")
    print(f"Dealer's hand: [{dealer_hand[0]}, _]")

    # Check for blackjack
    if calculate_hand_value(player_hand) == 21:
        print("Blackjack! You win!")
        break

    # Ask the player to hit or stand
    choice = input("Do you want to hit or stand? ")
    if choice.lower() == "hit":
        player_hand.append(deal_card())
        if calculate_hand_value(player_hand) > 21:
            print(f"Your hand: {player_hand}")
            print(f"Dealer's hand: {dealer_hand}")
            print("Bust! You lose.")
            break
    elif choice.lower() == "stand":
        break

# Dealer's turn
if calculate_hand_value(player_hand) <= 21:
    print(f"Your hand: {player_hand}")
    print(f"Dealer's hand: {dealer_hand}")

    while calculate_hand_value(dealer_hand) < 17:
        dealer_hand.append(deal_card())

    print(f"Dealer's hand: {dealer_hand}")

    if calculate_hand_value(dealer_hand) > 21:
        print("Dealer busts! You win!")
    elif calculate_hand_value(dealer_hand) >= calculate_hand_value(player_hand):
        print("Dealer wins!")
    else:
        print("You win!")
