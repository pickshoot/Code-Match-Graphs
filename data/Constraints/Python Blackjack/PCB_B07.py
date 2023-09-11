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
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        has_ace = False
        for card in self.cards:
            if card.rank == "A":
                has_ace = True
            value += self.get_card_value(card)
        if has_ace and value + 10 <= 21:
            value += 10
        return value

    def get_card_value(self, card):
        if card.rank in ["J", "Q", "K"]:
            return 10
        elif card.rank == "A":
            return 1
        else:
            return int(card.rank)

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def play(self):
        self.deal_initial_cards()
        print(f"Dealer's hand: {self.dealer_hand.cards[0]}")
        print(f"Your hand: {self.player_hand}")
        while self.player_hand.get_value() < 21:
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == "hit":
                self.hit(self.player_hand)
                print(f"Your hand: {self.player_hand}")
            elif choice.lower() == "stand":
                break
        player_value = self.player_hand.get_value()
        if player_value > 21:
            self.player_bust()
        else:
            self.play_dealer_turn()

    def deal_initial_cards(self):
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())
        self.player_hand.add_card(self.deck.deal())
        self.dealer_hand.add_card(self.deck.deal())

    def hit(self, hand):
        hand.add_card(self.deck.deal())

    def player_bust(self):
        print("You bust!")
        self.end_game()

    def play_dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.hit(self.dealer_hand)
        print(f"Dealer's hand: {self.dealer_hand}")
        dealer_value = self.dealer_hand.get_value()
        if dealer_value > 21:
            self.dealer_bust()
        else:
            self.compare_hands(player_value=self.player_hand.get_value(),
                                dealer_value=dealer_value)

    def dealer_bust(self):
        print("Dealer bust!")
        self.end_game()

    def compare_hands(self, player_value, dealer_value):
        if player_value > dealer_value:
            print("You win!")
        elif dealer_value > player_value:
            print("Dealer wins!")
        else:
            print("It's a tie!")
        self.end_game()

    def end_game(self):
        play_again = input("Do you want to play again? ")
        if play_again.lower() == "yes":
            self.__init__()
            self.play()
        else:
            print("Thanks for playing!")

if __name__ == "__main__":
    game = Blackjack()
    game.play()