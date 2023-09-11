import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = self._get_value()

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def _get_value(self):
        if self.rank in ["Jack", "Queen", "King"]:
            return 10
        elif self.rank == "Ace":
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        self.cards = []
        self._create_deck()

    def _create_deck(self):
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ["Jack", "Queen", "King", "Ace"]:
                self.cards.append(Card(suit, rank))

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
        value = sum([card.value for card in self.cards])
        if value > 21 and any([card.rank == "Ace" for card in self.cards]):
            value -= 10
        return value

    def __str__(self):
        return ", ".join([str(card) for card in self.cards])

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def start_game(self):
        for i in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while True:
            print(f"Your hand: {self.player_hand}")
            print(f"Dealer's hand: {self.dealer_hand.cards[0]}")
            if self.player_hand.get_value() > 21:
                print("Bust! You lose.")
                return False
            elif self.player_hand.get_value() == 21:
                print("21! You win.")
                return True
            else:
                choice = input("Hit or stand? ")
                if choice.lower() == "hit":
                    self.player_hand.add_card(self.deck.deal_card())
                else:
                    return None

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        print(f"Dealer's hand: {self.dealer_hand}")
        if self.dealer_hand.get_value() > 21:
            print("Dealer bust! You win.")
            return True
        elif self.dealer_hand.get_value() >= self.player_hand.get_value():
            print("Dealer wins.")
            return False
        else:
            print("You win!")
            return True

    def play_game(self):
        self.start_game()
        self.player_turn()
        if self.player_hand.get_value() <= 21:
            self.dealer_turn()

if __name__ == "__main__":
    game = Blackjack()
    game.play_game()