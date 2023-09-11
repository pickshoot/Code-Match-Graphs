import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    ranks = [str(n) for n in range(2, 11)] + ['J', 'Q', 'K', 'A']
    suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']

    def __init__(self, num_decks):
        self.cards = [Card(rank, suit) for rank in self.ranks for suit in self.suits for _ in range(num_decks)]
        self.shuffle()

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
            if card.rank == 'A':
                num_aces += 1
                value += 11
            elif card.rank in ['K', 'Q', 'J']:
                value += 10
            else:
                value += int(card.rank)
        while num_aces > 0 and value > 21:
            value -= 10
            num_aces -= 1
        return value

class Player:
    def __init__(self, name, bankroll):
        self.name = name
        self.bankroll = bankroll
        self.hand = Hand()

    def place_bet(self):
        while True:
            bet = input(f"{self.name}, your bankroll is ${self.bankroll}. How much would you like to bet? ")
            if not bet.isdigit():
                print("Please enter a valid integer.")
            elif int(bet) > self.bankroll:
                print("You don't have enough money for that bet.")
            else:
                self.bet = int(bet)
                break

    def hit_or_stand(self, deck):
        while True:
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                self.hand.add_card(deck.deal_card())
                print(f"{self.name} was dealt {self.hand.cards[-1]}")
                print(f"{self.name}'s hand is now worth {self.hand.get_value()}.")
                if self.hand.get_value() > 21:
                    print(f"{self.name} has busted!")
                    self.bankroll -= self.bet
                    return False
            elif action.lower() == 'stand':
                return True
            else:
                print("Please enter a valid action.")

    def play(self, deck):
        self.hand = Hand()
        self.place_bet()
        self.hand.add_card(deck.deal_card())
        self.hand.add_card(deck.deal_card())
        print(f"{self.name}'s hand: {self.hand.cards[0]} and [hidden]")
        print(f"Dealer's hand: {deck.cards[0]} and [hidden]")
        if self.hand.get_value() == 21:
            print(f"{self.name} has blackjack!")
            self.bankroll += self.bet * 1.5
            return
        while self.hit_or_stand(deck):
            print(f"{self.name} stands.")
            dealer_hand = Hand()
            dealer_hand.add_card(deck.deal_card())
            dealer_hand.add_card(deck.deal_card())
            print(f"Dealer's hand: {dealer_hand.cards[0]} and [hidden]")
            while dealer_hand.get_value() < 17:
                dealer_hand.add_card(deck.deal_card())
                print(f"Dealer was dealt {dealer_hand.cards[-1]}.")
            print(f"Dealer's hand is worth {dealer_hand.get_value()}.")
            if dealer_hand.get_value() > 21:
                print("Dealer has busted!")
                self.bankroll += self.bet
            elif dealer_hand.get_value() > self.hand.get_value():
                print("Dealer wins!")
                self.bankroll -= self.bet
            elif dealer_hand.get_value() < self.hand.get_value():
                print(f"{self.name} wins!")
                self.bankroll += self.bet
            else:
                print("It's a tie!")

class Blackjack:
    def __init__(self):
        self.deck = None
        self.player = None

    def welcome(self):
        print("Welcome to Blackjack!")
        name = input("What is your name? ")
        print(f"Hello, {name}! Let's play some Blackjack.")

    def num_decks(self):
        while True:
            num = input("How many decks of cards would you like to play with? ")
            if not num.isdigit():
                print("Please enter a valid integer.")
            elif int(num) <= 0:
                print("Please enter a number greater than 0.")
            else:
                self.deck = Deck(int(num))
                break

    def play(self):
        while True:
            self.player = Player(input("What is your name? "), 100)
            print(f"Hello, {self.player.name}! Let's play some Blackjack.")
            self.num_decks()
            while self.player.bankroll > 0:
                self.player.play(self.deck)
                if input("Do you want to play another hand? ").lower() == 'no':
                    print("Thanks for playing!")
                    return
            print("You're out of money. Game over.")
            if input("Do you want to play again? ").lower() == 'no':
                print("Thanks for playing!")
                return

if __name__ == '__main__':
    game = Blackjack()
    game.play()

