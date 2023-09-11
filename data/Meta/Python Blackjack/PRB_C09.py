import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __repr__(self):
        return f"{self.value} of {self.suit}"

class Deck:
    def __init__(self, num_decks):
        self.cards = []
        suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
        values = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']
        for _ in range(num_decks):
            for suit in suits:
                for value in values:
                    self.cards.append(Card(suit, value))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.value == 'Ace':
            if self.value + 11 > 21:
                self.value += 1
            else:
                self.value += 11
        elif card.value in ['Jack', 'Queen', 'King']:
            self.value += 10
        else:
            self.value += int(card.value)

class Player:
    def __init__(self, name, bankroll):
        self.name = name
        self.bankroll = bankroll
        self.hand = Hand()

    def add_bankroll(self, amount):
        self.bankroll += amount

    def remove_bankroll(self, amount):
        self.bankroll -= amount

    def reset_hand(self):
        self.hand = Hand()

class Blackjack:
    def __init__(self):
        self.deck = None
        self.player = None
        self.dealer = Hand()

    def start_game(self):
        print("Welcome to Blackjack!")
        name = input("What is your name? ")
        bankroll = int(input("How much money do you have? "))
        self.player = Player(name, bankroll)
        self.play()

    def play(self):
        while True:
            num_decks = int(input("How many decks would you like to play with? "))
            self.deck = Deck(num_decks)
            self.deck.shuffle()
            self.player.reset_hand()
            self.dealer = Hand()
            bet = int(input("How much would you like to bet? "))
            self.player.remove_bankroll(bet)
            self.player.hand.add_card(self.deck.deal_card())
            self.player.hand.add_card(self.deck.deal_card())
            self.dealer.add_card(self.deck.deal_card())
            self.dealer.add_card(self.deck.deal_card())
            print(f"Your cards: {self.player.hand.cards}")
            print(f"Dealer's cards: {self.dealer.cards[0]} and [Face Down]")
            while True:
                action = input("Would you like to hit or stand? ")
                if action.lower() == 'hit':
                    self.player.hand.add_card(self.deck.deal_card())
                    print(f"Your cards: {self.player.hand.cards}")
                    if self.player.hand.value > 21:
                        print("Bust! You lose.")
                        break
                elif action.lower() == 'stand':
                    while self.dealer.value < 17:
                        self.dealer.add_card(self.deck.deal_card())
                    print(f"Dealer's cards: {self.dealer.cards}")
                    while self.dealer.value < 17:
                        self.dealer.add_card(self.deck.deal_card())
                        print(f"Dealer's cards: {self.dealer.cards}")
                    if self.dealer.value > 21:
                        print("Dealer busts! You win!")
                        self.player.add_bankroll(bet*2)
                    elif self.dealer.value == self.player.hand.value:
                        print("Push! It's a tie.")
                        self.player.add_bankroll(bet)
                    elif self.dealer.value > self.player.hand.value:
                        print("Dealer wins!")
                    else:
                        print("You win!")
                        self.player.add_bankroll(bet*2)
                    print(f"Your bankroll is now {self.player.bankroll}.")
                    break
            if self.player.bankroll == 0:
                print("You're out of money! Game over.")
                break
            play_again = input("Would you like to play again? (y/n) ")
            if play_again.lower() == 'n':
                break

if __name__ == '__main__':
    game = Blackjack()
    game.start_game()
