import random

# Initialize the deck
suits = ['hearts', 'diamonds', 'clubs', 'spades']
values = [str(num) for num in range(2, 11)] + ['Jack', 'Queen', 'King', 'Ace']
deck = [(s, v) for s in suits for v in values]

# Define a function to calculate the value of a hand
def calculate_hand(hand):
    """Calculate the value of a hand"""
    value = 0
    for card in hand:
        if card[1] == 'Ace':
            value += 11
        elif card[1] in ['Jack', 'Queen', 'King']:
            value += 10
        else:
            value += int(card[1])
    # Adjust for aces
    num_aces = len([card for card in hand if card[1] == 'Ace'])
    while value > 21 and num_aces:
        value -= 10
        num_aces -= 1
    return value

# Define a function to deal a card from the deck
def deal_card(deck):
    """Deal a card from the deck"""
    return deck.pop()

# Define a function to play the game
def play_game():
    """Play the game"""
    # Shuffle the deck
    random.shuffle(deck)
    # Deal the cards
    player_hand = [deal_card(deck), deal_card(deck)]
    dealer_hand = [deal_card(deck), deal_card(deck)]
    # Print the initial hands
    print("Player's hand:", player_hand)
    print("Dealer's hand:", dealer_hand[0])
    # Player's turn
    while True:
        action = input("Hit or stand? ")
        if action.lower() == 'hit':
            player_hand.append(deal_card(deck))
            print("Player's hand:", player_hand)
            if calculate_hand(player_hand) > 21:
                print("Bust! You lose.")
                return
        elif action.lower() == 'stand':
            break
    # Dealer's turn
    while calculate_hand(dealer_hand) < 17:
        dealer_hand.append(deal_card(deck))
    print("Dealer's hand:", dealer_hand)
    # Determine the winner
    player_score = calculate_hand(player_hand)
    dealer_score = calculate_hand(dealer_hand)
    if player_score > 21:
        print("Bust! You lose.")
    elif dealer_score > 21:
        print("Dealer busts! You win.")
    elif player_score > dealer_score:
        print("You win!")
    elif dealer_score > player_score:
        print("Dealer wins!")
    else:
        print("It's a tie!")

# Start the game
play_game()