import random


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ["Ace", "King", "Queen", "Jack"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank == "Ace":
            self.value += 11
        elif card.rank in ["King", "Queen", "Jack"]:
            self.value += 10
        else:
            self.value += int(card.rank)

        if self.value > 21 and any(card.rank == "Ace" for card in self.cards):
            self.value -= 10
            for card in self.cards:
                if card.rank == "Ace":
                    card.rank = "Ace (1)"
                    break

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
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        print(f"Player's Hand: {self.player_hand}, value = {self.player_hand.value}")
        print(f"Dealer's Hand: {self.dealer_hand.cards[0]}")

        while self.player_hand.value <= 21:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.player_hand.add_card(self.deck.deal())
                print(f"Player's Hand: {self.player_hand}, value = {self.player_hand.value}")
            elif action == "stand":
                break

        if self.player_hand.value > 21:
            print("Player busts. Dealer wins.")
            return

        while self.dealer_hand.value < 17:
            self.dealer_hand.add_card(self.deck.deal())
        print(f"Dealer's Hand: {self.dealer_hand}, value = {self.dealer_hand.value}")

        if self.dealer_hand.value > 21:
            print("Dealer busts. Player wins.")
            return

        if self.player_hand.value > self.dealer_hand.value:
            print("Player wins.")
        elif self.dealer_hand.value > self.player_hand.value:
            print("Dealer wins.")
        else:
            print("It's a tie.")


game = Blackjack()
game.play()