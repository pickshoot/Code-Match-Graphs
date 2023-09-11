import random

# Define global variables
SUITS = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
RANKS = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
VALUES = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define Card class
class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = VALUES[rank]

    def __str__(self):
        return f'{self.rank} of {self.suit}'

# Define Deck class
class Deck:
    def __init__(self):
        self.cards = []
        for suit in SUITS:
            for rank in RANKS:
                card = Card(suit, rank)
                self.cards.append(card)

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

# Define Hand class
class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += card.value
        if card.rank == 'Ace':
            self.aces += 1

    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

# Define Chips class
class Chips:
    def __init__(self, total=100):
        self.total = total
        self.bet = 0

    def win_bet(self):
        self.total += self.bet

    def lose_bet(self):
        self.total -= self.bet

# Define functions for game logic
def take_bet(chips):
    while True:
        try:
            chips.bet = int(input('How many chips would you like to bet? '))
        except ValueError:
            print('Please enter an integer value.')
        else:
            if chips.bet > chips.total:
                print(f'Your bet cannot exceed {chips.total}.')
            else:
                break

def hit(deck, hand):
    hand.add_card(deck.deal_card())
    hand.adjust_for_ace()

def hit_or_stand(deck, hand):
    global playing
    while True:
        decision = input('Would you like to hit or stand? Enter "h" or "s". ')
        if decision.lower() == 'h':
            hit(deck, hand)
            print(f'\nPlayer drew: {hand.cards[-1]}')
            print(f'Player hand value: {hand.value}\n')
            if hand.value > 21:
                print('Player busts!')
                return 'bust'
        elif decision.lower() == 's':
            print('Player stands. Dealer is playing.')
            return 'stand'
        else:
            print('Invalid input. Please enter "h" or "s".')

def show_some(player_hand, dealer_hand):
    print('Dealer hand:')
    print('One card hidden!')
    print(dealer_hand.cards[1])
    print('\nPlayer hand:')
    for card in player_hand.cards:
        print(card)

def show_all(player_hand, dealer_hand):
    print('Dealer hand:')
    for card in dealer_hand.cards:
        print(card)
    print(f'Dealer hand value: {dealer_hand.value}')
    print('\nPlayer hand:')
    for card in player_hand.cards:
        print(card)
    print(f'Player hand value: {player_hand.value}')

def player_busts(chips):
    print('Player busts! Dealer wins.')
    chips.lose_bet()

def player_wins(chips):
    print('Player wins!')
    chips.win_bet()

def dealer_busts(chips):
    print('Dealer busts! Player wins.')
    chips.win_bet()

def dealer_wins(chips):
    print('Dealer wins!')
    chips.lose_bet()

def push():
    print('It\'s a tie!')

# Start game loop
while True:
    # Print welcome message
    print('Welcome to Blackjack!\n')

    # Create deck and shuffle
    deck = Deck()
    deck.shuffle()

    # Create player hand and deal two cards
    player_hand = Hand()
    player_hand.add_card(deck.deal_card())
    player_hand.add_card(deck.deal_card())

    # Create dealer hand and deal two cards
    dealer_hand = Hand()
    dealer_hand.add_card(deck.deal_card())
    dealer_hand.add_card(deck.deal_card())

    # Create player chips and take bet
    player_chips = Chips()
    take_bet(player_chips)

    # Show initial hands
    show_some(player_hand, dealer_hand)
    playing=True
    # Check for player blackjack
    if player_hand.value == 21:
        print('Player has blackjack! Player wins.')
        player_chips.win_bet()
        playing = False

    # Start game loop
    while playing:
        # Ask player to hit or stand
        decision = hit_or_stand(deck, player_hand)
        if decision == 'stand':
            # Reveal dealer's face-down card
            show_all(player_hand, dealer_hand)
            # Check for dealer blackjack
            if dealer_hand.value == 21:
                print('Dealer has blackjack! Dealer wins.')
                player_chips.lose_bet()
            else:
                # Dealer hits until hand value is 17 or higher
                while dealer_hand.value < 17:
                    hit(deck, dealer_hand)
                    print(f'Dealer drew: {dealer_hand.cards[-1]}')
                    print(f'Dealer hand value: {dealer_hand.value}\n')
                # Check for dealer bust
                if dealer_hand.value > 21:
                    dealer_busts(player_chips)
                # Check for winner
                elif dealer_hand.value > player_hand.value:
                    dealer_wins(player_chips)
                elif player_hand.value > dealer_hand.value:
                    player_wins(player_chips)
                else:
                    push()
            # End game
            playing = False

    # Print player's remaining chips
    print(f'\nPlayer\'s remaining chips: {player_chips.total}')

    # Ask player if they want to play again
    decision = input('\nWould you like to play again? Enter "y" or "n". ')
    if decision.lower() == 'y':
        playing = True
        continue
    else:
        print('Thanks for playing!')
        break

