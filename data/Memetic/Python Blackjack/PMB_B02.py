import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = self.get_value()

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def get_value(self):
        if self.rank in ["J", "Q", "K"]:
            return 10
        elif self.rank == "A":
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ["J", "Q", "K", "A"]:
                self.cards.append(Card(suit, rank))
        random.shuffle(self.cards)

    def draw_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0

    def add_card(self, card):
        self.cards.append(card)
        self.value += card.value
        if card.rank == "A":
            self.aces += 1

    def adjust_for_ace(self):
        while self.value > 21 and self.aces > 0:
            self.value -= 10
            self.aces -= 1

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.result = None

    def deal_cards(self):
        self.player_hand.add_card(self.deck.draw_card())
        self.dealer_hand.add_card(self.deck.draw_card())
        self.player_hand.add_card(self.deck.draw_card())
        self.dealer_hand.add_card(self.deck.draw_card())

    def player_turn(self):
        while True:
            print(f"\nPlayer Hand: {', '.join(map(str, self.player_hand.cards))}")
            print(f"Player Value: {self.player_hand.value}")
            choice = input("Hit or Stand? ").lower()
            if choice == "hit":
                self.player_hand.add_card(self.deck.draw_card())
                self.player_hand.adjust_for_ace()
                if self.player_hand.value > 21:
                    print("Bust! Player loses.")
                    self.result = "loss"
                    return
            elif choice == "stand":
                break

    def dealer_turn(self):
        while self.dealer_hand.value < 17:
            self.dealer_hand.add_card(self.deck.draw_card())
            self.dealer_hand.adjust_for_ace()
        print(f"\nDealer Hand: {', '.join(map(str, self.dealer_hand.cards))}")
        print(f"Dealer Value: {self.dealer_hand.value}")
        if self.dealer_hand.value > 21:
            print("Dealer busts! Player wins.")
            self.result = "win"
        elif self.dealer_hand.value > self.player_hand.value:
            print("Dealer wins!")
            self.result = "loss"
        elif self.dealer_hand.value < self.player_hand.value:
            print("Player wins!")
            self.result = "win"
        else:
            print("It's a tie!")
            self.result = "tie"

    def play_game(self):
        self.deal_cards()
        self.player_turn()
        if self.result is None:
            self.dealer_turn()
            
def main():
    print("Welcome to Blackjack!")
    while True:
        game = Blackjack()
        game.play_game()
        if game.result == "win":
            print("Congratulations, you win!")
        elif game.result == "loss":
            print("Sorry, you lose.")
        else:
            print("It's a tie!")
        play_again = input("Would you like to play again? (y/n) ").lower()
        if play_again != "y":
            break
    print("Thanks for playing!")

if __name__ == "__main__":
    main()