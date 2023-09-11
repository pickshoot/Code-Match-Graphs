import random


class Card:
    """Represents a playing card."""

    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:
    """Represents a deck of playing cards."""

    def __init__(self):
        self.cards = []
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for rank in range(1, 14):
                card = Card(suit, rank)
                self.cards.append(card)

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()


class Hand:
    """Represents a hand of playing cards."""

    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        num_aces = 0
        for card in self.cards:
            if card.rank == 1:
                num_aces += 1
                value += 11
            elif card.rank > 10:
                value += 10
            else:
                value += card.rank
        while value > 21 and num_aces:
            value -= 10
            num_aces -= 1
        return value


class Blackjack:
    """Represents a game of Blackjack."""

    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        self.player_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        print("Dealer's hand:", self.dealer_hand.cards[0], "and one hidden card")
        print("Your hand:", self.player_hand.cards)
        while True:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
                print("Your hand:", self.player_hand.cards)
                if self.player_hand.get_value() > 21:
                    print("Bust! You lose.")
                    return
                elif choice.lower() == 'stand':
                    break
            while self.dealer_hand.get_value() < 17:
                self.dealer_hand.add_card(self.deck.deal_card())
            print("Dealer's hand:", self.dealer_hand.cards)
            if self.dealer_hand.get_value() > 21:
                print("Dealer busts! You win.")
            elif self.player_hand.get_value() > self.dealer_hand.get_value():
                print("You win!")
            elif self.player_hand.get_value() < self.dealer_hand.get_value():
                print("You lose.")
            else:
                print("It's a tie.")

if __name__ == '__main__':
    game = Blackjack()
    game.play()