import random

#Card suits and values
SUITS = ["Hearts", "Diamonds", "Clubs", "Spades"]
VALUES = {"Ace": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "Jack": 10, "Queen": 10, "King": 10}

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __str__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        for suit in SUITS:
            for value in VALUES:
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
        has_ace = False
        for card in self.cards:
            value += VALUES[card.value]
            if card.value == "Ace":
                has_ace = True
        if has_ace and value + 10 <= 21:
            value += 10
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class BlackjackGame:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        # Deal initial cards
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

        # Player's turn
        while True:
            print(f"Your cards: {self.player_hand}")
            print(f"Dealer's card: {self.dealer_hand.cards[0]}")
            action = input("Do you want to hit or stand? ").lower()
            if action == "hit":
                self.player_hand.add_card(self.deck.deal_card())
                if self.player_hand.get_value() > 21:
                    print(f"Bust! Your cards: {self.player_hand}")
                    print(f"Dealer's cards: {self.dealer_hand}")
                    return
            elif action == "stand":
                break
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

        # Dealer's turn
        self.dealer_hand.cards[0].hidden = False
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        print(f"Your cards: {self.player_hand}")
        print(f"Dealer's cards: {self.dealer_hand}")
        if self.dealer_hand.get_value() > 21:
            print("Dealer bust! You win.")
        elif self.dealer_hand.get_value() > self.player_hand.get_value():
            print("Dealer wins.")
        elif self.player_hand.get_value() > self.dealer_hand.get_value():
            print("You win!")
        else:
            print("Tie game.")

if __name__ == "__main__":
    game = BlackjackGame()
    game.play()
