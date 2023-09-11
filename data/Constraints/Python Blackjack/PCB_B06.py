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
            for rank in range(1, 14):
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop(0)


class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0

    def add_card(self, card):
        self.cards.append(card)
        if card.rank == 1 and self.value + 11 <= 21:
            self.value += 11
        elif card.rank >= 10:
            self.value += 10
        else:
            self.value += card.rank

    def __str__(self):
        return ", ".join([str(card) for card in self.cards])


class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        self.player_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

        print("Player hand:", self.player_hand)
        print("Dealer hand:", self.dealer_hand.cards[0])

        if self.player_hand.value == 21:
            print("Player wins!")
            return

        while True:
            choice = input("Hit or stand? ")
            if choice.lower() == "hit":
                self.player_hand.add_card(self.deck.deal_card())
                print("Player hand:", self.player_hand)
                if self.player_hand.value > 21:
                    print("Player busts, dealer wins!")
                    return
            elif choice.lower() == "stand":
                break
            else:
                print("Invalid choice, please try again.")

        while self.dealer_hand.value < 17:
            self.dealer_hand.add_card(self.deck.deal_card())

        print("Player hand:", self.player_hand)
        print("Dealer hand:", self.dealer_hand)

        if self.dealer_hand.value > 21:
            print("Dealer busts, player wins!")
        elif self.dealer_hand.value > self.player_hand.value:
            print("Dealer wins!")
        elif self.player_hand.value > self.dealer_hand.value:
            print("Player wins!")
        else:
            print("It's a tie!")

if __name__ == "__main__":
    game = Blackjack()
    game.play()