import random

class Blackjack:
    def __init__(self):
        self.deck = [2,3,4,5,6,7,8,9,10,'J','Q','K','A']*4
        self.player_hand = []
        self.dealer_hand = []

    def deal_cards(self):
        random.shuffle(self.deck)
        self.player_hand = [self.deck.pop(), self.deck.pop()]
        self.dealer_hand = [self.deck.pop(), self.deck.pop()]

    def calculate_hand_value(self, hand):
        hand_value = 0
        num_aces = 0
        for card in hand:
            if card == 'A':
                num_aces += 1
                hand_value += 11
            elif card in ['J', 'Q', 'K']:
                hand_value += 10
            else:
                hand_value += card
        while num_aces > 0 and hand_value > 21:
            hand_value -= 10
            num_aces -= 1
        return hand_value

    def player_turn(self):
        while True:
            print("Your current hand:", self.player_hand)
            print("Dealer's card showing:", self.dealer_hand[0])
            choice = input("Do you want to hit or stand? ")
            if choice.lower() == 'hit':
                self.player_hand.append(self.deck.pop())
                if self.calculate_hand_value(self.player_hand) > 21:
                    print("Bust! You lose.")
                    return 'dealer'
            else:
                break
        return None

    def dealer_turn(self):
        while self.calculate_hand_value(self.dealer_hand) < 17:
            self.dealer_hand.append(self.deck.pop())
        if self.calculate_hand_value(self.dealer_hand) > 21:
            print("Dealer busts! You win.")
            return 'player'
        else:
            return None

    def play(self):
        while True:
            self.deal_cards()
            print("Your current hand:", self.player_hand)
            print("Dealer's card showing:", self.dealer_hand[0])
            if self.calculate_hand_value(self.player_hand) == 21:
                print("Blackjack! You win!")
            else:
                winner = self.player_turn()
                if winner is None:
                    winner = self.dealer_turn()
                if winner is None:
                    player_hand_value = self.calculate_hand_value(self.player_hand)
                    dealer_hand_value = self.calculate_hand_value(self.dealer_hand)
                    if player_hand_value > dealer_hand_value:
                        print("You win!")
                    elif dealer_hand_value > player_hand_value:
                        print("Dealer wins.")
                    else:
                        print("It's a tie.")
            choice = input("Do you want to play again? ")
            if choice.lower() == 'no':
                break
            else:
                self.player_hand = []
                self.dealer_hand = []
        print("Thanks for playing!")

game = Blackjack()
game.play()
