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
        suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
        values = ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]
        for suit in suits:
            for value in values:
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
            if card.value == "Ace":
                has_ace = True
            value += self.get_card_value(card)
        if has_ace and value <= 11:
            value += 10
        return value

    def get_card_value(self, card):
        if card.value in ["Jack", "Queen", "King"]:
            return 10
        elif card.value == "Ace":
            return 1
        else:
            return int(card.value)

    def __str__(self):
        cards_string = ""
        for card in self.cards:
            cards_string += str(card) + ", "
        return cards_string[:-2]

class BlackjackGame:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        print("Welcome to Blackjack!")
        for i in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())

        print(f"Player's Hand: {self.player_hand}")
        print(f"Dealer's Hand: {self.dealer_hand.cards[0]}")

        while True:
            player_choice = input("Would you like to hit or stand? ")
            if player_choice.lower() == "hit":
                self.player_hand.add_card(self.deck.deal_card())
                print(f"Player's Hand: {self.player_hand}")
                if self.player_hand.get_value() > 21:
                    print("Bust! You lose.")
                    return
            elif player_choice.lower() == "stand":
                break
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
            print(f"Dealer's Hand: {self.dealer_hand}")
            if self.dealer_hand.get_value() > 21:
                print("Dealer busts! You win!")
                return

        if self.player_hand.get_value() > self.dealer_hand.get_value():
            print("You win!")
        elif self.player_hand.get_value() < self.dealer_hand.get_value():
            print("Dealer wins.")
        else:
            print("It's a tie!")

if __name__ == "__main__":
    game = BlackjackGame()
    game.play()