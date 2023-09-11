import random

# Define card values
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

# Define function to calculate hand value
def hand_value(hand):
    # Initialize variables
    value = 0
    aces = 0
    # Loop through cards in hand
    for card in hand:
        # Add value of card to total value
        value += card_values[card]
        # Keep track of aces
        if card == 'A':
            aces += 1
    # Adjust value of aces
    while value > 21 and aces > 0:
        value -= 10
        aces -= 1
    # Return hand value
    return value

# Define function to deal cards
def deal_cards():
    # Initialize variables
    deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4
    random.shuffle(deck)
    player_hand = []
    dealer_hand = []
    # Deal two cards to player and dealer
    for i in range(2):
        player_card = deck.pop()
        dealer_card = deck.pop()
        player_hand.append(player_card)
        dealer_hand.append(dealer_card)
    # Return player and dealer hands
    return player_hand, dealer_hand, deck

# Define function to display hands
def display_hands(player_hand, dealer_hand, show_dealer_card=False):
    # Display player's hand
    print("Player's hand: ", end="")
    for card in player_hand:
        print(card, end=" ")
    print(f"({hand_value(player_hand)})")
    # Display dealer's hand
    if show_dealer_card:
        print("Dealer's hand: ", end="")
        for card in dealer_hand:
            print(card, end=" ")
        print(f"({hand_value(dealer_hand)})")
    else:
        print(f"Dealer's hand: {dealer_hand[0]} ?")

# Define function to get player's move
def get_player_move():
    while True:
        move = input("Do you want to hit or stand? ")
        if move.lower() in ['hit', 'h', 'stand', 's']:
            return move.lower()

# Define function for dealer's turn
def dealer_turn(deck, dealer_hand):
    # Hit until hand value is 17 or higher
    while hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())

# Define function to determine winner
def determine_winner(player_hand, dealer_hand):
    player_value = hand_value(player_hand)
    dealer_value = hand_value(dealer_hand)
    # Player busts
    if player_value > 21:
        return 'Dealer'
    # Dealer busts
    elif dealer_value > 21:
        return 'Player'
    # Player wins
    elif player_value > dealer_value:
        return 'Player'
    # Dealer wins
    elif dealer_value > player_value:
        return 'Dealer'
    # Tie
    else:
        return 'Tie'

# Define function to play game
def play_game():
    # Initialize variables
    player_chips = 100
    while True:
        print(f"You have {player_chips} chips.")
        # Get player's bet
        while True:
            try:
                player_bet = int(input("How many chips do you want to bet? "))
                if player_bet <= 0 or player_bet > player_chips:
                    raise ValueError
                break
            except ValueError:
                print("Invalid bet.")
        # Deal cards
        player_hand, dealer_hand, deck = deal_cards()
        # Display hands
        display_hands(player_hand, dealer_hand)
        # Player's turn
        while True:
            move = get_player_move()
            if move == 'hit':
                player_hand.append(deck.pop())
                display_hands(player_hand, dealer_hand)
                if hand_value(player_hand) > 21:
                    print("You busted!")
                    player_chips -= player_bet
                    break
            else:
                break
        if hand_value(player_hand) <= 21:
            # Dealer's turn
            display_hands(player_hand, dealer_hand, show_dealer_card=True)
            dealer_turn(deck, dealer_hand)
            display_hands(player_hand, dealer_hand, show_dealer_card=True)
            if hand_value(dealer_hand) > 21:
                print("Dealer busted!")
                player_chips += player_bet
            else:
                winner = determine_winner(player_hand, dealer_hand)
                if winner == 'Player':
                    print("You win!")
                    player_chips += player_bet
                elif winner == 'Dealer':
                    print("Dealer wins!")
                    player_chips -= player_bet
                else:
                    print("It's a tie!")
        # Check if player has run out of chips
        if player_chips == 0:
            print("You ran out of chips!")
            break
        # Ask player if they want to play again
        play_again = input("Do you want to play again? ")
        if play_again.lower() not in ['yes', 'y']:
            break

play_game()
