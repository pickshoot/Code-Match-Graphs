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
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for value in range(2, 11):
                self.cards.append(Card(suit, str(value)))
            for value in ["J", "Q", "K", "A"]:
                self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
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
            if card.value == "A":
                num_aces += 1
                value += 11
            elif card.value in ["K", "Q", "J"]:
                value += 10
            else:
                value += int(card.value)
        while num_aces > 0 and value > 21:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        print("Welcome to Blackjack!")
        for i in range(2):
            self.player_hand.add_card(self.deck.deal())
            self.dealer_hand.add_card(self.deck.deal())
        print(f"Your hand: {self.player_hand}")
        print(f"Dealer's hand: {self.dealer_hand.cards[0]}")
        while True:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == "hit":
                self.player_hand.add_card(self.deck.deal())
                print(f"Your hand: {self.player_hand}")
                if self.player_hand.get_value() > 21:
                    print("You busted! Dealer wins.")
                    return
            elif choice.lower() == "stand":
                break
            else:
                print("Invalid choice. Please try again.")
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal())
        print(f"Dealer's hand: {self.dealer_hand}")
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if dealer_value > 21:
            print("Dealer busted! You win.")
        elif player_value > dealer_value:
            print("You win!")
        elif player_value < dealer_value:
            print("Dealer wins.")
        else:
            print("It's a tie.")

if __name__ == "__main__":
    game = Blackjack()
    game.play()