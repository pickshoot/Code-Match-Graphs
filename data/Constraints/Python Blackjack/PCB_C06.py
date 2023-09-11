import random

def create_deck():
    deck = []
    for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
        for value in ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King', 'Ace']:
            deck.append(value + ' of ' + suit)
    return deck

def get_card_value(card):
    value = card.split()[0]
    if value in ['Jack', 'Queen', 'King']:
        return 10
    elif value == 'Ace':
        return 11
    else:
        return int(value)

def get_hand_value(hand):
    value = sum([get_card_value(card) for card in hand])
    aces = hand.count('Ace')
    while value > 21 and aces > 0:
        value -= 10
        aces -= 1
    return value

def print_hand(hand):
    print(', '.join(hand))
    print('Total value:', get_hand_value(hand))

def play_game():
    deck = create_deck()
    random.shuffle(deck)
    player_hand = [deck.pop(), deck.pop()]
    dealer_hand = [deck.pop()]
    print('Player hand:')
    print_hand(player_hand)
    print('Dealer hand:')
    print(dealer_hand[0])
    while True:
        choice = input('Do you want to hit or stand? ')
        if choice.lower() == 'hit':
            player_hand.append(deck.pop())
            print('Player hand:')
            print_hand(player_hand)
            if get_hand_value(player_hand) > 21:
                print('Bust! Dealer wins.')
                return
        elif choice.lower() == 'stand':
            break
        else:
            print('Invalid input. Please enter "hit" or "stand".')
    print('Dealer hand:')
    print_hand(dealer_hand)
    while get_hand_value(dealer_hand) < 17:
        dealer_hand.append(deck.pop())
        print('Dealer hand:')
        print_hand(dealer_hand)
    if get_hand_value(dealer_hand) > 21:
        print('Dealer bust! Player wins.')
    elif get_hand_value(dealer_hand) > get_hand_value(player_hand):
        print('Dealer wins.')
    elif get_hand_value(dealer_hand) < get_hand_value(player_hand):
        print('Player wins!')
    else:
        print('Tie!')

if __name__ == '__main__':
    play_game()