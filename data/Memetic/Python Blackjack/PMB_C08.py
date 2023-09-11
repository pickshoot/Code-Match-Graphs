import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        if rank == 'A':
            self.value = 11
        elif rank in ['K', 'Q', 'J']:
            self.value = 10
        else:
            self.value = int(rank)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ['hearts', 'diamonds', 'clubs', 'spades']:
            for rank in ['A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K']:
                self.cards.append(Card(suit, rank))
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
        if card.rank == 'A':
            self.aces += 1

        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

class Game:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        print('Welcome to Blackjack!')
        self.player_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

        print('Dealer shows:', self.dealer_hand.cards[0].rank)
        print('Your hand:', [card.rank for card in self.player_hand.cards])
        while self.player_hand.value < 21:
            action = input('Do you want to hit or stand? ')
            if action.lower() == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
                print('Your hand:', [card.rank for card in self.player_hand.cards])
            else:
                break

        if self.player_hand.value > 21:
            print('You busted!')
        else:
            while self.dealer_hand.value < 17:
                self.dealer_hand.add_card(self.deck.deal_card())

            print('Dealer hand:', [card.rank for card in self.dealer_hand.cards])
            if self.dealer_hand.value > 21:
                print('Dealer busted! You win!')
            elif self.dealer_hand.value > self.player_hand.value:
                print('Dealer wins!')
            elif self.dealer_hand.value < self.player_hand.value:
                print('You win!')
            else:
                print('It\'s a tie!')

        print('Thanks for playing!')

if __name__ == '__main__':
    game = Game()
    game.play()