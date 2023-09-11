import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def get_value(self):
        if self.rank in ["J", "Q", "K"]:
            return 10
        elif self.rank == "A":
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        self.cards = []
        self.reset()

    def reset(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(str(rank), suit))
            for rank in ["J", "Q", "K", "A"]:
                self.cards.append(Card(rank, suit))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        if not self.cards:
            return None
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += card.get_value()
        if card.rank == "A":
            self.aces += 1

    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

class Player:
    def __init__(self):
        self.hand = Hand()

    def hit(self, deck):
        self.hand.add_card(deck.draw_card())
        self.hand.adjust_for_ace()

    def is_busted(self):
        return self.hand.value > 21

class Dealer(Player):
    def __init__(self):
        super().__init__()

    def show_one_card(self):
        print("Dealer's hand:")
        print(" <card hidden>")
        print(f" {self.hand.cards[1]}")

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player()
        self.dealer = Dealer()

    def play(self):
        self.player.hit(self.deck)
        self.dealer.hit(self.deck)
        self.player.hit(self.deck)
        self.dealer.hit(self.deck)

        while True:
            print("Your hand:")
            for card in self.player.hand.cards:
                print(f" {card}")
            print(f"Value: {self.player.hand.value}")
            if self.player.is_busted():
                print("Busted! You lose.")
                return

            print("Dealer's hand:")
            self.dealer.show_one_card()
            if self.dealer.hand.value >= 17:
                break
            print("Dealer hits.")
            self.dealer.hit(self.deck)

        print("Dealer's hand:")
        for card in self.dealer.hand.cards:
            print(f" {card}")
        print(f"Value: {self.dealer.hand.value}")
        if self.dealer.is_busted():
            print("Dealer busted! You win.")
            return

        if self.player.hand.value > self.dealer.hand.value:
            print("You win!")
        elif self.player.hand.value == self.dealer.hand.value:
            print("It's a tie!")
        else:
            print("You lose.")

if __name__ == "__main__":
    print("Welcome to Blackjack!")
    while True:
        game = Blackjack()
        game.play()
        choice = input("Play again? (Y/N)")
        if choice.lower() != "y":
            break
    print("Thanks for playing!")
