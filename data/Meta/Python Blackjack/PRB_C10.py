import random

# Define global variables
suits = ['Hearts', 'Diamonds', 'Spades', 'Clubs']
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
values = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define classes
class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = values[rank]

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    def __init__(self, num_decks):
        self.cards = []
        for i in range(num_decks):
            for suit in suits:
                for rank in ranks:
                    self.cards.append(Card(suit, rank))
        self.shuffle()

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

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

class Chips:
    def __init__(self):
        self.total = 100
        self.bet = 0

    def win_bet(self):
        self.total += self.bet

    def lose_bet(self):
        self.total -= self.bet

# Define functions
def take_bet(chips):
    while True:
        try:
            chips.bet = int(input("How many chips would you like to bet? "))
        except ValueError:
            print("Please enter a valid integer.")
        else:
            if chips.bet > chips.total:
                print(f"You do not have enough chips! You have {chips.total} chips.")
            else:
                break

def hit(deck, hand):
    hand.add_card(deck.deal_card())
    hand.adjust_for_ace()

def hit_or_stand(deck, hand):
    global playing
    while True:
        move = input("Would you like to hit or stand? Enter 'h' or 's': ")
        if move[0].lower() == 'h':
            hit(deck, hand)
            print(f"Your hand: {', '.join([str(card) for card in player_hand.cards])}")
            if player_hand.value > 21:
                print("You busted!")
                #chips.lose_bet()
                playing = False
                break
        elif move[0].lower() == 's':
            print("You stand. Dealer's turn.")
            playing = False
            break
        else:
            print("Please enter 'h' or 's'.")

def show_some(player, dealer):
    print("Dealer's hand:")
    print(f"{dealer.cards[1]}\n\n")
    print("Your hand:")
    print(f"{', '.join([str(card) for card in player.cards])}\n")

def show_all(player, dealer):
    print("Dealer's hand:")
    print(f"{', '.join([str(card) for card in dealer.cards])}\n")
    print("Your hand:")
    print(f"{', '.join([str(card) for card in player.cards])}\n")

def player_wins(chips):
    print("You win!")
    chips.win_bet()

def player_loses(chips):
    print("Dealer wins!")
    chips.lose_bet()

def tie():
    print("It's a tie!")

# Start game
print("Welcome to Blackjack!")
player_name = input("What's your name? ")
print(f"Hi {player_name}! Here are the rules:\n\
        - The goal of the game is to have a hand value closer to 21 than the dealer's hand value without going over 21.\n\
        - Cards 2-10 are worth their face value, face cards (Jack, Queen, King) are worth 10, and Aces are worth 1 or 11.\n\
        - At the start of the game, you and the dealer will each be dealt two cards. One of the dealer's cards will be face down.\n\
        - You will then have the option to 'hit' (draw another card) or 'stand' (keep your current hand).\n\
        - If you go over 21, you lose (bust).\n\
        - Once you stand, the dealer will reveal their face down card and continue to draw cards until their hand value is at least 17.\n\
        - If the dealer goes over 21, you win.\n\
        - If both you and the dealer have the same hand value, it's a tie.\n\
        - You will start with 100 chips. Good luck!\n")

while True:
    num_decks = int(input("How many decks would you like to play with? "))
    if num_decks < 1:
        print("Please enter a valid number of decks.")
    else:
        break

player_chips = Chips()

while True:
    # Set up game
    deck = Deck(num_decks)
    player_hand = Hand()
    dealer_hand = Hand()
    for i in range(2):
        player_hand.add_card(deck.deal_card())
        dealer_hand.add_card(deck.deal_card())
    playing = True

    # Prompt player for bet
    take_bet(player_chips)

    # Show cards
    show_some(player_hand, dealer_hand)

    while playing:
        # Prompt player to hit or stand
        hit_or_stand(deck, player_hand)

        # Show cards
        show_some(player_hand, dealer_hand)

    # Dealer's turn
    while dealer_hand.value < 17:
        hit(deck, dealer_hand)

    # Show all cards
    show_all(player_hand, dealer_hand)

    # Determine winner
    if dealer_hand.value > 21:
        player_wins(player_chips)
    elif player_hand.value > 21:
        player_loses(player_chips)
    elif player_hand.value > dealer_hand.value:
        player_wins(player_chips)
    elif player_hand.value < dealer_hand.value:
        player_loses(player_chips)
    else:
        tie()

    # Show total chips
    print(f"\n{player_name}'s total chips: {player_chips.total}\n")

    # Ask player if they want to play again
    if player_chips.total == 0:
        print("You're out of chips! Better luck next time.")
        break
    elif input("Would you like to play again? Enter 'y' or 'n': ").lower() == 'n':
        break
    else:
        print("Let's play again!\n")

print(f"Thanks for playing, {player_name}!")

