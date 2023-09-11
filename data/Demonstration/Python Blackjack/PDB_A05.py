import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __str__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        self.build()

    def build(self):
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for value in range(1, 14):
                self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        return self.cards.pop()

class Player:
    def __init__(self):
        self.hand = []
        self.score = 0

    def hit(self, card):
        self.hand.append(card)
        self.score += card.value

    def show_hand(self):
        print("Player's hand:")
        for card in self.hand:
            print(card)
        print("Score:", self.score)

class Dealer:
    def __init__(self):
        self.hand = []
        self.score = 0

    def hit(self, card):
        self.hand.append(card)
        self.score += card.value

    def show_hand(self):
        print("Dealer's hand:")
        print(self.hand[0])
        print("<hidden card>")

class Game:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player()
        self.dealer = Dealer()

    def start(self):
        print("Welcome to Blackjack!")
        self.deal()
        self.player.show_hand()
        self.dealer.show_hand()
        self.player_turn()

    def deal(self):
        self.player.hit(self.deck.draw_card())
        self.player.hit(self.deck.draw_card())
        self.dealer.hit(self.deck.draw_card())
        self.dealer.hit(self.deck.draw_card())

    def player_turn(self):
        while self.player.score < 21:
            choice = input("Would you like to hit or stand? ")
            if choice.lower() == "hit":
                self.player.hit(self.deck.draw_card())
                self.player.show_hand()
            elif choice.lower() == "stand":
                self.dealer_turn()
                break

    def dealer_turn(self):
        self.dealer.show_hand()
        while self.dealer.score < 17:
            self.dealer.hit(self.deck.draw_card())
            self.dealer.show_hand()
        self.determine_winner()

    def determine_winner(self):
        if self.player.score > 21:
            print("You busted! Dealer wins.")
        elif self.dealer.score > 21:
            print("Dealer busted! You win.")
        elif self.player.score > self.dealer.score:
            print("You win!")
        elif self.dealer.score > self.player.score:
            print("Dealer wins.")
        else:
            print("It's a tie.")

game = Game()
game.start()
