import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        if rank in ['K', 'Q', 'J']:
            self.value = 10
        elif rank == 'A':
            self.value = 11
        else:
            self.value = int(rank)

    def __str__(self):
        return f'{self.rank}{self.suit}'

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ['♠', '♡', '♢', '♣']:
            for rank in ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
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

        while self.value > 21 and self.aces > 0:
            self.value -= 10
            self.aces -= 1

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()
    def __str__(self):
        return f'{self.name} has {", ".join(str(card) for card in self.hand.cards)} with a value of {self.hand.value}'

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player('Player')
        self.dealer = Player('Dealer')
    
    def deal_initial_cards(self):
        self.player.hand.add_card(self.deck.draw_card())
        self.dealer.hand.add_card(self.deck.draw_card())
        self.player.hand.add_card(self.deck.draw_card())
        self.dealer.hand.add_card(self.deck.draw_card())

    def hit(self, player):
        player.hand.add_card(self.deck.draw_card())

    def play(self):
        self.deal_initial_cards()
        print(self.player)
        print(self.dealer)
        while True:
            action = input('Do you want to hit or stand? (h/s) ')
            if action == 'h':
                self.hit(self.player)
                print(self.player)
                if self.player.hand.value > 21:
                    print('Bust! You lose!')
                    break
            elif action == 's':
                while self.dealer.hand.value < 17:
                    self.hit(self.dealer)
                print(self.dealer)
                if self.dealer.hand.value > 21:
                    print('Dealer bust! You win!')
                    break
                elif self.player.hand.value > self.dealer.hand.value:
                    print('You win!')
                    break
                elif self.player.hand.value < self.dealer.hand.value:
                    print('You lose!')
                    break
                else:
                    print('Push! It\'s a tie!')
                    break

if __name__ == '__main__':
    game = Blackjack()
    game.play()