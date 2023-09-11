#Import necessary libraries

import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def get_value(self):
        if self.rank == 'A':
            return 11
        elif self.rank in ['K', 'Q', 'J']:
            return 10
        else:
            return int(self.rank)

    def __str__(self):
        return f"{self.rank}{self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        # Create a new deck of cards and add each card to it
        for suit in ['♠', '♣', '♥', '♦']:
            for rank in ['A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K']:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        # Shuffle the deck to randomize the order of the cards
        random.shuffle(self.cards)

    def is_empty(self):
        # Check if the deck is empty to determine if the game should continue or end
        return not bool(self.cards)

    def draw_card(self):
        if self.is_empty():
            return None
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
            card_value = card.get_value()
            if card_value == 11:
                num_aces += 1
                value += card_value
            elif card_value >= 10:
                value += 10
            else:
                value += card_value
        while value > 21 and num_aces:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ' '.join(map(str, self.cards))

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal(self):
        # Deal two cards to the player and two cards to the dealer
        for i in range(2):
            self.player_hand.add_card(self.deck.draw_card())
            self.dealer_hand.add_card(self.deck.draw_card())

    def player_turn(self):
        while True:
            print(f"Your hand: {self.player_hand}")
            print(f"Dealer's hand: {self.dealer_hand.cards[0]} X")
            choice = input("Do you want to hit or stand? (h/s) ")
            if choice.lower() == 'h':
                self.player_hand.add_card(self.deck.draw_card())
                if self.player_hand.get_value() > 21:
                    print(f"Busted! Your hand: {self.player_hand}")
                    return False
            elif choice.lower() == 's':
                return True
            else:
                print("Invalid input. Please try again.")

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
            print(f"Dealer hits. Dealer's hand: {self.dealer_hand}")
            if self.dealer_hand.get_value() > 21:
                print(f"Dealer busts! Your hand: {self.player_hand}")
                return False
        return True

    def determine_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()
        if player_value > 21:
            print("Dealer wins! You busted.")
        elif dealer_value > 21:
            print("You win! Dealer busted.")
        elif player_value == dealer_value:
            print("Push! It's a tie.")
        elif player_value > dealer_value:
            print("You win!")
        else:
            print("Dealer wins.")

    def play(self):
        print("Welcome to Blackjack!")
        while True:
            self.deal()
            if self.player_turn():
                if self.dealer_turn():
                    self.determine_winner()
            if input("Do you want to play again? (y/n) ").lower() != 'y':
                break

# Start the game
game = Blackjack()
game.play()
