import random

# Define constants
SUITS = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
RANKS = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
CARD_VALUES = {'Ace': 11, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define classes
class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit
    
    def __str__(self):
        return self.rank + " of " + self.suit

class Deck:
    def __init__(self):
        self.cards = []
        for suit in SUITS:
            for rank in RANKS:
                self.cards.append(Card(rank, suit))
    
    def shuffle(self):
        random.shuffle(self.cards)
    
    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
    
    def add_card(self, card):
        self.cards.append(card)
        self.value += CARD_VALUES[card.rank]
        if self.value > 21 and 'Ace' in [card.rank for card in self.cards]:
            self.value -= 10
    
    def __str__(self):
        return ', '.join([str(card) for card in self.cards]) + " (value: " + str(self.value) + ")"

class BlackjackGame:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.player_turn = True
    
    def play(self):
        # Deal initial cards
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        
        while True:
            # Print current hands
            print("Player's hand: " + str(self.player_hand))
            print("Dealer's hand: " + str(self.dealer_hand))
            
            if self.player_turn:
                # Player's turn
                if self.player_hand.value == 21:
                    print("Blackjack! Player wins!")
                    return
                elif self.player_hand.value > 21:
                    print("Player busts! Dealer wins!")
                    return
                else:
                    action = input("Hit or stand? ")
                    if action.lower() == 'hit':
                        self.player_hand.add_card(self.deck.deal_card())
                    elif action.lower() == 'stand':
                        self.player_turn = False
                    else:
                        print("Invalid input!")
            else:
                # Dealer's turn
                if self.dealer_hand.value == 21:
                    print("Blackjack! Dealer wins!")
                    return
                elif self.dealer_hand.value > 21:
                    print("Dealer busts! Player wins!")
                    return
                elif self.dealer_hand.value >= 17:
                    if self.dealer_hand.value > self.player_hand.value:
                        print("Dealer wins!")
                        return
                    elif self.dealer_hand.value < self.player_hand.value:
                        print("Player wins!")
                        return
                    else:
                        print("It's a tie!")
                        return
                else:
                    self.dealer_hand.add_card(self.deck.deal_card())

# Create a new game
game = BlackjackGame()

# Play the game
game.play()