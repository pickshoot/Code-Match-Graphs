import random

# define global variables
SUITS = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
RANKS = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
VALUES = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}
DECK = []

# define functions
def create_deck():
    for suit in SUITS:
        for rank in RANKS:
            DECK.append((rank, suit))

def shuffle_deck():
    random.shuffle(DECK)

def deal_card():
    return DECK.pop(0)

def calculate_hand(hand):
    hand_value = sum(VALUES[card[0]] for card in hand)
    for card in hand:
        if card[0] == 'Ace' and hand_value > 21:
            hand_value -= 10
    return hand_value

def display_hand(hand, dealer=False):
    print("Dealer's Hand: " if dealer else "Your Hand: ", end='')
    for card in hand:
        print(f"{card[0]} of {card[1]}, ", end='')
    print(f"Total value: {calculate_hand(hand)}")

# main program loop
create_deck()
shuffle_deck()

player_hand = [deal_card(), deal_card()]
dealer_hand = [deal_card()]

display_hand(player_hand)
display_hand(dealer_hand, dealer=True)

# player's turn
while True:
    player_choice = input("Do you want to hit or stand? ")
    if player_choice.lower() == "hit":
        player_hand.append(deal_card())
        display_hand(player_hand)
        if calculate_hand(player_hand) > 21:
            print("Bust! You lose!")
            exit()
    elif player_choice.lower() == "stand":
        break
    else:
        print("Invalid input. Please enter 'hit' or 'stand'.")

# dealer's turn
display_hand(dealer_hand, dealer=True)
while calculate_hand(dealer_hand) < 17:
    dealer_hand.append(deal_card())
    display_hand(dealer_hand, dealer=True)

# determine winner
player_hand_value = calculate_hand(player_hand)
dealer_hand_value = calculate_hand(dealer_hand)

if player_hand_value > dealer_hand_value:
    print("You win!")
elif player_hand_value < dealer_hand_value:
    print("Dealer wins!")
else:
    print("It's a tie!")
