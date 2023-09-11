import random

def get_card_value(card):
    if card in ['J', 'Q', 'K']:
        return 10
    elif card == 'A':
        return 11
    else:
        return int(card)

def get_hand_value(hand):
    value = sum([get_card_value(card) for card in hand])
    if value > 21 and 'A' in hand:
        value -= 10
    return value

def deal_card(deck, hand):
    card = deck.pop()
    hand.append(card)

def print_hand(hand):
    print(' '.join(hand), ':', get_hand_value(hand))

def play_blackjack():
    deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4
    random.shuffle(deck)

    player_hand = []
    deal_card(deck, player_hand)
    deal_card(deck, player_hand)

    dealer_hand = []
    deal_card(deck, dealer_hand)
    deal_card(deck, dealer_hand)

    print('Player:')
    print_hand(player_hand)

    print('Dealer:')
    print(dealer_hand[0], ' X')

    while True:
        choice = input('Hit or stand? (h/s)')
        if choice == 'h':
            deal_card(deck, player_hand)
            print('Player:')
            print_hand(player_hand)
            if get_hand_value(player_hand) > 21:
                print('Player busts!')
                return -1
        else:
            break

    dealer_value = get_hand_value(dealer_hand)
    while dealer_value < 17:
        deal_card(deck, dealer_hand)
        dealer_value = get_hand_value(dealer_hand)

    print('Dealer:')
    print_hand(dealer_hand)

    player_value = get_hand_value(player_hand)
    if player_value > 21:
        print('Player busts!')
        return -1
    elif dealer_value > 21:
        print('Dealer busts!')
        return 1
    elif player_value > dealer_value:
        print('Player wins!')
        return 1
    elif player_value < dealer_value:
        print('Dealer wins!')
        return -1
    else:
        print('Push!')
        return 0

if __name__ == '__main__':
    money = 100
    while True:
        print('Money:', money)
        bet = int(input('Enter bet amount: '))
        result = play_blackjack()
        money += result * bet
        if money == 0:
            print('Game over!')
            break
