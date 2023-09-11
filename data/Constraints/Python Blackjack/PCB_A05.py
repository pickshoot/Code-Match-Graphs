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
        value = 0
        has_ace = False
        for card in self.cards:
            if card.rank == "Ace":
                has_ace = True
            value += min(10, int(card.rank))
        if has_ace and value <= 11:
            value += 10
        return value

    def __str__(self):
        cards_string = ""
        for card in self.cards:
            cards_string += str(card) + "\n"
        return cards_string

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_cards(self):
        for i in range(2):
            self.player_hand.add_card(self.deck.deal_card())
            self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while self.player_hand.get_value() < 21:
            print(f"\nYour hand:\n{self.player_hand}\n")
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == "hit":
                self.player_hand.add_card(self.deck.deal_card())
            elif choice.lower() == "stand":
                break
            else:
                print("Invalid choice. Please choose 'hit' or 'stand'.")

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())

    def show_hands(self, show_dealer_hand=False):
        print(f"\nDealer's hand:\n{self.dealer_hand if show_dealer_hand else 'Hidden'}\n")
        print(f"Your hand:\n{self.player_hand}\n")

    def check_win(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()

        if player_value > 21:
            print("You busted! Dealer wins.")
        elif dealer_value > 21:
            print("Dealer busted! You win.")
        elif player_value > dealer_value:
            print("You win!")
        elif dealer_value > player_value:
            print("Dealer wins.")
        else:
            print("It's a tie!")

    def play(self):
        self.deal_cards()
        self.show_hands()
        self.player_turn()

        if self.player_hand.get_value() <= 21:
            self.dealer_turn()
            self.show_hands(show_dealer_hand=True)
            self.check_win()

if __name__ == "__main__":
    print("Welcome to Blackjack!")
    while True:
        game = Blackjack()
        game.play()
        choice = input("Do you want to play again? (y/n) ")
        if choice.lower() != "y":
            break
    print("Thanks for playing!")