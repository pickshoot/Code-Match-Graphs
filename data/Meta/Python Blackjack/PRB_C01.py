import random

def create_deck(num_decks):
    """
    Creates a deck of cards by concatenating num_decks standard decks of 52 cards each
    """
    suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
    ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King', 'Ace']
    deck = []
    for i in range(num_decks):
        for suit in suits:
            for rank in ranks:
                deck.append((suit, rank))
    return deck

def shuffle_deck(deck):
    """
    Shuffles a deck of cards
    """
    random.shuffle(deck)
    return deck

def deal_cards(deck, num_cards):
    """
    Deals a specified number of cards from the deck
    """
    cards = []
    for i in range(num_cards):
        cards.append(deck.pop())
    return cards

def get_card_value(card):
    """
    Returns the value of a card in Blackjack
    """
    rank = card[1]
    if rank == 'Ace':
        return 11
    elif rank in ['King', 'Queen', 'Jack', '10']:
        return 10
    else:
        return int(rank)

def get_hand_value(hand):
    """
    Returns the value of a Blackjack hand
    """
    value = 0
    num_aces = 0
    for card in hand:
        card_value = get_card_value(card)
        if card[1] == 'Ace':
            num_aces += 1
        value += card_value
    while num_aces > 0 and value > 21:
        value -= 10
        num_aces -= 1
    return value

def display_hand(hand, hide_first_card=False):
    """
    Displays a hand of cards
    """
    if hide_first_card:
        print('Dealer shows:', 'X', hand[1])
    else:
        print('Hand:', hand)

def get_bet(bankroll):
    """
    Prompts the user for their bet and returns it
    """
    while True:
        try:
            bet = int(input(f'You have ${bankroll}. How much would you like to bet? '))
            if bet <= 0 or bet > bankroll:
                print('Invalid bet. Please enter a valid bet.')
            else:
                return bet
        except ValueError:
            print('Invalid input. Please enter a valid number.')

def play_again():
    """
    Asks the user if they want to play again and returns True or False
    """
    while True:
        response = input('Do you want to play again? (Y/N) ').lower()
        if response == 'y':
            return True
        elif response == 'n':
            return False
        else:
            print('Invalid input. Please enter Y or N.')

def blackjack():
    """
    Plays a game of Blackjack against the computer
    """
    print('Welcome to Blackjack!')
    name = input('What is your name? ')
    print(f'Hello, {name}!')
    print('The goal of the game is to get as close to 21 points as possible without going over.')
    print('Face cards (Kings, Queens, and Jacks) are worth 10 points.')
    print('Aces are worth 1 or 11 points.')
    print('You will')
    bankroll = 1000  # Starting bankroll
    while True:
        num_decks = int(input('How many decks of cards would you like to play with? '))
        if num_decks < 1:
            print('Invalid input. Please enter a positive number.')
        else:
            break
    deck = create_deck(num_decks)
    shuffle_deck(deck)
    while True:
        bet = get_bet(bankroll)
        player_hand = deal_cards(deck, 2)
        dealer_hand = deal_cards(deck, 2)
        display_hand(player_hand)
        display_hand(dealer_hand, hide_first_card=True)
        while True:
            response = input('Do you want to hit or stand? ').lower()
            if response == 'hit':
                player_hand += deal_cards(deck, 1)
                display_hand(player_hand)
                if get_hand_value(player_hand) > 21:
                    print('Bust! You lose.')
                    bankroll -= bet
                    break
            elif response == 'stand':
                while get_hand_value(dealer_hand) < 17:
                    dealer_hand += deal_cards(deck, 1)
                    display_hand(dealer_hand, hide_first_card=True)
                if get_hand_value(dealer_hand) > 21:
                    print('Dealer busts! You win.')
                    bankroll += bet
                    break
                elif get_hand_value(dealer_hand) == get_hand_value(player_hand):
                    print('Push!')
                    break
                elif get_hand_value(dealer_hand) > get_hand_value(player_hand):
                    print('Dealer wins.')
                    bankroll -= bet
                    break
                else:
                    print('You win!')
                    bankroll += bet
                    break
            else:
                print('Invalid input. Please enter hit or stand.')
        if bankroll == 0:
            print('Game over! You are out of money.')
            break
        if not play_again():
            break

blackjack()
