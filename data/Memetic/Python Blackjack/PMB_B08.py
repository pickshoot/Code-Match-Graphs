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
        self.build()
    
    def build(self):
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for rank in range(1, 14):
                self.cards.append(Card(suit, rank))
    
    def shuffle(self):
        random.shuffle(self.cards)
        
    def draw_card(self):
        return self.cards.pop()
    
class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0
        
    def add_card(self, card):
        self.cards.append(card)
        if card.rank == 1:
            self.aces += 1
        self.value += self.get_card_value(card)
        
    def get_card_value(self, card):
        if card.rank in [11, 12, 13]:
            return 10
        elif card.rank == 1:
            return 11
        else:
            return card.rank
        
    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1
            
class Game:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
    
    def deal_cards(self):
        for _ in range(2):
            self.player_hand.add_card(self.deck.draw_card())
            self.dealer_hand.add_card(self.deck.draw_card())
            
    def hit(self, hand):
        hand.add_card(self.deck.draw_card())
        hand.adjust_for_ace()
        
    def show_some(self):
        print("Dealer's Hand:")
        print(" <card hidden>")
        print("", self.dealer_hand.cards[1])
        print("\nPlayer's Hand:", *self.player_hand.cards, sep="\n ")
        
    def show_all(self):
        print("Dealer's Hand:", *self.dealer_hand.cards, sep="\n ")
        print("Dealer's Hand =",self.dealer_hand.value)
        print("\nPlayer's Hand:", *self.player_hand.cards, sep="\n ")
        print("Player's Hand =",self.player_hand.value)
        
    def player_busts(self):
        print("Player busts!")
    
    def player_wins(self):
        print("Player wins!")
        
    def dealer_busts(self):
        print("Dealer busts!")
        
    def dealer_wins(self):
        print("Dealer wins!")
    
    def push(self):
        print("It's a tie!")
        
    def play(self):
        self.deal_cards()
        self.show_some()
        
        while True:
            action = input("Would you like to hit or stand? Enter 'h' or 's': ")
            if action == 'h':
                self.hit(self.player_hand)
                self.show_some()
                if self.player_hand.value > 21:
                    self.player_busts()
                    break
            elif action == 's':
                break
        
        if self.player_hand.value <= 21:
            while self.dealer_hand.value < 17:
                self.hit(self.dealer_hand)
            
            self.show_all()
            
            if self.dealer_hand.value > 21:
                self.dealer_busts()
            else:
                if self.player_hand.value > self.dealer_hand.value:
                    self.player_wins()
                elif self.player_hand.value < self.dealer_hand.value:
                    self.dealer_wins()
                else:
                    self.push()

if __name__ == '__main__':
    game = Game()
    game.play()