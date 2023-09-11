import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return self.rank + " of " + self.suit

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        has_ace = False
        for card in self.cards:
            if card.rank in ["Jack", "Queen", "King"]:
                value += 10
            elif card.rank == "Ace":
                has_ace = True
                value += 11
            else:
                value += int(card.rank)
        if has_ace and value > 21:
            value -= 10
        return value

    def __str__(self):
        cards_str = ""
        for card in self.cards:
            cards_str += str(card) + "\n"
        return cards_str

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        for i in range(2):
            self.player_hand.add_card(self.deck.deal())
            self.dealer_hand.add_card(self.deck.deal())

    def player_turn(self):
        while True:
            print("Your hand:")
            print(self.player_hand)
            print("Your current total is:", self.player_hand.get_value())
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == "hit":
                self.player_hand.add_card(self.deck.deal())
                if self.player_hand.get_value() > 21:
                    print("You bust!")
                    return False
            elif choice.lower() == "stand":
                return True
            else:
                print("Invalid choice. Please choose hit or stand.")

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal())
        print("Dealer's hand:")
        print(self.dealer_hand)
        print("Dealer's current total is:", self.dealer_hand.get_value())
        if self.dealer_hand.get_value() > 21:
            print("Dealer busts! You win!")
        elif self.dealer_hand.get_value() > self.player_hand.get_value():
            print("Dealer wins!")
        elif self.dealer_hand.get_value() == self.player_hand.get_value():
            print("It's a tie!")
        else:
            print("You win!")

    def play(self):
        print("Welcome to Blackjack!")
        self.deal_initial_cards()
        if self.player_hand.get_value() == 21:
            print("Blackjack! You win!")
        else:
            player_turn_result = self.player_turn()
            if player_turn_result:
                self.dealer_turn()

        print("Thanks for playing!")

game = Blackjack()
game.play()
