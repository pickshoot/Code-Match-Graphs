import random

class Blackjack:
    def __init__(self):
        self.deck = []
        self.player_cards = []
        self.dealer_cards = []
        self.player_score = 0
        self.dealer_score = 0

    def generate_deck(self):
        ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
        suits = ['hearts', 'diamonds', 'clubs', 'spades']
        for rank in ranks:
            for suit in suits:
                self.deck.append(rank + ' of ' + suit)

    def deal_cards(self):
        random.shuffle(self.deck)
        self.player_cards = [self.deck.pop(), self.deck.pop()]
        self.dealer_cards = [self.deck.pop(), self.deck.pop()]

    def calculate_score(self, cards):
        score = 0
        aces = 0
        for card in cards:
            rank = card.split()[0]
            if rank.isdigit():
                score += int(rank)
            elif rank == 'A':
                aces += 1
                score += 11
            else:
                score += 10
        while score > 21 and aces:
            score -= 10
            aces -= 1
        return score

    def player_turn(self):
        while self.player_score < 21:
            print(f'Your cards: {self.player_cards}')
            print(f'Your score: {self.player_score}')
            choice = input('Do you want to hit or stand? ')
            if choice.lower() == 'hit':
                self.player_cards.append(self.deck.pop())
                self.player_score = self.calculate_score(self.player_cards)
            else:
                break

    def dealer_turn(self):
        while self.dealer_score < 17:
            print(f"Dealer's cards: {self.dealer_cards}")
            print(f"Dealer's score: {self.dealer_score}")
            self.dealer_cards.append(self.deck.pop())
            self.dealer_score = self.calculate_score(self.dealer_cards)

    def check_winner(self):
        if self.player_score == self.dealer_score:
            print('It is a tie!')
        elif self.player_score > 21:
            print('You busted, dealer wins!')
        elif self.dealer_score > 21:
            print('Dealer busted, you win!')
        elif self.player_score > self.dealer_score:
            print('You win!')
        else:
            print('Dealer wins!')

    def play(self):
        self.generate_deck()
        self.deal_cards()
        self.player_score = self.calculate_score(self.player_cards)
        self.dealer_score = self.calculate_score(self.dealer_cards)
        print(f"Dealer's cards: [{self.dealer_cards[0]}, <hidden>]")
        self.player_turn()
        if self.player_score <= 21:
            self.dealer_turn()
            self.check_winner()
        print(f"Your final score: {self.player_score}")
        print(f"Dealer's final score: {self.dealer_score}")

if __name__ == '__main__':
    game = Blackjack()
    game.play()
