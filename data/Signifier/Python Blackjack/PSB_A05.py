import random

# Define the deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = []
for suit in suits:
    for rank in ranks:
        deck.append(f'{rank} of {suit}')

# Define the values of cards
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define the function to deal the cards
def deal_cards():
    return deck.pop(random.randint(0, len(deck)-1))

# Define the function to calculate the total value of a hand
def total(hand):
    total_value = 0
    num_aces = 0
    for card in hand:
        rank = card.split()[0]
        total_value += values[rank]
        if rank == 'Ace':
            num_aces += 1
    while total_value > 21 and num_aces > 0:
        total_value -= 10
        num_aces -= 1
    return total_value

# Define the function to play the game
def play_game():
    print('Welcome to Blackjack!')
    print()
    # Shuffle the deck
    random.shuffle(deck)
    # Deal the cards
    player_hand = [deal_cards(), deal_cards()]
    dealer_hand = [deal_cards(), deal_cards()]
    # Show the player's hand
    print('Your hand:')
    for card in player_hand:
        print(card)
    print(f'Total value: {total(player_hand)}')
    print()
    # Ask the player to hit or stand
    while True:
        action = input('Do you want to hit or stand? ')
        if action.lower() == 'hit':
            player_hand.append(deal_cards())
            print('Your hand:')
            for card in player_hand:
                print(card)
            print(f'Total value: {total(player_hand)}')
            if total(player_hand) > 21:
                print('Bust! You lose.')
                return
        elif action.lower() == 'stand':
            break
        else:
            print('Invalid input. Please enter "hit" or "stand".')
    print()
    # Show the dealer's hand
    print('Dealer\'s hand:')
    for card in dealer_hand:
        print(card)
    print(f'Total value: {total(dealer_hand)}')
    print()
    # Dealer hits until their hand is at least 17
    while total(dealer_hand) < 17:
        dealer_hand.append(deal_cards())
        print('Dealer hits.')
        print('Dealer\'s hand:')
        for card in dealer_hand:
            print(card)
        print(f'Total value: {total(dealer_hand)}')
        print()
    # Determine the winner
    player_total = total(player_hand)
    dealer_total = total(dealer_hand)
    if dealer_total > 21:
        print('Dealer busts! You win.')
    elif player_total > dealer_total:
        print('You win!')
    elif dealer_total > player_total:
        print('Dealer wins.')
    else:
        print('It\'s a tie.')
    print()
    # Ask the player if they want to play again
    while True:
        play_again = input('Do you want to play again? ')
        if play_again.lower() == 'yes':
            play_game()
            break
        elif play_again.lower() == 'no':
            print('Thanks for playing!')
            return
        else:
            print('Invalid input. Please enter "yes" or "no".')
play_game()