import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def value(self):
        if self.rank == 'A':
            return 11
        elif self.rank in ['K', 'Q', 'J']:
            return 10
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        self.cards = []
        for rank in ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']:
            for suit in ['♠', '♥', '♦', '♣']:
                self.cards.append(Card(rank, suit))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        if len(self.cards) == 0:
            return None
        else:
            return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def value(self):
        val = 0
        aces = 0
        for card in self.cards:
            if card.rank == 'A':
                aces += 1
            val += card.value()
        while aces > 0 and val > 21:
            val -= 10
            aces -= 1
        return val

    def __str__(self):
        return ", ".join(str(card.rank) + card.suit for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.player_wins = False
        self.player_busts = False
        self.dealer_busts = False
        self.dealer_stands = False

    def play(self):
        self.player_hand.add_card(self.deck.draw_card())
        self.dealer_hand.add_card(self.deck.draw_card())
        self.player_hand.add_card(self.deck.draw_card())
        self.dealer_hand.add_card(self.deck.draw_card())

        # Check for player blackjack
        if self.player_hand.value() == 21:
            self.player_wins = True
            return

        while True:
            # Player's turn
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                self.player_hand.add_card(self.deck.draw_card())
                print("Your hand:", self.player_hand)
                if self.player_hand.value() > 21:
                    self.player_busts = True
                    return
            elif action.lower() == 'stand':
                break
            else:
                print("Invalid action. Please try again.")

        # Dealer's turn
        self.dealer_hand.cards[1].hidden = False
        print("Dealer's hand:", self.dealer_hand)
        while self.dealer_hand.value() < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
            print("Dealer hits:", self.dealer_hand)
            if self.dealer_hand.value() > 21:
                self.dealer_busts = True
                return
        self.dealer_stands = True

        # Compare hands
        player_val = self.player_hand.value()
        dealer_val = self.dealer_hand.value()
        if player_val > dealer_val:
            self.player_wins = True
        elif player_val < dealer_val:
            pass
        else:
            # If player and dealer have the same value, it's a tie and no one wins or loses.
            print("It's a tie!")
            return
        # If we reach this point, it means the dealer won
        return

game = Blackjack()
game.play()
