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
            for value in ["Jack", "Queen", "King", "Ace"]:
                self.cards.append(Card(suit, value))
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.value == "Ace":
            if self.value + 11 <= 21:
                self.value += 11
            else:
                self.value += 1
        elif card.value in ["Jack", "Queen", "King"]:
            self.value += 10
        else:
            self.value += int(card.value)

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()

    def hit(self, deck):
        card = deck.deal_card()
        self.hand.add_card(card)

    def __str__(self):
        return f"{self.name}: {self.hand}"

class BlackjackGame:
    def __init__(self):
        self.deck = Deck()
        self.player = Player("Player")
        self.dealer = Player("Dealer")

    def play(self):
        print("Welcome to Blackjack!")
        self.player.hit(self.deck)
        self.dealer.hit(self.deck)
        self.player.hit(self.deck)
        self.dealer.hit(self.deck, show=False)

        while True:
            print(self.player)
            choice = input("Would you like to hit or stand? ")
            if choice == "hit":
                self.player.hit(self.deck)
                if self.player.hand.value > 21:
                    print("Bust! You lose.")
                    return
            else:
                break

        while self.dealer.hand.value < 17:
            self.dealer.hit(self.deck)

        print(self.dealer)

        if self.dealer.hand.value > 21:
            print("Dealer busts! You win.")
            return

        if self.player.hand.value > self.dealer.hand.value:
            print("You win!")
        elif self.player.hand.value < self.dealer.hand.value:
            print("You lose.")
        else:
            print("It's a tie.")

if __name__ == "__main__":
    game = BlackjackGame()
    game.play()