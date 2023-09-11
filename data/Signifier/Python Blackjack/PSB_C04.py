import random

# Define the cards and their values
cards = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
card_values = {"2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "J": 10, "Q": 10, "K": 10, "A": 11}

# Define the Blackjack game class
class Blackjack:
    def __init__(self):
        self.deck = self.generate_deck()
        self.player_hand = []
        self.dealer_hand = []

    # Generate a new deck of cards
    def generate_deck(self):
        deck = []
        for card in cards:
            for i in range(4):
                deck.append(card)
        random.shuffle(deck)
        return deck

    # Deal a card from the deck
    def deal_card(self):
        return self.deck.pop()

    # Calculate the value of a hand
    def hand_value(self, hand):
        value = sum([card_values[card] for card in hand])
        if value > 21 and "A" in hand:
            value -= 10
        return value

    # Play a round of Blackjack
    def play_round(self):
        self.player_hand = [self.deal_card(), self.deal_card()]
        self.dealer_hand = [self.deal_card(), self.deal_card()]

        while True:
            print("Dealer shows: {}".format(self.dealer_hand[0]))
            print("Player hand: {}".format(self.player_hand))
            choice = input("Do you want to hit or stand? ")

            if choice == "hit":
                self.player_hand.append(self.deal_card())
                if self.hand_value(self.player_hand) > 21:
                    print("Player busts! Dealer wins!")
                    return -1

            elif choice == "stand":
                while self.hand_value(self.dealer_hand) < 17:
                    self.dealer_hand.append(self.deal_card())
                print("Dealer hand: {}".format(self.dealer_hand))
                player_value = self.hand_value(self.player_hand)
                dealer_value = self.hand_value(self.dealer_hand)
                if dealer_value > 21:
                    print("Dealer busts! Player wins!")
                    return 1
                elif dealer_value > player_value:
                    print("Dealer wins!")
                    return -1
                elif player_value > dealer_value:
                    print("Player wins!")
                    return 1
                else:
                    print("Push!")
                    return 0
game = Blackjack()
result = game.play_round()
