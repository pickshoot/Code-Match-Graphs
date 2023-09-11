import random

# Define the cards and their values
cards = {
    'Ace': 11, 
    '2': 2, 
    '3': 3, 
    '4': 4, 
    '5': 5, 
    '6': 6, 
    '7': 7, 
    '8': 8, 
    '9': 9, 
    '10': 10, 
    'Jack': 10, 
    'Queen': 10, 
    'King': 10
}

# Define a function to calculate the total value of the hand
def calculate_hand(hand):
    total = sum(hand)
    # If the hand contains an Ace and the total value is over 21, 
    # then the Ace must be counted as 1 instead of 11
    if 11 in hand and total > 21:
        total -= 10
    return total

# Define a function to deal the cards
def deal_cards():
    deck = list(cards.values()) * 4
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop(), deck.pop()]
    return deck, player_hand, dealer_hand

# Define a function to ask the player if they want to hit or stand
def hit_or_stand():
    while True:
        action = input('Do you want to hit or stand? ')
        if action.lower() in ['hit', 'stand']:
            return action.lower()
        print('Invalid input. Please enter either "hit" or "stand".')

# Define the main function for the game
def play_game():
    # Deal the cards
    deck, player_hand, dealer_hand = deal_cards()
    print(f'Your hand: {player_hand}')
    print(f'Dealer\'s hand: [{dealer_hand[0]}, ?]')
    
    # Player's turn
    while True:
        action = hit_or_stand()
        if action == 'hit':
            player_hand.append(deck.pop())
            print(f'Your hand: {player_hand}')
            if calculate_hand(player_hand) > 21:
                print('You busted!')
                return
        else:
            break
        
    # Dealer's turn
    print(f'Dealer\'s hand: {dealer_hand}')
    while calculate_hand(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        print(f'Dealer\'s hand: {dealer_hand}')
        if calculate_hand(dealer_hand) > 21:
            print('Dealer busted! You win!')
            return

    # Determine the winner
    player_total = calculate_hand(player_hand)
    dealer_total = calculate_hand(dealer_hand)
    if player_total > dealer_total:
        print('You win!')
    elif player_total < dealer_total:
        print('Dealer wins!')
    else:
        print('It\'s a tie!')

# Play the game
play_game()