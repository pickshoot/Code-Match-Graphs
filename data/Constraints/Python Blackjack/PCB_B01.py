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
            for value in range(2, 11):
                self.cards.append(Card(suit, str(value)))
            for value in ["J", "Q", "K", "A"]:
                self.cards.append(Card(suit, value))

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
            if card.value.isdigit():
                self.value += int(card.value)
            else:
                if card.value == "A":
                    has_ace = True
                    self.value += 11
                else:
                    self.value += 10
        if has_ace and self.value > 21:
            self.value -= 10

    def get_value(self):
        self.calculate_value()
        return self.value

    def display(self):
        if self.dealer:
            print("Dealer's Hand:")
            print(" <hidden card>")
            print("", self.cards[1])
        else:
            print("Player's Hand:")
            for card in self.cards:
                print("", card)
            print("Total value:", self.get_value())

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand(dealer=True)

    def deal_initial_cards(self):
        self.player_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())

    def player_turn(self):
        while True:
            self.player_hand.display()
            choice = input("Do you want to hit or stand? ").lower()
            if choice == "hit":
                self.player_hand.add_card(self.deck.deal())
                self.player_hand.calculate_value()
                if self.player_hand.get_value() > 21:
                    print("Bust! You lose.")
                    return "lose"
            elif choice == "stand":
                break

    def dealer_turn(self):
        self.dealer_hand.display()
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal())
            self.dealer_hand.calculate_value()
            self.dealer_hand.display()
        if self.dealer_hand.get_value() > 21:
            print("Dealer bust! You win.")
            return "win"

    def compare_hands(self):
        player_score = self.player_hand.get_value()
        dealer_score = self.dealer_hand.get_value()
        if player_score > dealer_score:
            print("You win!")
            return "win"
        elif player_score < dealer_score:
            print("Dealer wins.")
            return "lose"
        else:
            print("It's a tie!")
            return "tie"

    def play(self):
        print("Welcome to Blackjack!")
        self.deal_initial_cards()
        self.player_turn()
        if self.player_hand.get_value() <= 21:
            self.dealer_turn()
            if self.dealer_hand.get_value() <= 21:
                self.compare_hands()

if __name__ == "__main__":
    game = Blackjack()
    game.play()