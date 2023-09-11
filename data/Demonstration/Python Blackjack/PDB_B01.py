import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def value(self):
        if self.rank in ["Jack", "Queen", "King"]:
            return 10
        elif self.rank == "Ace":
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        ranks = [str(n) for n in range(2, 11)] + ["Jack", "Queen", "King", "Ace"]
        suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
        self.cards = [Card(rank, suit) for rank in ranks for suit in suits]
        random.shuffle(self.cards)

    def draw(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def value(self):
        value = sum(card.value() for card in self.cards)
        if value > 21 and any(card.rank == "Ace" for card in self.cards):
            value -= 10
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class BlackjackGame:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal(self):
        self.player_hand.add_card(self.deck.draw())
        self.dealer_hand.add_card(self.deck.draw())
        self.player_hand.add_card(self.deck.draw())
        self.dealer_hand.add_card(self.deck.draw())

    def player_turn(self):
        while self.player_hand.value() < 21:
            print(f"Your hand: {self.player_hand}")
            print(f"Dealer's upcard: {self.dealer_hand.cards[0]}")
            action = input("Hit or stand? ")
            if action.lower() == "hit":
                self.player_hand.add_card(self.deck.draw())
            else:
                break

    def dealer_turn(self):
        while self.dealer_hand.value() < 17:
            self.dealer_hand.add_card(self.deck.draw())

    def play(self):
        print("Welcome to Blackjack!")
        self.deal()
        print(f"Your hand: {self.player_hand}")
        print(f"Dealer's upcard: {self.dealer_hand.cards[0]}")
        if self.dealer_hand.cards[0].rank == "Ace":
            insurance = input("Would you like insurance? ")
            if insurance.lower() == "yes":
                if self.dealer_hand.value() == 21:
                    print("Dealer has Blackjack! You win 2:1 on your insurance bet.")
                else:
                    print("Dealer does not have Blackjack. You lose your insurance bet.")
        if self.player_hand.value() == 21 and len(self.player_hand.cards) == 2:
            print("Blackjack! You win!")
        else:
            self.player_turn()
            if self.player_hand.value() > 21:
                print("Bust! You lose.")
            else:
                self.dealer_turn()
                print(f"Your hand: {self.player_hand}")
                print(f"Dealer's hand: {self.dealer_hand}")
                if self.dealer_hand.value() > 21:
                    print("Dealer busts! You win.")
                elif self.dealer_hand.value() > self.player_hand.value():
                    print("Dealer wins.")
                elif self.player_hand.value() > self.dealer_hand.value():
                    print("You win!")
                else:
                    print("Push.")

game = BlackjackGame()
game.play()
