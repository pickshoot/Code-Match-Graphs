import random

# initialize deck of cards
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
deck = [(rank, suit) for suit in suits for rank in ranks]

# calculate the value of a hand
def calculate_hand_value(hand):
    value = 0
    num_aces = 0
    for card in hand:
        if card[0] in ['Jack', 'Queen', 'King']:
            value += 10
        elif card[0] == 'Ace':
            num_aces += 1
            value += 11
        else:
            value += int(card[0])
    while value > 21 and num_aces > 0:
        value -= 10
        num_aces -= 1
    return value

# print a hand of cards
def print_hand(hand):
    for card in hand:
        print(card[0] + ' of ' + card[1])

# main game loop
while True:
    # initialize player and dealer hands
    player_hand = [deck.pop(random.randint(0, len(deck)-1)), deck.pop(random.randint(0, len(deck)-1))]
    dealer_hand = [deck.pop(random.randint(0, len(deck)-1)), deck.pop(random.randint(0, len(deck)-1))]

    # print initial hands
    print('Your hand:')
    print_hand(player_hand)
    print('Dealer shows:')
    print(dealer_hand[0][0] + ' of ' + dealer_hand[0][1])
    print()

    # player's turn
    while True:
        choice = input('Do you want to hit or stand? ')
        if choice.lower() == 'hit':
            player_hand.append(deck.pop(random.randint(0, len(deck)-1)))
            print('Your hand:')
            print_hand(player_hand)
            if calculate_hand_value(player_hand) > 21:
                print('Busted!')
                break
        elif choice.lower() == 'stand':
            break

    # dealer's turn
    while calculate_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop(random.randint(0, len(deck)-1)))
        print('Dealer hand:')
        print_hand(dealer_hand)
        if calculate_hand_value(dealer_hand) > 21:
            print('Dealer busts!')
            break

    # compare hands
    player_score = calculate_hand_value(player_hand)
    dealer_score = calculate_hand_value(dealer_hand)
    print('Your score:', player_score)
    print('Dealer score:', dealer_score)
    if player_score > 21:
        print('You lose!')
    elif dealer_score > 21:
        print('You win!')
    elif player_score > dealer_score:
        print('You win!')
    elif dealer_score > player_score:
        print('You lose!')
    else:
        print('Tie!')

    # ask to play again
    again = input('Do you want to play again? (y/n) ')
    if again.lower() != 'y':
        break