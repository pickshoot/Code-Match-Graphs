import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for value in range(1, 14):
                self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        num_aces = 0
        for card in self.cards:
            if card.value == 1:
                num_aces += 1
                value += 11
            elif card.value >= 10:
                value += 10
            else:
                value += card.value
        while num_aces > 0 and value > 21:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ', '.join(str(card.value) + ' of ' + card.suit for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

    def play(self):
        print("Player's hand: {}".format(self.player_hand))
        print("Dealer's hand: {}".format(self.dealer_hand.cards[0]))
        while True:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
                print("Player's hand: {}".format(self.player_hand))
                if self.player_hand.get_value() > 21:
                    print("Player busts! Dealer wins.")
                    return
            elif choice.lower() == 'stand':
                while self.dealer_hand.get_value() < 17:
                    self.dealer_hand.add_card(self.deck.deal_card())
                print("Dealer's hand: {}".format(self.dealer_hand))
                if self.dealer_hand.get_value() > 21:
                    print("Dealer busts! Player wins.")
                    return
                elif self.dealer_hand.get_value() >= self.player_hand.get_value():
                    print("Dealer wins.")
                    return
                else:
                    print("Player wins!")
                    return
            else:
                print("Invalid choice. Please choose hit or stand.")

if __name__ == '__main__':
    game = Blackjack()
    game.play()
