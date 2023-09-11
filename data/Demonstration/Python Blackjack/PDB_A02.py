import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit
        self.value = self.get_value()

    def get_value(self):
        if self.rank in ["J", "Q", "K"]:
            return 10
        elif self.rank == "A":
            return 11
        else:
            return int(self.rank)

    def __str__(self):
        return f"{self.rank}{self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["♠", "♥", "♦", "♣"]:
            for rank in range(2, 11):
                self.cards.append(Card(str(rank), suit))
            for rank in ["J", "Q", "K", "A"]:
                self.cards.append(Card(rank, suit))
        self.shuffle()

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = sum([card.value for card in self.cards])
        if value > 21:
            for card in self.cards:
                if card.rank == "A":
                    value -= 10
                    if value <= 21:
                        break
        return value

    def is_blackjack(self):
        return len(self.cards) == 2 and self.get_value() == 21

    def is_bust(self):
        return self.get_value() > 21

    def __str__(self):
        return ", ".join([str(card) for card in self.cards])

class Blackjack:
    def __init__(self):
        self.deck = Deck()

    def start_game(self):
        print("Welcome to Blackjack!")
        self.player_hand = Hand()
        self.dealer_hand = Hand()

        # Deal initial cards
        for i in range(2):
            self.player_hand.add_card(self.deck.draw_card())
            self.dealer_hand.add_card(self.deck.draw_card())

        # Show initial hands
        print(f"Player's hand: {self.player_hand}")
        print(f"Dealer's hand: {self.dealer_hand.cards[0]}")

        # Player's turn
        while True:
            if self.player_hand.is_blackjack():
                print("Blackjack! You win!")
                return
            elif self.player_hand.is_bust():
                print("Bust! You lose.")
                return
            else:
                action = input("Do you want to hit or stand? ").lower()
                if action == "hit":
                    self.player_hand.add_card(self.deck.draw_card())
                    print(f"Player's hand: {self.player_hand}")
                elif action == "stand":
                    break
                else:
                    print("Invalid input. Please try again.")

        # Dealer's turn
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
        print(f"Dealer's hand: {self.dealer_hand}")

        # Determine winner
        if self.dealer_hand.is_bust() or self.player_hand.get_value() > self.dealer_hand.get_value():
            print("You win!")
        elif self.player_hand.get_value() == self.dealer_hand.get_value():
            print("It's a tie!")
        else:
            print("Dealer wins!")

if __name__ == "__main__":
    game = Blackjack()
    game.start_game()
