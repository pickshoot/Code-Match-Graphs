import random

class Card:
    def __init__(self, suit, value):
        self.suit = suit
        self.value = value

    def __repr__(self):
        return "{} of {}".format(self.value, self.suit)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ["Hearts", "Diamonds", "Clubs", "Spades"]:
            for value in range(1, 14):
                card = Card(suit, value)
                self.cards.append(card)

    def shuffle(self):
        random.shuffle(self.cards)

    def deal(self):
        return self.cards.pop()

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = []
        self.dealer_hand = []

    def deal_cards(self):
        for i in range(2):
            self.player_hand.append(self.deck.deal())
            self.dealer_hand.append(self.deck.deal())

    def hit(self, hand):
        hand.append(self.deck.deal())

    def calculate_hand(self, hand):
        hand_value = 0
        ace_count = 0
        for card in hand:
            if card.value == 1:
                ace_count += 1
                hand_value += 11
            elif card.value >= 10:
                hand_value += 10
            else:
                hand_value += card.value

        while hand_value > 21 and ace_count:
            hand_value -= 10
            ace_count -= 1

        return hand_value

    def play(self):
        self.deal_cards()

        while True:
            print("Dealer's hand: [{}][?]".format(self.dealer_hand[0]))
            print("Player's hand: {}".format(self.player_hand))
            player_hand_value = self.calculate_hand(self.player_hand)
            if player_hand_value == 21:
                print("Blackjack! You win!")
                break

            if player_hand_value > 21:
                print("Bust! You lose.")
                break

            hit_or_stand = input("Do you want to hit or stand? ")

            if hit_or_stand.lower() == "hit":
                self.hit(self.player_hand)
            elif hit_or_stand.lower() == "stand":
                break
            else:
                print("Invalid input. Please enter 'hit' or 'stand'.")

        dealer_hand_value = self.calculate_hand(self.dealer_hand)

        while dealer_hand_value < 17:
            self.hit(self.dealer_hand)
            dealer_hand_value = self.calculate_hand(self.dealer_hand)

        print("Dealer's hand: {}".format(self.dealer_hand))
        print("Dealer's hand value: {}".format(dealer_hand_value))
        print("Player's hand: {}".format(self.player_hand))
        print("Player's hand value: {}".format(player_hand_value))

        if dealer_hand_value > 21:
            print("Dealer bust! You win!")
        elif dealer_hand_value == player_hand_value:
            print("Push! It's a tie.")
        elif dealer_hand_value > player_hand_value:
            print("Dealer wins!")
        else:
            print("You win!")

if __name__ == "__main__":
    game = Blackjack()
    game.play()