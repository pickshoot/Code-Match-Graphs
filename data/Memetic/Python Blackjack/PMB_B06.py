import random

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(1, 14):
                if rank == 1:
                    self.cards.append("Ace of " + suit)
                elif rank == 11:
                    self.cards.append("Jack of " + suit)
                elif rank == 12:
                    self.cards.append("Queen of " + suit)
                elif rank == 13:
                    self.cards.append("King of " + suit)
                else:
                    self.cards.append(str(rank) + " of " + suit)

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Player:
    def __init__(self, name):
        self.name = name
        self.hand = []

    def add_card(self, card):
        self.hand.append(card)

    def get_hand_value(self):
        value = 0
        num_aces = 0
        for card in self.hand:
            if card.startswith("Ace"):
                num_aces += 1
                value += 11
            elif card.startswith("King") or card.startswith("Queen") or card.startswith("Jack"):
                value += 10
            else:
                value += int(card.split()[0])
        while value > 21 and num_aces > 0:
            value -= 10
            num_aces -= 1
        return value

class Game:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player = Player("Player")
        self.dealer = Player("Dealer")

    def play(self):
        for i in range(2):
            self.player.add_card(self.deck.deal_card())
            self.dealer.add_card(self.deck.deal_card())

        while True:
            print("Player hand:", self.player.hand, "Value:", self.player.get_hand_value())
            print("Dealer hand:", self.dealer.hand[0], "and one face-down card")

            if self.player.get_hand_value() == 21:
                print("Blackjack! You win!")
                return
            elif self.player.get_hand_value() > 21:
                print("Bust! Dealer wins.")
                return
            elif self.dealer.get_hand_value() > 21:
                print("Dealer busts! You win.")
                return
            
            choice = input("Hit or stand? ")
            if choice.lower() == "hit":
                self.player.add_card(self.deck.deal_card())
            elif choice.lower() == "stand":
                break

        while self.dealer.get_hand_value() < 17:
            self.dealer.add_card(self.deck.deal_card())

        print("Player hand:", self.player.hand, "Value:", self.player.get_hand_value())
        print("Dealer hand:", self.dealer.hand, "Value:", self.dealer.get_hand_value())

        if self.player.get_hand_value() > self.dealer.get_hand_value():
            print("You win!")
        elif self.player.get_hand_value() < self.dealer.get_hand_value():
            print("Dealer wins!")
        else:
            print("It's a tie!")

if __name__ == "__main__":
    game = Game()
    game.play()