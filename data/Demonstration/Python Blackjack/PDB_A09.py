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
            for value in range(1, 14):
                self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw_card(self):
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        num_aces = 0
        for card in self.cards:
            if card.value == 1:
                num_aces += 1
                value += 11
            elif card.value >= 10:
                value += 10
            else:
                value += card.value
        while value > 21 and num_aces > 0:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.game_over = False

    def deal_initial_cards(self):
        for _ in range(2):
            self.player_hand.add_card(self.deck.draw_card())
            self.dealer_hand.add_card(self.deck.draw_card())

    def player_turn(self):
        while True:
            print("Your hand: ", self.player_hand)
            print("Your hand value: ", self.player_hand.get_value())
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == "hit":
                self.player_hand.add_card(self.deck.draw_card())
                print("You drew a ", self.player_hand.cards[-1])
                if self.player_hand.get_value() > 21:
                    print("Bust! You lose.")
                    self.game_over = True
                    return
            else:
                print("You stand with a hand value of ", self.player_hand.get_value())
                return

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
            print("Dealer drew a ", self.dealer_hand.cards[-1])
        print("Dealer's hand: ", self.dealer_hand)
        print("Dealer's hand value: ", self.dealer_hand.get_value())

    def check_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if player_value > 21:
            print("Bust! You lose.")
        elif dealer_value > 21:
            print("Dealer busts! You win.")
        elif player_value > dealer_value:
            print("You win!")
        elif dealer_value > player_value:
            print("Dealer wins!")
        else:
            print("It's a tie.")
        self.game_over = True

    def play(self):
        self.deal_initial_cards()
        print("Dealer's face-up card: ", self.dealer_hand.cards[1])
        self.player_turn()
        if not self.game_over:
            self.dealer_turn()
            if not self.game_over:
                self.check_winner()

blackjack = Blackjack()
print("Welcome")
blackjack.play()
