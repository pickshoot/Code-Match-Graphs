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
            for rank in ["J", "Q", "K", "A"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop(0)

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank in ["J", "Q", "K"]:
            self.value += 10
        elif card.rank == "A":
            self.aces += 1
            self.value += 11
        else:
            self.value += int(card.rank)
        while self.value > 21 and self.aces > 0:
            self.value -= 10
            self.aces -= 1

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Game:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.player_chips = 100

    def take_bet(self):
        while True:
            try:
                bet = int(input("How many chips would you like to bet? "))
                if bet > self.player_chips:
                    print("Sorry, you don't have enough chips.")
                else:
                    self.player_chips -= bet
                    break
            except ValueError:
                print("Invalid input. Please enter a number.")
        return bet

    def hit(self, hand):
        hand.add_card(self.deck.deal())

    def player_turn(self):
        while True:
            action = input("Would you like to hit or stand? ")
            if action.lower() == "hit":
                self.hit(self.player_hand)
                print(f"Your hand: {self.player_hand}")
                if self.player_hand.value > 21:
                    print("Bust! You lose.")
                    return False
            elif action.lower() == "stand":
                print("You stand.")
                return True
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

    def dealer_turn(self):
        while self.dealer_hand.value < 17:
            self.hit(self.dealer_hand)
            print(f"Dealer's hand: {self.dealer_hand}")
            if self.dealer_hand.value > 21:
                print("Dealer busts! You win.")
                return False
        return True

    def show_all(self):
        print(f"Your hand: {self.player_hand}")
        print(f"Dealer's hand: {self.dealer_hand}")

    def show_some(self):
        print(f"Your hand: {self.player_hand}")
        print(f"Dealer's hand: {self.dealer_hand.cards[0]}, [hidden]")

    def play(self):
        while True:
            print(f"You have {self.player_chips} chips.")
            bet = self.take_bet()
            self.player_hand.add_card(self.deck.deal())
            self.player_hand.add_card(self.deck.deal())
            self.dealer_hand.add_card(self.deck.deal())
            self.show_some()
            if self.player_turn():
                if self.dealer_turn():
                    self.show_all()
                    if self.player_hand.value > self.dealer_hand.value:
                        print("You win!")
                        self.player_chips += bet * 2
                    elif self.player_hand.value == self.dealer_hand.value:
                        print("Push!")
                        self.player_chips += bet
                    else:
                        print("You lose.")
            if self.player_chips == 0:
                print("You're out of chips. Game over.")
                break
            else:
                again = input("Would you like to play again? ")
                if again.lower() == "yes":
                    self.deck = Deck()
                    self.deck.shuffle()
                    self.player_hand = Hand()
                    self.dealer_hand = Hand()
                else:
                    print("Thanks for playing!")
                    break

if __name__ == '__main__':
    game = Game()
    game.play()
