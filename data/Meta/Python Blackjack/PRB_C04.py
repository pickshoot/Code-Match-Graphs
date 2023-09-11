import random

def create_deck(num_decks):
    """Create a deck of cards with a given number of decks"""
    cards = ['2','3','4','5','6','7','8','9','10','J','Q','K','A'] * 4 * num_decks
    random.shuffle(cards)
    return cards

def get_card_value(card):
    """Get the numerical value of a card"""
    if card in ['K','Q','J']:
        return 10
    elif card == 'A':
        return 11
    else:
        return int(card)

def get_hand_value(hand):
    """Get the value of a hand"""
    value = sum([get_card_value(card) for card in hand])
    if value > 21 and 'A' in hand:
        # Ace can be worth 1 instead of 11 if hand value is over 21
        value -= 10
    return value

def get_winner(player_hand, dealer_hand):
    """Determine the winner of a hand"""
    player_value = get_hand_value(player_hand)
    dealer_value = get_hand_value(dealer_hand)
    if player_value > 21:
        return 'dealer'
    elif dealer_value > 21:
        return 'player'
    elif player_value > dealer_value:
        return 'player'
    elif dealer_value > player_value:
        return 'dealer'
    else:
        return 'tie'

def play_blackjack():
    """Play a game of Blackjack"""
    print('Welcome to Blackjack!')
    player_name = input('What is your name? ')
    player_bankroll = 100
    num_decks = int(input('How many decks would you like to play with? '))
    deck = create_deck(num_decks)
    while True:
        print(f'{player_name}, your current bankroll is ${player_bankroll}')
        if player_bankroll == 0:
            print('Sorry, you are out of money.')
            break
        bet = int(input('How much would you like to bet? '))
        if bet > player_bankroll:
            print('Sorry, you do not have enough money for that bet.')
            continue
        player_hand = [deck.pop(), deck.pop()]
        dealer_hand = [deck.pop(), deck.pop()]
        print(f'{player_name}, your hand is {player_hand} (value: {get_hand_value(player_hand)})')
        print(f'Dealer has a {dealer_hand[0]} showing')
        while True:
            action = input('Would you like to hit or stand? ')
            if action == 'hit':
                player_hand.append(deck.pop())
                print(f'{player_name}, your hand is {player_hand} (value: {get_hand_value(player_hand)})')
                if get_hand_value(player_hand) > 21:
                    print('Bust! Dealer wins.')
                    player_bankroll -= bet
                    break
            elif action == 'stand':
                dealer_value = get_hand_value(dealer_hand)
                while dealer_value < 17:
                    dealer_hand.append(deck.pop())
                    dealer_value = get_hand_value(dealer_hand)
                print(f'Dealer has {dealer_hand} (value: {dealer_value})')
                winner = get_winner(player_hand, dealer_hand)
                if winner == 'player':
                    print(f'{player_name} wins!')
                    player_bankroll += bet
                elif winner == 'dealer':
                    print('Dealer wins.')
                    player_bankroll -= bet
                else:
                    print('It\'s a tie!')
                break
            else:
                print('Invalid input. Please enter \'hit\' or \'stand\'')

        play_again = input('Would you like to play again? ')
        if play_again.lower() == 'no':
            print(f'Thank you for playing, {player_name}!')
            break

play_blackjack()
