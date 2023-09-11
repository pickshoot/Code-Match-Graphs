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
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []
        self.total = 0
    
    def add_card(self, card):
        self.cards.append(card)
        if card.rank == "Ace":
            if self.total + 11 <= 21:
                self.total += 11
            else:
                self.total += 1
        elif card.rank in ["King", "Queen", "Jack"]:
            self.total += 10
        else:
            self.total += int(card.rank)
    
    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
    
    def deal(self):
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
    
    def player_turn(self):
        while self.player_hand.total < 21:
            print(f"Player hand: {self.player_hand}")
            choice = input("Do you want to hit or stand? ").lower()
            if choice == "hit":
                self.player_hand.add_card(self.deck.deal())
            else:
                break
        print(f"Player hand: {self.player_hand}")
    
    def dealer_turn(self):
        while self.dealer_hand.total < 17:
            self.dealer_hand.add_card(self.deck.deal())
        print(f"Dealer hand: {self.dealer_hand}")
    
    def play(self):
        self.deal()
        self.player_turn()
        if self.player_hand.total > 21:
            print("Player busts. Dealer wins.")
            return
        self.dealer_turn()
        if self.dealer_hand.total > 21:
            print("Dealer busts. Player wins.")
            return
        if self.player_hand.total > self.dealer_hand.total:
            print("Player wins.")
        elif self.player_hand.total < self.dealer_hand.total:
            print("Dealer wins.")
        else:
            print("Tie game.")

game = Blackjack()
game.play()