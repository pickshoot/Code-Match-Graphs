import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value
        
    def __repr__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = [Card(s, v) for s in ["Spades", "Clubs", "Hearts", "Diamonds"] for v in ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]]
        
    def shuffle(self):
        if len(self.cards) > 1:
            random.shuffle(self.cards)
            
    def deal(self):
        if len(self.cards) > 1:
            return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        
    def add_card(self, card):
        self.cards.append(card)
        
    def calculate_value(self):
        self.value = 0
        has_ace = False
        for card in self.cards:
            if card.value == "Ace":
                has_ace = True
            self.value += self.get_card_value(card)
            if has_ace and self.value <= 11:
                self.value += 10

    def get_card_value(self, card):
        if card.value in ["Jack", "Queen", "King"]:
            return 10
        elif card.value in ["Ace"]:
            return 1
        else:
            return int(card.value)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())

    def hit(self, hand):
        hand.add_card(self.deck.deal())
        hand.calculate_value()

    def player_turn(self):
        while self.player_hand.value < 21:
            action = input("Do you want to hit or stand? ")
            if action.lower() == "hit":
                self.hit(self.player_hand)
                print(f"Player's hand: {self.player_hand.cards}, Value: {self.player_hand.value}")
            elif action.lower() == "stand":
                break
        print(f"Player's hand: {self.player_hand.cards}, Value: {self.player_hand.value}")

    def dealer_turn(self):
        while self.dealer_hand.value < 17:
            self.hit(self.dealer_hand)
            print(f"Dealer's hand: {self.dealer_hand.cards}, Value: {self.dealer_hand.value}")

    def determine_winner(self):
        if self.player_hand.value > 21:
            print("Player busts! Dealer wins.")
        elif self.dealer_hand.value > 21:
            print("Dealer busts! Player wins.")
        elif self.player_hand.value > self.dealer_hand.value:
            print("Player wins!")
        elif self.dealer_hand.value > self.player_hand.value:
            print("Dealer wins!")
        else:
            print("It's a tie!")

    def play_game(self):
        print("Let's play Blackjack!")
        self.deal_initial_cards()
        print(f"Player's hand: {self.player_hand.cards}, Value: {self.player_hand.value}")
        print(f"Dealer's hand: [{self.dealer_hand.cards[0]}, ***]")
        self.player_turn()
        if self.player_hand.value <= 21:
            self.dealer_turn()
        self.determine_winner()

if __name__ == "__main__":
    game = Blackjack()
    game.play_game()