import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __str__(self):
        return "{} of {}".format(self.value, self.suit)

class Deck:
    def __init__(self):
        self.cards = []
        self.build()

    def build(self):
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for value in range(1, 14):
                self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = []
        self.score = 0

    def draw_card(self, deck):
        card = deck.deal_card()
        self.hand.append(card)
        self.score += card.value

    def __str__(self):
        return "{}'s hand: {}".format(self.name, ", ".join(str(card) for card in self.hand))

class Blackjack:
    def __init__(self, player_name):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player(player_name)
        self.dealer = Player("Dealer")
        self.round_over = False

    def start_game(self):
        print("Welcome to Blackjack!")
        self.player.draw_card(self.deck)
        self.dealer.draw_card(self.deck)
        self.player.draw_card(self.deck)
        self.dealer.draw_card(self.deck)
        self.print_game_state()

    def print_game_state(self):
        print("Dealer's hand: {}".format(self.dealer.hand[0]))
        print(self.player)
        print("Score: {}".format(self.player.score))

    def hit(self):
        self.player.draw_card(self.deck)
        self.print_game_state()
        if self.player.score > 21:
            print("You busted! Dealer wins!")
            self.round_over = True

    def stand(self):
        while self.dealer.score < 17:
            self.dealer.draw_card(self.deck)
        print("Dealer's hand: {}".format(", ".join(str(card) for card in self.dealer.hand)))
        print("Dealer's score: {}".format(self.dealer.score))
        if self.dealer.score > 21 or self.dealer.score < self.player.score:
            print("You win!")
        elif self.dealer.score > self.player.score:
            print("Dealer wins!")
        else:
            print("It's a tie!")
        self.round_over = True

    def play(self):
        self.start_game()
        while not self.round_over:
            action = input("Do you want to hit or stand? ")
            if action.lower() == "hit":
                self.hit()
            elif action.lower() == "stand":
                self.stand()

game = Blackjack("Alice")
game.play()