import random

class Blackjack:
    def __init__(self):
        self.deck = []
        self.player_hand = []
        self.dealer_hand = []
        self.game_over = False
        self.initialize_deck()

    def initialize_deck(self):
        suits = ['hearts', 'diamonds', 'clubs', 'spades']
        values = ['ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'jack', 'queen', 'king']
        for suit in suits:
            for value in values:
                self.deck.append((value, suit))

    def shuffle_deck(self):
        random.shuffle(self.deck)

    def deal_card(self, hand):
        card = self.deck.pop()
        hand.append(card)

    def deal_initial_cards(self):
        self.deal_card(self.player_hand)
        self.deal_card(self.dealer_hand)
        self.deal_card(self.player_hand)
        self.deal_card(self.dealer_hand)

    def get_hand_value(self, hand):
        value = 0
        num_aces = 0
        for card in hand:
            if card[0] == 'ace':
                num_aces += 1
                value += 11
            elif card[0] in ['jack', 'queen', 'king']:
                value += 10
            else:
                value += int(card[0])
        while num_aces > 0 and value > 21:
            value -= 10
            num_aces -= 1
        return value

    def print_player_hand(self):
        print("Your hand:")
        for card in self.player_hand:
            print(card[0], "of", card[1])

    def print_dealer_hand(self, show_all=False):
        print("Dealer's hand:")
        if show_all:
            for card in self.dealer_hand:
                print(card[0], "of", card[1])
        else:
            print(self.dealer_hand[0][0], "of", self.dealer_hand[0][1])
            print("Unknown card")

    def player_turn(self):
        self.print_player_hand()
        while True:
            action = input("Do you want to hit or stand? ")
            if action == "hit":
                self.deal_card(self.player_hand)
                self.print_player_hand()
                if self.get_hand_value(self.player_hand) > 21:
                    print("You busted!")
                    self.game_over = True
                    break
            elif action == "stand":
                break
            else:
                print("Invalid input")

    def dealer_turn(self):
        self.print_dealer_hand(show_all=True)
        while self.get_hand_value(self.dealer_hand) < 17:
            self.deal_card(self.dealer_hand)
            self.print_dealer_hand(show_all=True)
            if self.get_hand_value(self.dealer_hand) > 21:
                print("Dealer busted!")
                self.game_over = True
                break

    def play(self):
        self.shuffle_deck()
        self.deal_initial_cards()
        self.print_player_hand()
        self.print_dealer_hand()
        if self.get_hand_value(self.player_hand) == 21:
            print("You have a Blackjack!")
            self.game_over = True
        else:
            self.player_turn()
            if not self.game_over:
                self.dealer_turn()
        if not self.game_over:
            player_value = self.get_hand_value(self.player_hand)
            dealer_value = self.get_hand_value(self.dealer_hand)
            print("Your hand value:", player_value)
            print("Dealer's hand value:", dealer_value)
            if player_value > dealer_value:
                print("You win!")
            elif dealer_value > player_value:
                print("Dealer wins!")
            else:
                print("It's a tie!")
        print("Game over")  # added for clarity

game = Blackjack()
game.play()
