import random

def create_deck():
    deck = []
    suits = ['hearts', 'diamonds', 'clubs', 'spades']
    ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    for suit in suits:
        for rank in ranks:
            deck.append(rank + ' of ' + suit)
    random.shuffle(deck)
    return deck

def deal(deck):
    hand = []
    for i in range(2):
        card = deck.pop()
        hand.append(card)
    return hand

def hit(hand, deck):
    hand.append(deck.pop())
    return hand

def calculate_hand(hand):
    total = 0
    aces = 0
    for card in hand:
        rank = card.split()[0]
        if rank == 'A':
            aces += 1
            total += 11
        elif rank in ['K', 'Q', 'J']:
            total += 10
        else:
            total += int(rank)
    while total > 21 and aces:
        total -= 10
        aces -= 1
    return total

def show_hand(hand):
    for card in hand:
        print(card)

def blackjack():
    deck = create_deck()
    player_hand = deal(deck)
    dealer_hand = deal(deck)
    print('Player hand:')
    show_hand(player_hand)
    print('Dealer hand:')
    print(dealer_hand[0])
    while True:
        choice = input('Do you want to hit or stand? ')
        if choice == 'hit':
            player_hand = hit(player_hand, deck)
            print('Player hand:')
            show_hand(player_hand)
            if calculate_hand(player_hand) > 21:
                print('Player busts! Dealer wins!')
                return
        else:
            break
    print('Dealer hand:')
    show_hand(dealer_hand)
    while calculate_hand(dealer_hand) < 17:
        dealer_hand = hit(dealer_hand, deck)
        print('Dealer hits.')
        print('Dealer hand:')
        show_hand(dealer_hand)
        if calculate_hand(dealer_hand) > 21:
            print('Dealer busts! Player wins!')
            return
    player_total = calculate_hand(player_hand)
    dealer_total = calculate_hand(dealer_hand)
    if player_total > dealer_total:
        print('Player wins!')
    elif dealer_total > player_total:
        print('Dealer wins!')
    else:
        print('Tie!')
    return

while True:
    play_again = input('Do you want to play again? (Y/N) ')
    if play_again.lower() == 'y':
        blackjack()
    else:
        break