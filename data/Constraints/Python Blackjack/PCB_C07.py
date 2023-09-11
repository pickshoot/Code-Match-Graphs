import random

# Define the deck of cards
deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
card_values = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 10, 'Q': 10, 'K': 10,
               'A': 11}

# Define the player and dealer hands
player_hand = []
dealer_hand = []


# Define the game functions
def deal_card(hand):
    """Deal a card from the deck."""
    card = random.choice(deck)
    hand.append(card)
    return card


def calculate_hand(hand):
    """Calculate the value of a hand."""
    total = sum([card_values[card] for card in hand])
    num_aces = hand.count('A')
    while total > 21 and num_aces:
        total -= 10
        num_aces -= 1
    return total


def print_hand(hand):
    """Print the cards in a hand."""
    print(", ".join(hand))


# Start the game
print("Welcome to Blackjack!")

while True:
    # Deal the initial cards
    player_hand = [deal_card(player_hand), deal_card(player_hand)]
    dealer_hand = [deal_card(dealer_hand), deal_card(dealer_hand)]

    # Show the hands
    print("Dealer's hand:")
    print(dealer_hand[0], "X")
    print("Your hand:")
    print_hand(player_hand)

    # Player's turn
    while True:
        choice = input("Do you want to hit or stand? ").lower()
        if choice not in ['hit', 'stand']:
            print("Invalid input. Please enter 'hit' or 'stand'.")
            continue
        if choice == 'hit':
            player_hand.append(deal_card(player_hand))
            print("Your hand:")
            print_hand(player_hand)
            if calculate_hand(player_hand) > 21:
                print("Bust! You lose.")
                break
        else:
            break

    # Dealer's turn
    if calculate_hand(player_hand) <= 21:
        print("Dealer's hand:")
        print_hand(dealer_hand)
        while calculate_hand(dealer_hand) < 17:
            dealer_hand.append(deal_card(dealer_hand))
            print("Dealer hits.")
            print_hand(dealer_hand)
        if calculate_hand(dealer_hand) > 21:
            print("Dealer busts! You win.")
        elif calculate_hand(dealer_hand) > calculate_hand(player_hand):
            print("Dealer wins.")
        elif calculate_hand(dealer_hand) < calculate_hand(player_hand):
            print("You win!")
        else:
            print("It's a tie!")

    # Play again?
    while True:
        again = input("Do you want to play again? ").lower()
        if again not in ['yes', 'no']:
            print("Invalid input. Please enter 'yes' or 'no'.")
            continue
        else:
            break
    if again == 'no':
        break

print("Thanks for playing Blackjack!")