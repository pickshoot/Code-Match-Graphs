import random


class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __repr__(self):
        return f"{self.value} of {self.suit}"


class Deck:
    def __init__(self):
        suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
        values = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]
        self.cards = [Card(suit, value) for suit in suits for value in values]
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.value == "A" and self.value + 11 <= 21:
            self.value += 11
        elif card.value == "A":
            self.value += 1
        elif card.value in ["K", "Q", "J"]:
            self.value += 10
        else:
            self.value += int(card.value)

    def __repr__(self):
        return f"{self.cards} (value: {self.value})"


class Game:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        print("Welcome to Blackjack!\n")

        # Deal initial cards
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        print(f"Dealer's up card: {self.dealer_hand.cards[1]}\n")
        print(f"Your hand: {self.player_hand}\n")

        # Player's turn
        while self.player_hand.value < 21:
            action = input("Would you like to hit or stand? ")
            if action == "hit":
                self.player_hand.add_card(self.deck.deal())
                print(f"\nYour hand: {self.player_hand}\n")
            elif action == "stand":
                break
            else:
                print("Invalid input. Please try again.")

        # Dealer's turn
        if self.player_hand.value <= 21:
            print(f"\nDealer's hand: {self.dealer_hand}\n")
            while self.dealer_hand.value < 17:
                self.dealer_hand.add_card(self.deck.deal())
                print(f"Dealer hits: {self.dealer_hand.cards[-1]}")
                print(f"Dealer's hand: {self.dealer_hand}\n")

        # Determine winner
        if self.player_hand.value > 21:
            print("Bust! You lose.")
        elif self.dealer_hand.value > 21:
            print("Dealer busts! You win.")
        elif self.player_hand.value > self.dealer_hand.value:
            print("You win!")
        elif self.dealer_hand.value > self.player_hand.value:
            print("You lose.")
        else:
            print("It's a tie.")

        play_again = input("\nWould you like to play again? ")
        if play_again.lower() == "yes":
            self.play()
        else:
            print("Thanks for playing!")

game = Game()
game.play()
