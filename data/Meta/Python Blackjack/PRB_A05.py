import random

# Card values dictionary
card_values = {
    '2': 2,
    '3': 3,
    '4': 4,
    '5': 5,
    '6': 6,
    '7': 7,
    '8': 8,
    '9': 9,
    '10': 10,
    'J': 10,
    'Q': 10,
    'K': 10,
    'A': 11
}

# Card suits list
card_suits = ['Spades', 'Hearts', 'Diamonds', 'Clubs']

# Card ranks list
card_ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']

# Function to create a deck of cards
def create_deck():
    deck = []
    for suit in card_suits:
        for rank in card_ranks:
            deck.append(rank + ' of ' + suit)
    random.shuffle(deck)
    return deck

# Function to deal a card from the deck
def deal_card(deck):
    return deck.pop()

# Function to calculate the value of a hand
def calculate_hand(hand):
    total = sum(card_values[card.split()[0]] for card in hand)
    aces = [card for card in hand if 'A' in card.split()]
    for ace in aces:
        if total > 21:
            total -= 10
    return total

# Function to display the current state of the game
def display_game_state(player_hand, dealer_hand, dealer_showing):
    print("Player's Hand:", player_hand, "(", calculate_hand(player_hand), "points)")
    print("Dealer's Hand:", [dealer_showing] + ['(hidden)'] * (len(dealer_hand) - 1), "(", calculate_hand([dealer_showing]), "points)")

# Function to check if the game has ended
def is_game_over(player_hand, dealer_hand):
    player_points = calculate_hand(player_hand)
    dealer_points = calculate_hand(dealer_hand)
    if player_points == 21 or dealer_points == 21 or len(player_hand) == 5:
        return True
    if player_points > 21 or dealer_points > 21:
        return True
    if len(dealer_hand) == 5:
        return True
    return False

# Function to play the game
def play_game():
    # Create a deck of cards and deal the initial hands
    deck = create_deck()
    player_hand = [deal_card(deck), deal_card(deck)]
    dealer_hand = [deal_card(deck), deal_card(deck)]
    dealer_showing = dealer_hand[0]

    # Display the initial state of the game
    display_game_state(player_hand, dealer_hand, dealer_showing)

    # Player's turn
    while True:
        choice = input("Do you want to hit or stand? ")
        if choice.lower() == 'hit':
            player_hand.append(deal_card(deck))
            display_game_state(player_hand, dealer_hand, dealer_showing)
            if is_game_over(player_hand, dealer_hand):
                break
        elif choice.lower() == 'stand':
            break

    # Dealer's turn
    while calculate_hand(dealer_hand) < 17:
        dealer_hand.append(deal_card(deck))
        display_game_state(player_hand, dealer_hand, dealer_showing)
        if is_game_over(player_hand, dealer_hand):
            break

    # Check the result of the game
    player_points = calculate_hand(player_hand)
    dealer_points = calculate_hand(dealer_hand)
    if player_points == dealer_points:
        print("Push!",dealer_points)
    elif player_points == 21 or dealer_points > 21 or len(player_hand) == 5:
        print("Congratulations, you win!",dealer_points)
    elif dealer_points == 21 or player_points > 21 or len(dealer_hand) == 5:
        print("Sorry, you lose.",dealer_points)
    else:
        if player_points > dealer_points:
            print("Congratulations, you win!",dealer_points)
        else:
            print("Sorry, you lose.",dealer_points)

    # Ask the player if they want to play again
    choice = input("Do you want to play again? ")
    if choice.lower() == 'yes':
        play_game()

# Start the game
play_game()

