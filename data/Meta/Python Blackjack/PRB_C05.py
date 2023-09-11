import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def value(self):
        if self.rank in ["J", "Q", "K"]:
            return 10
        elif self.rank == "A":
            return 11
        else:
            return int(self.rank)

    def __str__(self):
        return f"{self.rank}{self.suit}"

class Deck:
    def __init__(self, num_decks):
        self.cards = []
        for i in range(num_decks):
            for suit in ["♠", "♣", "♥", "♦"]:
                for rank in range(2, 11):
                    self.cards.append(Card(str(rank), suit))
                for rank in ["J", "Q", "K", "A"]:
                    self.cards.append(Card(rank, suit))
        self.shuffle()

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def value(self):
        value = 0
        num_aces = 0
        for card in self.cards:
            value += card.value()
            if card.rank == "A":
                num_aces += 1
        while value > 21 and num_aces > 0:
            value -= 10
            num_aces -= 1
        return value

    def __str__(self):
        return ", ".join([str(card) for card in self.cards])

class Game:
    def __init__(self):
        self.player_name = input("What is your name? ")
        print(f"Welcome to Blackjack, {self.player_name}!")
        self.bankroll = 100
        self.deck = None
        self.player_hand = None
        self.computer_hand = None

    def play(self):
        while True:
            if self.bankroll <= 0:
                print("You have run out of money. Game over.")
                return
            bet = input(f"You have ${self.bankroll}. How much would you like to bet? ")
            try:
                bet = int(bet)
            except ValueError:
                print("Invalid input. Please enter a number.")
                continue
            if bet > self.bankroll:
                print("You cannot bet more than your bankroll.")
                continue
            if bet <= 0:
                print("You must bet a positive amount.")
                continue
            self.deck = Deck(int(input("How many decks would you like to play with? ")))
            self.player_hand = Hand()
            self.computer_hand = Hand()
            self.player_hand.add_card(self.deck.deal())
            self.computer_hand.add_card(self.deck.deal())
            self.player_hand.add_card(self.deck.deal())
            self.computer_hand.add_card(self.deck.deal())
            print(f"Your cards: {self.player_hand}")
            print(f"Dealer's card: {self.computer_hand.cards[0]}")
            while True:
                choice = input("Do you want to hit or stand? ")
                if choice.lower() == "hit":
                    self.player_hand.add_card(self.deck.deal())
                    print(f"Your cards: {self.player_hand}")
                    if self.player_hand.value() > 21:
                        print("Bust! You lose.")
                        self.bankroll -= bet
                        break
                elif choice.lower() == "stand":
                    break
                else:
                    print("Invalid input. Please")
            if self.player_hand.value() <= 21:
                while self.computer_hand.value() < 17:
                    self.computer_hand.add_card(self.deck.deal())
                    print(f"Dealer's cards: {self.computer_hand}")
                if self.computer_hand.value() > 21:
                    print("Dealer busts! You win.")
                    self.bankroll += bet
                elif self.computer_hand.value() == self.player_hand.value():
                    print("Push! It's a tie.")
                elif self.computer_hand.value() > self.player_hand.value():
                    print("Dealer wins.")
                    self.bankroll -= bet
                else:
                    print("You win!")
                    self.bankroll += bet
            print(f"Your bankroll is now ${self.bankroll}.")
            choice = input("Do you want to play again? ")
            if choice.lower() != "yes":
                print("Thanks for playing!")
                return

if __name__ == '__main__':
    blackjack = Game()
    blackjack.play()
