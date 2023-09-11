import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(1, 14):
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        aces = 0
        for card in self.cards:
            if card.rank == 1:
                aces += 1
                value += 11
            elif card.rank in [11, 12, 13]:
                value += 10
            else:
                value += card.rank
        while aces > 0 and value > 21:
            value -= 10
            aces -= 1
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_cards(self):
        for _ in range(2):
            self.player_hand.add_card(self.deck.draw_card())
            self.dealer_hand.add_card(self.deck.draw_card())

    def player_turn(self):
        while True:
            print(f"\nYour hand: {self.player_hand}")
            print(f"Your hand value: {self.player_hand.get_value()}")
            if self.player_hand.get_value() > 21:
                print("Bust! You lose.")
                return False
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.player_hand.add_card(self.deck.draw_card())
            elif action == "stand":
                return True
            else:
                print("Invalid input. Please try again.")

    def dealer_turn(self):
        print(f"\nDealer's hand: {self.dealer_hand}")
        print(f"Dealer's hand value: {self.dealer_hand.get_value()}")
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
            print(f"Dealer hits: {self.dealer_hand}")
        if self.dealer_hand.get_value() > 21:
            print("Dealer busts! You win.")
        elif self.dealer_hand.get_value() >= self.player_hand.get_value():
            print("Dealer wins.")
        else:
            print("You win!")

    def play(self):
        print("Welcome to Blackjack!")
        while True:
            self.player_hand = Hand()
            self.dealer_hand = Hand()
            self.deal_cards()
            print(f"\nDealer's face up card: {self.dealer_hand.cards[1]}")
            if self.player_turn():
                self.dealer_turn()
            choice = input("Do you want to play again? (y/n) ")
            if choice.lower() != "y":
                break
        print("Thanks for playing!")

game = Blackjack()
game.play()
