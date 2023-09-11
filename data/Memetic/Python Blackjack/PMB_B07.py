import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    ranks = [str(n) for n in range(2, 11)] + ['J', 'Q', 'K', 'A']
    suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']

    def __init__(self):
        self.cards = [Card(rank, suit) for rank in self.ranks for suit in self.suits]
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.total = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        self.total += self.card_value(card)

        if card.rank == 'A':
            self.aces += 1

    def card_value(self, card):
        if card.rank in ['J', 'Q', 'K']:
            return 10
        elif card.rank == 'A':
            return 11
        else:
            return int(card.rank)

    def adjust_for_ace(self):
        while self.total > 21 and self.aces:
            self.total -= 10
            self.aces -= 1

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()

    def __str__(self):
        return f"{self.name}: {', '.join(str(card) for card in self.hand.cards)}"

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.player = Player("Player")
        self.dealer = Player("Dealer")

    def play(self):
        print("Welcome to Blackjack!")
        print("=====================")
        self.player.hand.add_card(self.deck.deal())
        self.player.hand.add_card(self.deck.deal())
        self.dealer.hand.add_card(self.deck.deal())
        self.dealer.hand.add_card(self.deck.deal())

        while True:
            print(f"\n{self.player}\n")
            if self.player.hand.total == 21:
                print("Blackjack! You win!")
                break
            elif self.player.hand.total > 21:
                print("Bust! You lose.")
                break

            choice = input("Hit or stand? ")
            if choice.lower() == 'hit':
                self.player.hand.add_card(self.deck.deal())
            elif choice.lower() == 'stand':
                while self.dealer.hand.total < 17:
                    self.dealer.hand.add_card(self.deck.deal())
                print(f"\n{self.dealer}\n")
                if self.dealer.hand.total > 21:
                    print("Dealer bust! You win!")
                    break
                elif self.dealer.hand.total > self.player.hand.total:
                    print("Dealer wins!")
                    break
                elif self.dealer.hand.total < self.player.hand.total:
                    print("You win!")
                    break
                else:
                    print("Push! It's a tie.")
                    break

if __name__ == '__main__':
    game = Blackjack()
    game.play()