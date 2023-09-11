import random

class Card:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def get_value(self):
        if self.rank in ['J', 'Q', 'K']:
            return 10
        elif self.rank == 'A':
            return 11
        else:
            return int(self.rank)

class Deck:
    def __init__(self):
        ranks = [str(i) for i in range(2, 11)] + ['J', 'Q', 'K', 'A']
        suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
        self.cards = [Card(rank, suit) for rank in ranks for suit in suits]
        self.shuffle()

    def __str__(self):
        return '\n'.join([str(card) for card in self.cards])

    def shuffle(self):
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
        self.value += card.get_value()
        if card.rank == 'A':
            self.aces += 1

    def adjust_for_ace(self):
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1

    def __str__(self):
        return ', '.join([str(card) for card in self.cards])

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        self.deck = Deck()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

        # deal initial cards
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

        # player's turn
        while True:
            print("Your hand:", self.player_hand)
            print("Dealer's up card:", self.dealer_hand.cards[0])
            choice = input("Hit or stand? ")
            if choice.lower() == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
                self.player_hand.adjust_for_ace()
                if self.player_hand.value > 21:
                    print("Bust! You lose.")
                    return
            elif choice.lower() == 'stand':
                break
            else:
                print("Invalid choice. Try again.")

        # dealer's turn
        while self.dealer_hand.value < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
            self.dealer_hand.adjust_for_ace()

        # determine winner
        print("Your hand:", self.player_hand)
        print("Dealer's hand:", self.dealer_hand)
        if self.dealer_hand.value > 21:
            print("Dealer bust! You win.")
        elif self.dealer_hand.value > self.player_hand.value:
            print("Dealer wins!")
        elif self.player_hand.value > self.dealer_hand.value:
            print("You win!")
        else:
            print("It's a tie.")

if __name__ == '__main__':
    game = Blackjack()
    game.play()
