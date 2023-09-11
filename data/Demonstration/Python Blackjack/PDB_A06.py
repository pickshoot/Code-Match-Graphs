import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = self.get_value()

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def get_value(self):
        if self.rank in ["JACK", "QUEEN", "KING"]:
            return 10
        elif self.rank == "ACE":
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in ["ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        ace_count = 0
        for card in self.cards:
            if card.rank == "ACE":
                ace_count += 1
            value += card.value
        while value > 21 and ace_count > 0:
            value -= 10
            ace_count -= 1
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        for i in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while self.player_hand.get_value() < 21:
            print(f"Your hand: {self.player_hand}")
            choice = input("Hit or stand? (h/s)").lower()
            if choice == "h":
                self.player_hand.add_card(self.deck.deal_card())
            elif choice == "s":
                break
        print(f"Your hand: {self.player_hand}")

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        print(f"Dealer's hand: {self.dealer_hand}")

    def get_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if player_value > 21:
            return "Dealer"
        elif dealer_value > 21:
            return "Player"
        elif player_value > dealer_value:
            return "Player"
        elif dealer_value > player_value:
            return "Dealer"
        else:
            return "Tie"

    def play(self):
        print("Welcome to Blackjack!")
        self.deal_initial_cards()
        print(f"Your hand: {self.player_hand}")
        print(f"Dealer's hand: {self.dealer_hand.cards[0]}")
        self.player_turn()
        if self.player_hand.get_value() <= 21:
            self.dealer_turn()
        winner = self.get_winner()
        print(f"{winner} wins!")

if __name__ == "__main__":
    game = Blackjack()
    game.play()
