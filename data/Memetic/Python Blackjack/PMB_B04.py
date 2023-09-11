import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value
        
    def __repr__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = [Card(suit, value) for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]
                                          for value in ["2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"]]
        
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
        num_aces = 0
        for card in self.cards:
            if card.value.isdigit():
                value += int(card.value)
            elif card.value == "Ace":
                num_aces += 1
                value += 11
            else:
                value += 10
        while value > 21 and num_aces > 0:
            value -= 10
            num_aces -= 1
        return value
        
    def __repr__(self):
        return ", ".join(str(card) for card in self.cards)

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = Hand()

    def add_card_to_hand(self, card):
        self.hand.add_card(card)
    
    def get_hand_value(self):
        return self.hand.get_value()
        
    def __repr__(self):
        return f"{self.name}'s hand: {self.hand}"
    
class Dealer:
    def __init__(self):
        self.hand = Hand()
    
    def add_card_to_hand(self, card):
        self.hand.add_card(card)
    
    def get_hand_value(self):
        return self.hand.get_value()
        
    def __repr__(self):
        return f"Dealer's hand: {self.hand.cards[0]}, <face-down card>"

class BlackjackGame:
    def __init__(self, num_players):
        self.deck = Deck()
        self.deck.shuffle()
        self.players = [Player(f"Player {i+1}") for i in range(num_players)]
        self.dealer = Dealer()
        
    def deal_initial_cards(self):
        for player in self.players:
            player.add_card_to_hand(self.deck.deal_card())
            player.add_card_to_hand(self.deck.deal_card())
        self.dealer.add_card_to_hand(self.deck.deal_card())
        self.dealer.add_card_to_hand(self.deck.deal_card())
        
    def play_game(self):
        print("Starting game of Blackjack!\n")
        self.deal_initial_cards()
        for player in self.players:
            print(player)
        print(self.dealer)
        for player in self.players:
            while player.get_hand_value() < 21:
                action = input(f"{player.name}, do you want to hit or stand? ")
                if action.lower() == "hit":
                    player.add_card_to_hand(self.deck.deal_card())
                    print(player)
                elif action.lower() == "stand":
                    break
                else:
                    print("Invalid action. Please enter 'hit' or 'stand'.")
            print(f"{player.name} stands with a hand value of {player.get_hand_value()}.\n")
        while self.dealer.get_hand_value() < 17:
            self.dealer.add_card_to_hand(self.deck.deal_card())
        print(f"Dealer's hand: {self.dealer.hand}")
        if self.dealer.get_hand_value() > 21:
            print("Dealer busts! All remaining players win.")
            for player in self.players:
                if player.get_hand_value() <= 21:
                    print(f"{player.name} wins!")
        else:
            for player in self.players:
                if player.get_hand_value() > 21:
                    print(f"{player.name} busts! Dealer wins.")
                elif player.get_hand_value() > self.dealer.get_hand_value():
                    print(f"{player.name} wins!")
                elif player.get_hand_value() == self.dealer.get_hand_value():
                    print(f"{player.name} ties with dealer.")
                else:
                    print(f"{player.name} loses to dealer.")

    def __repr__(self):
        return f"Blackjack game with {len(self.players)} players and dealer {self.dealer}"

game = BlackjackGame(num_players=2)
game.play_game()