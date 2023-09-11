import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __repr__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = [Card(s, v) for s in ["Spades", "Clubs", "Hearts", "Diamonds"] for v in range(1, 14)]

    def shuffle(self):
        if len(self.cards) > 1:
            random.shuffle(self.cards)

    def deal(self):
        if len(self.cards) > 1:
            return self.cards.pop(0)

class Hand:
    def __init__(self, dealer=False):
        self.dealer = dealer
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)

    def calculate_value(self):
        self.value = 0
        has_ace = False
        for card in self.cards:
            if card.value == 1:
                has_ace = True
            self.value += min(card.value, 10)
        if has_ace and self.value <= 11:
            self.value += 10

    def get_value(self):
        self.calculate_value()
        return self.value

    def display(self):
        if self.dealer:
            print("Dealer's Hand:")
            print("<card hidden>")
            print(self.cards[1])
        else:
            print("Player's Hand:")
            for card in self.cards:
                print(card)
            print("Hand Value:", self.get_value())

class Game:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand(dealer=True)

    def play(self):
        # deal initial 2 cards to player and dealer
        for i in range(2):
            self.player_hand.add_card(self.deck.deal())
            self.dealer_hand.add_card(self.deck.deal())

        # display initial hands
        self.player_hand.display()
        self.dealer_hand.display()

        # player's turn
        while self.player_hand.get_value() < 21:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.player_hand.add_card(self.deck.deal())
                self.player_hand.display()
            else:
                break

        player_hand_value = self.player_hand.get_value()

        # dealer's turn
        if player_hand_value <= 21:
            while self.dealer_hand.get_value() < 17:
                self.dealer_hand.add_card(self.deck.deal())
            self.dealer_hand.display()

            # determine winner
            dealer_hand_value = self.dealer_hand.get_value()

            if dealer_hand_value > 21:
                print("Dealer busts! You win!")
            elif dealer_hand_value == player_hand_value:
                print("Push! It's a tie.")
            elif dealer_hand_value > player_hand_value:
                print("Dealer wins!")
            else:
                print("You win!")

        else:
            print("You bust! Dealer wins.")

if __name__ == "__main__":
    game = Game()
    game.play()