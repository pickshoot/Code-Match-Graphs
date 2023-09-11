import random

# Define the card ranks, suits, and values
ranks = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
values = {'Ace': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'Jack': 10, 'Queen': 10, 'King': 10}

# Define the classes for the game
class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        for rank in ranks:
            for suit in suits:
                self.cards.append(Card(rank, suit))

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
            value += values[card.rank]
            if card.rank == 'Ace':
                has_ace = True
        if has_ace and value + 10 <= 21:
            value += 10
        return value

    def __str__(self):
        hand_str = ""
        for card in self.cards:
            hand_str += str(card) + ", "
        return hand_str[:-2]

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while self.player_hand.get_value() < 21:
            print("Player's hand:", self.player_hand)
            hit = input("Do you want to hit or stand? ").lower()
            if hit == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
            elif hit == 'stand':
                break
        print("Player's hand:", self.player_hand)

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        print("Dealer's hand:", self.dealer_hand)

    def get_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if player_value > 21:
            return "Dealer wins"
        elif dealer_value > 21:
            return "Player wins"
        elif player_value > dealer_value:
            return "Player wins"
        elif dealer_value > player_value:
            return "Dealer wins"
        else:
            return "Tie"

    def play_game(self):
        self.deal_initial_cards()
        print("Player's hand:", self.player_hand)
        print("Dealer's hand:", self.dealer_hand.cards[0])
        self.player_turn()
        if self.player_hand.get_value() <= 21:
            self.dealer_turn()
        print(self.get_winner())

# Start the game
if __name__ == '__main__':
    game = Blackjack()
    game.play_game()