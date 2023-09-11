import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    ranks = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
    suits = ["Hearts", "Diamonds", "Clubs", "Spades"]

    def __init__(self):
        self.cards = [Card(rank, suit) for rank in self.ranks for suit in self.suits]
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
            if card.rank == "Ace":
                num_aces += 1
                value += 11
            elif card.rank in ["King", "Queen", "Jack"]:
                value += 10
            else:
                value += int(card.rank)
        while num_aces > 0 and value > 21:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        print("Let's play Blackjack!")
        for i in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())
        print(f"Dealer's upcard: {self.dealer_hand.cards[0]}")
        print(f"Your hand: {self.player_hand}")
        while True:
            if self.player_hand.get_value() > 21:
                print("You bust!")
                return
            action = input("Do you want to hit or stand? ")
            if action.lower() == "hit":
                self.player_hand.add_card(self.deck.deal_card())
                print(f"Your hand: {self.player_hand}")
            elif action.lower() == "stand":
                break
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        print(f"Dealer's hand: {self.dealer_hand}")
        if self.dealer_hand.get_value() > 21:
            print("Dealer busts! You win!")
        elif self.dealer_hand.get_value() > self.player_hand.get_value():
            print("Dealer wins!")
        elif self.player_hand.get_value() > self.dealer_hand.get_value():
            print("You win!")
        else:
            print("It's a tie!")

if __name__ == "__main__":
    game = Blackjack()
    game.play()
