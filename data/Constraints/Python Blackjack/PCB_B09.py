import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __repr__(self):
        return "{} of {}".format(self.value, self.suit)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for value in range(1, 14):
                card = Card(suit, value)
                self.cards.append(card)
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += self.get_card_value(card)

    def get_card_value(self, card):
        if card.value in [11, 12, 13]:
            return 10
        elif card.value == 1:
            return 11
        else:
            return card.value

    def adjust_for_ace(self):
        while self.value > 21 and self.has_ace():
            self.value -= 10

    def has_ace(self):
        return any(card.value == 1 for card in self.cards)

class Game:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        for _ in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while self.player_hand.value < 21:
            print("Player's hand: ", self.player_hand.cards)
            print("Player's value: ", self.player_hand.value)
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.player_hand.add_card(self.deck.deal_card())
                self.player_hand.adjust_for_ace()
            else:
                break

    def dealer_turn(self):
        while self.dealer_hand.value < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
            self.dealer_hand.adjust_for_ace()

    def show_hands(self, reveal_dealer_hand=False):
        print("Player's hand: ", self.player_hand.cards)
        print("Player's value: ", self.player_hand.value)
        if reveal_dealer_hand:
            print("Dealer's hand: ", self.dealer_hand.cards)
            print("Dealer's value: ", self.dealer_hand.value)
        else:
            print("Dealer's hand: [Hidden]")

    def get_winner(self):
        if self.player_hand.value > 21:
            return "Dealer"
        elif self.dealer_hand.value > 21:
            return "Player"
        elif self.player_hand.value > self.dealer_hand.value:
            return "Player"
        elif self.dealer_hand.value > self.player_hand.value:
            return "Dealer"
        else:
            return "Push"

    def play_game(self):
        self.deal_initial_cards()
        self.show_hands()
        self.player_turn()
        if self.player_hand.value <= 21:
            self.dealer_turn()
            self.show_hands(reveal_dealer_hand=True)
            winner = self.get_winner()
            print("Winner: ", winner)

if __name__ == '__main__':
    game = Game()
    game.play_game()