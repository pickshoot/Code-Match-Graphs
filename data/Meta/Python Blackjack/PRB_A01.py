import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        if rank == 'Ace':
            self.value = 11
        elif rank in ['King', 'Queen', 'Jack']:
            self.value = 10
        else:
            self.value = int(rank)

    def __str__(self):
        return f"{self.rank} of {self.suit}"

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for rank in ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']:
                self.cards.append(Card(suit, rank))
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += card.value
        if card.rank == 'Ace':
            self.aces += 1

        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

class Player:
    def __init__(self):
        self.hand = Hand()

    def hit(self, deck):
        self.hand.add_card(deck.deal_card())

    def has_blackjack(self):
        return len(self.hand.cards) == 2 and self.hand.value == 21

class Game:
    def __init__(self):
        self.deck = Deck()
        self.player = Player()
        self.computer = Player()

    def deal_cards(self):
        self.player.hit(self.deck)
        self.computer.hit(self.deck)
        self.player.hit(self.deck)
        self.computer.hit(self.deck)

    def display_table(self):
        print("\nPlayer's hand:")
        for card in self.player.hand.cards:
            print(card)
        print(f"Total value: {self.player.hand.value}\n")
        print("Computer's hand:")
        print(self.computer.hand.cards[0])
        print("One card hidden\n")

    def player_turn(self):
        while True:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.player.hit(self.deck)
                self.display_table()
                if self.player.hand.value > 21:
                    print("Bust! You lose.")
                    return False
            elif action == "stand":
                return True
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

    def computer_turn(self):
        while self.computer.hand.value < 17:
            self.computer.hit(self.deck)
            self.display_table()
        if self.computer.hand.value > 21:
            print("Computer busts! You win.")
            return True
        return False

    def determine_winner(self):
        if self.player.has_blackjack() and not self.computer.has_blackjack():
            print("Blackjack! You win.")
            return
        elif self.computer.has_blackjack() and not self.player.has_blackjack():
            print("Computer has blackjack. You lose.")
            return
        elif self.player.hand.value > 21:
            print("Bust! You lose.")
            return
        elif self.computer.hand.value > 21:
            print("Computer busts!")
        elif self.player.hand.value > self.computer.hand.value:
            print("You win!")
            return
        elif self.player.hand.value < self.computer.hand.value:
            print("You lose.")
            return
        else:
            print("Push.")
            return

    def play_again(self):
        while True:
            action = input("Do you want to play again? (y/n) ")
            if action == "y":
                return True
            elif action == "n":
                return False
            else:
                print("Invalid input. Please enter 'y' or 'n'.")

    def play(self):
        while True:
            print("\nNew round starting...")
            self.deal_cards()
            self.display_table()
            player_stands = self.player_turn()
            if player_stands:
                computer_wins = self.computer_turn()
                if not computer_wins:
                    self.determine_winner()
            if not self.play_again():
                print("Thanks for playing!")
                break

if __name__ == '__main__':
    game = Game()
    game.play()
