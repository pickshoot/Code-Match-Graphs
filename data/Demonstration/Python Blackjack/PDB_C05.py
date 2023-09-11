import random

class Card:
    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank

    def __str__(self):
        return "{} of {}".format(self.rank, self.suit)

class Deck:
    def __init__(self):
        self.cards = []
        for suit in ['Hearts', 'Diamonds', 'Clubs', 'Spades']:
            for rank in range(2, 11):
                self.cards.append(Card(suit, str(rank)))
            for rank in ['J', 'Q', 'K', 'A']:
                self.cards.append(Card(suit, rank))

    def shuffle(self):
        random.shuffle(self.cards)

    def deal_card(self):
        return self.cards.pop()

class Hand:
    def __init__(self):
        self.cards = []

    def add_card(self, card):
        self.cards.append(card)

    def get_value(self):
        value = 0
        aces = 0
        for card in self.cards:
            if card.rank == 'A':
                aces += 1
            elif card.rank in ['K', 'Q', 'J']:
                value += 10
            else:
                value += int(card.rank)

        for i in range(aces):
            if value + 11 <= 21:
                value += 11
            else:
                value += 1

        return value

    def __str__(self):
        return ", ".join(str(card) for card in self.cards)

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()

    def deal_initial_cards(self):
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())
        self.player_hand.add_card(self.deck.deal_card())
        self.dealer_hand.add_card(self.deck.deal_card())

    def player_turn(self):
        while True:
            print("Your hand: {}".format(self.player_hand))
            print("Dealer's face-up card: {}".format(self.dealer_hand.cards[0]))
            action = input("Do you want to hit or stand? ")
            if action.lower() == 'hit':
                self.player_hand.add_card(self.deck.deal_card())
                if self.player_hand.get_value() > 21:
                    print("You bust!")
                    return 'dealer'
            elif action.lower() == 'stand':
                break
            else:
                print("Invalid action, please try again.")

        return None

    def dealer_turn(self):
        while self.dealer_hand.get_value() < 17:
            self.dealer_hand.add_card(self.deck.deal_card())
        if self.dealer_hand.get_value() > 21:
            print("Dealer busts!")
            return 'player'
        return None

    def determine_winner(self):
        player_value = self.player_hand.get_value()
        dealer_value = self.dealer_hand.get_value()

        if player_value > 21:
            return 'dealer'
        elif dealer_value > 21:
            return 'player'
        elif player_value > dealer_value:
            return 'player'
        elif dealer_value > player_value:
            return 'dealer'
        else:
            return 'tie'

    def play(self):
        self.deal_initial_cards()

        print("Your hand: {}".format(self.player_hand))
        print("Dealer's face-up card: {}".format(self.dealer_hand.cards[0]))

        result = self.player_turn()
        if result is None:
            result = self.dealer_turn()

        if result is None:
            winner = self.determine_winner()
            if winner == 'player':
                print("You win!")
            elif winner == 'dealer':
                print("Dealer wins!")
            else:
                print("It's a tie!")

game = Blackjack()
game.play()
