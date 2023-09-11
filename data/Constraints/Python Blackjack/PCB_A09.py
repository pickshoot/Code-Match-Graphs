import random


class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return f"{self.rank} of {self.suit}"

    def value(self):
        if self.rank in ["Jack", "Queen", "King"]:
            return 10
        elif self.rank == "Ace":
            return 11
        else:
            return int(self.rank)


class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ["Jack", "Queen", "King", "Ace"]:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop(0)


class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def value(self):
        total = 0
        aces = 0
        for card in self.cards:
            total += card.value()
            if card.rank == "Ace":
                aces += 1
        while total > 21 and aces > 0:
            total -= 10
            aces -= 1
        return total

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)


class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial(self):
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())

    def is_blackjack(self, hand):
        return hand.value() == 21 and len(hand.cards) == 2

    def player_turn(self):
        while self.player_hand.value() < 21:
            print(f"\nYour hand: {self.player_hand}")
            print(f"Dealer's hand: {self.dealer_hand.cards[0]}")
            action = input("\nDo you want to hit or stand? ")
            if action.lower() == "hit":
                self.player_hand.add_card(self.deck.deal())
            elif action.lower() == "stand":
                break
        print(f"\nYour final hand: {self.player_hand}")

    def dealer_turn(self):
        while self.dealer_hand.value() < 17:
            self.dealer_hand.add_card(self.deck.deal())
        print(f"\nDealer's final hand: {self.dealer_hand}")

    def determine_winner(self):
        player_total = self.player_hand.value()
        dealer_total = self.dealer_hand.value()
        if player_total > 21:
            print("You bust, dealer wins!")
        elif dealer_total > 21:
            print("Dealer busts, you win!")
        elif player_total > dealer_total:
            print("You win!")
        elif dealer_total > player_total:
            print("Dealer wins!")
        else:
            print("It's a tie!")

    def play(self):
        print("Welcome to Blackjack!\n")
        self.deal_initial()
        if self.is_blackjack(self.player_hand):
            print("Blackjack! You win!")
            return
        while True:
            self.player_turn()
            if self.player_hand.value() > 21:
                print("You bust, dealer wins!")
                return
            self.dealer_turn()
            if self.dealer_hand.value() > 21:
                print("Dealer busts, you win!")
                return
            self.determine_winner()
            play_again = input("\nDo you want to play again? (y/n) ")
            if play_again.lower() != "y":
                break
            else:
                self.deck = Deck()
                self.deck.shuffle()
                self.player_hand = Hand()
                self.dealer_hand = Hand()
                self.deal_initial()
                if self.is_blackjack(self.player_hand):
                    print("Blackjack! You win!")
                    return

if __name__ == "__main__":
    game = Blackjack()
    game.play()